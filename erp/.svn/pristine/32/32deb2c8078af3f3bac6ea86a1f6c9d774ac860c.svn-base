package cn.caecc.erp.modules.service;

import java.util.List;
import java.util.Map;
import com.github.pagehelper.PageInfo;
import cn.caecc.erp.modules.dao.ex.dto.MaterialActivitiApplyDto;
import cn.caecc.erp.modules.dao.ex.dto.MaterialActivitiApplyExDto;
import cn.caecc.erp.modules.dao.mybatis.entity.MaterialActivitiApply;

public interface MaterialActivitiApplyService {

	MaterialActivitiApplyExDto create(MaterialActivitiApplyExDto materialActivitiApplyExDto);

	MaterialActivitiApply findById(int id);

	MaterialActivitiApplyDto findDetail(int id);

	PageInfo<MaterialActivitiApplyDto> getList(Integer loginUserId, int pageNo, int pageSize, String drafts,
			int isSuccess, Integer did, String accruingAmounts, Integer wid);

	/**
	 * 更新物资采购申请表和物品清单表
	 * @param materialActivitiApplyExDto
	 * @return
	 */
	MaterialActivitiApplyExDto update(MaterialActivitiApplyExDto materialActivitiApplyExDto);
	
	/**
	 * 只更新物资采购申请表
	 * @param materialActivitiApply
	 * @return
	 */
	MaterialActivitiApply update(MaterialActivitiApply materialActivitiApply);

	int startProcess(String processDefinitionKey, String bussinessKey, Map<String, Object> variables) throws Exception ;
	
	void setSuccess(int id);

	/**
	 * 通过查询当月累计金额获取下一步审批人参数
	 * @param accruingAmountsDto
	 * @return
	 */
	Integer findAccruingAmounts(Integer did, List<String> gnameList, String totalMoney);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	int deleteById(int id);

}
