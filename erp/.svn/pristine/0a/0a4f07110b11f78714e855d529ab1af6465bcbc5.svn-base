/**
 * 
 */
package cn.caecc.erp.controller.workflow.approver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.caecc.erp.controller.BaseController;
import cn.caecc.erp.modules.dao.mybatis.entity.User;
import cn.caecc.erp.support.message.Message;
import cn.caecc.erp.support.workflow.approver.service.EvaluateApproverService;

/**
 * @author weizhen
 *
 */
@Controller
@RequestMapping(value="/api/approver/EvaluateProcess")
public class EvaluateApproverController extends BaseController {

	@Autowired
	private EvaluateApproverService evaluateApproverService;
	/**
	 * 
	 */
	public EvaluateApproverController() {
		// TODO Auto-generated constructor stub
	}

	@ResponseBody
	@RequestMapping(value = "/ApplyTask", method = RequestMethod.GET)
	public Message getApplyTaskNextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> userList = evaluateApproverService.getApplyTaskNextApprover();
		message.setObj(userList);
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/BusinessDepartmentEvaluateTask", method = RequestMethod.GET)
	public Message getBusinessDepartmentEvaluateTaskNextApprover() {
		
		Message message = new Message();
		message.setSuccess(true);
		List<User> userList = evaluateApproverService.getBusinessDepartmentEvaluateTaskNextApprover();
		message.setObj(userList);
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/ManagerEvaluateTask", method = RequestMethod.GET)
	public Message getManagerEvaluateTaskNextApprover(String processInstanceId) {
		
		Message message = new Message();
		message.setSuccess(true);
		List<User> userList = evaluateApproverService.getManagerEvaluateTaskNextApprover(processInstanceId);
		message.setObj(userList);
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/ManagementAndProcurementEvaluateTask", method = RequestMethod.GET)
	public Message getManagementAndProcurementEvaluateTaskNextApprover(String processInstanceId) {
		
		Message message = new Message();
		message.setSuccess(true);
		List<User> userList = evaluateApproverService.getManagementAndProcurementEvaluateTaskNextApprover(processInstanceId);
		message.setObj(userList);
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/AssignedManagerEvaluateTask", method = RequestMethod.GET)
	public Message getAssignedManagerEvaluateTaskNextApprover() {
		
		Message message = new Message();
		message.setSuccess(true);
		List<User> userList = evaluateApproverService.getAssignedManagerEvaluateTaskNextApprover();
		message.setObj(userList);
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/GeneralManagerEvaluateTask", method = RequestMethod.GET)
	public Message getGeneralManagerEvaluateTaskNextApprover() {
		
		Message message = new Message();
		message.setSuccess(true);
		List<User> userList = evaluateApproverService.getGeneralManagerEvaluateTaskNextApprover();
		message.setObj(userList);
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/InviteTendersCommitteeLeaderEvaluateTask", method = RequestMethod.GET)
	public Message getInviteTendersCommitteeLeaderEvaluateTaskNextApprover(String processInstanceId) {
		
		Message message = new Message();
		message.setSuccess(true);
		List<User> userList = evaluateApproverService.getInviteTendersCommitteeLeaderEvaluateTaskNextApprover(processInstanceId);
		message.setObj(userList);
		return message;
	}
}
