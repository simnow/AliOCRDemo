package cn.caecc.erp.modules.dao.ex.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.caecc.erp.modules.dao.ex.dto.OfficesupplyGoodsRelationshipActivitiApplyDto;
import cn.caecc.erp.modules.dao.mybatis.entity.OfficesupplyGoodsRelationshipActivitiApply;

public interface OfficesupplyGoodsRelationshipActivitiApplyExMapper {

	/**
	 * 批量更新
	 * @param list
	 * @return
	 */
	int batchUpdate(@Param("ulist")List<OfficesupplyGoodsRelationshipActivitiApply> list);

	/**
	 * 批量增加
	 * @param list
	 * @return
	 */
	int batchAdd(@Param("list")List<OfficesupplyGoodsRelationshipActivitiApply> list);

	/**
	 * 查询详情
	 * @param id
	 * @return
	 */
	OfficesupplyGoodsRelationshipActivitiApplyDto findDetail(@Param("id")int id);

	/**
	 * 批量查询
	 * @param idList
	 * @return
	 */
	List<OfficesupplyGoodsRelationshipActivitiApply> batchFind(@Param("flist")List<Integer> idList);

}
