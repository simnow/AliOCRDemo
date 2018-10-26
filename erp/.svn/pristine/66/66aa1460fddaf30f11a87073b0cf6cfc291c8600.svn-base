package cn.caecc.erp.modules.dao.ex.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.caecc.erp.modules.dao.ex.dto.WorkloadActivitiApplyDto;

public interface WorkloadActivitiApplyExMapper {

	WorkloadActivitiApplyDto findDetail(@Param("id")Integer id);

	List<WorkloadActivitiApplyDto> getList(@Param("userid")Integer loginUserId, @Param("drafts")String drafts, @Param("isSuccess")Integer isSuccess);
}
