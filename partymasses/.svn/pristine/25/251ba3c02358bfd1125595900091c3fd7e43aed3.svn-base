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

import com.partymasses.modules.dao.mybatis.entity.QuestionType;
import com.partymasses.modules.service.QuestionTypeService;
import com.partymasses.support.constant.Contants;
import com.partymasses.support.message.Message;
@Controller
@RequestMapping("/api/questionType")
public class QuestionTypeController {

	@Autowired
	private QuestionTypeService questionTypeService;
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	@RequiresRoles(value = {Contants.SUPERADMIN,Contants.ADMIN},logical = Logical.OR)
	public Message create(@RequestBody QuestionType questionType) {
		Message message = new Message();
		message.setSuccess(false);
		boolean isExist = questionTypeService.isExistQuestionType(questionType);
		if (isExist) {
			message.setMsg("该类型已经存在");
		}else {
			message = questionTypeService.create(questionType);
		}
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	@RequiresRoles(value = {Contants.SUPERADMIN,Contants.ADMIN},logical = Logical.OR)
	public QuestionType findByPrimeryKey(@PathVariable("id") int id) {
		return questionTypeService.findByPrimeryKey(id);
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT)
	@RequiresRoles(value = {Contants.SUPERADMIN,Contants.ADMIN},logical = Logical.OR)
	public Message updateByPrimeryKey(@RequestBody QuestionType questionType) {
		return questionTypeService.updateByPrimeryKey(questionType);
	}
	
	@ResponseBody
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	@RequiresRoles(value = {Contants.SUPERADMIN,Contants.ADMIN},logical = Logical.OR)
	public Message delByPrimeryKey(@PathVariable("id") int id) {
		return questionTypeService.delByPrimeryKey(id);
	}
}
