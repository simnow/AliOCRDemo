package com.bswebsite.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresRoles;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.aliyuncs.auth.sts.AssumeRoleResponse.Credentials;
//import com.bswebsite.modules.service.StsAssumeService;
import com.bswebsite.support.constant.Contants;
import com.bswebsite.support.message.Message;

@Controller
@RequestMapping(value="api/sts")
public class OssStsAssumeController {

//	@Autowired
//	private StsAssumeService stsAssumeService;
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	@RequiresRoles(value = {Contants.ADMIN })
	public Message getCredentials(){
		//return stsAssumeService.getSTSOSSAssumeRole();
		Map<String, Object> map=new HashMap<>();
		map.put("OSSACCESSKEYID", Contants.OSSACCESSKEYID);
		map.put("OSSACCESSKEYSECRET", Contants.OSSACCESSKEYSECRET);
		map.put("OSSBUCKET", Contants.OSSBUCKET);
		Message message=new Message();
		message.setAttributes(map);
		return message;
		
	}
}
