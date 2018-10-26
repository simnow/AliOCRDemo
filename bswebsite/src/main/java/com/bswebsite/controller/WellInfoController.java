package com.bswebsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bswebsite.modules.dao.mybatis.entity.WellInfo;
import com.bswebsite.modules.service.WellinfoService;
import com.bswebsite.support.message.Message;

/**
 * 井基础信息操作
 * @author GaiNing
 *
 */
@RequestMapping(value="/api/well")
@Controller
public class WellInfoController {

	@Autowired
	public WellinfoService wellInfoService;
	
	/**
	 * 添加井信息
	 * @param wellinfo
	 */
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Message insertWellInfo(@RequestBody WellInfo wellinfo){
		Message message=new Message();
		int result=wellInfoService.insertWellInfo(wellinfo);
		if(result>0){
			message.setObj(result);
			return message;
		}else{
			message.setSuccess(false);
			message.setMsg("存储失败");
		}
		return message;
	}
	/**
	 * 删除井信息
	 * @param id
	 * @return
	 */
	@RequestMapping(method=RequestMethod.DELETE)
	@ResponseBody
	public Message deleteWellInfoById(@PathVariable("id") int id){
		Message message=new Message();
		boolean result=wellInfoService.deleteWellInfo(id);
		if(result){
			message.setMsg("删除成功");
			return message;
		}else{
			message.setSuccess(false);
			message.setMsg("存储失败");
		}
		return message;
	}
	
	/**
	 * 更新
	 * @param wellinfo
	 * @return
	 */
	@RequestMapping(method=RequestMethod.PUT)
	@ResponseBody
	public Message updateWellInfo(@RequestBody WellInfo wellinfo){
		Message message=new Message();
		if(wellInfoService.updateWellInfo(wellinfo)){
			return message;
		}else{
			message.setMsg("更新失败");
			message.setSuccess(false);
			return message;
		}
	}
	/**
	 * 查询单井
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Message selectWellInfoById(@PathVariable("id") int id){
		Message message=new Message();
		WellInfo wellinfo=wellInfoService.selectWellInfoById(id);
		message.setObj(wellinfo);
		return message;
	}
	/**
	 * 查询状态井列表(state:0在钻井)
	 * @param state
	 * @return
	 */
	@RequestMapping(value="/state/{state}",method=RequestMethod.GET)
	@ResponseBody
	public Message selectWellInfoByState(@PathVariable("state") int state){
		Message message=new Message();
		List<WellInfo> wellinfolist=wellInfoService.selectWellInfoByState(state);
		message.setObj(wellinfolist);
		return message;
	}
}
