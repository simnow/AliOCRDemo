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
public interface OfficesupplyApproverService {
	/**
	 * 获取申请任务的下一环节审批人
	 */
	public List<User> getApplyTaskNextApprover();

	public List<User> getDepartmentTaskNextApprover();
	
	public List<User> getGeneralManagerOfficeConfirmTaskNextApprover();

	public List<User> getGeneralManagerOfficeLeaderTaskNextApprover(String processInstanceId, Integer isGeneralManager);

	public List<User> getLeaderTaskNextApprover();
	
	public List<User> getGeneralManagerTaskNextApprover(String processInstanceId, Integer money);
	
	public List<User> getChairmanTaskNextApprover(String processInstanceId);

	
}
