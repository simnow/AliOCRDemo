package com.partymasses.modules.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partymasses.modules.dao.mybatis.entity.TestQuestion;
import com.partymasses.modules.dao.mybatis.entity.TestQuestionExample;
import com.partymasses.modules.dao.mybatis.entity.TestQuestionExample.Criteria;
import com.partymasses.modules.dao.mybatis.mapper.TestQuestionMapper;
import com.partymasses.modules.service.TestQuestionService;
import com.partymasses.support.message.Message;
@Service
public class TestQuestionServiceImpl implements TestQuestionService {
	
	@Autowired
	private TestQuestionMapper testQuestionMapper;
	
	private final static Logger logger = LoggerFactory.getLogger(TestQuestionServiceImpl.class);

	@Override
	public Message create(TestQuestion testQuestion) {
		Message message = new Message();
		message.setSuccess(false);
		try {
			int insert = testQuestionMapper.insert(testQuestion);
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
	public List<TestQuestion> findByTestId(int testId) {
		List<TestQuestion> list = null;
		try {
			TestQuestionExample testQuestionExample = new TestQuestionExample();
			Criteria criteria = testQuestionExample.createCriteria();
			criteria.andTestidEqualTo(testId);
			list = testQuestionMapper.selectByExample(testQuestionExample);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return list;
	}

	@Override
	public List<TestQuestion> findByQuestionId(int questionId) {
		List<TestQuestion> list = null;
		try {
			TestQuestionExample testQuestionExample = new TestQuestionExample();
			Criteria criteria = testQuestionExample.createCriteria();
			criteria.andTestidEqualTo(questionId);
			list = testQuestionMapper.selectByExample(testQuestionExample);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return list;
	}

	@Override
	public Message delByTestId(int testId) {
		Message message = new Message();
		message.setSuccess(false);
		try {
			TestQuestionExample testQuestionExample = new TestQuestionExample();
			Criteria criteria = testQuestionExample.createCriteria();
			criteria.andTestidEqualTo(testId);
			int result = testQuestionMapper.deleteByExample(testQuestionExample);
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
	public Message delByQuestionId(int questionId) {
		Message message = new Message();
		message.setSuccess(false);
		try {
			TestQuestionExample testQuestionExample = new TestQuestionExample();
			Criteria criteria = testQuestionExample.createCriteria();
			criteria.andTestidEqualTo(questionId);
			int result = testQuestionMapper.deleteByExample(testQuestionExample);
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
