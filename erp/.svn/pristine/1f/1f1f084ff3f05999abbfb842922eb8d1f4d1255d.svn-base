package cn.caecc.erp.controller;

import java.util.Map;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageInfo;
import cn.caecc.erp.modules.dao.ex.dto.OfficesupplyActivitiApplyDto;
import cn.caecc.erp.modules.dao.ex.dto.OfficesupplyActivitiApplyExDto;
import cn.caecc.erp.modules.dao.mybatis.entity.OfficesupplyActivitiApply;
import cn.caecc.erp.modules.service.OfficesupplyActivitiApplyService;
import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.message.Message;
import cn.caecc.erp.support.workflow.entity.ProcessInstanceApplyEntity;

@Controller
@RequestMapping("/api/officesupply-activiti-apply")
public class OfficesupplyActivitiApplyController {

	@Autowired
	private OfficesupplyActivitiApplyService officesupplyActivitiApplyService;
	
	/**
	 * 新建办公用品采购申请
	 * @param officesupplyActivitiApplyExDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	@RequiresPermissions(Contants.OFFICESUPPLY_ADD_PERMISSION)
	public Message create(@RequestBody OfficesupplyActivitiApply officesupplyActivitiApply) {
		Message message = new Message();
		message.setSuccess(false);
		officesupplyActivitiApply =  officesupplyActivitiApplyService.create(officesupplyActivitiApply);
		if (officesupplyActivitiApply != null) {
			message.setObj(officesupplyActivitiApply);
			message.setSuccess(true);
		} else {
			message.setMsg("未查询到相关数据");
		}
		return message;
	}
	
	/**
	 * 通过id查询
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	@RequiresPermissions(Contants.OFFICESUPPLY_SELECT_PERMISSION)
	public Message findById(@PathVariable("id")int id) {
		Message message = new Message();
		message.setSuccess(false);
		OfficesupplyActivitiApply officesupplyActivitiApply = officesupplyActivitiApplyService.findById(id);
		if (officesupplyActivitiApply != null) {
			message.setObj(officesupplyActivitiApply);
			message.setSuccess(true);
		}else {
			message.setMsg("未查询到相关数据");
		}
		return message;
	}
	
	/**
	 * 查询详情
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/detail/{id}",method = RequestMethod.GET)
	@RequiresPermissions(Contants.OFFICESUPPLY_SELECT_PERMISSION)
	public Message findDetail(@PathVariable("id")int id) {
		Message message = new Message();
		message.setSuccess(false);
		OfficesupplyActivitiApplyDto officesupplyActivitiApplyDto = officesupplyActivitiApplyService.findDetail(id);
		if (officesupplyActivitiApplyDto != null) {
			message.setObj(officesupplyActivitiApplyDto);
			message.setSuccess(true);
		}else {
			message.setMsg("未查询到相关数据");
		}
		return message;
	}
	
	/**
	 * 按条件获取分页列表
	 * @param pageNo
	 * @param pageSize
	 * @param drafts
	 * @param isSuccess
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	@RequiresPermissions(Contants.OFFICESUPPLY_SELECT_PERMISSION)
	public Message getList(Integer userid, int pageNo, int pageSize, String drafts, int isSuccess) {
		Message message = new Message();
		message.setSuccess(false);
		if (pageNo == 0) {
			message.setMsg("请选中要查询的页数");
		}else if (pageSize == 0) {
			message.setMsg("每页显示大小不能为0");
		}else {
				
			PageInfo<OfficesupplyActivitiApplyDto> pageInfo = officesupplyActivitiApplyService.getList(userid, pageNo, pageSize, drafts, isSuccess);
			if (pageInfo != null) {
				message.setObj(pageInfo);
				message.setSuccess(true);
			}else {
				message.setMsg("获取列表失败");
			}
		}
		return message;
	}
	
	/**
	 * 更新
	 * @param officesupplyActivitiApplyExDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT)
	@RequiresPermissions(Contants.OFFICESUPPLY_UPDATE_PERMISSION)
	public Message update(@RequestBody OfficesupplyActivitiApplyExDto officesupplyActivitiApplyExDto) {		
		Message message = new Message();
		message.setSuccess(false);
		officesupplyActivitiApplyExDto = officesupplyActivitiApplyService.update(officesupplyActivitiApplyExDto);
		if (officesupplyActivitiApplyExDto != null) {
			message.setObj(officesupplyActivitiApplyExDto);
			message.setSuccess(true);
		} else {
			message.setMsg("未查询到相关数据");
		}
		return message;
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	public Message deleteById(@PathVariable("id")int id) {
		Message message = new Message();
		message.setSuccess(false);
		int result = officesupplyActivitiApplyService.deleteById(id);
		if (result > 0) {
			message.setSuccess(true);
		}else {
			message.setMsg("删除失败");
		}
		return message;
	}
	
	/**
	 * 开始流程
	 * @param processInstanceApplyEntity
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/startprocess",method = RequestMethod.POST)
	@RequiresPermissions(Contants.OFFICESUPPLY_PROCESS_START_PERMISSION)
	public Message startProcess(@RequestBody ProcessInstanceApplyEntity processInstanceApplyEntity) {
		String processDefinitionKey = processInstanceApplyEntity.getProcessDefinitionKey();
		String bussinessKey = processInstanceApplyEntity.getBussinessKey();
		Map<String, Object> variables = processInstanceApplyEntity.getVariables();
		Message message = new Message();
		message.setSuccess(false);
		try {
			int ret = officesupplyActivitiApplyService.startProcess(processDefinitionKey, bussinessKey, variables);
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
}
