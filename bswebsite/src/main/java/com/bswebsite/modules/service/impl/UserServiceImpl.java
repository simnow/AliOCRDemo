package com.bswebsite.modules.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bswebsite.modules.dao.mybatis.entity.User;
import com.bswebsite.modules.dao.mybatis.entity.UserExample;
import com.bswebsite.modules.dao.mybatis.entity.UserExample.Criteria;
import com.bswebsite.modules.dao.mybatis.mapper.UserMapper;
import com.bswebsite.modules.service.UserService;
import com.bswebsite.support.exception.CommonException;
import com.bswebsite.support.message.Message;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;

	@Override
	public User getUserInfo(int id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public User getUserByNumber(String mobile) {
		UserExample example=new UserExample();
		Criteria criteria=example.createCriteria();
		criteria.andMobileEqualTo(mobile);
		List<User> userllist=userMapper.selectByExample(example);
		if(userllist==null||userllist.size()==0){
			return null;
		}
		return userllist.get(0);
	}

	/**
	 * 用户登陆
	 */
	@Override
	public Message userLogin(String mobile, String password) {
		Message message=new Message();
		message.setSuccess(false);
		
		//判断用户是否存在
		User user=getUserByNumber(mobile);
		if(user==null){
			message.setMsg("不存在该用户");
			return message;
		}
		//判断密码是否正确
		if(!password.equals(user.getPassword())){
			message.setMsg("密码错误");
			return message;
		}
		//正确后，shiro管理
		 try{
			 UsernamePasswordToken token = new UsernamePasswordToken(mobile,password);  
			 Subject currentUser = SecurityUtils.getSubject();  
			 if (!currentUser.isAuthenticated()){
			    //使用shiro来验证  
			    token.setRememberMe(true);  
			    Map<String, Object> info=new HashMap<>();
			    info.put("userId", user.getId());
			    info.put("name", user.getName());
			    info.put("roletype", user.getRoletype());
			    info.put("mobile", user.getMobile());
			    //放用户信息
			    message.setAttributes(info);
			    currentUser.login(token); 
			 } 
		}catch(Exception ex){
		    throw new CommonException(ex.getMessage());
		}
		 message.setSuccess(true);
		return message;
	}

}
