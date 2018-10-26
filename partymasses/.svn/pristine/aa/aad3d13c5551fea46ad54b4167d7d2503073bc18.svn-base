package com.partymasses.modules.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partymasses.modules.dao.ex.mapper.FunctionExMapper;
import com.partymasses.modules.dao.mybatis.entity.Function;
import com.partymasses.modules.dao.mybatis.entity.FunctionExample;
import com.partymasses.modules.dao.mybatis.entity.UnitFunction;
import com.partymasses.modules.dao.mybatis.mapper.FunctionMapper;
import com.partymasses.modules.dao.mybatis.mapper.UnitFunctionMapper;
import com.partymasses.modules.service.FunctionService;

@Service
public class FunctionServiceImpl implements FunctionService{
	@Autowired
	private FunctionExMapper functionExMapper;
	@Autowired
	private FunctionMapper functionMapper;
	@Autowired
	private UnitFunctionMapper unitFunctionMapper;
	@Override
	public List<Function> getByUnitID(int id) {
		List<UnitFunction>listu=new ArrayList<UnitFunction>();
		listu=unitFunctionMapper.getByUnitID(id);
		List<Function> fid=new ArrayList<Function>();
		for(UnitFunction u:listu){
			int n=u.getFunctionid();
			fid.add(functionMapper.selectByPrimaryKey(n));
		}
		return fid;
	}
	@Override
	public List<Function> selectAllFunction() {
		List<Function>list=new ArrayList<Function>();
		list=functionExMapper.selectAllFunction();
		return list;
	}
}
