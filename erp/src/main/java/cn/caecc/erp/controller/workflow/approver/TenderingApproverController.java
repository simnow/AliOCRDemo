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
import cn.caecc.erp.support.workflow.approver.service.TenderingApproverService;


/**
 * @author weizhen
 *
 */
@Controller
@RequestMapping(value="/api/approver/TenderingProcess")
public class TenderingApproverController extends BaseController {

	/**
	 * 
	 */
	@Autowired
	private TenderingApproverService tenderingApproverService;
	public TenderingApproverController() {
		// TODO Auto-generated constructor stub
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/ApplyTask", method = RequestMethod.GET)
	public Message getApplyTaskNextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = tenderingApproverService.getApplyTaskNextApprover();
		message.setObj(nextList);	
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/ManagementAndProcurementExaminationTask", method = RequestMethod.GET)
	public Message getManagementAndProcurementExaminationTaskNextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = tenderingApproverService.getManagementAndProcurementExaminationTaskNextApprover();
		message.setObj(nextList);	
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/ManagerTask", method = RequestMethod.GET)
	public Message getManagerTaskNextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = tenderingApproverService.getManagerTaskNextApprover();
		message.setObj(nextList);	
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/InviteTendersCommitteeLeaderTask", method = RequestMethod.GET)
	public Message getInviteTendersCommitteeLeaderTaskNextApprover(String processInstanceId) {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = tenderingApproverService.getInviteTendersCommitteeLeaderTaskNextApprover(processInstanceId);
		message.setObj(nextList);	
		return message;
	}
	
}
