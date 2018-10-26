package com.partymasses.modules.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.partymasses.modules.dao.mybatis.entity.Notice;
import com.partymasses.modules.dao.mybatis.entity.NoticeExample;
import com.partymasses.modules.dao.mybatis.entity.NoticeExample.Criteria;
import com.partymasses.modules.dao.mybatis.entity.User;
import com.partymasses.modules.dao.mybatis.entity.UserNotice;
import com.partymasses.modules.dao.mybatis.mapper.NoticeMapper;
import com.partymasses.modules.dao.mybatis.mapper.UserNoticeMapper;
import com.partymasses.modules.service.NoticeService;
import com.partymasses.modules.service.UserService;
import com.partymasses.support.constant.Contants;
import com.partymasses.support.websocket.service.Websocket;
@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	private NoticeMapper noticeMapper;
	@Autowired
	private UserNoticeMapper userNoticeMapper;
	@Autowired
	private UserService userService;
	@Override
	public Notice getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Notice> select() {
		//PageHelper.orderBy("CreateTime DESC");
		PageHelper.startPage(1, 3);
		List<Notice>list=new ArrayList<Notice>();
		list=noticeMapper.selectByExample(null);
		return list;
	}

	@Override
	public boolean create(String content, String scopeofapplication) {
	    Date time=new Date();
	    int id=noticeMapper.selectMaxId()+1;
	    Notice notice=new Notice();
	    notice.setContent(content);
	    notice.setId(id);
	    notice.setCreatetime(time);
	    notice.setScopeofapplication(scopeofapplication);
	    int n=noticeMapper.insert(notice);
	    if(n==0){
	    	return false;
	    }
	    
	    List<UserNotice>list=new ArrayList<UserNotice>();
	    List<User>listuser=new ArrayList<User>();
	    User userInfo=(User) SecurityUtils.getSubject().getSession().getAttribute(Contants.LOGINUSER);
	    listuser=userService.getListUserIdByUnitId(userInfo.getUnitid());
	    if (listuser.isEmpty()) {
			return false;
		}
	    
	    for (User userid:listuser) {
			list.add(new UserNotice(userid.getId(),id,1));
		}
	    int line=userNoticeMapper.addUserNotice(list);
	    if (line==0) {
			return false;
		}
	    for (int i = 0; i < listuser.size(); i++) {
	    	Websocket.sendMessage(listuser.get(i).getMobile(), content);
		}
	    //Websocket.sendMessage("18201009021", content);
		return true;
	}
	@Override
	public boolean deleteById(Integer id) {
		int n=noticeMapper.deleteByPrimaryKey(id);
		if (n==0) {
			return false;
		}
		return true;
	}

	@Override
	public int[] selectNoticeNumberByUserId(Integer userid) {
		int numberN=userNoticeMapper.quantityOfInformationNoRead(userid);
		int numberY=userNoticeMapper.quantityOfInformationReaded(userid);
		int[]number={numberN,numberY};
		return number;
	}

	@Override
	public List<Notice> selectNotice(int userid,int status) {
		//PageHelper.orderBy("CreateDate DESC,CreateTime DESC");
		PageHelper.startPage(1, 3);
		List<Notice>listn=new ArrayList<Notice>();
		List<Integer>list=new ArrayList<Integer>();
		if (status==1) {
			list=userNoticeMapper.selectByUserIdNoRead(userid);
			if (list.isEmpty()) {
				return null;
			}
			String ids="(";
			for (Integer integer : list) {
				ids=ids+integer.intValue()+",";
			}
			ids=ids.substring(0,ids.length()-1);
			ids=ids+")";
			listn=noticeMapper.selectById(ids);
			return listn;
		}else if(status==2){
			list=userNoticeMapper.selectByUserIdReaded(userid);
			if (list.isEmpty()) {
				return null;
			}
			String ids="(";
			for (Integer integer : list) {
				ids=ids+integer.intValue()+",";
			}
			ids=ids.substring(0,ids.length()-1);
			ids=ids+")";
			listn=noticeMapper.selectById(ids);
			
			return listn;
		}
		return null;
	}

	@Override
	public List<Notice> deleteNotice(List<Integer> noticeidlist) {
		NoticeExample example=new NoticeExample();
		Criteria criteria=example.createCriteria();
		criteria.andIdIn(noticeidlist);
		return noticeMapper.selectByExample(example);
	}
	@Override
	public boolean deleteNoticeS(UserNotice usernotice) {
		int n=userNoticeMapper.updateByPrimaryKeySelective(usernotice);
		if (n==0) {
			return false;
		}
		return true;
	}

	@Override
	public int deleteNotice(Integer noticeid) {
		// TODO Auto-generated method stub
		return 0;
	}

}
