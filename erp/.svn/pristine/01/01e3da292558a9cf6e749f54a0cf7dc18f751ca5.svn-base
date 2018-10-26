package cn.caecc.erp.modules.dao.ex.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.caecc.erp.modules.dao.ex.dto.ProjectActivitiApplyDto;

public interface ProjectActivitiApplyExMapper {
	
	List<ProjectActivitiApplyDto> getList(@Param(value = "userid")Integer userid, @Param(value = "drafts")String drafts, @Param(value = "isSuccess")Integer isSuccess, @Param(value = "name")String name);
	
	ProjectActivitiApplyDto findDetail(int id);
}
