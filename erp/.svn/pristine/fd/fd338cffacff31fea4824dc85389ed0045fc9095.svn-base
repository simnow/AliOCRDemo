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
import cn.caecc.erp.support.workflow.approver.service.SealApproverService;

/**
 * @author weizhen
 *
 */
@Controller
@RequestMapping(value="/api/approver/SealProcess")
public class SealApproverController extends BaseController {

	@Autowired
	private SealApproverService sealApproverService;
	/**
	 * 
	 */
	public SealApproverController() {
		// TODO Auto-generated constructor stub
	}

	@ResponseBody
	@RequestMapping(value = "/ApplyTask", method = RequestMethod.GET)
	public Message getApplyTaskNextApprover(int startUserId) {
		Message message = new Message();
		message.setSuccess(true);
		List<User> userList = sealApproverService.getApplyTaskNextApprover(startUserId);
		message.setObj(userList);
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/DepartmentalLeaderTask", method = RequestMethod.GET)
	public Message getDepartmentalLeaderTaskNextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> userList = sealApproverService.getDepartmentalLeaderTaskNextApprover();
		message.setObj(userList);
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/SecuritiesAffairsTask", method = RequestMethod.GET)
	public Message getSecuritiesAffairsTaskNextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> userList = sealApproverService.getSecuritiesAffairsTaskNextApprover();
		message.setObj(userList);
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/GeneralManagerTask", method = RequestMethod.GET)
	public Message getGeneralManagerTaskNextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> userList = sealApproverService.getGeneralManagerTaskNextApprover();
		message.setObj(userList);
		return message;
	}
}
