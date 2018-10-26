package cn.caecc.erp.modules.dao.ex.dto;

import cn.caecc.erp.modules.dao.mybatis.entity.VehicleActivitiApply;

public class VehicleActivitiApplyDto extends VehicleActivitiApply{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private String deptname;
	
	private String username;

}
