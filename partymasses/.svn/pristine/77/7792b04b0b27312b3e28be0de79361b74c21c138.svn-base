package com.partymasses.modules.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partymasses.modules.dao.ex.mapper.NewsRecordExMapper;
import com.partymasses.modules.dao.mybatis.entity.NewsRecord;
import com.partymasses.modules.dao.mybatis.entity.NewsRecordExample;
import com.partymasses.modules.dao.mybatis.entity.NewsRecordExample.Criteria;
import com.partymasses.modules.dao.mybatis.mapper.NewsRecordMapper;
import com.partymasses.modules.service.NewsRecordService;
import com.partymasses.support.util.DateUtil;
@Service
public class NewsRecordServiceImpl implements NewsRecordService {

	@Autowired
	private NewsRecordExMapper mapper;
	@Autowired
	private NewsRecordMapper recordMapper;
	
	/**
	 * 如果已经存在，就不能插入，否则就进行插入
	 */
	@Override
	public Boolean insertReocrd(NewsRecord record) {
		record.setCreatetime(DateUtil.getcurrentDateTime());
		try {
			int result=mapper.insertNewRecord(record);
			if(result>0){
				return false;//已经存在
			}else{
				return true;
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return true;
	}

	@Override
	public void deleteGoods(int newsId, int userId,int type) {
		NewsRecordExample example=new NewsRecordExample();
		Criteria criteria=example.createCriteria();
		criteria.andNewsidEqualTo(newsId);
		criteria.andUseridEqualTo(userId);
		criteria.andTypeEqualTo(type);
		try {
			recordMapper.deleteByExample(example);
		} catch (Exception e) {
			e.getMessage();
		}
	}

}
