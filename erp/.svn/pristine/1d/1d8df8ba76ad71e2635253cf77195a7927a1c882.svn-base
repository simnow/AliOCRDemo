package cn.caecc.erp.modules.dao.ex.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.caecc.erp.modules.dao.ex.dto.FixedassetsActivitiApplyDto;

public interface FixedassetsActivitiApplyExMapper {

	/**
	 * 查询详情
	 * @param id
	 * @return
	 */
	FixedassetsActivitiApplyDto findDetail(@Param("id")Integer id);

	/**
	 * 获取分页列表
	 * @param loginUserId
	 * @return
	 */
	List<FixedassetsActivitiApplyDto> getList(@Param("userid")Integer loginUserId, @Param("drafts")String drafts, @Param("isSuccess")Integer isSuccess);

}
