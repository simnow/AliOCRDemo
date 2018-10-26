package com.partymasses.modules.service;

import java.util.List;

import com.partymasses.modules.dao.mybatis.entity.Question;
import com.partymasses.support.message.Message;

public interface QuestionService {
	
	public Message create(Question question);
	
	public Question findByPrimeryKey(int id);
	
	public List<Question> findByTypeId(int typeId);
	
	public List<Question> findByStyleId(int styleId);

	public Message updateByPrimeryKey(Question question);
	
	public Message delByPrimeryKey(int id);

	public boolean isExistQuestion(Question question);
}
