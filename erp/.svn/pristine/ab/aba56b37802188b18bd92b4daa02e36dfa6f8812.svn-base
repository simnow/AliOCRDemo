package cn.caecc.erp.controller;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageInfo;
import cn.caecc.erp.modules.dao.ex.dto.OutsideActivitiApplyDto;
import cn.caecc.erp.modules.dao.mybatis.entity.OutsideActivitiApply;
import cn.caecc.erp.modules.service.OutsideActivitiApplyService;
import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.message.Message;
import cn.caecc.erp.support.workflow.entity.ProcessInstanceApplyEntity;

@Controller
@RequestMapping(value="/api/outside-activiti-apply")
public class OutsideActivitiApplyController {
	@Autowired
	private  OutsideActivitiApplyService  osApplyService;
	
	/** 
	* FunName:addOutsideApply(
	* Description : 添加外勤申请
	* @param：OutsideActivitiApply
	* @return Message
	* @Author:shh
	* @Create Date: 2018-06-22
	*/ 
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(Contants.OUTSIDE_ADD_PERMISSION)
	public  Message  addOutsideApply(@RequestBody OutsideActivitiApply outsideActivitiApply){
		
		Message message = new Message();
		message.setSuccess(false);
		try {
			outsideActivitiApply =  osApplyService.addOutsideActivitiApply(outsideActivitiApply);
			message.setSuccess(true);
			message.setObj(outsideActivitiApply);
			
		} catch (Exception ex) {
			message.setMsg(ex.getMessage());
		}
		return message;
		
	}
	
	/** 
	* FunName:deleteOutsideApply
	* Description : 删除申请
	* @param： 
	* @return Message
	* @Author:shh
	* @Create Date: 2018-06-22
	*/ 
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public  Message  deleteOutsideApply(@PathVariable("id") int id){
		Message message = new Message();
		message.setSuccess(false);
		try {
			int ret = osApplyService.deleteOsActivitiApply(id);
			if (ret > 0) {
				message.setSuccess(true);
			} else {
				message.setMsg("删除失败");
			}
		} catch (Exception ex) {
			message.setMsg(ex.getMessage());
		}
		return message;		
		
	}
	/** 
	* FunName:getAcApplyPageList
	* Description : 获取列表
	* @param：
	* @return Message
	* @Author:shh
	* @Create Date: 2018-06-21
	*/ 
	@RequestMapping(value="/applylist",method=RequestMethod.GET)
	@ResponseBody
	@RequiresPermissions(Contants.OUTSIDE_SELECT_PERMISSION)
	public  Message  getOsApplyPageList(@RequestParam(value="state",required=true) int state
			,@RequestParam(value="pagesize",required=true) int pagesize
			,@RequestParam(value="pageno",required=true) int pageno
			,@RequestParam(value="uid",required=true) int uid){
		
		Message message = new Message();
		message.setSuccess(false);
		try {
			PageInfo<OutsideActivitiApply> outsideActivitiApplyList = osApplyService.getOsApplyPageList(state,pagesize,pageno,uid);
			message.setObj(outsideActivitiApplyList);
			message.setSuccess(true);
			
		} catch (Exception ex) {
			message.setMsg(ex.getMessage());
		}
		return message;	
		
	}
	
	/** 
	* FunName:getAcApplyPageList
	* Description : 获取列表
	* @param：
	* @return Message
	* @Author:shh
	* @Create Date: 2018-06-21
	*/ 
	@RequestMapping(value="/startprocess",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(Contants.OUTSIDE_PROCESS_START_PERMISSION)
	public  Message  startProcess(@RequestBody ProcessInstanceApplyEntity processInstanceApplyEntity) {
		String processDefinitionKey = processInstanceApplyEntity.getProcessDefinitionKey();
		String bussinessKey = processInstanceApplyEntity.getBussinessKey();
		Map<String, Object> variables = processInstanceApplyEntity.getVariables();
		Message message = new Message();
		message.setSuccess(false);
		try {
			int ret = osApplyService.startOsActivitiApply(processDefinitionKey, bussinessKey, variables);
			if (ret > 0) {
				message.setSuccess(true);
			} else {
				message.setMsg("开启流程失败");
			}
		} catch (Exception ex) {
			message.setMsg(ex.getMessage());
		}
		return message;		
	}
	
	/** 
	* FunName: getOsApplyById
	* Description : 
	* @param：
	* @return Message
	* @Author:shh
	* @Create Date: 2018-06-21
	*/ 
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	@ResponseBody
	@RequiresPermissions(Contants.OUTSIDE_SELECT_PERMISSION)
	public  Message  getOsApplyById(@PathVariable("id") int id) {
		Message message = new Message();
		message.setSuccess(false);
		try {
			OutsideActivitiApplyDto outsideActivitiApplyDto = osApplyService.getOsApplyById(id);
			message.setObj(outsideActivitiApplyDto);
			message.setSuccess(true);
			
		} catch (Exception ex) {
			message.setMsg(ex.getMessage());
		}
		return message;	
		
	}
	
	/** 
	* FunName: updateOsApplyById
	* Description : 
	* @param：
	* @return Message
	* @Author:shh
	* @Create Date: 2018-06-21
	*/ 
	@RequestMapping(method=RequestMethod.PUT)
	@ResponseBody
	@RequiresPermissions(Contants.OUTSIDE_UPDATE_PERMISSION)
	public  Message  updateOsApplyById(@RequestBody OutsideActivitiApply outsideActivitiApply) {
		Message message = new Message();
		message.setSuccess(false);
		try {
			outsideActivitiApply = osApplyService.updateOsApply(outsideActivitiApply);
			message.setObj(outsideActivitiApply);
			message.setSuccess(true);
		} catch (Exception ex) {
			message.setMsg(ex.getMessage());
		}
		return message;	
		
	}
	

}
