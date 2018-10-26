package cn.caecc.erp.support.sms.service.serviceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Service;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import cn.caecc.erp.support.sms.entity.SmsCodeEntity;
import cn.caecc.erp.support.sms.service.SmsService;
import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.exception.CommonException;
import cn.caecc.erp.support.util.DateUtil;
import cn.caecc.erp.support.util.JacksonUtil;

@Service
@PropertySource(Contants.SMS_CONFIG)
public class SmsServiceImpl implements SmsService {
	@Autowired
	private HttpSession session;

	
	
	@Value("${sms.product}")
	private String product;

	@Value("${sms.domain}")
	private String domain;

	@Value("${sms.accesskeyId}")
	private String accesskeyId;

	@Value("${sms.accesskeySecret}")
	private String accesskeySecret;
	
	@Value("${sms.signName}")
	public String signName;

	@Value("${sms.sessionKey}")
	public String sessionKey;
	
	@Value("${sms.expireTimeMinute}")
	public long expireTimeMinute;
	
	@Value("${sms.intervalTimeMinute}")
	public long intervalTimeMinute;
	
	@Value("${sms.rejectModel}")
	public String rejectModel;
	
	@Value("${sms.approveModel}")
	public String approveModel;
	
	
	@Value("${sms.loginSign}")
	public String loginSign;
	
	@Value("${sms.loginModel}")
	public String loginModel;
	
	
	@Value("${sms.updatePasswordSign}")
	public String updatePasswordSign;
	
	@Value("${sms.updatePasswordModel}")
	public String updatePasswordModel;
	
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer() {
		PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		propertySourcesPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
		return propertySourcesPlaceholderConfigurer;
	}
	
	
	// 发送验证信息
	private boolean sendSms(String phonenumber, String modeltype, Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub

		String mess = JacksonUtil.jsonNode2String(map);
		// 发送短信
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		// 初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", this.accesskeyId,
				this.accesskeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", this.product, this.domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);

		// 组装请求对象-具体描述见控制台-文档部分内容
		SendSmsRequest request = new SendSmsRequest();
		// 必填:待发送手机号
		request.setPhoneNumbers(phonenumber);
		// 必填:短信签名-可在短信控制台中找到
		request.setSignName(this.signName);
		// 必填:短信模板-可在短信控制台中找到
		request.setTemplateCode(modeltype);
		// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		request.setTemplateParam(mess);
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
		// 获取session判断是否超时
		if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
			return true;
		} else {
			return false;
		}

	}

	private boolean sendLonginOrModifyPasswordSms(String phonenumber, String modeltype, String sendsign)
			throws Exception {

		@SuppressWarnings("unchecked")
		Map<String, Map<String, SmsCodeEntity>> phoneCodeMap = (Map<String, Map<String, SmsCodeEntity>>) session
				.getAttribute(this.sessionKey);
		if (phoneCodeMap == null) {
			phoneCodeMap = new HashMap<String, Map<String, SmsCodeEntity>>();
		}
		if (!phoneCodeMap.containsKey(phonenumber)) {
			phoneCodeMap.put(phonenumber, new HashMap<String, SmsCodeEntity>());
		}
		Map<String, SmsCodeEntity> phoneItemCodeMap = phoneCodeMap.get(phonenumber);
		if (phoneItemCodeMap.containsKey(sendsign)) {
			SmsCodeEntity smsCodeEntity = phoneItemCodeMap.get(sendsign);
			Long sendTime = smsCodeEntity.getTime();
			Long currentTime = DateUtil.getcurrentDateTime().getTime();
			Long diff = currentTime - sendTime;
			if ((diff / (1000) < 60 * this.intervalTimeMinute)) {
				throw new CommonException("请求太频繁，请稍后再试");
			}
		}
		SmsCodeEntity smsCodeEntity = new SmsCodeEntity();
		String code = makeCode();
		Map<String, String> map = new HashMap<String, String>();
		map.put("code", code);
		boolean result = sendSms(phonenumber, modeltype, map);
		if (result) {
			smsCodeEntity.setCode(code);
			Long currentTime = DateUtil.getcurrentDateTime().getTime();
			smsCodeEntity.setTime(currentTime);
			phoneItemCodeMap.put(sendsign, smsCodeEntity);
			session.setAttribute(this.sessionKey, phoneCodeMap);
		} else {
			throw new CommonException("验证码发送失败");
		}
		return true;
	}

	@Override
	public boolean checkLoginSms(String phonenumber, String code) throws Exception {
		// TODO Auto-generated method stub
		return checkSms(phonenumber, code, this.loginSign);
	}

	@Override
	public boolean checkModifyPasswordSms(String phonenumber, String code) throws Exception {
		// TODO Auto-generated method stub
		return checkSms(phonenumber, code, this.updatePasswordSign);
	}

	private boolean checkSms(String phonenumber, String code, String sendsign) throws Exception {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		/**
		 * String：手机号
		 * String： login or update
		 */
		Map<String, Map<String, SmsCodeEntity>> phoneCodeMap = (Map<String, Map<String, SmsCodeEntity>>) session
				.getAttribute(this.sessionKey);
		if (phoneCodeMap != null) {
			Map<String, SmsCodeEntity> phoneItemCodeMap = phoneCodeMap.get(phonenumber);
			if (phoneItemCodeMap != null) {
				SmsCodeEntity smsCodeEntity = phoneItemCodeMap.get(sendsign);
				if (smsCodeEntity != null) {
					String sendCode = smsCodeEntity.getCode();
					if (sendCode.equals(code)) {
						Long sendTime = smsCodeEntity.getTime();
						Long currentTime = DateUtil.getcurrentDateTime().getTime();
						Long diff = currentTime - sendTime;
						if (diff / (1000 * 60) >= this.expireTimeMinute) {
							throw new CommonException("验证码超时，请重新获取");
						} else {
							return true;
						}
					} else {
						throw new CommonException("验证码错误");
					}
				} else {
					throw new CommonException("请先获取登录验证码");
				}
			} else {
				throw new CommonException("请先使用手机" + phonenumber + "获取验证码");
			}
		} else {
			throw new CommonException("请先获取验证码");
		}
	}

	@Override
	public boolean sendLoginSms(String phonenumber) throws Exception {
		// TODO Auto-generated method stub

		return sendLonginOrModifyPasswordSms(phonenumber, this.loginModel, this.loginSign);

	}

	@Override
	public boolean sendModifyPasswordSms(String phonenumber) throws Exception {
		return sendLonginOrModifyPasswordSms(phonenumber, this.updatePasswordModel, this.updatePasswordSign);

	}

	@Override
	public boolean sendWorkflowRejectSms(String phonenumber, String name, String processDefinitionName, String who,
			String result) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("processname", processDefinitionName);
		map.put("who", who);
		map.put("name", name);
		map.put("result", result);
		return sendSms(phonenumber, this.rejectModel, map);
	}

	@Override
	public boolean sendWorkflowApproveSms(String phonenumber, String name, String processDefinitionName)
			throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("processname", processDefinitionName);
		map.put("name", name);
		return sendSms(phonenumber, this.approveModel, map);
	}

	/**
	 * 生成验证码
	 * 
	 * @param id
	 * @return
	 */
	public static String makeCode() {
		Random random = new Random();
		StringBuffer code = new StringBuffer("");
		for (int i = 0; i < 6; i++) {
			code.append(random.nextInt(10));
		}
		return code.toString();
	}
}
