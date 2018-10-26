/**
 * 
 */
package cn.caecc.erp.modules.service.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.caecc.erp.modules.dao.mybatis.entity.LoginLog;
import cn.caecc.erp.modules.dao.mybatis.entity.LoginLogExample;
import cn.caecc.erp.modules.dao.mybatis.entity.LoginLogExample.Criteria;
import cn.caecc.erp.modules.dao.mybatis.mapper.LoginLogMapper;
import cn.caecc.erp.modules.service.LoginLogService;
import cn.caecc.erp.support.util.DateUtil;

/**
 * @author weizhen
 *
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {
	@Autowired
	private LoginLogMapper loginLogMapper;
	
	/**
	 * 
	 */
	public LoginLogServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.modules.service.LoginLogService#findByUserId(int)
	 */
	@Override
	public List<LoginLog> findByUserId(int userId) {
		// TODO Auto-generated method stub
		LoginLogExample example = new LoginLogExample();
		Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(userId);
		List<LoginLog> loginLogList = loginLogMapper.selectByExample(example);
		return loginLogList;
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.modules.service.LoginLogService#addLoginLog(cn.caecc.erp.modules.dao.mybatis.entity.LoginLog)
	 */
	@Override
	public int addLoginLog(LoginLog loginLog) {
		// TODO Auto-generated method stub
		return loginLogMapper.insert(loginLog);
		

	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.modules.service.LoginLogService#addLogoutLog(cn.caecc.erp.modules.dao.mybatis.entity.LoginLog)
	 */
	@Override
	public int updateLogoutLog(int id) {
		// TODO Auto-generated method stub
		LoginLog loginLog = new LoginLog();
		loginLog.setLogouttime(DateUtil.getcurrentDateTime());
		LoginLogExample example = new LoginLogExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		return loginLogMapper.updateByExampleSelective(loginLog, example);
	}

}
