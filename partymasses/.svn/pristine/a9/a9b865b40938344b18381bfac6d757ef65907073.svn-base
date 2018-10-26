package com.partymasses.modules.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partymasses.modules.dao.mybatis.entity.VideoType;
import com.partymasses.modules.dao.mybatis.entity.VideoTypeExample;
import com.partymasses.modules.dao.mybatis.entity.VideoTypeExample.Criteria;
import com.partymasses.modules.dao.mybatis.mapper.VideoTypeMapper;
import com.partymasses.modules.service.VideoTypeService;
@Service
public class VideoTypeServiceImpl implements VideoTypeService {
	
	@Autowired
	private VideoTypeMapper videoTypeMapper;
	
	private final static Logger logger = LoggerFactory.getLogger(VideoTypeServiceImpl.class);

	@Override
	public List<VideoType> findVideoType() {
		VideoTypeExample example = new VideoTypeExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdIsNotNull();
		List<VideoType> list = null;
		try {
			list = videoTypeMapper.selectByExample(example);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return list;
	}

}
