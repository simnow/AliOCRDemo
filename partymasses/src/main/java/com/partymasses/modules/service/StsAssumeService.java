package com.partymasses.modules.service;

import com.aliyuncs.auth.sts.AssumeRoleResponse.Credentials;

public interface StsAssumeService {
	public Credentials getSTSOSSAssumeRole();

}
