package com.partymasses.modules.service;

import java.util.List;

import com.partymasses.modules.dao.mybatis.entity.News;
import com.partymasses.modules.dao.mybatis.entity.User;
import com.partymasses.support.message.Message;

public interface NewService {

	public int insertNews(News news);
	
	public Message updateNewsById(News news);
	
	public Message deleteNewsById(int id, User user);
	
	public List<News> findNewsListByUnitId(int unitId);
	
	public News findNewsById(int newsId);

	List<News> findNewList();

	Boolean insertCount(int newsId,int type);


	public Boolean insertRecordAndCount(int newsId, User user);

	public List<News> findNewList(int pageNo, int pageSize, String type);

}
