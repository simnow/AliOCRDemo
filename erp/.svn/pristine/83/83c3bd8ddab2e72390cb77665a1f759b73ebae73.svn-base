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
import cn.caecc.erp.support.workflow.approver.service.WorkloadApproverService;

/**
 * @author weizhen
 *
 */
@Controller
@RequestMapping(value="/api/approver/WorkloadProcess")
public class WorkloadApproverController extends BaseController {

	/**
	 * 
	 */
	@Autowired
	private WorkloadApproverService workloadApproverService;
	public WorkloadApproverController() {
		// TODO Auto-generated constructor stub
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/ApplyTask", method = RequestMethod.GET)
	public Message getApplyTaskNextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = workloadApproverService.getApplyTaskNextApprover();
		message.setObj(nextList);	
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/BusinessDepartmentTask1", method = RequestMethod.GET)
	public Message getBusinessDepartmentTask1NextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = workloadApproverService.getBusinessDepartmentTask1NextApprover();
		message.setObj(nextList);	
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/AssignedManagerTask", method = RequestMethod.GET)
	public Message getAssignedManagerTaskNextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = workloadApproverService.getAssignedManagerTaskNextApprover();
		message.setObj(nextList);	
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/GeneralManagerTask1", method = RequestMethod.GET)
	public Message getGeneralManagerTask1NextApprover(String processInstanceId) {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = workloadApproverService.getGeneralManagerTask1NextApprover(processInstanceId);
		message.setObj(nextList);	
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/BusinessDepartmentTask2", method = RequestMethod.GET)
	public Message getBusinessDepartmentTask2NextApprover(String processInstanceId) {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = workloadApproverService.getBusinessDepartmentTask2NextApprover(processInstanceId);
		message.setObj(nextList);	
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/DepartmentConfirmTask", method = RequestMethod.GET)
	public Message getDepartmentConfirmTaskNextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = workloadApproverService.getDepartmentConfirmTaskNextApprover();
		message.setObj(nextList);	
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/DepartmentLeaderConfirmTask", method = RequestMethod.GET)
	public Message findDepartmentLeaderConfirmTaskNextApprover(String processInstanceId) {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = workloadApproverService.findDepartmentLeaderConfirmTaskNextApprover(processInstanceId);
		message.setObj(nextList);	
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/BusinessDepartmentTask3", method = RequestMethod.GET)
	public Message getBusinessDepartmentTask3NextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = workloadApproverService.getBusinessDepartmentTask3NextApprover();
		message.setObj(nextList);	
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/BusinessDepartmentLeaderTask", method = RequestMethod.GET)
	public Message getBusinessDepartmentLeaderTaskNextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = workloadApproverService.getBusinessDepartmentLeaderTaskNextApprover();
		message.setObj(nextList);	
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/ManagementAndProcurementExaminationTask", method = RequestMethod.GET)
	public Message getManagementAndProcurementExaminationTaskNextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = workloadApproverService.getManagementAndProcurementExaminationTaskNextApprover();
		message.setObj(nextList);	
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/FinancialManagementTask", method = RequestMethod.GET)
	public Message getFinancialManagementTaskNextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = workloadApproverService.getFinancialManagementTaskNextApprover();
		message.setObj(nextList);	
		return message;
	}

}
