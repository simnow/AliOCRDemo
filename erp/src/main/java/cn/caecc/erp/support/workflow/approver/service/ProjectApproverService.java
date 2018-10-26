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
public interface ProjectApproverService {
	/**
	 * 获取申请任务的下一环节审批人
	 */
	public List<User> getApplyTaskNextApprover(int startUserId);

	/**
	 * 获取主管领导以上任务下一环节审批人
	 */
	public List<User> getManagerTaskNextApprover();
	
	public List<User> getManagementAndProcurementTaskNextApprover();

	public List<User> getSecuritiesAffairsTaskNextApprover();

	public List<User> getFinancialManagementTaskNextApprover();

	public List<User> getFinancialOfficerTaskNextApprover(String processInstanceId);

	public List<User> getAssignedManagerTaskNextApprover();

	public List<User> getGeneralManagerTaskNextApprover();

}
