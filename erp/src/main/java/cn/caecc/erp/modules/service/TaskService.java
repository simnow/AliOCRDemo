package cn.caecc.erp.modules.service;

import com.github.pagehelper.PageInfo;

import cn.caecc.erp.modules.dao.ex.dto.TaskDto;
import cn.caecc.erp.modules.dao.mybatis.entity.Task;

public interface TaskService {
	
	public Task  addTask(Task task);
	
	public Task  updateTask(Task task);
	
	public PageInfo<TaskDto>  procedureGetTaskListByCondition(int pagesize,int pageno);
	
	public int  deleteTask(int id);
	
	public TaskDto  getTaskById(int id);
	

}