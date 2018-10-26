package com.bswebsite.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bswebsite.modules.service.PersonService;
import com.bswebsite.support.message.Message;
@Controller
@RequestMapping(value = "api")
public class PersonController {

	@Autowired
	private PersonService personService;
	/**
	 * 通过名字获取单个信息
	 */
	@RequestMapping(value="/person")
	@ResponseBody
	public Message getPersionInfoByName(String name){
		if(StringUtils.isBlank(name)){
			return null;
		}
		Message message=new Message();
		message.setAttributes(personService.getPersionInfoByName(name));
		return message;
	}
	
	
	/**
	 * 通过名字like获取单个信息
	 */
	@RequestMapping(value="/person/name")
	@ResponseBody
	public Message getPersionInfoByName(String name,int pageNo,int pageSize){
		if(StringUtils.isBlank(name)){
			return null;
		}
		Message message=new Message();
		message.setAttributes(personService.getPersionInfoByName(name,pageNo,pageSize));
		return message;
	}
	
	/**
	 * 通过类型（yuanshi，zhuanjia）进行查找，并且按照A-Z进行排序，并分页
	 */
	@RequestMapping(value="/person/type")
	@ResponseBody
	public Message getAllByType(String type,int pageNo,int pageSize){
		Message message=new Message();
		message.setAttributes(personService.getAllByType(type, pageNo, pageSize));
		return message;
	}
}
