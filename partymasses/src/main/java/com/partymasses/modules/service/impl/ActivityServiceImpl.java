package com.partymasses.modules.service.impl;



import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partymasses.modules.dao.dto.AtvDto;
import com.partymasses.modules.dao.mybatis.entity.Activity;
import com.partymasses.modules.dao.mybatis.entity.ActivityUnits;
import com.partymasses.modules.dao.mybatis.entity.ActivityUsers;
import com.partymasses.modules.dao.mybatis.entity.ActivityUsersExample;
import com.partymasses.modules.dao.mybatis.entity.ActivityUsersExample.Criteria;
import com.partymasses.modules.dao.mybatis.entity.User;
import com.partymasses.modules.dao.mybatis.mapper.ActivityMapper;
import com.partymasses.modules.dao.mybatis.mapper.ActivityUnitsMapper;
import com.partymasses.modules.dao.mybatis.mapper.ActivityUsersMapper;
import com.partymasses.modules.service.ActivityService;
import com.partymasses.support.constant.Contants;
import com.partymasses.support.message.Message;
import com.partymasses.support.util.DateUtil;
@Service
public class ActivityServiceImpl implements ActivityService{
	@Autowired
	private ActivityUsersMapper activityUsersMapper;
	@Autowired
	private ActivityMapper activityMapper;
	@Autowired
	private ActivityUnitsMapper atvunitMapper;
	@Override
	public Message userEnroll(Integer activityid) {
		// 判断是否传入参数
		if(activityid==null){
				return  new Message(false,"请选择要报名的活动");
		}
		Activity activity=activityMapper.selectByPrimaryKey(activityid);
		if(activity==null){
				return  new Message(false,"活动不存在");
		}
		if(activity.getAtvstate()==Contants.ATV_CANCEL){
				return  new Message(false,"活动已撤销");
		}
		if(activity.getAtvenrollment()>=activity.getAtvlimit()){
			
			    return  new Message(false,"报名名额已满");	    
		}
		//报名时间判断未加
		if(!DateUtil.compareDate(activity.getAtvsperiod(),activity.getAtveperiod())){
				return  new Message(false,"报名已截止");
		}
		User userInfo=(User) SecurityUtils.getSubject().getSession().getAttribute(Contants.LOGINUSER);
		ActivityUsers activityUsers=new ActivityUsers();
		activityUsers.setUserid(userInfo.getId());
		activityUsers.setAtvid(activityid);
		activityUsers.setApplicationdate(DateUtil.getcurrentDateTime());
		int num=activityUsersMapper.insertSelective(activityUsers);
		//报名成功活动人数增加
		if(num>0){
			 activity.setAtvenrollment(activity.getAtvenrollment()+1);
			 activityMapper.updateByPrimaryKey(activity);
		}
		return new Message(true,"报名成功");
	}
	@Override
	public Message userCancelEnroll(Integer activityid) {
		// TODO Auto-generated method stub
		if(activityid==null){
			return  new Message(false,"请选择要取消报名的活动");
		}
		User userInfo=(User) SecurityUtils.getSubject().getSession().getAttribute(Contants.LOGINUSER);
		ActivityUsersExample activityUsersExample=new ActivityUsersExample();
		Criteria criteria= activityUsersExample.createCriteria();
		criteria.andUseridEqualTo(userInfo.getId());
		criteria.andAtvidEqualTo(activityid);
		int num=activityUsersMapper.deleteByExample(activityUsersExample);
		if(num<=0){
			return new  Message(false,"未查询到报名信息");
		}
		Activity activity=activityMapper.selectByPrimaryKey(activityid);
		activity.setAtvenrollment(activity.getAtvenrollment()-1);
		activityMapper.updateByPrimaryKey(activity);
		return new Message(true,"取消报名成功");
		
	}
	@Override
	public Message createActivity(Activity activity) {
		// TODO Auto-generated method stub
		if(activity.getAtvname()==null||"".equals(activity.getAtvname())){
				return new Message(false,"请填写活动标题");
		}
		if(activity.getAtvcontent()==null||"".equals(activity.getAtvcontent())){
				return new Message(false,"请填写新闻内容");
		}
		if(activity.getAtvsdate()==null||activity.getAtvedate()==null){
				return new Message(false,"请填写活动时间");
		}
		if(activity.getAtvsperiod()==null||activity.getAtveperiod()==null){
				return new Message(false,"请填写报名时间");
		}
		User userInfo=(User) SecurityUtils.getSubject().getSession().getAttribute(Contants.LOGINUSER);
		activity.setAtvunit(userInfo.getUnitid());
		activityMapper.insertSelective(activity);
		Message  message=new Message(true,"活动创建成功");
		message.setObj(activity);
		return message;
	}
	@Override
	public Message updateActivity(Activity activity) {
		// TODO Auto-generated method stub
		Activity atv=activityMapper.selectByPrimaryKey(activity.getId());
		//修改状态
		int num=activityMapper.updateByPrimaryKeySelective(activity);
		if(num<0){
			return new Message(false,"未查询到此条记录");
		}
		//发布
		if(atv.getAtvstate()==Contants.ATV_UNRELEASE&&activity.getAtvstate()==Contants.ATV_RELEASE){
			ActivityUnits activityUnits=new ActivityUnits();
			activityUnits.setUnitid(atv.getAtvunit());
			activityUnits.setAtvid(activity.getId());	
			atvunitMapper.insert(activityUnits);
			return new Message(true,"发布成功");
		}
		//撤销
		if(activity.getAtvstate()==Contants.ATV_CANCEL&&atv.getAtvstate()!=(activity.getAtvstate())){
			
				//暂时撤销无处理
			
		}
		return new Message();
	}
	@Override
	public Message getAtvList() {
		// TODO Auto-generated method stub
		User userInfo=(User) SecurityUtils.getSubject().getSession().getAttribute(Contants.LOGINUSER);
		
		if(userInfo.getUnitid()==null){
			 return new Message(true,"查询出错，未查询到你的所属单位");
		}
		List<Activity> resultList=activityMapper.getAtvList(userInfo.getUnitid());
		
		Message message=new Message();
		
		message.setObj(resultList);
		
		return message;
	}
	@Override
	public Message distributeActivity(Integer atvid,String unitids) {
		
		if(atvid==null){
			return new Message(false,"请选中你要分配的单位");
		}
		if(unitids==null||"".equals(unitids)){
			return new Message(false,"请选中要分配的单位");
		}
		AtvDto atvdto=new AtvDto();
		atvdto.setUnitids(sIntArray(unitids));
		atvdto.setAtvid(atvid);
		List<ActivityUnits> resultList=atvunitMapper.selectIsExist(atvdto);
		if(resultList!=null&&resultList.size()>0){
			
			return new Message(false,"选中行已分配，请重新操作");
		}
		atvunitMapper.distributeActivity(atvdto);
		return new Message(true,"操作成功");
		
	}
	public static int[] sIntArray(String str){
			String[] strArray=str.split(",");
			int [] intArray=new int[strArray.length];
			for (int i = 0; i < intArray.length; i++) {
				intArray[i]=Integer.parseInt(strArray[i]);
			}
			return intArray;
	} 
	
}
