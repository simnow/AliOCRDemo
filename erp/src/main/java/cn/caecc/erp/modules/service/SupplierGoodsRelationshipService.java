package cn.caecc.erp.modules.service;

import java.util.List;

import cn.caecc.erp.modules.dao.mybatis.entity.SupplierGoodsRelationship;

public interface SupplierGoodsRelationshipService {

	public List<SupplierGoodsRelationship> batchAdd(List<SupplierGoodsRelationship> list);
		
	public int batchUpdate(int Supplierid,List<SupplierGoodsRelationship> list);

}
