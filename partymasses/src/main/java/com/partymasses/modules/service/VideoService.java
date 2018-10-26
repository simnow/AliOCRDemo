package com.partymasses.modules.service;

import java.util.List;

import com.partymasses.modules.dao.mybatis.entity.Video;

public interface VideoService {
	
	public List<Video> findVideoByTypeId(int id);
}
