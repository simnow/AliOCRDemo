package com.partymasses.modules.dao.ex.mapper;

import com.partymasses.modules.dao.mybatis.entity.UnitFunction;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UnitFunctionExMapper {
	/*批量插入*/
	int addUnitFunction(@Param("unitfunctions") List<UnitFunction> unitfunctions);
	List<UnitFunction> getByUnitID(@Param("unitid")Integer unitid);
	int deleteByUnitId(@Param("unitid")Integer unitid);
}