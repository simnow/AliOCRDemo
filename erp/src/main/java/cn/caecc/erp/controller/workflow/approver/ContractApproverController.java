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
import cn.caecc.erp.support.workflow.approver.service.ContractApproverService;

/**
 * @author weizhen
 *
 */
@Controller
@RequestMapping(value="/api/approver/ContractProcess")
public class ContractApproverController extends BaseController {

	/**
	 * 
	 */
	@Autowired
	private ContractApproverService contractApproverService;
	public ContractApproverController() {
		// TODO Auto-generated constructor stub
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/ApplyTask", method = RequestMethod.GET)
	public Message getApplyTaskNextApprover(int startUserId) {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = contractApproverService.getApplyTaskNextApprover();
		message.setObj(nextList);	
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/BusinessDepartmentTask", method = RequestMethod.GET)
	public Message getBusinessDepartmentTaskNextApprover(String processInstanceId) {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = contractApproverService.getBusinessDepartmentTaskNextApprover(processInstanceId);
		message.setObj(nextList);
		return message;

	}
	
	@ResponseBody
	@RequestMapping(value = "/ManagerTask", method = RequestMethod.GET)
	public Message getManagerTaskNextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = contractApproverService.getManagerTaskNextApprover();
		message.setObj(nextList);
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/SecuritiesAffairsTask", method = RequestMethod.GET)
	public Message getSecuritiesAffairsTaskNextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = contractApproverService.getSecuritiesAffairsTaskNextApprover();
		message.setObj(nextList);
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/FinancialManagementTask", method = RequestMethod.GET)
	public Message getFinancialManagementTaskNextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = contractApproverService.getFinancialManagementTaskNextApprover();
		message.setObj(nextList);
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/FinancialOfficerTask", method = RequestMethod.GET)
	public Message getFinancialOfficerTaskNextApprover(String processInstanceId) {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = contractApproverService.getFinancialOfficerTaskNextApprover(processInstanceId);
		message.setObj(nextList);
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/AssignedManagerTask", method = RequestMethod.GET)
	public Message getAssignedManagerTaskNextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = contractApproverService.getAssignedManagerTaskNextApprover();
		message.setObj(nextList);
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/GeneralManagerTask", method = RequestMethod.GET)
	public Message getGeneralManagerTaskNextApprover(int isChairman) {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = contractApproverService.getGeneralManagerTaskNextApprover(isChairman);
		message.setObj(nextList);
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/ChairmanTask", method = RequestMethod.GET)
	public Message getChairmanTaskNextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = contractApproverService.getChairmanTaskNextApprover();
		message.setObj(nextList);
		return message;
	}

}
