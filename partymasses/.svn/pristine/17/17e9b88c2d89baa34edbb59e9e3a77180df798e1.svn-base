package com.partymasses.modules.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partymasses.modules.dao.mybatis.entity.Test;
import com.partymasses.modules.dao.mybatis.mapper.TestMapper;
import com.partymasses.modules.service.TestService;
import com.partymasses.support.message.Message;
@Service
public class TestServiceImpl implements TestService {
	
	@Autowired
	private TestMapper testMapper;
	
	private final static Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

	@Override
	public Message create(Test test) {
		Message message = new Message();
		message.setSuccess(false);
		try {
			int insert = testMapper.insert(test);
			if (insert > 0) {
				message.setSuccess(true);
			}else {
				message.setMsg("添加失败");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			message.setMsg(e.getCause().getMessage());
		}
		return message;
	}

	@Override
	public Test findByPrimeryKey(int id) {
		Test test = null;
		try {
			test =  testMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return test;
	}

	@Override
	public Message updateByPrimeryKey(Test test) {
		Message message = new Message();
		message.setSuccess(false);
		try {
			int result =  testMapper.updateByPrimaryKey(test);
			if (result > 0) {
				message.setSuccess(true);
			}else {
				message.setMsg("更新失败");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			message.setMsg(e.getCause().getMessage());
		}
		return message;
	}

	@Override
	public Message delByPrimeryKey(int id) {
		Message message = new Message();
		message.setSuccess(false);
		try {
			int result = testMapper.deleteByPrimaryKey(id);
			if (result > 0 ) {
				message.setSuccess(true);
			}else {
				message.setMsg("删除失败");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			message.setMsg(e.getCause().getMessage());
		}
		return message;
	}

}
