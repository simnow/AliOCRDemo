/**
 * 
 */
package cn.caecc.erp.support.workflow.approver.service.serviceImpl;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.caecc.erp.modules.dao.mybatis.entity.User;
import cn.caecc.erp.modules.service.DeptmentService;
import cn.caecc.erp.modules.service.UserService;
import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.workflow.approver.service.ContractApproverService;
import cn.caecc.erp.support.workflow.service.WorkflowService;

/**
 * @author weizhen
 *
 */
@Service
public class ContractApproverServiceImpl extends BaseApproverService implements ContractApproverService {

	/**
	 * 
	 */
	@Autowired
	private UserService userService;
	
	@Autowired
	private DeptmentService deptMentService;
	
	@Autowired
	private WorkflowService workflowService;
	
	@Autowired
	private HttpSession session;
	
	public ContractApproverServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.approver.service.ContractApproverService#getApplyTaskNextApprover(int)
	 */
	@Override
	public List<User> getApplyTaskNextApprover() {
		// TODO Auto-generated method stub
		List<User> userList = userService.getList();
		return super.preProcessUsers(userList);
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.approver.service.ContractApproverService#getBusinessDepartmentTaskNextApprover()
	 */
	@Override
	public List<User> getBusinessDepartmentTaskNextApprover(String processInstanceId) {
		// TODO Auto-generated method stub
		List<User> userList = null;
		String strStartUserId = workflowService.queryHistoryTaskOfRuntimeInstanceAssigneeId(processInstanceId,
				"ApplyTask");
		if (strStartUserId != null) {
			userList = userService.findManagerLeader(Integer.parseInt(strStartUserId));
		}
		return super.preProcessUsers(userList);
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.approver.service.ContractApproverService#getManagerTaskNextApprover()
	 */
	@Override
	public List<User> getManagerTaskNextApprover() {
		// TODO Auto-generated method stub
		
		List<User> userList = deptMentService.getDepartmentMasters(Contants.SECURITIESAFFAIRS);
		return super.preProcessUsers(userList);
	}
	
	
	@Override
	public List<User> getSecuritiesAffairsTaskNextApprover() {
		// TODO Auto-generated method stub
		List<User> userList = deptMentService.getDepartmentMasters(Contants.FINANCIALMANAGEMENT);
		return super.preProcessUsers(userList);
	}
	

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.approver.service.ContractApproverService#getFinancialManagementTaskNextApprover()
	 */
	@Override
	public List<User> getFinancialManagementTaskNextApprover() {
		// TODO Auto-generated method stub
		List<User> userList = deptMentService.getDepartmentUsers(Contants.FINANCIALOFFICER);
		return super.preProcessUsers(userList);
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.approver.service.ContractApproverService#getFinancialOfficerTaskNextApprover()
	 */
	@Override
	public List<User> getFinancialOfficerTaskNextApprover(String processInstanceId) {
		// TODO Auto-generated method stub
		List<User> userList = null;
		String strStartUserId = workflowService.queryHistoryTaskOfRuntimeInstanceAssigneeId(processInstanceId,
				"BusinessDepartmentTask");
		if (strStartUserId != null) {
			userList = userService.findAssignedManagerLeader(Integer.parseInt(strStartUserId));
		}
		return super.preProcessUsers(userList);
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.approver.service.ContractApproverService#getAssignedManagerEvaluateTaskNextApprover()
	 */
	@Override
	public List<User> getAssignedManagerTaskNextApprover() {
		// TODO Auto-generated method stub
		List<User> userList = deptMentService.getDepartmentUsers(Contants.GENERALMENERALMANAGER);
		return super.preProcessUsers(userList);
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.approver.service.ContractApproverService#getGeneralManagerTaskNextApprover()
	 */
	@Override
	public List<User> getGeneralManagerTaskNextApprover(int isChairman) {
		// TODO Auto-generated method stub
		List<User> userList = null;
		if (isChairman == 0) { //经营采办部
			userList = deptMentService.getDepartmentUsers(Contants.MANAGEMENTANDPROCUREMENT);
		}
		else {
			userList = deptMentService.getDepartmentUsers(Contants.CHAIRMAN);

		}
		return super.preProcessUsers(userList);
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.approver.service.ContractApproverService#getChairmanTaskNextApprover()
	 */
	@Override
	public List<User> getChairmanTaskNextApprover() {
		// TODO Auto-generated method stub
		List<User> userList = deptMentService.getDepartmentUsers(Contants.MANAGEMENTANDPROCUREMENT);
		return super.preProcessUsers(userList);
	}


}
