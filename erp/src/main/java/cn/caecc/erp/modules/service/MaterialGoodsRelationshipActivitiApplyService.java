package cn.caecc.erp.modules.service;

import java.io.InputStream;
import java.util.List;
import cn.caecc.erp.modules.dao.ex.dto.MaterialGoodsRelationshipActivitiApplyDto;
import cn.caecc.erp.modules.dao.mybatis.entity.MaterialGoodsRelationshipActivitiApply;

public interface MaterialGoodsRelationshipActivitiApplyService {

	List<MaterialGoodsRelationshipActivitiApply> create(List<MaterialGoodsRelationshipActivitiApply> list);
	
	MaterialGoodsRelationshipActivitiApplyDto findDetail(int id);
	
	List<MaterialGoodsRelationshipActivitiApply> batchFind(List<Integer> list);

	List<MaterialGoodsRelationshipActivitiApply> batchUpdate(List<MaterialGoodsRelationshipActivitiApply> list);

	int deleteByMid(int id);
	
	List<MaterialGoodsRelationshipActivitiApply> exportExcel(InputStream inputStream,String fileName) throws Exception;
}
