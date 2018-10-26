/**
 * 
 */
package cn.caecc.erp.support.workflow.approver.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.caecc.erp.modules.dao.mybatis.entity.User;
import cn.caecc.erp.modules.service.DeptmentService;
import cn.caecc.erp.modules.service.UserService;
import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.workflow.approver.service.EvaluateApproverService;
import cn.caecc.erp.support.workflow.service.WorkflowService;

/**
 * @author weizhen
 *
 */
@Service
public class EvaluateApproverServiceImpl extends BaseApproverService implements EvaluateApproverService {

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
	public EvaluateApproverServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.approver.service.EvaluateApproverService#getApplyTaskNextApprover()
	 */
	@Override
	public List<User> getApplyTaskNextApprover() {
		// TODO Auto-generated method stub
		List<User> userList = userService.getList();
		return super.preProcessUsers(userList);
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.approver.service.EvaluateApproverService#getBusinessDepartmentEvaluateTaskNextApprover()
	 */
	@Override
	public List<User> getBusinessDepartmentEvaluateTaskNextApprover() {
		// TODO Auto-generated method stub
		Integer userId = (Integer)session.getAttribute(Contants.LOGINUSERID);
		List<User> userList = userService.findManagerLeader(userId);
		return super.preProcessUsers(userList);
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.approver.service.EvaluateApproverService#getManagerEvaluateTaskNextApprover(java.lang.String)
	 */
	@Override
	public List<User> getManagerEvaluateTaskNextApprover(String processInstanceId) {
		// TODO Auto-generated method stub
		List<User> userList = null;
		String strStartUserId = workflowService.queryHistoryTaskOfRuntimeInstanceAssigneeId(processInstanceId,
				"ApplyTask");
		if (strStartUserId != null) {
			userList = new ArrayList<User>();
			User user = userService.findDtoById(Integer.parseInt(strStartUserId));
			userList.add(user);
		}
		return super.preProcessUsers(userList);
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.approver.service.EvaluateApproverService#getManagementAndProcurementEvaluateTaskNextApprover()
	 */
	@Override
	public List<User> getManagementAndProcurementEvaluateTaskNextApprover(String processInstanceId) {
		// TODO Auto-generated method stub
		List<User> userList = null;
		String strUserId = workflowService.queryHistoryTaskOfRuntimeInstanceAssigneeId(processInstanceId,
				"ManagementAndProcurementEvaluateTask");
		if (strUserId != null) {
			userList = userService.findAssignedManagerLeader(Integer.parseInt(strUserId));
		}
		return super.preProcessUsers(userList);
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.approver.service.EvaluateApproverService#getAssignedManagerEvaluateTaskNextApprover()
	 */
	@Override
	public List<User> getAssignedManagerEvaluateTaskNextApprover() {
		// TODO Auto-generated method stub
		List<User> userList = deptMentService.getDepartmentUsers(Contants.GENERALMENERALMANAGER);
		return super.preProcessUsers(userList);
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.approver.service.EvaluateApproverService#getGeneralManagerEvaluateTaskNextApprover()
	 */
	@Override
	public List<User> getGeneralManagerEvaluateTaskNextApprover() {
		// TODO Auto-generated method stub
		List<User> userList = deptMentService.getDepartmentUsers(Contants.INVITETENDERSCOMMITTEE);
		return super.preProcessUsers(userList);
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.approver.service.EvaluateApproverService#getInviteTendersCommitteeLeaderEvaluateTaskNextApprover(java.lang.String)
	 */
	@Override
	public List<User> getInviteTendersCommitteeLeaderEvaluateTaskNextApprover(String processInstanceId) {
		// TODO Auto-generated method stub
		List<User> userList = new ArrayList<User>();
		String strStartUserId = workflowService.queryHistoryTaskOfRuntimeInstanceAssigneeId(processInstanceId,
				"ApplyTask");
		if (strStartUserId != null) {
			User user = userService.findDtoById(Integer.parseInt(strStartUserId));
			userList.add(user);
		}
		return super.preProcessUsers(userList);
	}

}