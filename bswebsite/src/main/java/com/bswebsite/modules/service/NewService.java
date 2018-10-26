package com.bswebsite.modules.service;

import java.util.Map;

import com.bswebsite.modules.dao.mybatis.entity.News;
import com.bswebsite.modules.dao.mybatis.entity.User;
import com.bswebsite.support.message.Message;

public interface NewService {

	public int insertNews(News news);
	
	public Message updateNewsById(News news);
	
	public Message deleteNewsById(int id, User user);
	
	public News findNewsById(int newsId);

	Map<String, Object> findNewList();

	Boolean insertCount(int newsId,int type);

	public Map<String,Object> findNewListByType(String newstype,int pageNo,int pageSize,String keyword);

	Map<String, Object> findNewListByKeyword(String keyword, int pageNo, int pageSize);

}
