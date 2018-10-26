package cn.caecc.erp.support.statistics.service;

import java.util.List;
import java.util.Map;
import cn.caecc.erp.modules.dao.mybatis.entity.LoginLog;
import cn.caecc.erp.support.statistics.entity.OnlineDepartmentUserEntity;

/**
 * @author weizhen
 */
public interface StatisticsService {
	/*
	 * 记录一个登录用户
	 */
	public void addLoginUser(Object session);
	/**
	 * 得到所有的登录用户
	 */
	public Map<String, Map<String, LoginLog>> updateAndGetLoginUsers();
	
	public List<OnlineDepartmentUserEntity> updateAndGetOnlineDepartmentUserList();

	public int getSimilarOnlineUserCount();
	/**
	 * 移除用户
	 * @param user
	 */
	public void removeLoginUser(Object session);


}
