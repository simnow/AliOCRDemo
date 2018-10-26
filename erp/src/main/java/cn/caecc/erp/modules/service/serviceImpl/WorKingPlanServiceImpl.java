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
import cn.caecc.erp.modules.dao.ex.dto.WorkingPlanDto;
import cn.caecc.erp.modules.dao.ex.mapper.DepartmentExMapper;
import cn.caecc.erp.modules.dao.ex.mapper.WorkingPlanExMapper;
import cn.caecc.erp.modules.dao.mybatis.entity.User;
import cn.caecc.erp.modules.dao.mybatis.entity.WorkingPlan;
import cn.caecc.erp.modules.dao.mybatis.mapper.UserMapper;
import cn.caecc.erp.modules.dao.mybatis.mapper.WorkingPlanMapper;
import cn.caecc.erp.modules.service.WorKingPlanService;
import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.exception.CommonException;
import cn.caecc.erp.support.util.DateUtil;
@Service
public class WorKingPlanServiceImpl implements WorKingPlanService{
	@Autowired
	private WorkingPlanMapper wpMapper;
	@Autowired
	private WorkingPlanExMapper wpExMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired 
	private DepartmentExMapper deptExMapper;
	@Override
	public WorkingPlan addWorkingPlan(WorkingPlan workingPlan) {
		// TODO Auto-generated method stub
		if(StringUtils.isBlank(workingPlan.getDescription())){		
			throw new CommonException("请填写工作计划内容");
		}
		if(workingPlan.getWeek()==null){		
			throw new CommonException("请选择计划时间");
		}
	//通过部门和时间看是否本周已经存在记录
		List<WorkingPlan> existList=wpExMapper.findByWeekAndDid(workingPlan);
		if(existList!=null&&existList.size()>0){
		    throw new CommonException("本周工作计划计划不能重复录入");
		}
		Integer loginUserId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(Contants.LOGINUSERID);
		workingPlan.setCreateuserid(loginUserId);
		workingPlan.setCreatetime(DateUtil.getcurrentDate());
		wpMapper.insertSelective(workingPlan);
		return workingPlan;
	}

	@Override
	public WorkingPlan updateWorkingPlan(WorkingPlan workingPlan) {
		// TODO Auto-generated method stub
		if(StringUtils.isBlank(workingPlan.getDescription())){		
			throw new CommonException("请填写工作计划内容");
		}
		if(workingPlan.getWeek()==null){		
			throw new CommonException("请选择计划时间");
		}
		int num=wpMapper.updateByPrimaryKeySelective(workingPlan);
		if(num<=0){
			 throw new CommonException("参数异常");	
		}
		return workingPlan;
	}

	@Override
	public int deleteWorkingPlan(int id) {
		// TODO Auto-generated method stub
		WorkingPlan WorkingPlan=wpMapper.selectByPrimaryKey(id);
		if(WorkingPlan==null){
			throw new CommonException("记录不存在");
		}
		return wpMapper.deleteByPrimaryKey(id);
	}

	@Override
	public WorkingPlan getWorkingPlanById(int id) {
		// TODO Auto-generated method stub
		WorkingPlanDto workingPlanDto=wpExMapper.findDtoById(id);
		if(workingPlanDto==null){
			throw new CommonException("记录不存在");
		}
		return workingPlanDto;
	}

	@Override
	public PageInfo<WorkingPlanDto> getTodayWorkingPlan(int pagesize,int pageno) {
		// TODO Auto-generated method stub
		Integer loginUserId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(Contants.LOGINUSERID);
		User user=userMapper.selectByPrimaryKey(loginUserId);
		if(user==null){
			return new PageInfo<WorkingPlanDto>();
		}
		if(user.getDid()==null){
			return new PageInfo<WorkingPlanDto>();
		}
		DepartmentDto dept=deptExMapper.findDeptDetail(user.getDid());
		if(dept==null){
			return new PageInfo<WorkingPlanDto>();
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
		params.put("nowsummary","y");
		pagesize=pagesize>0?pagesize:10;
		pageno=pageno>0?pageno:1;
		PageHelper.startPage(pageno, pagesize);
		List<WorkingPlanDto> resultList=wpExMapper.findPlanByCondition(params);
		PageInfo<WorkingPlanDto> pageInfo=new PageInfo<>(resultList);
		return pageInfo;
	}

	@Override
	public PageInfo<WorkingPlanDto> getMoreWorkingPlan(int pagesize,int pageno) {
		// TODO Auto-generated method stub
		Integer loginUserId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(Contants.LOGINUSERID);
		User user=userMapper.selectByPrimaryKey(loginUserId);
		if(user==null){
			return new PageInfo<WorkingPlanDto>();
		}
		if(user.getDid()==null){
			return new PageInfo<WorkingPlanDto>();
		}
		DepartmentDto dept=deptExMapper.findDeptDetail(user.getDid());
		if(dept==null){
			return new PageInfo<WorkingPlanDto>();
		}
		//获取开始结束时间
		Date day=new Date();    
		Date startTime=DateUtil.getCurrentMonday(day,0);
		Date endTime=DateUtil.getPreviousSunday(day,2);
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
		params.put("starttime",startTime);
		params.put("endtime",endTime);
		pagesize=pagesize>0?pagesize:10;
		pageno=pageno>0?pageno:1;
		PageHelper.startPage(pageno, pagesize);
		List<WorkingPlanDto> resultList=wpExMapper.findPlanByCondition(params);
		PageInfo<WorkingPlanDto> pageInfo=new PageInfo<>(resultList);
		return pageInfo;
	}

}