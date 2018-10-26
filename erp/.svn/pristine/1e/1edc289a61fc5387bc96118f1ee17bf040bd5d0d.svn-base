package cn.caecc.erp.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.caecc.erp.modules.dao.mybatis.entity.WorkingPlan;
import cn.caecc.erp.modules.service.WorKingPlanService;
import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.message.Message;

@Controller
@RequestMapping(value="/api/workingplan")
public class WorKingPlanController {
	
	@Autowired
	private WorKingPlanService wpService;
	
	/**
	 * FunName:addWorkingPlan : 添加工作计划
	 * 
	 * @param：WorkingPlan
	 * @return Message
	 * @Author:shh
	 * @Create Date: 2018-07-30
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(Contants.WORK_PLAN_ADD_PERMISSION)
	public Message addWorkingPlan(@RequestBody WorkingPlan workingPlan) {
	
			Message message=new Message();
			wpService.addWorkingPlan(workingPlan);
			message.setObj(workingPlan);
			return message;
	}
	/**
	 * FunName:updateWorkingPlan : 修改工作计划
	 * 
	 * @param：WorkingPlan
	 * @return Message
	 * @Author:shh
	 * @Create Date: 2018-07-30
	 */
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	@RequiresPermissions(Contants.WORK_PLAN_UPDATE_PERMISSION)
	public Message updateWorkingPlan(@RequestBody WorkingPlan workingPlan) {
	
			Message message=new Message();
			wpService.updateWorkingPlan(workingPlan);
			message.setObj(workingPlan);
			return message;
	}
	/** 
	* FunName:deleteWorkingPlan
	* Description :删除
	* @param：id
	* @return Message
	* @Author:shh
	* @Create Date: 2018-07-30
	*/ 
	@RequestMapping(method=RequestMethod.DELETE)
	@ResponseBody
	@RequiresPermissions(Contants.WORK_PLAN_DELETE_PERMISSION)
	public  Message deleteWorkingPlan(int id){	
		wpService.deleteWorkingPlan(id);
		return new Message();
	}
	/** 
	* FunName:getDtoById
	* Description :查询
	* @param：id
	* @return Message
	* @Author:shh
	* @Create Date: 2018-07-30
	*/ 
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	@ResponseBody
	public  Message  getDtoById(@PathVariable("id") int id){
		Message message=new Message();	
		message.setObj(wpService.getWorkingPlanById(id));
		return message;
	}
	/** 
	* FunName:getTodayWorkingPlan
	* Description :查询本周计划
	* @param：id
	* @return Message
	* @Author:shh
	* @Create Date: 2018-07-31
	*/ 
	@RequestMapping(value="/today",method=RequestMethod.GET)
	@ResponseBody
	public  Message  getTodayWorkingPlan(@RequestParam(value="pagesize",required=true) int pagesize,@RequestParam(value="pageno",required=true) int pageno){
		Message message=new Message();	
		message.setObj(wpService.getTodayWorkingPlan(pagesize, pageno));
		return message;
	}
	/** 
	* FunName:getMoreWorkingPlan
	* Description :查询
	* @param：id
	* @return Message
	* @Author:shh
	* @Create Date: 2018-07-31
	*/ 
	@RequestMapping(value="/more",method=RequestMethod.GET)
	@ResponseBody
	public  Message  getMoreWorkingPlan(@RequestParam(value="pagesize",required=true) int pagesize,@RequestParam(value="pageno",required=true) int pageno){
		Message message=new Message();	
		message.setObj(wpService.getMoreWorkingPlan(pagesize, pageno));
		return message;
	}

}
