package com.partymasses.modules.service;

import java.util.List;

import com.partymasses.modules.dao.mybatis.entity.VideoType;

public interface VideoTypeService {

	public List<VideoType> findVideoType();
}
