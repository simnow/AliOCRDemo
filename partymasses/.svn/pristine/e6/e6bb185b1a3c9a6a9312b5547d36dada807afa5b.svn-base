package com.partymasses.modules.service;

import java.util.List;

import com.partymasses.modules.dao.mybatis.entity.Blogs;
import com.partymasses.support.message.Message;
public interface BlogService {

	public Boolean insertBlog(String blogtext, int newsid, int parentId);
	
	public Message findAllBlogByNewsId(int newsId);
	/**
	 * 通过新闻id和留言id查询  回复
	 * @param newsId
	 * @param blogId
	 * @return
	 */
	public List<Blogs> findBlogReply(int newsId,int blogId);

}
