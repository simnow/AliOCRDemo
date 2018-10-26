package cn.caecc.erp.modules.service.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.caecc.erp.modules.dao.ex.dto.DepartmentDto;
import cn.caecc.erp.modules.dao.ex.dto.WorkingSummaryDto;
import cn.caecc.erp.modules.dao.ex.mapper.DepartmentExMapper;
import cn.caecc.erp.modules.dao.ex.mapper.WorkingSummaryExMapper;
import cn.caecc.erp.modules.dao.mybatis.entity.User;
import cn.caecc.erp.modules.dao.mybatis.entity.WorkingSummary;
import cn.caecc.erp.modules.dao.mybatis.mapper.UserMapper;
import cn.caecc.erp.modules.dao.mybatis.mapper.WorkingSummaryMapper;
import cn.caecc.erp.modules.service.WorkingSummaryService;
import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.exception.CommonException;
import cn.caecc.erp.support.util.DateUtil;
@Service
public class WorkingSummaryServiceImpl implements WorkingSummaryService {
	@Autowired
	private WorkingSummaryMapper wsMapper;
	@Autowired
	private WorkingSummaryExMapper wsExMapper;
	@Autowired 
	private UserMapper userMapper;
	@Autowired 
	private DepartmentExMapper deptExMapper;
	@Override
	public WorkingSummary addWorkingSummary(WorkingSummary workingSummary) {
		// TODO Auto-generated method stub
		//判断参数
		if(StringUtils.isBlank(workingSummary.getDescription())){		
			throw new CommonException("请填写工作总结内容");
		}
		if(workingSummary.getWeek()==null){		
			throw new CommonException("请选择总结时间");
		}
		//通过部门和时间看是否本周已经存在记录
		List<WorkingSummaryDto> existList=wsExMapper.findByWeekAndDid(workingSummary);
		if(existList!=null&&existList.size()>0){
			    throw new CommonException("本周工作总结不能重复录入");
		}
		Integer loginUserId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(Contants.LOGINUSERID);
		workingSummary.setCreateuserid(loginUserId);
		workingSummary.setCreatetime(DateUtil.getcurrentDate());
		wsMapper.insertSelective(workingSummary);
		return workingSummary;
	}

	@Override
	public WorkingSummary updateWorkingSummary(WorkingSummary workingSummary) {
		// TODO Auto-generated method stub
		if(StringUtils.isBlank(workingSummary.getDescription())){		
			throw new CommonException("请填写工作总结内容");
		}
		if(workingSummary.getWeek()==null){		
			throw new CommonException("请选择总结时间");
		}
		int num=wsMapper.updateByPrimaryKeySelective(workingSummary);
		if(num<=0){
			 throw new CommonException("参数异常");	
		}
		return workingSummary;
	}

	@Override
	public int deleteWorkingSummary(int id) {
		// TODO Auto-generated method stub
		WorkingSummary workingSummary=wsMapper.selectByPrimaryKey(id);
		if(workingSummary==null){
			throw new CommonException("记录不存在");
		}
		return wsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public WorkingSummaryDto getWorkingSummaryById(int id) {
		// TODO Auto-generated method stub
		WorkingSummaryDto workingSummaryDto=wsExMapper.findDtoById(id);
		if(workingSummaryDto==null){
			throw new CommonException("记录不存在");
		}
		return workingSummaryDto;
	}

	@Override
	public PageInfo<WorkingSummaryDto> getTodayWorkingSummary(int pagesize,int pageno) {
		// TODO Auto-generated method stub
		Integer loginUserId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(Contants.LOGINUSERID);
		User user=userMapper.selectByPrimaryKey(loginUserId);
		if(user==null){
			return new PageInfo<WorkingSummaryDto>();
		}
		if(user.getDid()==null){
			return new PageInfo<WorkingSummaryDto>();
		}
		DepartmentDto dept=deptExMapper.findDeptDetail(user.getDid());
		if(dept==null){
			return new PageInfo<WorkingSummaryDto>();
		}
		Map<String, Object> params=new HashMap<String,Object>();
		//主管领导
		if(Contants.MANAGER.equals(dept.getDtypename())){
			params.put("role",1);
			params.put("did",user.getDid());
		}
		//分管领导
		if(Contants.ASSIGNEDMANAGER.equals(dept.getDtypename())){
			
		}
		else{
			params.put("did",user.getDid());
		}
		//本周
		params.put("nowsummary","y");
		pageno=pageno>0?pageno:1;
		pagesize=pagesize>0?pagesize:10;
		PageHelper.startPage(pageno,pagesize);
		List<WorkingSummaryDto> resultList=wsExMapper.findlistByCondition(params);
		PageInfo<WorkingSummaryDto> pageInfo=new PageInfo<>(resultList);
		return pageInfo;
		
		
	}

	@Override
	public PageInfo<WorkingSummaryDto> getFourWorkingSummary(int pagesize,int pageno) {
		// TODO Auto-generated method stub
		//获取登录信息
		Integer loginUserId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(Contants.LOGINUSERID);
		User user=userMapper.selectByPrimaryKey(loginUserId);
		if(user==null){
			return new PageInfo<WorkingSummaryDto>();
		}
		if(user.getDid()==null){
			return new PageInfo<WorkingSummaryDto>();
		}
		DepartmentDto dept=deptExMapper.findDeptDetail(user.getDid());
		if(dept==null){
			return new PageInfo<WorkingSummaryDto>();
		}
		//获取开始结束时间
		Date day=new Date();    
		Date startTime=DateUtil.getCurrentMonday(day, -2);
		Date endTime=DateUtil.getPreviousSunday(day, 0);
		Map<String, Object> params=new HashMap<String,Object>();
		if(Contants.MANAGER.equals(dept.getDtypename())){
			params.put("role",1);
			params.put("did",user.getDid());
		}
		//分管领导
		if(Contants.ASSIGNEDMANAGER.equals(dept.getDtypename())){
			
		}
		else{
			params.put("did",user.getDid());
		}
		params.put("starttime",startTime);
		params.put("endtime",endTime);
		pageno=pageno>0?pageno:1;
		pagesize=pagesize>0?pagesize:10;
		PageHelper.startPage(pageno,pagesize);
		List<WorkingSummaryDto> resultList=wsExMapper.findlistByCondition(params);
		PageInfo<WorkingSummaryDto> pageInfo=new PageInfo<>(resultList);
		return pageInfo;
		
		
	}
	
	
	

}
