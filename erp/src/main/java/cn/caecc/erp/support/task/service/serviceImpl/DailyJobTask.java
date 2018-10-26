/**
 * 
 */
package cn.caecc.erp.support.task.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import cn.caecc.erp.modules.service.ContractActivitiApplyService;
import cn.caecc.erp.support.workflow.service.WorkflowService;


/**
 * @author weizhen
 *
 */
public class DailyJobTask {

	/**
	 * 
	 */
	@Autowired 
	private WorkflowService workflowService;
	@Autowired
	private ContractActivitiApplyService contractActivitiApplyService;
	public DailyJobTask() {
		// TODO Auto-generated constructor stub
	}
	
	 public void service(){  
         /**业务逻辑*/ 
		// MyWebSocketHandler.sendKeeplive();
		 try {
			 workflowService.clearAttachmentOssTempDirectoryDaily();
			 contractActivitiApplyService.checkContractDaily();
		 } catch (Exception ex) {
			 
		 }
     }  

}
