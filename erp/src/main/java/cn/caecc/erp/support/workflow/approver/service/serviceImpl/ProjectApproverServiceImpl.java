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
import cn.caecc.erp.support.workflow.approver.service.ProjectApproverService;
import cn.caecc.erp.support.workflow.service.WorkflowService;

/**
 * @author weizhen
 *
 */
@Service
public class ProjectApproverServiceImpl extends BaseApproverService implements ProjectApproverService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private DeptmentService deptMentService;
	
	@Autowired
	private WorkflowService workflowService;
	/**
	 * 
	 */
	public ProjectApproverServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.service.ProjectApproverService#getApplyTaskNextApprover(int)
	 */
	@Override
	public List<User> getApplyTaskNextApprover(int startUserId) {
		// TODO Auto-generated method stub
		List<User> userList =  userService.findManagerLeader(startUserId);
		return super.preProcessUsers(userList);
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.service.ProjectApproverService#getManagerTaskNextApprover()
	 */
	@Override
	public List<User> getManagerTaskNextApprover() {
		// TODO Auto-generated method stub
		List<User> userList = deptMentService.getDepartmentMasters(Contants.MANAGEMENTANDPROCUREMENT);
		return super.preProcessUsers(userList);
	}


	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.service.ProjectApproverService#getManagementAndProcurementTaskNextApprover()
	 */
	@Override
	public List<User> getManagementAndProcurementTaskNextApprover() {
		// TODO Auto-generated method stub
		List<User> userList = deptMentService.getDepartmentMasters(Contants.SECURITIESAFFAIRS);
		return super.preProcessUsers(userList);
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.service.ProjectApproverService#getSecuritiesAffairsTaskNextApprover()
	 */
	@Override
	public List<User> getSecuritiesAffairsTaskNextApprover() {
		// TODO Auto-generated method stub
		List<User> userList = deptMentService.getDepartmentMasters(Contants.FINANCIALMANAGEMENT);
		return super.preProcessUsers(userList);
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.service.ProjectApproverService#getFinancialManagementTaskNextApprover()
	 */
	@Override
	public List<User> getFinancialManagementTaskNextApprover() {
		// TODO Auto-generated method stub
		List<User> userList = deptMentService.getDepartmentMasters(Contants.FINANCIALOFFICER);
		return super.preProcessUsers(userList);
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.service.ProjectApproverService#getFinancialOfficerTaskNextApprover()
	 */
	@Override
	public List<User> getFinancialOfficerTaskNextApprover(String processInstanceId) {
		// TODO Auto-generated method stub		
		List<User> userList = null;
		String strStartUserId = workflowService.queryHistoryTaskOfRuntimeInstanceAssigneeId(processInstanceId,
				"ApplyTask");
		if (strStartUserId != null) {
			userList = userService.findAssignedManagerLeader(Integer.parseInt(strStartUserId));
		}
		return super.preProcessUsers(userList);
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.service.ProjectApproverService#getAssignedManagerTaskNextApprover()
	 */
	@Override
	public List<User> getAssignedManagerTaskNextApprover() {
		// TODO Auto-generated method stub
		List<User> userList = deptMentService.getDepartmentUsers(Contants.GENERALMENERALMANAGER);
		return super.preProcessUsers(userList);
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.service.ProjectApproverService#getGeneralManagerTaskNextApprover()
	 */
	@Override
	public List<User> getGeneralManagerTaskNextApprover() {
		// TODO Auto-generated method stub
		List<User> userList = deptMentService.getDepartmentUsers(Contants.CHAIRMAN);
		return super.preProcessUsers(userList);
	}


}
