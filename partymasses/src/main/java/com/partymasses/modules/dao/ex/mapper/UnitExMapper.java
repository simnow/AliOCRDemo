package com.partymasses.modules.dao.ex.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.partymasses.modules.dao.mybatis.entity.Unit;

public interface UnitExMapper {
	int selectMaxId();
	List<Unit>select();
	int creat(Unit unit);
	List<Unit>selectBySelectUnit(@Param("unitc")Unit unit);
}