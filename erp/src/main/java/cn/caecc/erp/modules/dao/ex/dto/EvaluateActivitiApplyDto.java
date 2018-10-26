package cn.caecc.erp.modules.dao.ex.dto;

import cn.caecc.erp.modules.dao.mybatis.entity.EvaluateActivitiApply;

public class EvaluateActivitiApplyDto extends EvaluateActivitiApply {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String uname;//申请人
	
	private String dname;//部门名称
	
	private String sname;//供应商
	
	private String cname;//创建人

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
