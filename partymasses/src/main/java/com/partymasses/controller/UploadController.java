package com.partymasses.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.partymasses.modules.dao.mybatis.entity.User;
import com.partymasses.modules.service.UserService;
import com.partymasses.support.constant.Contants;
import com.partymasses.support.message.Message;
import com.partymasses.support.util.FileParsingUtil;

@Controller
@RequestMapping(value = "/api/excel")
public class UploadController {

	@Autowired
	private UserService userService;
	
	private final static Logger logger = LoggerFactory.getLogger(UploadController.class); 

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	@RequiresRoles(value = {Contants.SUPERADMIN,Contants.ADMIN},logical=Logical.OR)
	public Message Upload(MultipartFile file, HttpServletRequest request) throws IllegalStateException, IOException {
		Message message = new Message();
		String path = request.getServletContext().getRealPath("/upload");
		String fileName = file.getOriginalFilename();
		File dir = new File(path, fileName);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		file.transferTo(dir);
		User user = (User) request.getSession().getAttribute(Contants.LOGINUSER);
		if (user != null) {
			List<User> list = null;
			try {
				list = FileParsingUtil.readExcel(dir, user);
			} catch (Exception e) {
				message.setSuccess(false);
				message.setMsg(e.getMessage());
				return message;
			}
			if (list == null) {
				message.setSuccess(false);
				message.setMsg("Excel表格为空");
				dir.delete();
				return message;
			} else {
				dir.delete();
				try {
					message = userService.insertUserList(list);
				} catch (Exception e) {
					logger.info(e.getMessage());
					message.setSuccess(false);
					message.setMsg(e.getCause().getMessage());
					return message;
				}
				return message;
			}

		} else {
			message.setSuccess(false);
			message.setMsg("您还未登录！");
			dir.delete();
			return message;
		}
	}
}
