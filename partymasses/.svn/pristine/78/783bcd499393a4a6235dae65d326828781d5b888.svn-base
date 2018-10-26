package com.partymasses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.partymasses.modules.service.BaseAreaService;
import com.partymasses.support.message.Message;
@RequestMapping(value="api/basearea")
@Controller
public class BaseAreaController {
	
	@Autowired
	private BaseAreaService baseAreaService;
	//查询区别级联列表
	@RequestMapping(value="/{gid}",method=RequestMethod.GET)
	@ResponseBody
	public Message getAreaList(@PathVariable("gid") String gid){
		
		 return baseAreaService.getAreaList(gid);
		
	}
	@RequestMapping(value="/info",method=RequestMethod.GET)
	@ResponseBody
	public Message getAreabylevel(){
		
			return baseAreaService.getAreabylevel();
		
	}
	@RequestMapping(value="/info/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Message getAreabyId(@PathVariable("id") String id){
		
			return baseAreaService.getAreabyId(id);
		
	}
	
}
