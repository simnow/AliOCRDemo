package com.partymasses.modules.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partymasses.modules.dao.mybatis.entity.QuestionStyle;
import com.partymasses.modules.dao.mybatis.entity.QuestionStyleExample;
import com.partymasses.modules.dao.mybatis.entity.QuestionStyleExample.Criteria;
import com.partymasses.modules.dao.mybatis.mapper.QuestionStyleMapper;
import com.partymasses.modules.service.QuestionStyleService;
import com.partymasses.support.message.Message;
@Service
public class QuestionStyleServiceImpl implements QuestionStyleService {
	
	@Autowired
	private QuestionStyleMapper questionStyleMapper;
	
	private final static Logger logger = LoggerFactory.getLogger(QuestionServiceImpl.class);

	@Override
	public Message create(QuestionStyle questionStyle) {
		Message message = new Message();
		message.setSuccess(false);
		try {
			int insert = questionStyleMapper.insert(questionStyle);
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
	public QuestionStyle findByPrimeryKey(int id) {
		QuestionStyle questionStyle = null;
		try {
			questionStyle = questionStyleMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return questionStyle;
	}

	@Override
	public Message updateByPrimery(QuestionStyle questionStyle) {
		Message message = new Message();
		message.setSuccess(false);
		try {
			int result = questionStyleMapper.updateByPrimaryKey(questionStyle);
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
			int result = questionStyleMapper.deleteByPrimaryKey(id);
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
	public boolean isExistQuestionStyle(QuestionStyle questionStyle) {
		QuestionStyleExample questionStyleExample = new QuestionStyleExample();
		Criteria criteria = questionStyleExample.createCriteria();
		criteria.andNameEqualTo(questionStyle.getName());
		List<QuestionStyle> list = null;
		try {
			list = questionStyleMapper.selectByExample(questionStyleExample);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		if (list != null && list.size()>0) {
			return true;//存在
		}
		return false;//不存在
	}

}
