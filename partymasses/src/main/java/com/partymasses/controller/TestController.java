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

import com.partymasses.modules.dao.mybatis.entity.Test;
import com.partymasses.modules.service.TestQuestionService;
import com.partymasses.modules.service.TestService;
import com.partymasses.modules.service.TestUserService;
import com.partymasses.support.constant.Contants;
import com.partymasses.support.message.Message;
@Controller
@RequestMapping("/api/test")
public class TestController {
	
	@Autowired
	private TestService testService;
	@Autowired
	private TestQuestionService testQuestionService;
	@Autowired
	private TestUserService testUserService;

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	@RequiresRoles(value = {Contants.SUPERADMIN,Contants.ADMIN},logical = Logical.OR)
    public Message create(@RequestBody Test test){
    	return testService.create(test);
    }
	
	@ResponseBody
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	@RequiresRoles(value = {Contants.SUPERADMIN,Contants.ADMIN},logical = Logical.OR)
	public Test findByPrimeryKey(@PathVariable("id") int id){
		return testService.findByPrimeryKey(id);
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT)
	@RequiresRoles(value = {Contants.SUPERADMIN,Contants.ADMIN},logical = Logical.OR)
	public Message updateByPrimeryKey(@RequestBody Test test){
		return testService.updateByPrimeryKey(test);
	}
	
	@ResponseBody
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	@RequiresRoles(value = {Contants.SUPERADMIN,Contants.ADMIN},logical = Logical.OR)
	public Message delByPrimeryKey(@PathVariable("id") int id){
		//删除考试里的试题记录
		testQuestionService.delByTestId(id);
		//删除考试的用户记录
		testUserService.delByTestId(id);
		//删除考试
		return testService.delByPrimeryKey(id);
	}
}
