package com.partymasses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.partymasses.modules.dao.mybatis.entity.Limit;
import com.partymasses.modules.service.LimitService;
import com.partymasses.support.message.Message;

@Controller
@RequestMapping("/api/limit")
public class LimitController {

	@Autowired
	private LimitService limitService ;
	
	@ResponseBody
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public Limit findByUnitId(@PathVariable("id") int id) {
		return limitService.findByUnitId(id);
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT)
	public Message update(@RequestBody Limit limit) {
		return limitService.update(limit);
	}
}
