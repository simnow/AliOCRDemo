package com.partymasses.modules.service;

import java.util.List;

import com.partymasses.modules.dao.mybatis.entity.User;
import com.partymasses.support.message.Message;

public interface UserService {

	public User getUserInfo(int id);
	
	public User getUserByNumber(String mobile);

	public Message userLogin(String mobile, String password);

	public Message delById(int id);

	public Message delByIdAndUnit(int id, User user);
	
	public Message updateUserById(User user);

	Message insertUserList(List<User> userList) throws Exception;

	Message updatePassWord(String oldPw, String newPwd);

	public Message insertUser(User user);
	
	public List<User> getListUserIdByUnitId(int unitid);

	Boolean isExitMobile(String mobile);

	Message getadminList(int unitId);

	public Message selectUserByMobileOrName(String name, String mobile,String roleType,int unitId,int pageNum,int pageSize);

	
}