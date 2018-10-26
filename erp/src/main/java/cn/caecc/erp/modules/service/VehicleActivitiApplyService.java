package cn.caecc.erp.modules.service;

import java.util.Map;
import com.github.pagehelper.PageInfo;
import cn.caecc.erp.modules.dao.ex.dto.VehicleActivitiApplyDto;
import cn.caecc.erp.modules.dao.mybatis.entity.VehicleActivitiApply;

public interface VehicleActivitiApplyService {

	public VehicleActivitiApply addVehicleActivitiApply(VehicleActivitiApply vehicleActivitiApply);

	public int deleteVehicleActivitiApply(int id);

	public PageInfo<VehicleActivitiApply> getVehicleApplyPageList(int state,int pagesize,int pageno,int uid);

	public int startVehicleActivitiApply(String processDefinitionKey, String bussinessKey,
			Map<String, Object> variables) throws Exception;

	public VehicleActivitiApplyDto getVehicleActivitiApply(int id);

	public VehicleActivitiApply updateVehicleApply(VehicleActivitiApply vehicleActivitiApply);

	public void setSuccess(int id);

}
