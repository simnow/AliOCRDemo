package com.partymasses.controller;

import com.partymasses.modules.dao.mybatis.entity.User;
import com.partymasses.modules.service.SmsService;
import com.partymasses.modules.service.UserService;
import com.partymasses.support.message.Message;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 17/6/7.
 * 短信API产品的DEMO程序,工程中包含了一个SmsDemo类，直接通过
 * 执行main函数即可体验短信产品API功能(只需要将AK替换成开通了云通信-短信产品功能的AK即可)
 * 工程依赖了2个jar包(存放在工程的libs目录下)
 * 1:aliyun-java-sdk-core.jar
 * 2:aliyun-java-sdk-dysmsapi.jar
 *
 * 备注:Demo工程编码采用UTF-8
 * 国际短信发送请勿参照此DEMO
 */
@Controller
@RequestMapping(value="api")
public class SmsController {
    @Autowired
    private UserService userservice;
    @Autowired 
    private SmsService smsService;
	/** 
	 * 短信验证发送
	 * @param string
	 * @return Message
	 */
	@RequestMapping(value="/sms/logininfo/{number}",method=RequestMethod.GET)
	@ResponseBody
	public Message userLoginMessage(@PathVariable("number") String phonenumber) {
				//判断手机号是否已注册
				if(phonenumber==null||"".equals(phonenumber)){		
					return  new Message(false,"请填写手机号");
				}
				User user=userservice.getUserByNumber(phonenumber);
				if(user==null){
					return  new Message(false,"该手机号未进行注册");		
				}
				try {
					return smsService.sendLoginSms(phonenumber);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					return new Message(false,"短信发送失败");
				}
	} 
	@RequestMapping(value="/sms/updateinfo/{number}",method=RequestMethod.GET)
	@ResponseBody
	public Message userUpdateMessage(@PathVariable("number") String phonenumber) {
				//判断手机号是否已注册
				if(phonenumber==null||"".equals(phonenumber)){		
					return  new Message(false,"请填写手机号");
				}
				User user=userservice.getUserByNumber(phonenumber);
				if(user==null){
					return  new Message(false,"该手机号未进行注册");		
				}
				try {
					return smsService.sendUpdateSms(phonenumber);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					return new Message(false,"短信发送失败");
				}
	} 
    
}
