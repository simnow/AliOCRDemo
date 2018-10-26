package com.bswebsite.modules.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bswebsite.modules.dao.mybatis.entity.Bx;
import com.bswebsite.modules.dao.mybatis.mapper.BxMapper;
import com.bswebsite.modules.service.BxService;
@Service
public class BxServiceImpl implements BxService {

	@Autowired
	private BxMapper bxMapper;
	@Override
	public boolean insert(Bx bx) {
		if(bxMapper.insertSelective(bx)>0){
			return true;
		}
		return false;
	}
	@Override
	public List<Bx> selectAll() {
		return bxMapper.selectByExample(null);
	}
	@Override
	public boolean deleteById(int id) {
		if(bxMapper.deleteByPrimaryKey(id)>0){
			return true;
		}
		return false;
	}
	@Override
	public boolean deleteAll() {
		bxMapper.deleteByExample(null);
		return true;
	}

}
