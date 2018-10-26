package com.partymasses.modules.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.partymasses.modules.dao.mybatis.entity.Question;
import com.partymasses.modules.dao.mybatis.entity.QuestionExample;
import com.partymasses.modules.dao.mybatis.entity.QuestionExample.Criteria;
import com.partymasses.modules.dao.mybatis.mapper.QuestionMapper;
import com.partymasses.modules.service.QuestionService;
import com.partymasses.support.message.Message;
@Service
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	private QuestionMapper questionMapper;

	private final static Logger logger = LoggerFactory.getLogger(QuestionServiceImpl.class);
	/**
	 * 新增题目
	 */
	@Override
	public Message create(Question question) {
		Message message = new Message();
		try {
			int insert = questionMapper.insert(question);
			if (insert>0) {
				message.setSuccess(true);
			}else {
				message.setSuccess(false);
				message.setMsg("添加失败");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			message.setMsg(e.getCause().getMessage());
		}
		return message;
	}

	/**
	 * 通过主键查询题目
	 */
	@Override
	public Question findByPrimeryKey(int id) {
		Question question = null;
		try {
			question = questionMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return question;
	}
	
	/**
	 * 查询某一类题目（党建、团建等）
	 */
	@Override
	public List<Question> findByTypeId(int typeId) {
		QuestionExample questionExample = new QuestionExample();
		Criteria criteria = questionExample.createCriteria();
		criteria.andStyleidEqualTo(typeId);
		List<Question> list = null;
		try {
			list = questionMapper.selectByExample(questionExample);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return list;
	}
	
	/**
	 * 查询某一类题目（选择、判断、填空等）
	 */
	@Override
	public List<Question> findByStyleId(int styleId) {
		QuestionExample questionExample = new QuestionExample();
		Criteria criteria = questionExample.createCriteria();
		criteria.andStyleidEqualTo(styleId);
		List<Question> list = null;
		try {
			list = questionMapper.selectByExample(questionExample);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return list;
	}

	/**
	 * 通过主键更新题目
	 */
	@Override
	public Message updateByPrimeryKey(Question question) {
		Message message = new Message();
		message.setSuccess(false);
		try {
			int result = questionMapper.updateByPrimaryKey(question);
			if (result>0) {
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

	/**
	 * 通过主键删除题目
	 */
	@Override
	public Message delByPrimeryKey(int id) {
		Message message = new Message();
		message.setSuccess(false);
		Question question = this.findByPrimeryKey(id);
		if (question != null) {
			try {
				int result = questionMapper.deleteByPrimaryKey(id);
				if (result>0) {
					message.setSuccess(true);
				}else {
					message.setMsg("删除失败");
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				message.setMsg(e.getCause().getMessage());
			}	
		}else {
			message.setMsg("删除内容不存在");
		}
		return message;
	}
	
	/**
	 * 查询题目是否存在
	 */
	@Override
	public boolean isExistQuestion(Question question) {
		QuestionExample questionExample = new QuestionExample();
		Criteria criteria = questionExample.createCriteria();
		criteria.andStemnameEqualTo(question.getStemname());
		criteria.andStyleidEqualTo(question.getStyleid());
		List<Question> list = null;
		try {
			list = questionMapper.selectByExample(questionExample);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		if (list == null || list.size()<=0) {
			return false;//不存在
		}
		return true;//存在
	}

	

}
