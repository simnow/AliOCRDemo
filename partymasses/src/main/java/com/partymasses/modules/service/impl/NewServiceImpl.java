package com.partymasses.modules.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.partymasses.modules.dao.ex.mapper.NewsExMapper;
import com.partymasses.modules.dao.mybatis.entity.News;
import com.partymasses.modules.dao.mybatis.entity.NewsExample;
import com.partymasses.modules.dao.mybatis.entity.NewsExample.Criteria;
import com.partymasses.modules.dao.mybatis.entity.NewsRecord;
import com.partymasses.modules.dao.mybatis.entity.User;
import com.partymasses.modules.dao.mybatis.mapper.NewsMapper;
import com.partymasses.modules.service.NewService;
import com.partymasses.modules.service.NewsRecordService;
import com.partymasses.support.constant.Contants;
import com.partymasses.support.message.Message;
import com.partymasses.support.util.DateUtil;
@Service
public class NewServiceImpl implements NewService {

	@Autowired
	private NewsMapper newsMapper;
	@Autowired
	private NewsExMapper newsExMapper;
	@Autowired
	private NewsRecordService newsRecordService;
	@Override
	public int insertNews(News news) {
		news.setCreatetime(DateUtil.getcurrentDateTime());
		return newsMapper.insert(news);
	}

	@Override
	public Message updateNewsById(News news) {
		Message message=new Message();
		news.setUpdatetime(DateUtil.getcurrentDateTime());
		int result=newsMapper.updateByPrimaryKeyWithBLOBs(news);
		if(result>0){
			message.setMsg("更新成功");
			message.setObj(news.getId());
		}else{
			message.setMsg("更新失败");
			message.setSuccess(false);
		}
		return message;
	}

	/**
	 * 双层判断
	 */
	@Override
	public Message deleteNewsById(int id,User user) {
		int result=0;
		//如果是超级管理员，可以删，如果是管理员删当下管理的用户，如果是用户不可以删除
		if(Contants.ADMIN.equals(user.getRoletype())){
			NewsExample example=new NewsExample();
			Criteria criteria=example.createCriteria();
			criteria.andIdEqualTo(id);
			criteria.andUnitidEqualTo(user.getUnitid());
			result=newsMapper.deleteByExample(example);
		}else if(Contants.SUPERADMIN.equals(user.getRoletype())){
			result=newsMapper.deleteByPrimaryKey(id);
		}
		if(result>0){
			return new Message();
		}else{
			Message message=new Message();
			message.setMsg("删除失败");
			message.setSuccess(false);
			return message;
		}
	}

	/**
	 * 通过单位找新闻列表
	 */
	@Override
	public List<News> findNewsListByUnitId(int unitId) {
		NewsExample example=new NewsExample();
		Criteria criteria=example.createCriteria();
		criteria.andUnitidEqualTo(unitId);
		return newsMapper.selectByExampleWithBLOBs(example);
		
	}
	
	@Override
	public List<News> findNewList(){
		//如果是游客，就可以访问所有，如果是有单位类型的，就访问本单位和原始的
		User userInfo=(User) SecurityUtils.getSubject().getSession().getAttribute(Contants.LOGINUSER);
		if(userInfo==null){
			NewsExample example=new NewsExample();
			Criteria criteria=example.createCriteria();
			criteria.andUnitidEqualTo(0);
			return newsMapper.selectByExampleWithBLOBs(example);
		}else if(Contants.USER.equals(userInfo.getRoletype())||Contants.ADMIN.equals(userInfo.getRoletype())){
			//查询用户下边的新闻+赠送
			NewsExample example=new NewsExample();
			Criteria criteria=example.createCriteria();
			List<Integer> unitList=new ArrayList<>();
			unitList.add(0);
			unitList.add(userInfo.getUnitid());
			criteria.andUnitidIn(unitList);
			return newsMapper.selectByExampleWithBLOBs(example);
		}
		return newsExMapper.selectAll();
	}

	@Override
	public News findNewsById(int newsId) {
		return newsMapper.selectByPrimaryKey(newsId);
	}
	
	@Override
	public Boolean insertCount(int newsId,int type){
		try {
			if(Contants.ReadType.equals(type)){
				newsExMapper.updateNewReadCount(newsId);
			}else if(Contants.GoodType.equals(type)){
				newsExMapper.updateNewGoodsCount(newsId);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return true;
	}

	@Override
	public Boolean insertRecordAndCount(int newsId, User userInfo) {
		//阅读量，先查询是否已经阅读了，阅读了返回true，没有阅读false
		NewsRecord record=new NewsRecord();
		record.setNewsid(newsId);
		record.setType(Contants.ReadType);
		record.setUnitid(userInfo.getUnitid());
		record.setUserid(userInfo.getId());
		try {
			//新增记录
			if(!newsRecordService.insertReocrd(record)){ //如果
			//新增数量
			insertCount(newsId,Contants.ReadType);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return true;
	}

	@Override
	public List<News> findNewList(int pageNo, int pageSize, String type) {
		PageHelper.startPage(pageNo, pageSize);
		//如果是游客，就可以访问所有，如果是有单位类型的，就访问本单位和原始的
				User userInfo=(User) SecurityUtils.getSubject().getSession().getAttribute(Contants.LOGINUSER);
				if(userInfo==null){
					NewsExample example=new NewsExample();
					Criteria criteria=example.createCriteria();
					criteria.andUnitidEqualTo(0);
					if(StringUtils.isNotBlank(type)){
						criteria.andNewstypeEqualTo(type);
					}
					return newsMapper.selectByExampleWithBLOBs(example);
				}else if(Contants.USER.equals(userInfo.getRoletype())||Contants.ADMIN.equals(userInfo.getRoletype())){
					//查询用户下边的新闻+赠送
					NewsExample example=new NewsExample();
					Criteria criteria=example.createCriteria();
					List<Integer> unitList=new ArrayList<>();
					unitList.add(0);
					unitList.add(userInfo.getUnitid());
					criteria.andUnitidIn(unitList);
					if(StringUtils.isNotBlank(type)){
						criteria.andNewstypeEqualTo(type);
					}
					return newsMapper.selectByExampleWithBLOBs(example);
				}
				return newsExMapper.selectAll();
	}

}
