package com.partymasses.modules.dao.ex.mapper;

import com.partymasses.modules.dao.mybatis.entity.Function;
import com.partymasses.modules.dao.mybatis.entity.FunctionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FunctionExMapper {
	Function getByUnitIDFunction(@Param("functionid")Integer functionid);
	List<Function> selectAllFunction();
}