package cn.caecc.erp.modules.dao.ex.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.caecc.erp.modules.dao.ex.dto.MaterialActivitiApplyDto;
import cn.caecc.erp.modules.dao.ex.dto.MaterialGoodsRelationshipActivitiApplyDto;

public interface MaterialActivitiApplyExMapper {

	MaterialActivitiApplyDto findDetail(@Param("id")Integer id);

	List<MaterialActivitiApplyDto> getList(@Param("userid")Integer loginUserId, @Param("drafts")String drafts, @Param("isSuccess")Integer isSuccess, @Param("did")Integer did, @Param("accruingAmounts")String accruingAmounts, @Param("wid")Integer wid);
	
	List<MaterialGoodsRelationshipActivitiApplyDto> getChildrenList(Integer mid);

}
