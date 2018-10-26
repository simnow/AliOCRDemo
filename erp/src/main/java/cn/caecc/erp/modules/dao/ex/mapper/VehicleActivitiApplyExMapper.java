package cn.caecc.erp.modules.dao.ex.mapper;

import java.util.List;
import java.util.Map;

import cn.caecc.erp.modules.dao.ex.dto.VehicleActivitiApplyDto;
import cn.caecc.erp.modules.dao.mybatis.entity.VehicleActivitiApply;

public interface VehicleActivitiApplyExMapper {
	
	VehicleActivitiApplyDto getCardApplyById(int id);
	
	List<VehicleActivitiApply> getVePageList(Map<String, Object> params);

}
