package cn.caecc.erp.modules.dao.ex.dto;

import cn.caecc.erp.modules.dao.mybatis.entity.EventActivitiApply;

public class EventActivitiApplyDto extends EventActivitiApply{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private  String username;
	
	private  String deptname;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}



}
