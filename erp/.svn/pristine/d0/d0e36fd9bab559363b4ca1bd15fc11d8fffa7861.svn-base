package cn.caecc.erp.modules.dao.ex.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.caecc.erp.modules.dao.ex.dto.OfficesupplyActivitiApplyDto;
import cn.caecc.erp.modules.dao.ex.dto.OfficesupplyGoodsRelationshipActivitiApplyDto;

public interface OfficesupplyActivitiApplyExMapper {

	/**
	 * 查询详情
	 * @param id
	 * @return
	 */
	OfficesupplyActivitiApplyDto findDetail(@Param("id")int id);

	/**
	 * 按条件获取分页列表
	 * @param loginUserId
	 * @param drafts
	 * @param isSuccess
	 * @return
	 */
	List<OfficesupplyActivitiApplyDto> getList(@Param("userid")Integer loginUserId, @Param("drafts")String drafts, @Param("isSuccess")int isSuccess);
	
	/**
	 * 获取办公用品清单
	 * @param oid
	 * @param gcode
	 * @param cgid
	 * @return
	 */
	List<OfficesupplyGoodsRelationshipActivitiApplyDto> getChildrenList(Integer oid);

}
