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
import cn.caecc.erp.support.workflow.approver.service.ProjectApproverService;

/**
 * @author weizhen
 *
 */
@Controller
@RequestMapping(value="/api/approver/ProjectProcess")
public class ProjectApproverController extends BaseController {

	/**
	 * 
	 */
	@Autowired
	private ProjectApproverService projectApproverService;
	public ProjectApproverController() {
		// TODO Auto-generated constructor stub
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/ApplyTask", method = RequestMethod.GET)
	public Message getApplyTaskNextApprover(int startUserId) {
		Message message = new Message();
		message.setSuccess(true);
		List<User> userList = projectApproverService.getApplyTaskNextApprover(startUserId);
		message.setObj(userList);
		return message;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/ManagerTask", method = RequestMethod.GET)
	public Message getManagerTaskNextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> userList = projectApproverService.getManagerTaskNextApprover();
		message.setObj(userList);
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/ManagementAndProcurementTask", method = RequestMethod.GET)
	public Message getManagementAndProcurementTaskNextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> userList = projectApproverService.getManagementAndProcurementTaskNextApprover();
		message.setObj(userList);
		return message;

	}
	
	@ResponseBody
	@RequestMapping(value = "/SecuritiesAffairsTask", method = RequestMethod.GET)
	public Message getSecuritiesAffairsTaskNextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> userList = projectApproverService.getSecuritiesAffairsTaskNextApprover();
		message.setObj(userList);
		return message;
	}

	@ResponseBody
	@RequestMapping(value = "/FinancialManagementTask", method = RequestMethod.GET)
	public Message getFinancialManagementTaskNextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> userList = projectApproverService.getFinancialManagementTaskNextApprover();
		message.setObj(userList);
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/FinancialOfficerTask", method = RequestMethod.GET)
	public Message getFinancialOfficerTaskNextApprover(String processInstanceId) {
		Message message = new Message();
		message.setSuccess(true);
		List<User> userList = projectApproverService.getFinancialOfficerTaskNextApprover(processInstanceId);
		message.setObj(userList);
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/AssignedManagerTask", method = RequestMethod.GET)
	public Message getAssignedManagerTaskNextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> userList = projectApproverService.getAssignedManagerTaskNextApprover();
		message.setObj(userList);
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/GeneralManagerTask", method = RequestMethod.GET)
	public Message getGeneralManagerTaskNextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> userList = projectApproverService.getGeneralManagerTaskNextApprover();
		message.setObj(userList);
		return message;
	}

}
