package com.partymasses.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.partymasses.modules.dao.mybatis.entity.NewsRecord;
import com.partymasses.modules.dao.mybatis.entity.User;
import com.partymasses.modules.service.NewsRecordService;
import com.partymasses.support.constant.Contants;
import com.partymasses.support.message.Message;
import com.partymasses.support.util.DateUtil;

@Controller
@RequestMapping(value="api/good")
public class GoodController {

	@Autowired
	private NewsRecordService newsRecordService;
	@RequestMapping(value = "/{newsId}",method = RequestMethod.GET)
	@ResponseBody
	public Message insertGood(@PathVariable("newsId") int newsId){
		User userInfo=(User) SecurityUtils.getSubject().getSession().getAttribute(Contants.LOGINUSER);
		NewsRecord record=new NewsRecord();
		record.setCreatetime(DateUtil.getcurrentDateTime());
		record.setNewsid(newsId);
		record.setType(Contants.GoodType);
		record.setUnitid(userInfo.getUnitid());
		record.setUserid(userInfo.getId());
		//对结果不做处理
		newsRecordService.insertReocrd(record);
		return new Message();
	}
	
	/**
	 * 取消点赞
	 */
	@RequestMapping(value = "/{newsId}",method = RequestMethod.DELETE)
	@ResponseBody
	public Message deleteGood(@PathVariable("newsId") int newsId){
		User userInfo=(User) SecurityUtils.getSubject().getSession().getAttribute(Contants.LOGINUSER);
		//对结果不做处理
		newsRecordService.deleteGoods(newsId,userInfo.getId(),Contants.GoodType);
		return new Message();
	}
}
