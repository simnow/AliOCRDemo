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
import cn.caecc.erp.support.workflow.approver.service.FixedassetsApproverService;

/**
 * @author weizhen
 *
 */
@Controller
@RequestMapping(value="/api/approver/FixedassetsProcess")
public class FixedassetsApproverController extends BaseController {

	/**
	 * 
	 */
	@Autowired
	private FixedassetsApproverService fixedassetsApproverService;
	public FixedassetsApproverController() {
		// TODO Auto-generated constructor stub
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/ApplyTask", method = RequestMethod.GET)
	public Message getApplyTaskNextApprover(int startUserId, int departmentCode) {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = fixedassetsApproverService.getApplyTaskNextApprover(startUserId, departmentCode);
		message.setObj(nextList);	
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/GeneralManagerOfficeOrEquipmentExaminationTask", method = RequestMethod.GET)
	public Message getGeneralManagerOfficeOrEquipmentExaminationTaskNextApprover(String processInstanceId) {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = fixedassetsApproverService.getGeneralManagerOfficeOrEquipmentExaminationTaskNextApprover(processInstanceId);
		message.setObj(nextList);	
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/ManagerTask", method = RequestMethod.GET)
	public Message getManagerTaskNextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = fixedassetsApproverService.getManagerTaskNextApprover();
		message.setObj(nextList);	
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/ManagementAndProcurementTask", method = RequestMethod.GET)
	public Message getManagementAndProcurementTaskNextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = fixedassetsApproverService.getManagementAndProcurementTaskNextApprover();
		message.setObj(nextList);	
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/FinancialManagementTask", method = RequestMethod.GET)
	public Message getFinancialManagementTaskNextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = fixedassetsApproverService.getFinancialManagementTaskNextApprover();
		message.setObj(nextList);	
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/FinancialOfficerTask", method = RequestMethod.GET)
	public Message getFinancialOfficerTaskApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = fixedassetsApproverService.getFinancialOfficerTaskApprover();
		message.setObj(nextList);	
		return message;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/GeneralManagerTask", method = RequestMethod.GET)
	public Message getGeneralManagerTaskNextApprover(String processInstanceId, Integer unitPrice) {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = fixedassetsApproverService.getGeneralManagerTaskNextApprover(processInstanceId, unitPrice);
		message.setObj(nextList);	
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/ChairmanTask", method = RequestMethod.GET)
	public Message getChairmanTaskNextApprover(String processInstanceId, int isSecuritiesAffairs) {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = fixedassetsApproverService.getChairmanTaskNextApprover(processInstanceId, isSecuritiesAffairs);
		message.setObj(nextList);	
		return message;
	}
	
}
