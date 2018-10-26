package cn.caecc.erp.modules.dao.ex.mapper;

import java.util.List;
import java.util.Map;

import cn.caecc.erp.modules.dao.ex.dto.WorkingPlanDto;
import cn.caecc.erp.modules.dao.mybatis.entity.WorkingPlan;


public interface WorkingPlanExMapper {
   
	List<WorkingPlan> findByWeekAndDid(WorkingPlan WorkingPlan) ;
	
	WorkingPlanDto findDtoById(int id);
	
	List<WorkingPlanDto>  findPlanByCondition(Map<String, Object> params) ;
	
}
