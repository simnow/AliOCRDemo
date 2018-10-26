package cn.caecc.erp.modules.dao.ex.mapper;

import org.apache.ibatis.annotations.Param;

import cn.caecc.erp.modules.dao.mybatis.entity.WellMud;

public interface WellMudExMapper {
	
	public int inserWellMud(@Param("wellmud")WellMud wellMud);
}
