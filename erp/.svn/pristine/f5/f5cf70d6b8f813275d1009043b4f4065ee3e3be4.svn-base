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
import cn.caecc.erp.modules.dao.ex.dto.WorkloadActivitiApplyDto;
import cn.caecc.erp.modules.dao.mybatis.entity.WorkloadActivitiApply;
import cn.caecc.erp.modules.service.WorkloadActivitiApplyService;
import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.message.Message;
import cn.caecc.erp.support.workflow.entity.ProcessInstanceApplyEntity;

@Controller
@RequestMapping("/api/workload-activiti-apply")
public class WorkloadActivitiApplyController {

	@Autowired
	private WorkloadActivitiApplyService workloadActivitiApplyService;
	
	/**
	 * 新建保存
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	@RequiresPermissions(Contants.WORKLOAD_ADD_PERMISSION)
	public Message create(@RequestBody WorkloadActivitiApply workloadActivitiApply){
		Message message = new Message();
		message.setSuccess(false);
		workloadActivitiApply = workloadActivitiApplyService.create(workloadActivitiApply);
		if (workloadActivitiApply != null) {
			message.setObj(workloadActivitiApply);
			message.setSuccess(true);
		} else {
			message.setMsg("未查询到相关数据");
		}
		return message;
	}
	
	/**
	 * 通过id查询
	 */
	@ResponseBody
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	@RequiresPermissions(Contants.MATERIAL_SELECT_PERMISSION)
	public Message findById(@PathVariable("id")int id) {
		Message message = new Message();
		message.setSuccess(false);
		WorkloadActivitiApply workloadActivitiApply = workloadActivitiApplyService.findById(id);
		if (workloadActivitiApply != null) {
			message.setObj(workloadActivitiApply);
			message.setSuccess(true);
		}else {
			message.setMsg("未查询到相关数据");
		}
		return message;
	}
	
	/**
	 * 查询详情
	 */
	@ResponseBody
	@RequestMapping(value = "/detail/{id}",method = RequestMethod.GET)
	@RequiresPermissions(Contants.MATERIAL_SELECT_PERMISSION)
	public Message findDetail(@PathVariable("id")int id) {
		Message message = new Message();
		message.setSuccess(false);
		WorkloadActivitiApplyDto workloadActivitiApplyDto = workloadActivitiApplyService.findDetail(id);
		if (workloadActivitiApplyDto != null) {
			message.setObj(workloadActivitiApplyDto);
			message.setSuccess(true);
		}else {
			message.setMsg("未查询到相关数据");
		}
		return message;
	}
	
	/**
	 * 按条件获取分页列表
	 */
	@ResponseBody
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	@RequiresPermissions(Contants.WORKLOAD_SELECT_PERMISSION)
	public Message getList(int pageNo, int pageSize, String drafts, int isSuccess, Integer userid) {
		Message message = new Message();
		message.setSuccess(false);
		if (pageNo == 0) {
			message.setMsg("请选中要查询页数");
		}else if (pageSize == 0) {
			message.setMsg("页数大小不能为0");
		}else {
			PageInfo<WorkloadActivitiApplyDto> pageInfo = workloadActivitiApplyService.getList(userid, pageNo, pageSize, drafts, isSuccess);
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
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT)
	@RequiresPermissions(Contants.WORKLOAD_UPDATE_PERMISSION)
	public Message update(@RequestBody WorkloadActivitiApply workloadActivitiApply) {
		Message message = new Message();
		message.setSuccess(false);
		workloadActivitiApply = workloadActivitiApplyService.update(workloadActivitiApply);
		if (workloadActivitiApply != null) {
			message.setObj(workloadActivitiApply);
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
		int result = workloadActivitiApplyService.deleteById(id);
		if (result > 0) {
			message.setSuccess(true);
		}else {
			message.setMsg("删除失败");
		}
		return message;
	}
	
	/**
	 * 开始流程
	 */
	@ResponseBody
	@RequestMapping(value = "/startprocess",method = RequestMethod.POST)
	@RequiresPermissions(Contants.WORKLOAD_PROCESS_START_PERMISSION)
	public Message startProcess(@RequestBody ProcessInstanceApplyEntity processInstanceApplyEntity) {
		String processDefinitionKey = processInstanceApplyEntity.getProcessDefinitionKey();
		String bussinessKey = processInstanceApplyEntity.getBussinessKey();
		Map<String, Object> variables = processInstanceApplyEntity.getVariables();
		Message message = new Message();
		message.setSuccess(false);
		try {
			int ret = workloadActivitiApplyService.startProcess(processDefinitionKey, bussinessKey, variables);
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
