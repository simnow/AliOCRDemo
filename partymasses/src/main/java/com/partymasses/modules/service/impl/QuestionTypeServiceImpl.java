package com.partymasses.modules.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partymasses.modules.dao.mybatis.entity.QuestionType;
import com.partymasses.modules.dao.mybatis.entity.QuestionTypeExample;
import com.partymasses.modules.dao.mybatis.entity.QuestionTypeExample.Criteria;
import com.partymasses.modules.dao.mybatis.mapper.QuestionTypeMapper;
import com.partymasses.modules.service.QuestionTypeService;
import com.partymasses.support.message.Message;
@Service
public class QuestionTypeServiceImpl implements QuestionTypeService {
	
	@Autowired
	private QuestionTypeMapper questionTypeMapper;
	
	private final static Logger logger = LoggerFactory.getLogger(QuestionTypeServiceImpl.class);

	@Override
	public Message create(QuestionType questionType) {
		Message message = new Message();
		message.setSuccess(false);
		try {
			int insert = questionTypeMapper.insert(questionType);
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
	public QuestionType findByPrimeryKey(int id) {
		QuestionType questionType = null;
		try {
			questionType = questionTypeMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return questionType;
	}

	@Override
	public Message updateByPrimeryKey(QuestionType questionType) {
		Message message = new Message();
		message.setSuccess(false);
		try {
			int result = questionTypeMapper.updateByPrimaryKey(questionType);
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
			int result = questionTypeMapper.deleteByPrimaryKey(id);
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
	public boolean isExistQuestionType(QuestionType questionType) {
		QuestionTypeExample questionTypeExample = new QuestionTypeExample();
		Criteria criteria = questionTypeExample.createCriteria();
		criteria.andNameEqualTo(questionType.getName());
		List<QuestionType> list = null;
		try {
			list = questionTypeMapper.selectByExample(questionTypeExample);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		if (list != null && list.size() > 0) {
			return true;//存在
		}
		return false;//不存在
	}

}
