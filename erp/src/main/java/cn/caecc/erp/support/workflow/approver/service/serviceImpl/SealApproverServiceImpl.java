/**
 * 
 */
package cn.caecc.erp.support.workflow.approver.service.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.caecc.erp.modules.dao.mybatis.entity.User;
import cn.caecc.erp.modules.service.DeptmentService;
import cn.caecc.erp.modules.service.UserService;
import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.workflow.approver.service.SealApproverService;

/**
 * @author weizhen
 *
 */
@Service
public class SealApproverServiceImpl extends BaseApproverService implements SealApproverService {

	/**
	 * 
	 */
	@Autowired
	private UserService userService;
	
	@Autowired
	private DeptmentService deptMentService;
	public SealApproverServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.approver.service.SealApproverService#getApplyTaskNextApprover(int)
	 */
	@Override
	public List<User> getApplyTaskNextApprover(int startUserId) {
		// TODO Auto-generated method stub
		List<User> userList = userService.findDepartmentLeader(startUserId);
		return super.preProcessUsers(userList);
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.approver.service.SealApproverService#getDepartmentalLeaderTaskNextApprover(int)
	 */
	@Override
	public List<User> getDepartmentalLeaderTaskNextApprover() {
		// TODO Auto-generated method stub
		List<User> userList = deptMentService.getDepartmentMasters(Contants.SECURITIESAFFAIRS);
		return super.preProcessUsers(userList);
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.approver.service.SealApproverService#getSecuritiesAffairsTaskNextApprover(int)
	 */
	@Override
	public List<User> getSecuritiesAffairsTaskNextApprover() {
		// TODO Auto-generated method stub
		List<User> userList = deptMentService.getDepartmentUsers(Contants.GENERALMENERALMANAGER);
		return super.preProcessUsers(userList);
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.approver.service.SealApproverService#getGeneralManagerTaskNextApprover(int)
	 */
	@Override
	public List<User> getGeneralManagerTaskNextApprover() {
		// TODO Auto-generated method stub
		List<User> userList = deptMentService.getDepartmentUsers(Contants.GENERALMENERALMANAGEROFFICE);
		return super.preProcessUsers(userList);
	}

}
