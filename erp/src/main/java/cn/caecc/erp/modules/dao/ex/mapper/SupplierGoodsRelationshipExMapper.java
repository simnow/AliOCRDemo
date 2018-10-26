package cn.caecc.erp.modules.dao.ex.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.caecc.erp.modules.dao.ex.dto.SupplierGoodsRelationshipExDto;
import cn.caecc.erp.modules.dao.mybatis.entity.SupplierGoodsRelationship;

public interface SupplierGoodsRelationshipExMapper {

	List<SupplierGoodsRelationshipExDto> getList(@Param(value = "lists")List<String> list, @Param(value = "isQualified")Integer isQualified);

	int batchAdd(@Param(value = "list")List<SupplierGoodsRelationship> list);

//	int batchUpdate(@Param(value = "ulist")List<SupplierGoodsRelationship> list);

}
