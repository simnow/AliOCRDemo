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
import cn.caecc.erp.support.workflow.approver.service.OfficesupplyApproverService;

/**
 * @author weizhen
 *
 */
@Controller
@RequestMapping(value="/api/approver/OfficesupplyProcess")
public class OfficesupplyApproverController extends BaseController {

	/**
	 * 
	 */
	@Autowired
	private OfficesupplyApproverService officesupplyApproverService;
	public OfficesupplyApproverController() {
		// TODO Auto-generated constructor stub
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/ApplyTask", method = RequestMethod.GET)
	public Message getApplyTaskNextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = officesupplyApproverService.getApplyTaskNextApprover();
		message.setObj(nextList);	
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/DepartmentTask", method = RequestMethod.GET)
	public Message getDepartmentTaskNextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = officesupplyApproverService.getDepartmentTaskNextApprover();
		message.setObj(nextList);	
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/GeneralManagerOfficeConfirmTask", method = RequestMethod.GET)
	public Message getGeneralManagerOfficeConfirmTaskNextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = officesupplyApproverService.getGeneralManagerOfficeConfirmTaskNextApprover();
		message.setObj(nextList);	
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/GeneralManagerOfficeLeaderTask", method = RequestMethod.GET)
	public Message getGeneralManagerOfficeLeaderTaskNextApprover(String processInstanceId, Integer isGeneralManager) {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = officesupplyApproverService.getGeneralManagerOfficeLeaderTaskNextApprover(processInstanceId, isGeneralManager);
		message.setObj(nextList);	
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/LeaderTask", method = RequestMethod.GET)
	public Message getLeaderTaskNextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = officesupplyApproverService.getLeaderTaskNextApprover();
		message.setObj(nextList);	
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/GeneralManagerTask", method = RequestMethod.GET)
	public Message getGeneralManagerTaskNextApprover(String processInstanceId, Integer money) {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = officesupplyApproverService.getGeneralManagerTaskNextApprover(processInstanceId, money);
		message.setObj(nextList);	
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/ChairmanTask", method = RequestMethod.GET)
	public Message getChairmanTaskNextApprover(String processInstanceId) {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = officesupplyApproverService.getChairmanTaskNextApprover(processInstanceId);
		message.setObj(nextList);	
		return message;
	}
	

}
