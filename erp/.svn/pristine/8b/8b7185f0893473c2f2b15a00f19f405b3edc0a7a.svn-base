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

import cn.caecc.erp.modules.dao.ex.dto.QualityfeedbackActivitiApplyDto;
import cn.caecc.erp.modules.dao.mybatis.entity.QualityfeedbackActivitiApply;
import cn.caecc.erp.modules.service.QualityfeedbackActivitiApplyService;
import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.message.Message;
import cn.caecc.erp.support.workflow.entity.ProcessInstanceApplyEntity;

@Controller
@RequestMapping("/api/qualityfeedback-activiti-apply")
public class QualityfeedbackActivitiApplyController {

	@Autowired
	private QualityfeedbackActivitiApplyService qualityfeedbackActivitiApplyService;

	/**
	 * 新建
	 * 
	 * @param qualityfeedbackActivitiApply
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	@RequiresPermissions(Contants.QUALITY_FEEDBACK_ADD_PERMISSION)
	public Message create(@RequestBody QualityfeedbackActivitiApply qualityfeedbackActivitiApply) {
		Message message = new Message();
		message.setSuccess(false);
		qualityfeedbackActivitiApply = qualityfeedbackActivitiApplyService.create(qualityfeedbackActivitiApply);
		if (qualityfeedbackActivitiApply != null) {
			message.setObj(qualityfeedbackActivitiApply);
			message.setSuccess(true);
		} else {
			message.setMsg("未查询到相关数据");
		}
		return message;
	}

	/**
	 * 查询详情
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	@RequiresPermissions(Contants.QUALITY_FEEDBACK_SELECT_PERMISSION)
	public Message findDeatil(@PathVariable("id") int id) {
		Message message = new Message();
		message.setSuccess(false);
		QualityfeedbackActivitiApplyDto qualityfeedbackActivitiApplyDto = qualityfeedbackActivitiApplyService
				.findDeatil(id);
		if (qualityfeedbackActivitiApplyDto != null) {
			message.setObj(qualityfeedbackActivitiApplyDto);
			message.setSuccess(true);
		} else {
			message.setMsg("未查询到相关数据");
		}
		return message;
	}

	/**
	 * 按条件获取分页列表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param drafts
	 * @param isSuccess
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@RequiresPermissions(Contants.QUALITY_FEEDBACK_SELECT_PERMISSION)
	public Message getList(int pageNo, int pageSize, String drafts, int isSuccess, Integer sid, Integer userid) {
		Message message = new Message();
		message.setSuccess(false);
		if (pageNo == 0) {
			message.setMsg("请选中要查询页数");
		} else if (pageSize == 0) {
			message.setMsg("每页显示数不能为0");
		} else {
			PageInfo<QualityfeedbackActivitiApplyDto> pageInfo = qualityfeedbackActivitiApplyService.getList(userid,
					pageNo, pageSize, drafts, isSuccess, sid);
			if (pageInfo != null) {
				message.setObj(pageInfo);
				message.setSuccess(true);
			} else {
				message.setMsg("获取列表失败");
			}
		}
		return message;
	}

	/**
	 * 更新
	 * 
	 * @param qualityfeedbackActivitiApply
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT)
	@RequiresPermissions(Contants.QUALITY_FEEDBACK_UPDATE_PERMISSION)
	public Message update(@RequestBody QualityfeedbackActivitiApply qualityfeedbackActivitiApply) {
		Message message = new Message();
		message.setSuccess(false);
		qualityfeedbackActivitiApply = qualityfeedbackActivitiApplyService.update(qualityfeedbackActivitiApply);
		if (qualityfeedbackActivitiApply != null) {
			message.setObj(qualityfeedbackActivitiApply);
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
		int result = qualityfeedbackActivitiApplyService.deleteById(id);
		if (result > 0) {
			message.setSuccess(true);
		}else {
			message.setMsg("删除失败");
		}
		return message;
	}

	/**
	 * 开始流程
	 * 
	 * @param processInstanceApplyEntity
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/startprocess", method = RequestMethod.POST)
	@RequiresPermissions(Contants.QUALITY_FEEDBACK_PROCESS_START_PERMISSION)
	public Message startProccess(@RequestBody ProcessInstanceApplyEntity processInstanceApplyEntity) {
		String processDefinitionKey = processInstanceApplyEntity.getProcessDefinitionKey();
		String bussinessKey = processInstanceApplyEntity.getBussinessKey();
		Map<String, Object> variables = processInstanceApplyEntity.getVariables();
		Message message = new Message();
		message.setSuccess(false);
		try {
			int ret = qualityfeedbackActivitiApplyService.startProcess(processDefinitionKey, bussinessKey, variables);
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
