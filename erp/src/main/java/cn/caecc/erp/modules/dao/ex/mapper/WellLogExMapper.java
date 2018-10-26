package cn.caecc.erp.modules.dao.ex.mapper;

import org.apache.ibatis.annotations.Param;

import cn.caecc.erp.modules.dao.mybatis.entity.WellLog;

public interface WellLogExMapper {

	public int inserWellLog(@Param("wellLog")WellLog wellLog);
}
