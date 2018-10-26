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
public interface DispatchApproverService {

	/**
	 * 
	 */
	/**
	 * 获取申请任务的下一环节审批人
	 */
	public List<User> getApplyTaskNextApprover();
	
	public List<User> getGeneralManagerOfficeTaskNextApprover(String processInstanceId);

	public List<User> getDrafterReviewTaskNextApprover();
	
	public List<User> getDepartmentalLeaderTaskNextApprover();

	public List<User> getGeneralManagerOfficeLeaderTaskNextApprover(String processInstanceId);

	public List<User> getManagerTaskNextApprover();

	public List<User> getGeneralManagerTaskNextApprover(String processInstanceId, Integer isChairman);
	
	public List<User> getChairmanTaskNextApprover(String processInstanceId);
	

}
