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
import cn.caecc.erp.support.workflow.approver.service.OutsideApproverService;

/**
 * @author weizhen
 *
 */


@Controller
@RequestMapping(value="/api/approver/OutsideProcess")
public class OutsideApproverController extends BaseController {

	
	@Autowired
	private OutsideApproverService outsideApproverService;
	/**
	 * 
	 */
	public OutsideApproverController() {
		// TODO Auto-generated constructor stub
		
	}
	@ResponseBody
	@RequestMapping(value = "/ApplyTask", method = RequestMethod.GET)
	public Message getApplyTaskNextApprover(int startUserId) {
		Message message = new Message();
		message.setSuccess(true);
		List<User> userList = outsideApproverService.getApplyTaskNextApprover(startUserId);
		message.setObj(userList);
		return message;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/GeneralManagerOfficeTask", method = RequestMethod.GET)
	public Message getGeneralManagerOfficeTaskNextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> userList = outsideApproverService.getGeneralManagerOfficeTaskNextApprover();
		message.setObj(userList);
		return message;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/GeneralManagerOfficeLeaderTask", method = RequestMethod.GET)
	public Message getGeneralManagerOfficeLeaderTaskNextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> userList = outsideApproverService.getGeneralManagerOfficeLeaderTaskNextApprover();
		message.setObj(userList);
		return message;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/ManagerAboveLeaderTask", method = RequestMethod.GET)
	public Message getManagerAboveLeaderTaskNextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> userList = outsideApproverService.getManagerAboveLeaderTaskNextApprover();
		message.setObj(userList);
		return message;
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/ProcesserSummaryTask", method = RequestMethod.GET)
	public Message getProcesserSummaryTaskNextApprover(String processInstanceId) {
		Message message = new Message();
		message.setSuccess(true);
		List<User> userList = outsideApproverService.getProcesserSummaryTaskNextApprover(processInstanceId);
		message.setObj(userList);
		return message;
	}
	
}
