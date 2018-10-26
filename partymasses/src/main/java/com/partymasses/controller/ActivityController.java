package com.partymasses.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.partymasses.modules.dao.dto.AtvDto;
import com.partymasses.modules.dao.mybatis.entity.Activity;
import com.partymasses.modules.service.ActivityService;
import com.partymasses.support.message.Message;
@RequestMapping(value="/api/atv")
@Controller
public class ActivityController {
	
	@Autowired
	private ActivityService activityService;
	

	/*
	 * 创建活动
	 * param
	 * admin 
	 */
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	@RequiresRoles("admin")
	public  Message createActivity(@RequestBody Activity activity){
		
		return activityService.createActivity(activity);
		
	}
	/*
	 * 修改或发布活动
	 * param
	 * admin 
	 */
	@RequestMapping(method=RequestMethod.PUT)
	@ResponseBody
	@RequiresRoles("admin")
	public  Message updateActivity(@RequestBody Activity activity){
		
		return activityService.updateActivity(activity);
		
	}
	/*
	 * 查询本单位活动列表
	 * param
	 * user
	 */
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public Message  getAtvList(){
		
		return activityService.getAtvList();
		
	}
	
	/*
	 * 分配活动可参与下级单位
	 * param
	 * user
	 */
	@RequestMapping(value="/info",method=RequestMethod.POST)
	@ResponseBody
	public Message distributeActivity(Integer atvid,String unitids){
		return activityService.distributeActivity(atvid,unitids);
		
	}
	/*
	 * 用户报名
	 * param
	 * user
	 */
	@RequestMapping(value="/enroll/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Message  userEnroll(@PathVariable("id") Integer activityid){
		
		return activityService.userEnroll(activityid);
		
	}
	/*
	 * 取消报名
	 * param
	 * user
	 */
	@RequestMapping(value="/unenroll/{id}",method=RequestMethod.GET)
	@ResponseBody
	public  Message userCancelEnroll(@PathVariable("id") Integer activityid){
		
		return activityService.userCancelEnroll(activityid);
		
	}
	
}














