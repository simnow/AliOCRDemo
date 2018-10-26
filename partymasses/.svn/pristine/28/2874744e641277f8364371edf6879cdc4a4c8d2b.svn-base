package com.partymasses.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.partymasses.modules.dao.mybatis.entity.Notice;
import com.partymasses.modules.dao.mybatis.entity.User;
import com.partymasses.modules.dao.mybatis.entity.UserNotice;
import com.partymasses.modules.service.NoticeService;
import com.partymasses.support.constant.Contants;

@Controller
@RequestMapping(value = "/api/notice")
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	
	@ResponseBody
	//@RequestMapping(method=RequestMethod.POST)
	@RequestMapping(value ="/post")
	public Boolean create( String content, String scopeofapplication) {
		return noticeService.create(content, scopeofapplication);
	}
	@ResponseBody
	//@RequestMapping(value = "/selectNumber")
	@RequestMapping(value = "/number",method=RequestMethod.GET)
	public int[] selectNumber() {
		User userInfo=(User) SecurityUtils.getSubject().getSession().getAttribute(Contants.LOGINUSER);
		int userid=userInfo.getId();
		return noticeService.selectNoticeNumberByUserId(userid);
	}
	
	/*修改消息状态
	 * 根据需求修改方法，针对前台展示及反馈效果
	 * */
	
	@ResponseBody
	@RequestMapping(value = "/noticecontent/{status}",method=RequestMethod.GET)
	//@RequestMapping(value = "/selectNotice")
	public List<Notice> selectNotice(@PathVariable("status") int status) {
		User userInfo=(User) SecurityUtils.getSubject().getSession().getAttribute(Contants.LOGINUSER);
		int userid=userInfo.getId();
		return noticeService.selectNotice(userid, status);
	}
	
	/*@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public  Message createActivity(@RequestBody Activity activity){
		
		return activityService.createActivity(activity);
		
	}*/
	
	/*修改消息状态*/
	@ResponseBody
	//@RequestMapping(method=RequestMethod.PUT)
	@RequestMapping(value="put")
	public boolean deleteNotice(UserNotice usernotice) {
		return noticeService.deleteNoticeS(usernotice);
	}
	
	@ResponseBody
	//@RequestMapping(value="/{id}" method=RequestMethod.DELETE)
	//@PathVariable("id")
	@RequestMapping(value="delete")
	public boolean delete(int id) {
		return noticeService.deleteById(id);
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET)
	@RequiresRoles("admin")
	//@RequestMapping(value="get")
	public List<Notice> get() {
		return noticeService.select();
	}
}
