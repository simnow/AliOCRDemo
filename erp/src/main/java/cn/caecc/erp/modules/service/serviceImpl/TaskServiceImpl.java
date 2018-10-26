package cn.caecc.erp.modules.service.serviceImpl;


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
import cn.caecc.erp.modules.dao.ex.dto.TaskDto;
import cn.caecc.erp.modules.dao.ex.mapper.DepartmentExMapper;
import cn.caecc.erp.modules.dao.ex.mapper.TaskExMapper;
import cn.caecc.erp.modules.dao.mybatis.entity.Task;
import cn.caecc.erp.modules.dao.mybatis.entity.User;
import cn.caecc.erp.modules.dao.mybatis.mapper.TaskMapper;
import cn.caecc.erp.modules.dao.mybatis.mapper.UserMapper;
import cn.caecc.erp.modules.service.TaskService;
import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.exception.CommonException;
import cn.caecc.erp.support.util.DateUtil;

@Service
public class TaskServiceImpl implements TaskService{
	
	@Autowired
	private TaskMapper taskMapper;
	@Autowired
	private TaskExMapper taskExMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private DepartmentExMapper deptExMapper;
	@Override
	public Task addTask(Task task) {
		// TODO Auto-generated method stub
		//查询登录
		Integer loginUserId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(Contants.LOGINUSERID);
		//查询传入参数
		if(StringUtils.isBlank(task.getAgencyaffairs())){
			throw  new CommonException("请填写代办事宜");
		}
		if(task.getAgencystarttime()==null){
			throw new CommonException("请填写代办开始时间");
		}
		//存入数据
		task.setCreateuserid(loginUserId);
		task.setCreatetime(DateUtil.getcurrentDateTime());
		taskMapper.insertSelective(task);
		return task;
	}

	@Override
	public Task updateTask(Task task) {
		// TODO Auto-generated method stub
		//传入参数
		if(StringUtils.isBlank(task.getAgencyaffairs())){
			throw  new CommonException("请填写代办事宜");
		}
		if(task.getAgencystarttime()==null){
			throw new CommonException("请填写代办开始时间");
		}
		if(task.getIsfinished()==1){
			task.setFinishedtime(DateUtil.getcurrentDateTime());
		}
		//修改数据
		int num=taskMapper.updateByPrimaryKeySelective(task);
		if(num<=0){
			throw new CommonException("未找到要修改数据");
		}
		return task;
	}

	@Override
	public PageInfo<TaskDto> procedureGetTaskListByCondition(int pagesize,int pageno) {
		// TODO Auto-generated method stub
		Integer loginUserId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(Contants.LOGINUSERID);
		User user=userMapper.selectByPrimaryKey(loginUserId);
		if(user==null){
			return new PageInfo<TaskDto>();
		}
		if(user.getDid()==null){
			return new PageInfo<TaskDto>();
		}
		DepartmentDto dept=deptExMapper.findDeptDetail(user.getDid());
		Map<String, Object> params=new HashMap<String,Object>();
		if(Contants.MANAGER.equals(dept.getDtypename())){
			params.put("deptid", dept.getId());
		}
		else if(Contants.ASSIGNEDMANAGER.equals(dept.getDtypename())){
				
		}
		else{
			params.put("userid",loginUserId);
		}
		pageno=pageno>0?pageno:1;
		pagesize=pagesize>0?pagesize:10;
		PageHelper.startPage(pageno,pagesize);
		List<TaskDto> resultList = taskExMapper.procedureGetTaskPageList(params);
		PageInfo<TaskDto> pageInfo = new PageInfo<>(resultList);
		return pageInfo;
	}

	@Override
	public int deleteTask(int id) {
		// TODO Auto-generated method stub
		Task task=taskMapper.selectByPrimaryKey(id);
		if(task==null){
			throw new CommonException("该记录不存在");
		}
	    return 	taskMapper.deleteByPrimaryKey(id);
	
	
	}

	@Override
	public TaskDto getTaskById(int id) {
		// TODO Auto-generated method stub
		TaskDto taskDto=taskExMapper.getTaskById(id);
		return taskDto;	
	}
	
	

}
