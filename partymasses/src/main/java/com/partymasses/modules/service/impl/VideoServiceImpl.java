package com.partymasses.modules.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partymasses.modules.dao.mybatis.entity.Video;
import com.partymasses.modules.dao.mybatis.entity.VideoExample;
import com.partymasses.modules.dao.mybatis.entity.VideoExample.Criteria;
import com.partymasses.modules.dao.mybatis.mapper.VideoMapper;
import com.partymasses.modules.service.VideoService;
@Service
public class VideoServiceImpl implements VideoService {
	
	@Autowired
	private VideoMapper videoMapper;
	
	private final static Logger logger = LoggerFactory.getLogger(VideoServiceImpl.class);

	@Override
	public List<Video> findVideoByTypeId(int id) {
		VideoExample videoExample = new VideoExample();
		Criteria criteria = videoExample.createCriteria();
		criteria.andTypeidEqualTo(id);
		List<Video> list = null;
		try {
			list = videoMapper.selectByExample(videoExample);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return list;
	}

}
