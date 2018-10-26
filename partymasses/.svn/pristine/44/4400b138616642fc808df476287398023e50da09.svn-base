package com.partymasses.modules.service;

import java.util.List;

import com.partymasses.modules.dao.mybatis.entity.Notice;
import com.partymasses.modules.dao.mybatis.entity.UserNotice;

public interface NoticeService {
	Notice getById(Integer id);
	List<Notice> select();
	boolean create(String content, String scopeofapplication);
	boolean deleteById(Integer id);
	int[] selectNoticeNumberByUserId(Integer userid);
	List<Notice> selectNotice(int userid,int status);
	int deleteNotice(Integer noticeid);
	List<Notice> deleteNotice(List<Integer> noticeidlist);
	boolean deleteNoticeS(UserNotice usernotice);
}
