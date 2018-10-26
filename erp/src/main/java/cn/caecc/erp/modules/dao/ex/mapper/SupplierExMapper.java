package cn.caecc.erp.modules.dao.ex.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.caecc.erp.modules.dao.ex.dto.SupplierExDto;
import cn.caecc.erp.modules.dao.mybatis.entity.Goods;

public interface SupplierExMapper {

	SupplierExDto findDetail(int id);
	
	List<Goods> findGoodsName(int id);

	List<SupplierExDto> getList(@Param(value = "isQualified")Integer isQualified, @Param(value = "names")String name, @Param(value = "list")List<String> list);
}
