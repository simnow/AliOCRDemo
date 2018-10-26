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
import cn.caecc.erp.support.workflow.approver.service.MaterialApproverService;

/**
 * @author weizhen
 *
 */
@Controller
@RequestMapping(value="/api/approver/MaterialProcess")
public class MaterialApproverController extends BaseController {

	/**
	 * 
	 */
	@Autowired
	private MaterialApproverService materialApproverService;
	public MaterialApproverController() {
		// TODO Auto-generated constructor stub
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/ApplyTask", method = RequestMethod.GET)
	public Message getApplyTaskNextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = materialApproverService.getApplyTaskNextApprover();
		message.setObj(nextList);	
		return message;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/SupplyCentreTask1", method = RequestMethod.GET)
	public Message getSupplyCentreTask1NextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = materialApproverService.getSupplyCentreTask1NextApprover();
		message.setObj(nextList);	
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/BusinessDepartmentTask1", method = RequestMethod.GET)
	public Message getBusinessDepartmentTask1NextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = materialApproverService.getBusinessDepartmentTask1NextApprover();
		message.setObj(nextList);	
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/ManagerTask", method = RequestMethod.GET)
	public Message getManagerTaskNextApprover(String processInstanceId) {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = materialApproverService.getManagerTaskNextApprover(processInstanceId);
		message.setObj(nextList);	
		return message;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/AssignedManagerTask", method = RequestMethod.GET)
	public Message getAssignedManagerTaskNextApprover(String processInstanceId, Integer condition) {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = materialApproverService.getAssignedManagerTaskNextApprover(processInstanceId, condition);
		message.setObj(nextList);	
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/GeneralManagerTask", method = RequestMethod.GET)
	public Message getGeneralManagerTaskNextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = materialApproverService.getGeneralManagerTaskNextApprover();
		message.setObj(nextList);	
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/BusinessDepartmentTask2", method = RequestMethod.GET)
	public Message getBusinessDepartmentTask2NextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = materialApproverService.getBusinessDepartmentTask2NextApprover();
		message.setObj(nextList);	
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/SupplyCentreTask2", method = RequestMethod.GET)
	public Message getSupplyCentreTask2NextApprover(String processInstanceId) {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = materialApproverService.getSupplyCentreTask2NextApprover(processInstanceId);
		message.setObj(nextList);	
		return message;
	}
	

}
