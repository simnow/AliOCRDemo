package cn.caecc.erp.modules.dao.ex.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.caecc.erp.modules.dao.ex.dto.TenderingActivitiApplyDto;

public interface TenderingActivitiApplyExMapper {

	/**
	 * 查询详情
	 * @param id
	 * @return
	 */
	TenderingActivitiApplyDto findDetail(@Param("id")Integer id);

	/**
	 * 按条件获取分页列表
	 * @param loginUserId
	 * @param drafts
	 * @param isSuccess
	 * @return
	 */
	List<TenderingActivitiApplyDto> getList(@Param("userid")Integer loginUserId, @Param("drafts")String drafts, @Param("isSuccess")Integer isSuccess);

}
