package cn.caecc.erp.modules.service;

import java.util.List;
import cn.caecc.erp.modules.dao.ex.dto.OfficesupplyGoodsRelationshipActivitiApplyDto;
import cn.caecc.erp.modules.dao.mybatis.entity.OfficesupplyGoodsRelationshipActivitiApply;

public interface OfficesupplyGoodsRelationshipActivitiApplyService {

	/**
	 * 批量更新
	 * @param list
	 * @return
	 */
	List<OfficesupplyGoodsRelationshipActivitiApply> batchUpdate(List<OfficesupplyGoodsRelationshipActivitiApply> list);

	/**
	 * 批量增加
	 * @param list
	 * @return
	 */
	List<OfficesupplyGoodsRelationshipActivitiApply> batchAdd(List<OfficesupplyGoodsRelationshipActivitiApply> list);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	int deleteByOid(int id);

	/**
	 * 查询详情
	 * @param id
	 * @return
	 */
	OfficesupplyGoodsRelationshipActivitiApplyDto findDetail(int id);

	/**
	 * 批量查询
	 * @param idList
	 * @return
	 */
	List<OfficesupplyGoodsRelationshipActivitiApply> batchFind(List<Integer> idList);
}
