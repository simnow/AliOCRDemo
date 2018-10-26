package cn.caecc.erp.modules.dao.ex.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.caecc.erp.modules.dao.ex.dto.MaterialGoodsRelationshipActivitiApplyDto;
import cn.caecc.erp.modules.dao.mybatis.entity.MaterialGoodsRelationshipActivitiApply;

public interface MaterialGoodsRelationshipActivitiApplyExMapper {
	
	/**
	 * 批量增加
	 * @param list
	 * @return
	 */
	int batchAdd(@Param("list")List<MaterialGoodsRelationshipActivitiApply> list);

	/**
	 * 查询详情
	 * @param id
	 * @return
	 */
	MaterialGoodsRelationshipActivitiApplyDto findDetail(@Param("id")Integer id);

	/**
	 * 批量更新
	 * @param list
	 * @return
	 */
	int batchUpdate(@Param("ulist")List<MaterialGoodsRelationshipActivitiApply> list);

	/**
	 * 批量查询
	 * @param list
	 * @return
	 */
	List<MaterialGoodsRelationshipActivitiApply> batchFind(@Param("flist")List<Integer> list);
}
