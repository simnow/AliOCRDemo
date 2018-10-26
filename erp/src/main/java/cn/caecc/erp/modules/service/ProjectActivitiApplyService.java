package cn.caecc.erp.modules.service;

import java.util.Map;
import com.github.pagehelper.PageInfo;
import cn.caecc.erp.modules.dao.ex.dto.ProjectActivitiApplyDto;
import cn.caecc.erp.modules.dao.mybatis.entity.ProjectActivitiApply;

public interface ProjectActivitiApplyService {

	public ProjectActivitiApply create(ProjectActivitiApply projectActivitiApply);
	
	public ProjectActivitiApply findById(int id);
	
	public ProjectActivitiApplyDto findDetail(int id);
	
	public ProjectActivitiApply findByCode(String code);
	
	public PageInfo<ProjectActivitiApplyDto> getList(int pageNo, int pageSize, String drafts, int isSuccess, Integer userid, String name);
	
	public ProjectActivitiApply updateProjectApproval(ProjectActivitiApply projectActivitiApply);
	
	public int delProjectApprovalById(int id);
	
	public int delProjectApprovalByCode(String code);
	
	public int startProcess(String processDefinitionKey, String bussinessKey,
			Map<String, Object> variables)  throws Exception;
	
	public void setSuccess(int id);
}
