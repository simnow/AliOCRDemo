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
import cn.caecc.erp.support.workflow.approver.service.DispatchApproverService;

/**
 * @author weizhen
 *
 */
@Controller
@RequestMapping(value="/api/approver/DispatchProcess")
public class DispatchApproverController extends BaseController {

	@Autowired
	private DispatchApproverService dispatchApproverService;
	/**
	 * 
	 */
	public DispatchApproverController() {
		// TODO Auto-generated constructor stub
	}

	@ResponseBody
	@RequestMapping(value = "/ApplyTask", method = RequestMethod.GET)
	public Message getApplyTaskNextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> userList = dispatchApproverService.getApplyTaskNextApprover();
		message.setObj(userList);
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/GeneralManagerOfficeTask", method = RequestMethod.GET)
	public Message getGeneralManagerOfficeTaskNextApprover(String processInstanceId) {
		
		Message message = new Message();
		message.setSuccess(true);
		List<User> userList = dispatchApproverService.getGeneralManagerOfficeTaskNextApprover(processInstanceId);
		message.setObj(userList);
		return message;
	}
	@ResponseBody
	@RequestMapping(value = "/DrafterReviewTask", method = RequestMethod.GET)
	public Message getDrafterReviewTaskNextApprover() {
		
		Message message = new Message();
		message.setSuccess(true);
		List<User> userList = dispatchApproverService.getDrafterReviewTaskNextApprover();
		message.setObj(userList);
		return message;
	}
	@ResponseBody
	@RequestMapping(value = "/DepartmentalLeaderTask", method = RequestMethod.GET)
	public Message getDepartmentalLeaderTaskNextApprover() {
		
		Message message = new Message();
		message.setSuccess(true);
		List<User> userList = dispatchApproverService.getDepartmentalLeaderTaskNextApprover();
		message.setObj(userList);
		return message;
	}
	@ResponseBody
	@RequestMapping(value = "/GeneralManagerOfficeLeaderTask", method = RequestMethod.GET)
	public Message getGeneralManagerOfficeLeaderTaskNextApprover(String processInstanceId) {
		
		Message message = new Message();
		message.setSuccess(true);
		List<User> userList = dispatchApproverService.getGeneralManagerOfficeLeaderTaskNextApprover(processInstanceId);
		message.setObj(userList);
		return message;
	}
	@ResponseBody
	@RequestMapping(value = "/ManagerTask", method = RequestMethod.GET)
	public Message getManagerTaskNextApprover() {
		
		Message message = new Message();
		message.setSuccess(true);
		List<User> userList = dispatchApproverService.getManagerTaskNextApprover();
		message.setObj(userList);
		return message;
	}

	@ResponseBody
	@RequestMapping(value = "/GeneralManagerTask", method = RequestMethod.GET)
	public Message getGeneralManagerTaskNextApprover(String processInstanceId, Integer isChairman) {
		Message message = new Message();
		message.setSuccess(true);
		List<User> userList = dispatchApproverService.getGeneralManagerTaskNextApprover(processInstanceId, isChairman);
		message.setObj(userList);
		return message;
	}

	
	@ResponseBody
	@RequestMapping(value = "/ChairmanTask", method = RequestMethod.GET)
	public Message getChairmanTaskNextApprover(String processInstanceId) {
		
		Message message = new Message();
		message.setSuccess(true);
		List<User> userList = dispatchApproverService.getChairmanTaskNextApprover(processInstanceId);
		message.setObj(userList);
		return message;
	}
}
