/**
 * 
 */
package cn.caecc.erp.support.workflow.remind.service;

import org.activiti.engine.delegate.DelegateExecution;

/**
 * @author weizhen
 * 提醒服务
 *
 */
public interface RemindService {

	public void sendRemind(DelegateExecution execution, boolean isApprove);

	
}
