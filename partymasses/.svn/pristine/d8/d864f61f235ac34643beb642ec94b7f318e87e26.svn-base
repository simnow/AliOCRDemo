package com.partymasses.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.partymasses.modules.dao.mybatis.entity.TestQuestion;
import com.partymasses.modules.service.TestQuestionService;
import com.partymasses.support.constant.Contants;
import com.partymasses.support.message.Message;

@Controller
@RequestMapping("/api/testQuestion")
public class TestQuestionController {
	
	@Autowired
	private TestQuestionService testQuestionService;

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	@RequiresRoles(value = {Contants.SUPERADMIN,Contants.ADMIN},logical = Logical.OR)
	public Message create(int testId,Map<Integer, Integer> map) {
		Message message = new Message();
		List<Integer> list = new ArrayList<Integer>();
		for (Integer key : map.keySet()) {
			TestQuestion testQuestion = new TestQuestion();
			testQuestion.setTestid(testId);
			testQuestion.setQuestionid(key);
			testQuestion.setValue(map.get(key));
			message = testQuestionService.create(testQuestion);
			if (!message.isSuccess()) {
				if (list.size()>0) {
					for (int i = 0; i < list.size(); i++) {
						testQuestionService.delByQuestionId(list.get(i));
					}
				}
				return message;
			}else {
				list.add(key);
			}
		}
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/testid/{id}",method = RequestMethod.GET)
	@RequiresRoles(value = {Contants.SUPERADMIN,Contants.ADMIN},logical = Logical.OR)
	public List<TestQuestion> findByTestId(@PathVariable("id") int testId) {
		return testQuestionService.findByTestId(testId);
	}
	
	@ResponseBody
	@RequestMapping(value = "/questionid/{id}",method = RequestMethod.GET)
	@RequiresRoles(value = {Contants.SUPERADMIN,Contants.ADMIN},logical = Logical.OR)
	public List<TestQuestion> findByQuestionId(@PathVariable("id") int questionId) {
		return testQuestionService.findByQuestionId(questionId);
	}
	
	@ResponseBody
	@RequestMapping(value = "/testid/{id}",method = RequestMethod.DELETE)
	@RequiresRoles(value = {Contants.SUPERADMIN,Contants.ADMIN},logical = Logical.OR)
	public Message delByTestId(@PathVariable("id") int testId) {
		return testQuestionService.delByTestId(testId);
	}
	
	@ResponseBody
	@RequestMapping(value = "/questionid/{id}",method = RequestMethod.DELETE)
	@RequiresRoles(value = {Contants.SUPERADMIN,Contants.ADMIN},logical = Logical.OR)
	public Message delByQuestionId(@PathVariable("id") int questionId) {
		return testQuestionService.delByQuestionId(questionId);
	}
}
