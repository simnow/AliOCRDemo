package cn.caecc.erp.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageInfo;

import cn.caecc.erp.modules.dao.ex.dto.ContractActivitiApplyExDto;
import cn.caecc.erp.modules.dao.ex.dto.ContractGoodsRelationshipDto;
import cn.caecc.erp.modules.dao.mybatis.entity.ContractGoodsRelationship;
import cn.caecc.erp.modules.service.ContractGoodsRelationshipService;
import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.message.Message;

@Controller
@RequestMapping("/api/contract-goods-relationship")
public class ContractGoodsRelationshipController {

	@Autowired
	private ContractGoodsRelationshipService cgRelationshipService;

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	@RequiresPermissions(Contants.CONTRACT_ADD_PERMISSION)
	public Message create(@RequestBody List<ContractGoodsRelationship> list) {
		Message message = new Message();
		message.setSuccess(false);
		list = cgRelationshipService.create(list);
		if (list != null) {
			message.setObj(list);
			message.setSuccess(true);
		} else {
			message.setMsg("未查询到相关数据");
		}
		return message;
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@RequiresPermissions(Contants.CONTRACT_SELECT_PERMISSION)
	public Message findById(int id) {
		Message message = new Message();
		message.setSuccess(false);
		ContractGoodsRelationship contractGoodsRelationship = cgRelationshipService.findById(id);
		if (contractGoodsRelationship != null) {
			message.setObj(contractGoodsRelationship);
			message.setSuccess(true);
		} else {
			message.setMsg("未查询到相关数据");
		}
		return message;
	}

	@ResponseBody
	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	@RequiresPermissions(Contants.CONTRACT_SELECT_PERMISSION)
	public Message findDetail(int id) {
		Message message = new Message();
		message.setSuccess(false);
		ContractGoodsRelationship contractGoodsRelationship = cgRelationshipService.findDetail(id);
		if (contractGoodsRelationship != null) {
			message.setObj(contractGoodsRelationship);
			message.setSuccess(true);
		} else {
			message.setMsg("未查询到相关数据");
		}
		return message;
	}

	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@RequiresPermissions(Contants.CONTRACT_SELECT_PERMISSION)
	public Message getList(int pageNo, int pageSize, String gcode) {
		Message message = new Message();
		message.setSuccess(false);
		if (pageNo == 0) {
			message.setMsg("请选中要查询页数");
		} else if (pageSize == 0) {
			message.setMsg("页数大小不能为0");
		} else {
			PageInfo<ContractGoodsRelationshipDto> pageInfo = cgRelationshipService.getList(pageNo, pageSize, gcode);
			if (pageInfo != null) {
				message.setObj(pageInfo);
				message.setSuccess(true);
			} else {
				message.setMsg("获取列表失败");
			}
		}
		return message;
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT)
	@RequiresPermissions(Contants.CONTRACT_UPDATE_PERMISSION)
	public Message batchUpdate(@RequestBody ContractActivitiApplyExDto contractActivitiApplyExDto) {
		Message message = new Message();
		message.setSuccess(false);
		if (contractActivitiApplyExDto == null || contractActivitiApplyExDto.getId() == 0) {
			message.setMsg("参数异常");
		}else {
			message = cgRelationshipService.batchUpdate(contractActivitiApplyExDto);
			if (message.isSuccess()) {
				message.setSuccess(true);
			} else {
				message.setMsg("未查询到相关数据");
			}
		}
		return message;
	}

	@ResponseBody
	@RequestMapping(value = "/update",method = RequestMethod.PUT)
	@RequiresPermissions(Contants.CONTRACT_UPDATE_PERMISSION)
	public Message update(@RequestBody ContractActivitiApplyExDto contractActivitiApplyExDto) {
		Message message = new Message();
		message.setSuccess(false);
		if (contractActivitiApplyExDto == null || contractActivitiApplyExDto.getId() == 0) {
			message.setMsg("参数异常");
		}else {
			ContractActivitiApplyExDto cActivitiApplyExDto = cgRelationshipService.update(contractActivitiApplyExDto);
			if (cActivitiApplyExDto != null) {
				message.setObj(cActivitiApplyExDto);
				message.setSuccess(true);
			} else {
				message.setMsg("更新失败");
			}
		}
		return message;
	}
	
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@RequiresPermissions(Contants.CONTRACT_DELETE_PERMISSION)
	public Message deleteById(int id) {
		Message message = new Message();
		message.setSuccess(false);
		try {
			int ret = cgRelationshipService.deleteById(id);
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
}
