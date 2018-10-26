package cn.caecc.erp.modules.dao.ex.dto;

import cn.caecc.erp.modules.dao.mybatis.entity.SupplierGoodsRelationship;

public class SupplierGoodsRelationshipDto extends SupplierGoodsRelationship {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String gname;//物品名称
	
	private String model;//型号
	
	private String unit;//单位
	
	private String remarks;//备注

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
