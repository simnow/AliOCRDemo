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
public interface VehicleApproverService {

	/**
	 * 
	 */
	/**
	 * 获取申请任务的下一环节审批人
	 */
	public List<User> getApplyTaskNextApprover(int startUserId);
	
	/**
	 * 获取部门领导
	 */
	public List<User> getDepartmentalLeaderTaskNextApprover();
	
	
	/**
	 * 获取下一审批人
	 * 
	 */
	public List<User> getGeneralManagerOfficeTaskNextApprover();
	
	

}
