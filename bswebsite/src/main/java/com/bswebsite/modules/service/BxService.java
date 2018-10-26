package com.bswebsite.modules.service;

import java.util.List;

import com.bswebsite.modules.dao.mybatis.entity.Bx;

public interface BxService {

	boolean insert(Bx bx);

	List<Bx> selectAll();

	boolean deleteById(int id);

	boolean deleteAll();
}
