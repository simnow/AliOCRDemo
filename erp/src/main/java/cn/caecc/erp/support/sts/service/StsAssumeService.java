package cn.caecc.erp.support.sts.service;

import com.aliyuncs.auth.sts.AssumeRoleResponse.Credentials;

public interface StsAssumeService {
	/**
	 * 获取oss临时授权
	 * @return
	 */
	public Credentials getOSSCredentials();

}
