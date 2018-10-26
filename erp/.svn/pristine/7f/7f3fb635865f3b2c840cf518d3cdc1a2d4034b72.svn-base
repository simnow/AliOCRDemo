/**
 * 
 */
package test.support.workflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.caecc.erp.modules.service.ContractActivitiApplyService;
import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.message.Message;
import cn.caecc.erp.support.util.JacksonUtil;
import cn.caecc.erp.support.workflow.entity.ProcessIntanceEntity;
import cn.caecc.erp.support.workflow.service.WorkflowService;
import test.SpringTestBase;

/**
 * @author weizhen
 *
 */

public class ActivitiServiceImplTest extends SpringTestBase {
	@Autowired
	private WorkflowService workflowService;

	@Autowired
	private  ContractActivitiApplyService contractActivitiApplyService;
	
	@Test
	public void testUpdatexxx() {
		/*
		Map<String, Object> vs = new HashMap<String, Object>();
		List<String> attendeesUsers = new ArrayList<String>();
		attendeesUsers.add("1000000");

		vs.put("nextCandidateUsers", attendeesUsers);
		 */
		try
		{
			// workflowService.handleRuntimeTask("ProjectProcess", null, "", 1);
			contractActivitiApplyService.checkContractDaily();
		}
		catch(Exception ex)
		{
			
		}
	} 

	// @Test
	public void queryTasks() {
		String s = workflowService.queryProcessDefinitionKeyByHistoricProcessInstanceId("52501");
		System.out.println(s);
	}

	//@Test
	public void handleTasks() {
		try {
			workflowService.handleRuntimeTask("270008", new HashMap<String, Object>(), "",
					Contants.ResultEnum.Approve.ordinal());
		} catch (Exception ex) {
			
		}
	}

	//@Test
	public void queryRuntimeProcessInstanceDetail() {
	}
	//@Test
	public void queryTaskGraphicInfoMap() {
		ProcessIntanceEntity processIntanceEntity = workflowService.queryProcessInstanceWithDetailById("95023");
		

	}
	//@Test
	public void queryProcessInstanceDetailTest() {
	}
}
