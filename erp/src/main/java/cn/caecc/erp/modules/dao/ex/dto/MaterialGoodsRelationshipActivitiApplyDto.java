package cn.caecc.erp.modules.dao.ex.dto;

import cn.caecc.erp.modules.dao.mybatis.entity.MaterialGoodsRelationshipActivitiApply;

public class MaterialGoodsRelationshipActivitiApplyDto extends MaterialGoodsRelationshipActivitiApply {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String gname;//物品名称
	
	private String contractName;//合同名称
	
	private double unitPrice;//单价

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
