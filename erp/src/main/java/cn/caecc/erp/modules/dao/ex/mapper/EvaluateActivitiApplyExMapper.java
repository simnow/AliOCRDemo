package cn.caecc.erp.modules.dao.ex.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.caecc.erp.modules.dao.ex.dto.EvaluateActivitiApplyDto;

public interface EvaluateActivitiApplyExMapper {

	/**
	 * 查询详情
	 * @param id
	 * @return
	 */
	EvaluateActivitiApplyDto findDetail(@Param("id")Integer id);

	/**
	 * 按条件获取分页列表
	 * @param loginUserId
	 * @param drafts
	 * @param isSuccess
	 * @return
	 */
	List<EvaluateActivitiApplyDto> getList(@Param("userid")Integer loginUserId, @Param("drafts")String drafts, @Param("isSuccess")Integer isSuccess);

}
