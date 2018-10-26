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
import cn.caecc.erp.support.workflow.approver.service.FixedassetsApproverService;
import cn.caecc.erp.support.workflow.service.WorkflowService;

/**
 * @author weizhen
 *
 */
@Service
public class FixedassetsApproverServiceImpl extends BaseApproverService implements FixedassetsApproverService {

	/**
	 * 
	 */
	@Autowired
	private UserService userService;
	
	@Autowired
	private DeptmentService deptMentService;
	
	@Autowired
	private WorkflowService workflowService;
	
	public FixedassetsApproverServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.approver.service.FixedassetsApproverService#getApplyTaskNextApprover()
	 */
	@Override
	public List<User> getApplyTaskNextApprover(int startUserId, int departmentCode) {		
		// TODO Auto-generated method stub
		List<User> userList = null;
		if (departmentCode == 0) {
			userList = deptMentService.getDepartmentMasters(Contants.GENERALMENERALMANAGEROFFICE);
			
		} else if (departmentCode == 1) {
			userList = deptMentService.getDepartmentMasters(Contants.EQUIPMENTEXAMINATION);
			
		} else if (departmentCode == 2) {
			userList = userService.findManagerLeader(startUserId);
		}
			
		return super.preProcessUsers(userList);

	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.approver.service.FixedassetsApproverService#getGeneralManagerOfficeOrEquipmentExaminationTaskNextApprover()
	 */
	@Override
	public List<User> getGeneralManagerOfficeOrEquipmentExaminationTaskNextApprover(String processInstanceId) {
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
	 * @see cn.caecc.erp.support.workflow.approver.service.FixedassetsApproverService#getManagerTaskNextApprover()
	 */
	@Override
	public List<User> getManagerTaskNextApprover() {
		// TODO Auto-generated method stub
		List<User> userList = deptMentService.getDepartmentMasters(Contants.MANAGEMENTANDPROCUREMENT);
		return super.preProcessUsers(userList);
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.approver.service.FixedassetsApproverService#getManagementAndProcurementTaskNextApprover()
	 */
	@Override
	public List<User> getManagementAndProcurementTaskNextApprover() {
		// TODO Auto-generated method stub
		List<User> userList = deptMentService.getDepartmentMasters(Contants.FINANCIALMANAGEMENT);
		return super.preProcessUsers(userList);
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.approver.service.FixedassetsApproverService#getFinancialManagementTaskNextApprover()
	 */
	@Override
	public List<User> getFinancialManagementTaskNextApprover() {
		// TODO Auto-generated method stub
		List<User> userList = deptMentService.getDepartmentUsers(Contants.FINANCIALOFFICER);
		return super.preProcessUsers(userList);
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.approver.service.FixedassetsApproverService#getFinancialOfficerTaskApprover()
	 */
	@Override
	public List<User> getFinancialOfficerTaskApprover() {
		// TODO Auto-generated method stub
		List<User> userList = deptMentService.getDepartmentUsers(Contants.GENERALMENERALMANAGER);
		return super.preProcessUsers(userList);
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.approver.service.FixedassetsApproverService#getGeneralManagerTaskNextApprover(java.lang.String)
	 */
	@Override
	public List<User> getGeneralManagerTaskNextApprover(String processInstanceId, int unitPrice) {
		// TODO Auto-generated method stub
		List<User> userList = null;
		if (unitPrice > 300000) {
			userList = deptMentService.getDepartmentUsers(Contants.CHAIRMAN);
		}
		return super.preProcessUsers(userList);
	}

	/* (non-Javadoc)
	 * @see cn.caecc.erp.support.workflow.approver.service.FixedassetsApproverService#getChairmanTaskNextApprover()
	 */
	@Override
	public List<User> getChairmanTaskNextApprover(String processInstanceId,  int isSecuritiesAffairs) {
		// TODO Auto-generated method stub
		List<User> userList = null;
		if (isSecuritiesAffairs != 0) {
			userList = deptMentService.getDepartmentUsers(Contants.SECURITIESAFFAIRS);
		}
		return super.preProcessUsers(userList);
	}
	

}
