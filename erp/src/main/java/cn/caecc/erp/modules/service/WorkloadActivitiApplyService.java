package cn.caecc.erp.modules.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;

import cn.caecc.erp.modules.dao.ex.dto.WorkloadActivitiApplyDto;
import cn.caecc.erp.modules.dao.mybatis.entity.WorkloadActivitiApply;

public interface WorkloadActivitiApplyService {

	WorkloadActivitiApply create(WorkloadActivitiApply workloadActivitiApply);

	WorkloadActivitiApply findById(int id);

	WorkloadActivitiApplyDto findDetail(int id);

	PageInfo<WorkloadActivitiApplyDto> getList(Integer loginUserId, int pageNo, int pageSize, String drafts, int isSuccess);

	WorkloadActivitiApply update(WorkloadActivitiApply workloadActivitiApply);

	int startProcess(String processDefinitionKey, String bussinessKey, Map<String, Object> variables) throws Exception;

	void setSuccess(int id);

	int deleteById(int id);
}
