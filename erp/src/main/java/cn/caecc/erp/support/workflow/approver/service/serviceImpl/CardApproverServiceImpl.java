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
import cn.caecc.erp.support.workflow.approver.service.CardApproverService;

/**
 * @author weizhen
 *
 */

@Service
public class CardApproverServiceImpl extends BaseApproverService implements CardApproverService {

	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DeptmentService deptMentService;
	
	/**
	 * 
	 */
	public CardApproverServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.service.CardApproverService#getApplyTaskNextApprover(int)
	 */
	@Override
	public List<User> getApplyTaskNextApprover(int startUserId) {
		List<User> userList = userService.findDepartmentLeader(startUserId);
		return super.preProcessUsers(userList);
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.service.CardApproverService#getDepartmentalLeaderTaskNextApprover(int)
	 */
	@Override
	public List<User> getDepartmentalLeaderTaskNextApprover() {
		// TODO Auto-generated method stub
		List<User> userList = deptMentService.getDepartmentUsers(Contants.HUMANRESOURCE);
	    return super.preProcessUsers(userList);
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.service.CardApproverService#getHumanResourcesTaskNextApprover()
	 */
	@Override
	public List<User> getHumanResourcesTaskNextApprover() {
		// TODO Auto-generated method stub
		List<User> userList = deptMentService.getDepartmentUsers(Contants.GENERALMENERALMANAGEROFFICE);

		return super.preProcessUsers(userList);
	}

}
