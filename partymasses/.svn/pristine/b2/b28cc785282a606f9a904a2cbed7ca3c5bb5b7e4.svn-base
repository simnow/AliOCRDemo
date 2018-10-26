package com.partymasses.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.partymasses.modules.dao.mybatis.entity.VideoType;
import com.partymasses.modules.service.VideoTypeService;

@Controller
@RequestMapping("/api/videoType")
public class VideoTypeController {

	@Autowired
	private VideoTypeService videoTypeService;
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public List<VideoType> findVideoType() {
		return videoTypeService.findVideoType();
	}
}
