package cn.caecc.erp.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.caecc.erp.modules.dao.ex.dto.InputRole;
import cn.caecc.erp.modules.dao.ex.dto.OutputRole;
import cn.caecc.erp.modules.dao.mybatis.entity.Role;
import cn.caecc.erp.modules.service.DepartmentRoleRelationshipService;
import cn.caecc.erp.modules.service.RoleService;
import cn.caecc.erp.modules.service.UserRoleRelationshipService;
import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.message.Message;

@Controller
@RequestMapping("/api/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private DepartmentRoleRelationshipService dRService;
	@Autowired
	private UserRoleRelationshipService uRService;

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@RequiresPermissions(Contants.ROLE_MANAGE_PERMISSION)
	public Message findById(@PathVariable("id") int id) {
		Message message = new Message();
		message.setSuccess(true);
		Role role =  roleService.findById(id);
		message.setObj(role);
		return message;
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	@RequiresPermissions(Contants.ROLE_MANAGE_PERMISSION)
	public Message list() {
		Message message = new Message();
		message.setSuccess(true);
		List<Role> roleList = roleService.getRoleAll();
		message.setObj(roleList);
		return message;
	}

	@ResponseBody
	@RequestMapping(value = "/canModify", method = RequestMethod.GET)
	@RequiresPermissions(Contants.ROLE_MANAGE_PERMISSION)
	public Message assignRoles() {
		Message message = new Message();
		message.setSuccess(true);
		List<Role> roleList = roleService.getRoleByCanmodify();
		message.setObj(roleList);
		return message;
	}

	@ResponseBody
	@RequestMapping(value = "/userid/{id}", method = RequestMethod.GET)
	@RequiresPermissions(Contants.ROLE_MANAGE_PERMISSION)
	public Message checkRoles(@PathVariable("id") int id) {

		Message message = new Message();
		message.setSuccess(true);
		List<OutputRole> listUR = uRService.getUserCanmodifyOutputRole(id);
		message.setObj(listUR);
	//	String a = JacksonUtil.objectToJson(listUR);
	//	System.out.println(a);
		return message;
	}

	@ResponseBody
	@RequestMapping(value = "/departid/{id}", method = RequestMethod.GET)
	@RequiresPermissions(Contants.ROLE_MANAGE_PERMISSION)
	public Message checkDMRoles(@PathVariable("id") int id) {
		Message message = new Message();
		message.setSuccess(true);
		List<OutputRole> listUR = dRService.getDepartmentCanmodifyOutputRole(id);
		message.setObj(listUR);
	//	String a = JacksonUtil.objectToJson(listUR);
	//	System.out.println(a);
		return message;

	}
	@ResponseBody
	@RequestMapping(value = "/modify-role", method = RequestMethod.POST)
	@RequiresPermissions(Contants.ROLE_MANAGE_PERMISSION)
	public Message modifyRole(@RequestBody InputRole inputRole) {
		Message message = new Message();
	//	String a = JacksonUtil.objectToJson(inputRole);
	//	System.out.println(a);
		message.setSuccess(false);
		if (uRService.modifyUserRoles(inputRole)) {
			message.setSuccess(true);
		}
		return message;
	}
	@ResponseBody
	@RequestMapping(value = "/modify-dm-role", method = RequestMethod.POST)
	@RequiresPermissions(Contants.ROLE_MANAGE_PERMISSION)
	public Message modifyDMRole(@RequestBody InputRole inputRole) {
		Message message = new Message();
		message.setSuccess(false);
		if (dRService.modifyDepartmentRoles(inputRole)) {
			message.setSuccess(true);
		}
		return message;
	}

}
