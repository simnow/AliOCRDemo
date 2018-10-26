package com.partymasses.modules.service;

import java.util.List;

import com.partymasses.modules.dao.mybatis.entity.InputUnit;
import com.partymasses.modules.dao.mybatis.entity.InputUnitPut;
import com.partymasses.modules.dao.mybatis.entity.SelectUnit;
import com.partymasses.modules.dao.mybatis.entity.Unit;

public interface UnitService {
	Unit getById(Integer id);
	List<Unit> select(SelectUnit selectunit);
	Boolean update(InputUnitPut inputunitput);
	boolean create(InputUnit u);
	boolean deleteById(Integer id);
	List<Unit> selectAllUnit();
	List<Unit> selectLike(SelectUnit selectunit);
}
