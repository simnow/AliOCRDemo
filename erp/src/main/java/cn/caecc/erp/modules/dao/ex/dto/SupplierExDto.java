package cn.caecc.erp.modules.dao.ex.dto;

import java.util.List;

import cn.caecc.erp.modules.dao.mybatis.entity.Supplier;
import cn.caecc.erp.modules.dao.mybatis.entity.SupplierGoodsRelationship;

public class SupplierExDto extends Supplier {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<SupplierGoodsRelationship> list;//供应商类型集合

	public List<SupplierGoodsRelationship> getList() {
		return list;
	}

	public void setList(List<SupplierGoodsRelationship> list) {
		this.list = list;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
