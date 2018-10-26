package cn.caecc.erp.modules.dao.ex.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.caecc.erp.modules.dao.ex.dto.ContractActivitiApplyDto;
import cn.caecc.erp.modules.dao.mybatis.entity.ContractActivitiApply;

public interface ContractActivitiApplyExMapper {

	public ContractActivitiApplyDto findDetail(int id);
	
	public List<ContractActivitiApplyDto> getList(@Param("userid")Integer userid, @Param("drafts")String drafts, @Param("isSuccess")Integer isSuccess, @Param("operation")String operation, @Param("name")String name);

	public int updateByPrimaryKey(ContractActivitiApply contractActivitiApply);
}
