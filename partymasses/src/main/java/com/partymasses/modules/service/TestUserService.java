package com.partymasses.modules.service;

import java.util.List;

import com.partymasses.modules.dao.mybatis.entity.TestUser;
import com.partymasses.support.message.Message;

public interface TestUserService {

	public Message create(TestUser testUser);
	
	public List<TestUser> findByTestId(int testId);
	
	public List<TestUser> findByUserId(int userId);
	
	public Message update(TestUser testUser);
	
	public Message delByTestId(int testId);
	
	public Message delByUserId(int userId);
}
