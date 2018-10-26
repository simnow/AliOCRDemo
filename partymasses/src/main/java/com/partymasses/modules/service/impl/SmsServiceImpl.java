package com.partymasses.modules.service.impl;



import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.partymasses.modules.service.SmsService;
import com.partymasses.support.constant.Contants;
import com.partymasses.support.message.Message;
import com.partymasses.support.util.DateUtil;
import com.partymasses.support.util.JacksonUtil;
@Service
public class SmsServiceImpl implements SmsService{
	@Autowired  
	private HttpSession session;  
	@Override
	public Message sendLoginSms(String phonenumber) throws Exception {
		// TODO Auto-generated method stub
		return sendSms(phonenumber,Contants.LOGINMODEL,Contants.LOGINSMS);
	}
	@Override
	public Message sendUpdateSms(String phonenumber) throws Exception {
		// TODO Auto-generated method stub
		return sendSms(phonenumber,Contants.UPDATEMODEL,Contants.UPDATESMS);
	}
	@Override
	public Message checkLoginSms(String phonenumber, String code) {
		// TODO Auto-generated method stub
		return checkSms(phonenumber, code, Contants.LOGINSMS);
	}
	@Override
	public Message checkUpdateSms(String phonenumber, String code) {
		// TODO Auto-generated method stub
		return checkSms(phonenumber, code, Contants.UPDATESMS);
	}
	public Message checkSms(String phonenumber,String code, String sendsign) {
		// TODO Auto-generated method stub
		if(code==null||"".equals(code)){
				return new Message(false,"请填写验证码");	
		}
		String storecode=(String) session.getAttribute(phonenumber+sendsign);
		if(storecode==null||"".equals(storecode)){
				return new Message(false,"请您先获取验证码");	
			}
		String[] strarr=storecode.split(",");
		Long sendtime=Long.parseLong(strarr[1]);
		Long endtime=DateUtil.getcurrentDateTime().getTime();
		Long diff=endtime-sendtime;
		if(diff/(1000*60)>=15){
			return new Message(false,"验证码超时，请重新获取");
		}
		if(!code.equals(strarr[0])){
			return new Message(false,"验证码不正确");
		}
		return  new Message(true,"验证成功");
	}
	//发送验证信息
	public Message sendSms(String phonenumber,String modeltype,String sendsign) throws Exception {
		// TODO Auto-generated method stub
		 //检测验证码是否超时
			String str=(String) session.getAttribute(phonenumber+sendsign);
				if(str!=null&&!"".equals(str)){
					String[] strarr=str.split(",");
					Long sendtime=Long.parseLong(strarr[1]);
					Long endtime=DateUtil.getcurrentDateTime().getTime();
					Long diff=endtime-sendtime;
				if((diff/(1000)<=60)){
					return new Message(false,"请稍后重新获取验证码");	
				}
			}
			//生成短信编码
			String code=makeCode();
			Map<String, String> map=new HashMap<String,String>();
			map.put("code",code);
			String mess=JacksonUtil.jsonNode2String(map);
			//发送短信
			System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
	        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
	        //初始化acsClient,暂不支持region化
	        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", Contants.SMSACCESSKEYID, Contants.SMSACCESSKEYSECRET);
	        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", Contants.PRODUCT, Contants.DOMAIN);
	        IAcsClient acsClient = new DefaultAcsClient(profile);

	        //组装请求对象-具体描述见控制台-文档部分内容
	        SendSmsRequest request = new SendSmsRequest();
	        //必填:待发送手机号
	        request.setPhoneNumbers(phonenumber);
	        //必填:短信签名-可在短信控制台中找到
	        request.setSignName(Contants.SMSSIGNNAME);
	        //必填:短信模板-可在短信控制台中找到
	        request.setTemplateCode(modeltype);
	        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
	        request.setTemplateParam(mess);
	        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
	        //获取session判断是否超时
	        if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
	        	//发送存入session
				session.setAttribute(phonenumber+sendsign,code+","+DateUtil.getcurrentDateTime().getTime());
				return new Message(true,"验证码已发送");
	        }	
	        else
	        return  new Message(false,"验证码发送失败");

	        
	}
	/**
	 * 生成验证码
	 * @param id
	 * @return
	 */
	public static String  makeCode(){
			Random  random=new Random();
			StringBuffer code=new StringBuffer("");
			for (int i = 0; i <6; i++) {
				 code.append(random.nextInt(10));	
			}
			return code.toString();
	
	}
}
