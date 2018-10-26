package cn.caecc.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyuncs.auth.sts.AssumeRoleResponse.Credentials;

import cn.caecc.erp.support.message.Message;
import cn.caecc.erp.support.sts.service.StsAssumeService;

@Controller
@RequestMapping("/api/sts-assume")
public class StsAssumeController {
	
	@Autowired
	private StsAssumeService stsAssumeService;
	
	@ResponseBody
	@RequestMapping(value = "/oss/credentials", method = RequestMethod.GET)
	public Message getOSSCredentials() 
	{
		Message message = new Message();
		message.setSuccess(true);
		Credentials credentials = stsAssumeService.getOSSCredentials();
		message.setObj(credentials);
		return message;
	}

}