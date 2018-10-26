package cn.caecc.erp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.caecc.erp.modules.dao.mybatis.entity.DepartmentType;
import cn.caecc.erp.modules.service.DeptmentTypeService;
import cn.caecc.erp.support.message.Message;

@Controller
@RequestMapping("api/depttype")
public class DeptmentTypeController {
	
	@Autowired
	private DeptmentTypeService dtService;
	
	
	/** 
	* FunName: getDeptTypeList
	* Description : 获取部门类型下拉数据
	* @param：null
	* @return Message
	* @Author:shh
	* @Create Date: 2018-6-7
	*/ 
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public Message  getDeptTypeList(){
		Message message=new Message();
		List<DepartmentType> resultList=dtService.getDeptTypeList();
		message.setObj(resultList);
		return message;
		
	}
	

}
