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
import cn.caecc.erp.modules.dao.ex.dto.VehicleActivitiApplyDto;
import cn.caecc.erp.modules.dao.mybatis.entity.VehicleActivitiApply;
import cn.caecc.erp.modules.service.VehicleActivitiApplyService;
import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.message.Message;
import cn.caecc.erp.support.workflow.entity.ProcessInstanceApplyEntity;

@Controller
@RequestMapping(value="/api/vehicle-activiti-apply")
public class VehicleActivitiApplyController {
	@Autowired
	private  VehicleActivitiApplyService veApplyService;
	
	/** 
	* FunName:addVehicleApply
	* Description : 添加用印申请
	* @param：VehicleActivitiApply
	* @return Message
	* @Author:shh
	* @Create Date: 2018-06-26
	*/ 
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(Contants.VEHICLE_ADD_PERMISSION)
	public  Message  addVehicleApply(@RequestBody VehicleActivitiApply vehicleActivitiApply){
		Message message = new Message();
		message.setSuccess(false);
		vehicleActivitiApply =  veApplyService.addVehicleActivitiApply(vehicleActivitiApply);
		if (vehicleActivitiApply != null) {
			message.setObj(vehicleActivitiApply);
			message.setSuccess(true);
		} else {
			message.setMsg("未查询到相关数据");
		}
		return message;		
	}
	
	/** 
	* FunName:deleteVehicleApply
	* Description : 删除申请
	* @param：deleteVehicleApply
	* @return Message
	* @Author:shh
	* @Create Date: 2018-06-26
	*/ 
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public  Message  deleteVehicleApply(@PathVariable("id") int id){
		Message message = new Message();
		message.setSuccess(false);
		try {
			int ret = veApplyService.deleteVehicleActivitiApply(id);
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
	* FunName:getVehicleApplyPageList
	* Description : 获取列表
	* @param：
	* @return Message
	* @Author:shh
	* @Create Date: 2018-06-26
	*/ 
	@RequestMapping(value="/applylist",method=RequestMethod.GET)
	@ResponseBody
	@RequiresPermissions(Contants.VEHICLE_SELECT_PERMISSION)
	public  Message  getVehicleApplyPageList(@RequestParam(value="state",required=true) int state
			,@RequestParam(value="pagesize",required=true) int pagesize
			,@RequestParam(value="pageno",required=true) int pageno
			,@RequestParam(value="uid",required=true) int uid){
		Message message = new Message();
		message.setSuccess(false);
		try {
			PageInfo<VehicleActivitiApply> vehicleActivitiApplyList = veApplyService.getVehicleApplyPageList(state,pagesize,pageno,uid);
			message.setObj(vehicleActivitiApplyList);
			message.setSuccess(true);
			
		} catch (Exception ex) {
			message.setMsg(ex.getMessage());
		}
		return message;	
		
	}
	
	/** 
	* FunName:startVehicleApply
	* Description : 获取列表
	* @param：
	* @return Message
	* @Author:shh
	* @Create Date: 2018-06-21
	*/ 
	@RequestMapping(value="/startprocess",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(Contants.VEHICLE_PROCESS_START_PERMISSION)
	public  Message  startVehicleApply(@RequestBody ProcessInstanceApplyEntity processInstanceApplyEntity) {
		String processDefinitionKey = processInstanceApplyEntity.getProcessDefinitionKey();
		String bussinessKey = processInstanceApplyEntity.getBussinessKey();
		Map<String, Object> variables = processInstanceApplyEntity.getVariables();
		Message message = new Message();
		message.setSuccess(false);
		try {
			int ret = veApplyService.startVehicleActivitiApply(processDefinitionKey, bussinessKey, variables);
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
	* FunName:getVehicleApplyById
	* Description : 获取详情
	* @param：
	* @return Message
	* @Author:shh
	* @Create Date: 2018-06-27
	*/ 
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	@ResponseBody
	@RequiresPermissions(Contants.VEHICLE_SELECT_PERMISSION)
	public  Message  getVehicleApplyById(@PathVariable("id") int id) {
		Message message = new Message();
		message.setSuccess(false);
		try {
			VehicleActivitiApplyDto vehicleActivitiApplyDto = veApplyService.getVehicleActivitiApply(id);
			message.setObj(vehicleActivitiApplyDto);
			message.setSuccess(true);
			
		} catch (Exception ex) {
			message.setMsg(ex.getMessage());
		}
		return message;	
	}
	/** 
	* FunName:updateVehicleApplyById
	* Description :修改
	* @param：
	* @return Message
	* @Author:shh
	* @Create Date: 2018-06-29
	*/ 
	@RequestMapping(method=RequestMethod.PUT)
	@ResponseBody
	@RequiresPermissions(Contants.VEHICLE_UPDATE_PERMISSION)
	public  Message updateVehicleApplyById(@RequestBody VehicleActivitiApply vehicleActivitiApply) {
		Message message = new Message();
		message.setSuccess(false);
		try {
			vehicleActivitiApply = veApplyService.updateVehicleApply(vehicleActivitiApply);
			message.setObj(vehicleActivitiApply);
			message.setSuccess(true);
		} catch (Exception ex) {
			message.setMsg(ex.getMessage());
		}
		return message;	
		
	}

}
