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
public interface MaterialApproverService {
	/**
	 * 获取申请任务的下一环节审批人
	 */
	public List<User> getApplyTaskNextApprover();


	public List<User> getSupplyCentreTask1NextApprover();
	
	
	public List<User> getBusinessDepartmentTask1NextApprover();

	public List<User> getManagerTaskNextApprover(String processInstanceId);

	public List<User> getAssignedManagerTaskNextApprover(String processInstanceId, Integer condition);

	public List<User> getGeneralManagerTaskNextApprover();

	public List<User> getBusinessDepartmentTask2NextApprover();

	public List<User> getSupplyCentreTask2NextApprover(String processInstanceId);
	
	

}
