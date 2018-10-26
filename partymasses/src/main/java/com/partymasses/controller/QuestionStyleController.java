package com.partymasses.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.partymasses.modules.dao.mybatis.entity.QuestionStyle;
import com.partymasses.modules.service.QuestionStyleService;
import com.partymasses.support.constant.Contants;
import com.partymasses.support.message.Message;

@Controller
@RequestMapping("/api/questionStyle")
public class QuestionStyleController {
	
	@Autowired
	private QuestionStyleService questionStyleService;

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	@RequiresRoles(value = {Contants.ADMIN,Contants.SUPERADMIN},logical = Logical.OR)
	public Message create(@RequestBody QuestionStyle questionStyle) {
		Message message = new Message();
		message.setSuccess(false);
		boolean isExist = questionStyleService.isExistQuestionStyle(questionStyle);
		if (isExist) {
			message.setMsg("该类型已存在");
		}else {
			message = questionStyleService.create(questionStyle);
		}
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	@RequiresRoles(value = {Contants.ADMIN,Contants.SUPERADMIN},logical = Logical.OR)
	public QuestionStyle findByPrimeryKey(@PathVariable("id") int id) {
		return questionStyleService.findByPrimeryKey(id);
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT)
	@RequiresRoles(value = {Contants.ADMIN,Contants.SUPERADMIN},logical = Logical.OR)
	public Message updateByPrimeryKey(@RequestBody QuestionStyle questionStyle) {
		return questionStyleService.updateByPrimery(questionStyle);
	}
	
	@ResponseBody
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	@RequiresRoles(value = {Contants.ADMIN,Contants.SUPERADMIN},logical = Logical.OR)
	public Message delByPrimeryKey(@PathVariable("id") int id) {
		return questionStyleService.delByPrimeryKey(id);
	}
}
