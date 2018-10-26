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
public interface OutsideApproverService {

	/**
	 * 获取申请任务的下一环节审批人
	 */
	public List<User> getApplyTaskNextApprover(int startUserId);
	
	/**
	 * 获取总经理办公室任务下一环节审批人
	 */

	public List<User> getGeneralManagerOfficeTaskNextApprover();

	
	public List<User> getGeneralManagerOfficeLeaderTaskNextApprover();

	public List<User> getManagerAboveLeaderTaskNextApprover();

	/**
	 * 获取主管领导以上任务下一环节审批人
	 */
	public List<User> getProcesserSummaryTaskNextApprover(String processInstanceId);
	
}
