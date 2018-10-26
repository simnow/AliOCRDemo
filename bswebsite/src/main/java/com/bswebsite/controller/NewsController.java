package com.bswebsite.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bswebsite.modules.dao.mybatis.entity.News;
import com.bswebsite.modules.dao.mybatis.entity.User;
import com.bswebsite.modules.service.NewService;
import com.bswebsite.support.constant.Contants;
import com.bswebsite.support.message.Message;
import com.bswebsite.support.util.DateUtil;
import com.bswebsite.support.util.ImageUploadUtil;

/**
 * 新闻 ，通用
 * 
 * @author GaiNing
 *
 */
@Controller
@RequestMapping(value = "api/news")
public class NewsController {
	@Autowired
	private NewService newService;

	public static final String imgUrlPath="/imgUpload"; 
	/**
	 * 通过新闻类型查询列表并分页
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/type", method = RequestMethod.GET)
	@ResponseBody
	public Message getAllbyType(String newstype,int pageNo,int pageSize,String keyword) {
		Message message=new Message();
		message.setAttributes(newService.findNewListByType(newstype,pageNo,pageSize,keyword));
		//通过新闻type获取数据列表
		return  message;
	}
	
	/**
	 * 通过关键字进行搜索
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/keyword", method = RequestMethod.GET)
	@ResponseBody
	public Message getAllbyType(String keyword,int pageNo,int pageSize) {
		Message message=new Message();
		message.setAttributes(newService.findNewListByKeyword(keyword,pageNo,pageSize));
		//通过新闻type获取数据列表
		return  message;
	}
	
	
	/**
	 * 通过id查询新闻
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Message getNewsById(@PathVariable("id") int newsId, HttpServletRequest req) {
		newService.insertCount(newsId,Contants.ReadType);
		Message message=new Message();
		message.setObj(newService.findNewsById(newsId));
		return message;
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Message getNewsList() {
		Message message=new Message();
		message.setAttributes(newService.findNewList());
		return message;
	}

	/**
	 * 增加新闻，如果是超级管理员需要添加单位id
	 * 
	 * @param news
	 * @param req
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	@RequiresRoles(value = {Contants.ADMIN })
	public Message insertNews(@RequestBody News news, HttpServletRequest req) {
		User userinfo = (User) req.getSession().getAttribute(Contants.LOGINUSER);
		Message message = new Message();
		news.setCreateuser(userinfo.getId());
		news.setCreatetime(DateUtil.getcurrentDateTime());
		// 如果是用户
		if (Contants.ADMIN.equals(userinfo.getRoletype())) {
			int result = newService.insertNews(news);
			if (result > 0) {
				message.setMsg("插入成功");
//				message.setObj(result);
			} else {
				message.setMsg("插入失败");
				message.setSuccess(false);
			}
			return message;
		}
		message.setMsg("你没有权限");
		message.setSuccess(false);
		return message;
	}

	/**
	 * 修改新闻
	 * 
	 * @param news
	 * @param req
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	@RequiresRoles(value = {Contants.ADMIN })
	public Message updateNews(@RequestBody News news, HttpServletRequest req) {
		User userinfo = (User) req.getSession().getAttribute(Contants.LOGINUSER);
		Message message = new Message();
		// 如果是超级管理员，不做任何操作
		if (Contants.ADMIN.equals(userinfo.getRoletype())) {
			return newService.updateNewsById(news);
		} else {
			message.setMsg("你没有权限");
			message.setSuccess(false);
			return message;
		}
		
	}

	/**
	 * 删除新闻
	 * 
	 * @param id
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	@RequiresRoles(value = {Contants.ADMIN })
	public Message deleteNews(@PathVariable("id") int id, HttpServletRequest req) {
		User userinfo = (User) req.getSession().getAttribute(Contants.LOGINUSER);
		Message message = new Message();
		// 如果是超级管理员，不做任何操作
		if (Contants.ADMIN.equals(userinfo.getRoletype())) {
			return newService.deleteNewsById(id, userinfo);
		}else{
			message.setSuccess(false);
			message.setMsg("你没有权限");
			return message;
		}
	}
	
	/**
     * ckeditor图片上传
     * 
     * @Title imageUpload
     * @param request
     * @param response
     */
    @RequestMapping(value="/imageUpload", method = RequestMethod.POST)
    public void imageUpload(HttpServletRequest request, HttpServletResponse response) {
        try {
        	//放到指定文件目录
            ImageUploadUtil.ckeditor(request, response, imgUrlPath);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 粘贴监控
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/imageParseUpload", method = RequestMethod.POST)
    public String parseImgUpload(HttpServletRequest request){
    	String imgUrl=request.getParameter("src");
    	imgUrl = imgUrl.replace("data:image/png;base64,", "");
    	return ImageUploadUtil.uploadImg(imgUrl,imgUrlPath);
    }
}
