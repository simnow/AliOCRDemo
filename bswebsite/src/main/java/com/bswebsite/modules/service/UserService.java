package com.bswebsite.modules.service;

import com.bswebsite.modules.dao.mybatis.entity.User;
import com.bswebsite.support.message.Message;

public interface UserService {

public User getUserInfo(int id);
	
	public User getUserByNumber(String mobile);

	public Message userLogin(String mobile, String password);

}
