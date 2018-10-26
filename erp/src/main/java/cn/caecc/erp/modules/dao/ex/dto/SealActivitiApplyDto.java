package cn.caecc.erp.modules.dao.ex.dto;

import cn.caecc.erp.modules.dao.mybatis.entity.SealActivitiApply;

public class SealActivitiApplyDto extends SealActivitiApply{
	private static final long serialVersionUID = 1L;
	
	private String  username;
	
	private String  deptname;
	
	private String  sealname;

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

	public String getSealname() {
		return sealname;
	}

	public void setSealname(String sealname) {
		this.sealname = sealname;
	}

}
