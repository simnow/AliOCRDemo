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
public interface QualityFeedbackApproverService {
	/**
	 * 获取申请任务的下一环节审批人
	 */
	public List<User> getApplyTaskNextApprover();


	public List<User> getManagementAndProcurementProcessTaskNextApprover();
	
	public List<User> getBusinessDepartmentTaskNextApprover(String processInstanceId);

	public List<User> getManagementAndProcurementConfirmTaskNextApprover(String processInstanceId);
	
}
