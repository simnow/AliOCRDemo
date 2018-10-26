package com.bswebsite.controller;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bswebsite.modules.service.UserService;
import com.bswebsite.support.message.Message;
import com.bswebsite.support.util.AesUtil;
@Controller
@RequestMapping(value = "/api/user")
public class UserController {

	private final static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping(value = "/login")
	public Message login(String mobile, String password) {
		
		Message message = new Message();
		logger.info("登陆用户输入，用户手机号【{}】,密码【{}】",mobile,password);
		if (StringUtils.isBlank(password) || StringUtils.isBlank(mobile)) {
			message.setSuccess(false);
			logger.info("缺少参数pw【{}】，mobile【{}】",password,mobile);
			message.setMsg("缺少参数");
			return message;
		}
		password = AesUtil.encrypt(password);
		return message = userService.userLogin(mobile, password);
	}

}
