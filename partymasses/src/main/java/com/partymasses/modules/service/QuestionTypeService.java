package com.partymasses.modules.service;

import com.partymasses.modules.dao.mybatis.entity.QuestionType;
import com.partymasses.support.message.Message;

public interface QuestionTypeService {

	public Message create(QuestionType questionType);
	
	public QuestionType findByPrimeryKey(int id);
	
	public Message updateByPrimeryKey(QuestionType questionType);
	
	public Message delByPrimeryKey(int id);
	
	public boolean isExistQuestionType(QuestionType questionType);
}
