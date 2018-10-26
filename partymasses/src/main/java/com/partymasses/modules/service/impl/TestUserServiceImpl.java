package com.partymasses.modules.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partymasses.modules.dao.mybatis.entity.TestUser;
import com.partymasses.modules.dao.mybatis.entity.TestUserExample;
import com.partymasses.modules.dao.mybatis.entity.TestUserExample.Criteria;
import com.partymasses.modules.dao.mybatis.mapper.TestUserMapper;
import com.partymasses.modules.service.TestUserService;
import com.partymasses.support.message.Message;
@Service
public class TestUserServiceImpl implements TestUserService {
	
	@Autowired
	private TestUserMapper testUserMapper;
	
	private final static Logger logger = LoggerFactory.getLogger(TestUserServiceImpl.class);

	@Override
	public Message create(TestUser testUser) {
		Message message = new Message();
		message.setSuccess(false);
		try {
			int insert = testUserMapper.insert(testUser);
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
	public List<TestUser> findByTestId(int testId) {
		List<TestUser> list = null;
		try {
			TestUserExample testUserExample = new TestUserExample();
			Criteria createCriteria = testUserExample.createCriteria();
			createCriteria.andTestidEqualTo(testId);
			list = testUserMapper.selectByExample(testUserExample);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return list;
	}

	@Override
	public List<TestUser> findByUserId(int userId) {
		List<TestUser> list = null;
		try {
			TestUserExample testUserExample = new TestUserExample();
			Criteria criteria = testUserExample.createCriteria();
			criteria.andUseridEqualTo(userId);
			list = testUserMapper.selectByExample(testUserExample);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return list;
	}

	@Override
	public Message update(TestUser testUser) {
		Message message = new Message();
		message.setSuccess(false);
		try {
			int result = testUserMapper.updateByPrimaryKey(testUser);
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
	public Message delByTestId(int testId) {
		Message message = new Message();
		message.setSuccess(false);
		try {
			TestUserExample testUserExample = new TestUserExample();
			Criteria criteria = testUserExample.createCriteria();
			criteria.andTestidEqualTo(testId);
			int result = testUserMapper.deleteByExample(testUserExample);
			if (result > 0) {
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

	@Override
	public Message delByUserId(int userId) {
		Message message = new Message();
		message.setSuccess(false);
		try {
			TestUserExample testUserExample = new TestUserExample();
			Criteria criteria = testUserExample.createCriteria();
			criteria.andTestidEqualTo(userId);
			int result = testUserMapper.deleteByExample(testUserExample);
			if (result > 0) {
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
