package cn.caecc.erp.modules.dao.ex.dto;

import java.util.List;

import cn.caecc.erp.modules.dao.mybatis.entity.OfficesupplyActivitiApply;

public class OfficesupplyActivitiApplyDto extends OfficesupplyActivitiApply {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String uname;//申请人
	
	private String dname;//部门名称
	
	private String cname;//创建人
	
	private List<OfficesupplyGoodsRelationshipActivitiApplyDto> list;

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

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public List<OfficesupplyGoodsRelationshipActivitiApplyDto> getList() {
		return list;
	}

	public void setList(List<OfficesupplyGoodsRelationshipActivitiApplyDto> list) {
		this.list = list;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
