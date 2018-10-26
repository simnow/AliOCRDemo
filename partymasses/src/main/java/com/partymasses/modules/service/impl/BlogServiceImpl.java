package com.partymasses.modules.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partymasses.modules.dao.mybatis.entity.Blogs;
import com.partymasses.modules.dao.mybatis.entity.BlogsExample;
import com.partymasses.modules.dao.mybatis.entity.BlogsExample.Criteria;
import com.partymasses.modules.dao.mybatis.entity.User;
import com.partymasses.modules.dao.mybatis.mapper.BlogsMapper;
import com.partymasses.modules.service.BlogService;
import com.partymasses.support.constant.Contants;
import com.partymasses.support.message.Message;
import com.partymasses.support.util.DateUtil;
@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogsMapper blogMapper;
	@Override
	public Boolean insertBlog(String blogtext,int newsid,int parentId) {
		User userInfo=(User) SecurityUtils.getSubject().getSession().getAttribute(Contants.LOGINUSER);
		Blogs blog=new Blogs();
		blog.setCreateuser(userInfo.getId());
		blog.setCreatetime(DateUtil.getcurrentDateTime());
		blog.setNewsid(newsid);
		blog.setParentid(parentId);
		blog.setState(0);
		blog.setUnitid(userInfo.getUnitid());
		blog.setBlog(blogtext);
		int result=blogMapper.insert(blog);
		if(result>0){
			return true;
		}
		return false;
	}

	@Override
	public Message findAllBlogByNewsId(int newsId) {
		// 查询所有
		Message message=new Message();
		Map<String, Object> attributes=new HashMap<>();
		BlogsExample example=new BlogsExample();
		Criteria criteria=example.createCriteria();
		criteria.andNewsidEqualTo(newsId);
		example.setOrderByClause("createtime desc");
		List<Blogs> blogslist=blogMapper.selectByExample(example);
		List<Blogs> parendBlogs=new ArrayList<Blogs>();
		List<Blogs> childBlogs=new ArrayList<Blogs>();
		//先查找父类留言
		for(Blogs blog:blogslist){
			if(0==blog.getParentid()){
				parendBlogs.add(blog);
				attributes.put("parendBlogs", parendBlogs);
			}else{
				childBlogs.add(blog);
				attributes.put("childBlogs", childBlogs);
			}
		}
		message.setAttributes(attributes);
		return message;
	}

	@Override
	public List<Blogs> findBlogReply(int newsId, int blogId) {
		// TODO Auto-generated method stub
		return null;
	}

}
