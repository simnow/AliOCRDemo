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
public interface WorkloadApproverService {
	/**
	 * 获取申请任务的下一环节审批人
	 */
	public List<User> getApplyTaskNextApprover();


	public List<User> getBusinessDepartmentTask1NextApprover();
	
	
	public List<User> getAssignedManagerTaskNextApprover();

	public List<User> getGeneralManagerTask1NextApprover(String processInstanceId);

	public List<User> getBusinessDepartmentTask2NextApprover(String processInstanceId);

	public List<User> getDepartmentConfirmTaskNextApprover();

	public List<User> findDepartmentLeaderConfirmTaskNextApprover(String processInstanceId);

	public List<User> getBusinessDepartmentTask3NextApprover();

	public List<User> getBusinessDepartmentLeaderTaskNextApprover();

	public List<User> getManagementAndProcurementExaminationTaskNextApprover();

	public List<User> getFinancialManagementTaskNextApprover();

}
