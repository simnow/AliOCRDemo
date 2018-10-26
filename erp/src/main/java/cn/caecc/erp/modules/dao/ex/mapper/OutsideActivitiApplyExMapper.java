package cn.caecc.erp.modules.dao.ex.mapper;

import java.util.List;
import java.util.Map;

import cn.caecc.erp.modules.dao.ex.dto.OutsideActivitiApplyDto;
import cn.caecc.erp.modules.dao.mybatis.entity.OutsideActivitiApply;

public interface OutsideActivitiApplyExMapper {
	
	List<OutsideActivitiApply> getCaPageList(Map<String,Object> params);
	
	OutsideActivitiApplyDto getCardApplyById(int id);
	
}
