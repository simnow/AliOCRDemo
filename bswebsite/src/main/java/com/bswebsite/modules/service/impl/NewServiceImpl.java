package com.bswebsite.modules.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bswebsite.modules.dao.ex.mapper.NewsExMapper;
import com.bswebsite.modules.dao.mybatis.entity.NewsExample;
import com.bswebsite.modules.dao.mybatis.entity.NewsExample.Criteria;
import com.bswebsite.modules.dao.mybatis.entity.News;
import com.bswebsite.modules.dao.mybatis.entity.User;
import com.bswebsite.modules.dao.mybatis.mapper.NewsMapper;
import com.bswebsite.modules.service.NewService;
import com.bswebsite.support.constant.Contants;
import com.bswebsite.support.message.Message;
import com.bswebsite.support.util.DateUtil;
import com.github.pagehelper.PageHelper;
@Service
public class NewServiceImpl implements NewService {

	@Autowired
	private NewsMapper newsMapper;
	@Autowired
	private NewsExMapper newsExMapper;
	
	@Override
	public int insertNews(News news) {
		news.setCreatetime(DateUtil.getcurrentDateTime());
		news.setReadcount(0);
		return newsMapper.insert(news);
	}

	@Override
	public Message updateNewsById(News news) {
		Message message=new Message();
		news.setUpdatetime(DateUtil.getcurrentDateTime());
		int result=newsMapper.updateByPrimaryKeySelective(news);
		if(result>0){
			message.setMsg("更新成功");
			message.setObj(news.getId());
		}else{
			message.setMsg("更新成功");
			message.setMsg("更新失败");
		}
		return message;
	}

	/**
	 * 双层判断
	 */
	@Override
	public Message deleteNewsById(int id,User user) {
		int result=0;
		if(Contants.ADMIN.equals(user.getRoletype())){
			NewsExample example=new NewsExample();
			Criteria criteria=example.createCriteria();
			criteria.andIdEqualTo(id);
			result=newsMapper.deleteByExample(example);
			if(result>0){
				return new Message();
			}else{
				Message message=new Message();
				message.setMsg("删除失败");
				message.setSuccess(false);
				return message;
			}
		}else{
			Message message=new Message();
			message.setMsg("删除失败");
			message.setSuccess(false);
			return message;
		}
	}
	@Override
	public Map<String,Object> findNewList(){
		List<News> result= newsExMapper.selectAll();
		List<News> alist=new ArrayList<>();
		List<News> blist=new ArrayList<>();
		List<News> clist=new ArrayList<>();
		List<News> dlist=new ArrayList<>();
		for(News news:result){
			if("a".equals(news.getNewstype())){
				alist.add(news);
			}
			if("b".equals(news.getNewstype())){
				blist.add(news);
			}
			if("c".equals(news.getNewstype())){
				clist.add(news);
			}
			if("d".equals(news.getNewstype())){
				dlist.add(news);
			}
		}
		Map<String,Object> resultMap=new HashMap<>();
		resultMap.put("alist", alist);
		resultMap.put("blist", blist);
		resultMap.put("clist", clist);
		resultMap.put("dlist", dlist);
		return resultMap;
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
	public Map<String,Object> findNewListByType(String newstype,int pageNo,int pageSize,String keyword) {
		 Map<String,Object> map=new HashMap<>();
		 map.put("newstotal", 0);
		 NewsExample example=new NewsExample();
		 Criteria criteria=example.createCriteria();
		 Criteria criteria2=example.createCriteria();
		Criteria criteria3=example.createCriteria();
		 criteria.andNewstypeEqualTo(newstype);
		 if(StringUtils.isNotBlank(keyword)){
			 keyword = "%" + keyword + "%";
			 criteria.andNewscontentLike(keyword);
			 criteria2.andNewstitleLike(keyword);
			 criteria3.andNewsabstractLike(keyword);
		 }
		 example.or(criteria2);
		 example.or(criteria3);
		 example.setOrderByClause("publishtime desc");
		 List<News> result= newsMapper.selectByExample(example);
		 if(result!=null&&result.size()>0){
			map.put("newstotal", result.size());
			PageHelper.startPage(pageNo, pageSize);
			List<News> result2= newsMapper.selectByExample(example);
			if(result2!=null&&result2.size()>0){
				map.put("news", result2);
			}
		}
		return map;
	}

	@Override
	public Map<String,Object> findNewListByKeyword(String keyword,int pageNo,int pageSize) {
		Map<String,Object> map=new HashMap<>();
		map.put("newstotal", 0);
		NewsExample example=new NewsExample();
		Criteria criteria=example.createCriteria();
		Criteria criteria2=example.createCriteria();
		Criteria criteria3=example.createCriteria();
		if(StringUtils.isNotBlank(keyword)){
			keyword = "%" + keyword + "%";
			criteria.andNewscontentLike(keyword);
			criteria2.andNewstitleLike(keyword);
			criteria3.andNewsabstractLike(keyword);
		}
		example.or(criteria2);
		example.or(criteria3);
		example.setOrderByClause("publishtime desc");
		List<News> result= newsMapper.selectByExample(example);
		if(result!=null&&result.size()>0){
			map.put("newstotal", result.size());
			PageHelper.startPage(pageNo, pageSize);
			List<News> result2= newsMapper.selectByExample(example);
			if(result2!=null&&result2.size()>0){
				map.put("news", result2);
			}
		}
		return map;
	}
}
