package com.partymasses.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.partymasses.modules.dao.mybatis.entity.TestUser;
import com.partymasses.modules.service.TestUserService;
import com.partymasses.support.constant.Contants;
import com.partymasses.support.message.Message;

public class TestUserController {
	
	@Autowired
	private TestUserService testUserService;

	public Message create(int testId,int userId,Map<Integer, String> map){
		Message message = new Message();
		
		return message;
	}

	@ResponseBody
	@RequestMapping(value = "/testId/{id}",method = RequestMethod.GET)
	@RequiresRoles(value = {Contants.SUPERADMIN,Contants.ADMIN},logical = Logical.OR)
	public List<TestUser> findByTestId(@PathVariable("id") int testId){
		return testUserService.findByTestId(testId);
	}

	@ResponseBody
	@RequestMapping(value = "/userId/{id}",method = RequestMethod.GET)
	@RequiresRoles(value = {Contants.SUPERADMIN,Contants.ADMIN},logical = Logical.OR)
	public List<TestUser> findByUserId(@PathVariable("id")int userId){
		return testUserService.findByUserId(userId);
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	@RequiresRoles(value = {Contants.SUPERADMIN,Contants.ADMIN},logical = Logical.OR)
	public Message update(@RequestBody TestUser testUser){
		return testUserService.update(testUser);
	}
}
