package com.partymasses.modules.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.auth.sts.AssumeRoleRequest;
import com.aliyuncs.auth.sts.AssumeRoleResponse;
import com.aliyuncs.auth.sts.AssumeRoleResponse.Credentials;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.partymasses.modules.service.StsAssumeService;
import com.partymasses.support.constant.Contants;

public class StsAssumeServiceImpl implements StsAssumeService {
	private final static Logger logger = LoggerFactory.getLogger(StsAssumeServiceImpl.class);

	 private  AssumeRoleResponse assumeRole(String accessKeyId, String accessKeySecret,
             String roleArn, String roleSessionName, String policy,
             ProtocolType protocolType) throws ClientException, ServerException, com.aliyuncs.exceptions.ClientException
	 {
		 
		 IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);	

		 DefaultAcsClient client = new DefaultAcsClient(profile);
		// 创建一个 AssumeRoleRequest 并设置请求参数
		 final AssumeRoleRequest request = new AssumeRoleRequest();
		 request.setVersion("2015-04-01");
		 request.setMethod(MethodType.POST);
		 request.setProtocol(protocolType);
		
		 request.setRoleArn(roleArn);
		 request.setRoleSessionName(roleSessionName);
		 request.setPolicy(policy);
		
		 // 发起请求，并得到response
		 final AssumeRoleResponse response = (AssumeRoleResponse) client.getAcsResponse(request);
		
		 return response;		 

	 }
	
	@Override
	public Credentials getSTSOSSAssumeRole()
	{
	    String policy = null;
	    ProtocolType protocolType = ProtocolType.HTTPS;
	    Credentials credentials = null;
	    try
	    {
		    AssumeRoleResponse response = assumeRole(Contants.STSACCESSKEYID, 
		    		Contants.STSACCESSKEYSECRET, 
		    		Contants.RAMOSSWRITEROLEARN,	    		
		    		Contants.RAMOSSWRITEROLESESSIONNAME,
		    		policy,
		    		protocolType);
		    
		    credentials =  response.getCredentials();
		    /*
		    String accesskeyid = credentials.getAccessKeyId();
		    String accesskeysecret = credentials.getAccessKeySecret();//这个就是我们想要的安全token　String securitytoken = response.getCredentials().getSecurityToken();
		    System.out.print(accesskeyid);
		    System.out.print(accesskeysecret);
		    */
	    }
	    catch (Exception ex)
	    {
	    	logger.info(ex.getMessage());
	    	ex.printStackTrace();
	    }
	    return credentials;

	}
}
