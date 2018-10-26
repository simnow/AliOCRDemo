package cn.caecc.erp.modules.dao.ex.mapper;

import java.util.List;
import java.util.Map;

import cn.caecc.erp.modules.dao.ex.dto.WorkingSummaryDto;
import cn.caecc.erp.modules.dao.mybatis.entity.WorkingSummary;

public interface WorkingSummaryExMapper {
   
	List<WorkingSummaryDto> findByWeekAndDid(WorkingSummary workingSummary) ;
	
	WorkingSummaryDto findDtoById(int id);
	
	List<WorkingSummaryDto>  findlistByCondition(Map<String, Object> params) ;
	
}
