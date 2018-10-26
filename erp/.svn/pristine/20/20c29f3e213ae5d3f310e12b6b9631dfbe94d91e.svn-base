package cn.caecc.erp.modules.dao.ex.dto;

import java.io.Serializable;
import java.util.List;

import cn.caecc.erp.modules.dao.mybatis.entity.Department;

public class DepartmentDto extends Department implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//负责人姓名
    private String leadername;
    //创建人姓名
    private String cusername;
    //
    private String pdeptname;
    
    //类型名称
    private String dtypename;
    
    
	public String getDtypename() {
		return dtypename;
	}
	public void setDtypename(String dtypename) {
		this.dtypename = dtypename;
	}

	private List<DepartmentDto> children;

	
	public String getLeadername() {
		return leadername;
	}
	public void setLeadername(String leadername) {
		this.leadername = leadername;
	}
	public String getCusername() {
		return cusername;
	}
	public void setCusername(String cusername) {
		this.cusername = cusername;
	}
	public String getPdeptname() {
		return pdeptname;
	}
	public void setPdeptname(String pdeptname) {
		this.pdeptname = pdeptname;
	}
    
	public List<DepartmentDto> getChildren() {
		return children;
	}

	public void setChildren(List<DepartmentDto> children) {
		this.children = children;
	}
	

}
