package com.partymasses.modules.service;

import org.springframework.web.bind.annotation.RequestBody;

import com.partymasses.modules.dao.dto.AtvDto;
import com.partymasses.modules.dao.mybatis.entity.Activity;
import com.partymasses.support.message.Message;

public interface ActivityService {

	public Message  userEnroll(Integer activityid);
	
	public Message  userCancelEnroll(Integer activityid);
	
	public  Message createActivity(Activity activity);
	
	public  Message updateActivity(Activity activity);

	public Message  getAtvList();
	
	public Message distributeActivity(Integer atvid,String unitids);
}
