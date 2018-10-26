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
import cn.caecc.erp.support.workflow.approver.service.QualityFeedbackApproverService;


/**
 * @author weizhen
 *
 */
@Controller
@RequestMapping(value="/api/approver/QualityFeedbackProcess")
public class QualityFeedbackApproverController extends BaseController {

	/**
	 * 
	 */
	@Autowired
	private QualityFeedbackApproverService qualityApproverService;
	public QualityFeedbackApproverController() {
		// TODO Auto-generated constructor stub
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/ApplyTask", method = RequestMethod.GET)
	public Message getApplyTaskNextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = qualityApproverService.getApplyTaskNextApprover();
		message.setObj(nextList);	
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/ManagementAndProcurementProcessTask", method = RequestMethod.GET)
	public Message getManagementAndProcurementProcessTaskNextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = qualityApproverService.getManagementAndProcurementProcessTaskNextApprover();
		message.setObj(nextList);	
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/BusinessDepartmentTask", method = RequestMethod.GET)
	public Message getBusinessDepartmentTaskNextApprover(String processInstanceId) {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = qualityApproverService.getBusinessDepartmentTaskNextApprover(processInstanceId);
		message.setObj(nextList);	
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/ManagementAndProcurementConfirmTask", method = RequestMethod.GET)
	public Message getManagementAndProcurementConfirmTaskNextApprover(String processInstanceId) {
		Message message = new Message();
		message.setSuccess(true);
		List<User> nextList = qualityApproverService.getManagementAndProcurementConfirmTaskNextApprover(processInstanceId);
		message.setObj(nextList);	
		return message;
	}
	
}
