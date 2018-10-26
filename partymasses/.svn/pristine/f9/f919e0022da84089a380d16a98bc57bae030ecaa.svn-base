package com.partymasses.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.partymasses.modules.dao.mybatis.entity.Video;
import com.partymasses.modules.service.VideoService;

@Controller
@RequestMapping("/api/video")
public class VideoController {
	
	@Autowired
	private VideoService videoService;

	@ResponseBody
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public List<Video> findVideoByTypeId(@PathVariable("id") int id) {
		return videoService.findVideoByTypeId(id);
	}
}
