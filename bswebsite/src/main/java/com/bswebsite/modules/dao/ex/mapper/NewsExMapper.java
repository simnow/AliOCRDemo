package com.bswebsite.modules.dao.ex.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bswebsite.modules.dao.mybatis.entity.News;

public interface NewsExMapper {

	List<News> selectAll();

	void updateNewReadCount(@Param("Id") int id);
	
	void updateNewGoodsCount(@Param("Id") int id);
}