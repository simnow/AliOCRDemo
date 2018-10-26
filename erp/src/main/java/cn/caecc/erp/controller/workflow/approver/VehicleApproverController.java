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

import cn.caecc.erp.modules.dao.mybatis.entity.User;
import cn.caecc.erp.support.message.Message;
import cn.caecc.erp.support.workflow.approver.service.VehicleApproverService;

/**
 * @author weizhen
 *
 */
@Controller
@RequestMapping(value="/api/approver/VehicleProcess")
public class VehicleApproverController {
	@Autowired
	private VehicleApproverService vehicleApproverService;
	/**
	 * 
	 */
	public VehicleApproverController() {
		// TODO Auto-generated constructor stub
	}

	@ResponseBody
	@RequestMapping(value = "/ApplyTask", method = RequestMethod.GET)
	public Message getApplyTaskNextApprover(int startUserId) {
		Message message = new Message();
		message.setSuccess(true);
		List<User> userList = vehicleApproverService.getApplyTaskNextApprover(startUserId); 
		message.setObj(userList);
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/DepartmentalLeaderTask", method = RequestMethod.GET)
	public Message getDepartmentalLeaderTaskNextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> userList = vehicleApproverService.getDepartmentalLeaderTaskNextApprover(); 
		message.setObj(userList);
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/GeneralManagerOfficeTask", method = RequestMethod.GET)
	public Message getGeneralManagerOfficeTaskNextApprover() {
		Message message = new Message();
		message.setSuccess(true);
		List<User> userList = vehicleApproverService.getGeneralManagerOfficeTaskNextApprover(); 
		message.setObj(userList);
		return message;
	}
}