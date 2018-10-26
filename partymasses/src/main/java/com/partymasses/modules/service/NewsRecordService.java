package com.partymasses.modules.service;

import com.partymasses.modules.dao.mybatis.entity.NewsRecord;

public interface NewsRecordService {

	public Boolean insertReocrd(NewsRecord record);

	public void deleteGoods(int newsId, int userId,int type);
	
}
