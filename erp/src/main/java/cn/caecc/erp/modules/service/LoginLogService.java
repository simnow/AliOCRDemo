/**
 * 
 */
package cn.caecc.erp.modules.service;

import java.util.List;
import cn.caecc.erp.modules.dao.mybatis.entity.LoginLog;

/**
 * @author weizhen
 *
 */
public interface LoginLogService {

	/**
	 * 通过用户id查询该用户的登录信息
	 */
	public List<LoginLog> findByUserId(int userId);
	
	/**
	 * 增加一个用户的登录信息
	 * @param loginLog
	 */
	public int addLoginLog(LoginLog loginLog);

	/**
	 * 登出时修改登出日志
	 * @param loginLog
	 */
	public int updateLogoutLog(int id);

}
