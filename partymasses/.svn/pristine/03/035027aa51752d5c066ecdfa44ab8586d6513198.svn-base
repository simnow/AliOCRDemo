package com.partymasses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.partymasses.modules.dao.mybatis.entity.Blogs;
import com.partymasses.modules.service.BlogService;
import com.partymasses.support.message.Message;
@Controller
@RequestMapping(value="api/blog")
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	/**
	 * 新增，父类是0
	 * @param blog
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Message insertBlogs(@RequestBody Blogs blog){
		Message message=new Message();
		boolean reulst=blogService.insertBlog(blog.getBlog(), blog.getNewsid(), blog.getParentid());
		if(reulst){
			return message;
		}
		message.setSuccess(false);
		message.setMsg("请稍后再试");
		return message;
	}
	
	/**
	 * 通过新闻查看留言
	 * @param newsId
	 * @return
	 */
	@RequestMapping(value = "/{newsId}",method = RequestMethod.GET)
	@ResponseBody
	public Message findBlogs(@PathVariable("newsId")  int newsId){
		return blogService.findAllBlogByNewsId(newsId);
	}
}
