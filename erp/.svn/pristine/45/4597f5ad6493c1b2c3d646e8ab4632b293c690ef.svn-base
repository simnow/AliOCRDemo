/**
 * 
 */
package cn.caecc.erp.support.workflow.approver.service;

import java.util.List;

import cn.caecc.erp.modules.dao.mybatis.entity.User;

/**
 * @author weizhen
 *
 */
public interface EvaluateApproverService {
	/**
	 * 获取申请任务的下一环节审批人
	 */
	public List<User> getApplyTaskNextApprover();


	public List<User> getBusinessDepartmentEvaluateTaskNextApprover();
	
	public List<User> getManagerEvaluateTaskNextApprover(String processInstanceId);

	public List<User> getManagementAndProcurementEvaluateTaskNextApprover(String processInstanceId);
	
	public List<User> getAssignedManagerEvaluateTaskNextApprover();

	public List<User> getGeneralManagerEvaluateTaskNextApprover();

	public List<User> getInviteTendersCommitteeLeaderEvaluateTaskNextApprover(String processInstanceId);


	
}
