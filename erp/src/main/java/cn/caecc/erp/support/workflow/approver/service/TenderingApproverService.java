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
public interface TenderingApproverService {
	/**
	 * 获取申请任务的下一环节审批人
	 */
	public List<User> getApplyTaskNextApprover();


	public List<User> getManagementAndProcurementExaminationTaskNextApprover();
	
	public List<User> getManagerTaskNextApprover();

	public List<User> getInviteTendersCommitteeLeaderTaskNextApprover(String processInstanceId);
	
}