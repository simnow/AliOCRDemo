package cn.caecc.erp.modules.service;

import java.util.Map;
import com.github.pagehelper.PageInfo;
import cn.caecc.erp.modules.dao.ex.dto.OfficesupplyActivitiApplyDto;
import cn.caecc.erp.modules.dao.ex.dto.OfficesupplyActivitiApplyExDto;
import cn.caecc.erp.modules.dao.mybatis.entity.OfficesupplyActivitiApply;

public interface OfficesupplyActivitiApplyService {

	/**
	 * 新建办公用品采购申请
	 * @param officesupplyActivitiApply
	 * @return
	 */
	OfficesupplyActivitiApply create(OfficesupplyActivitiApply officesupplyActivitiApply);

	/**
	 * 通过id查询
	 * @param id
	 * @return
	 */
	OfficesupplyActivitiApply findById(int id);

	/**
	 * 查询详情
	 * @param id
	 * @return
	 */
	OfficesupplyActivitiApplyDto findDetail(int id);

	/**
	 * 按条件获取分页列表
	 * @param loginUserId
	 * @param pageNo
	 * @param pageSize
	 * @param drafts
	 * @param isSuccess
	 * @return
	 */
	PageInfo<OfficesupplyActivitiApplyDto> getList(Integer loginUserId, int pageNo, int pageSize, String drafts,
			int isSuccess);

	/**
	 * 更新
	 * @param officesupplyActivitiApplyExDto
	 * @return
	 */
	OfficesupplyActivitiApplyExDto update(OfficesupplyActivitiApplyExDto officesupplyActivitiApplyExDto);
	
	/**
	 * 只更新OfficesupplyActivitiApply
	 * @param officesupplyActivitiApply
	 * @return
	 */
	OfficesupplyActivitiApply update(OfficesupplyActivitiApply officesupplyActivitiApply);

	/**
	 * 开始流程
	 * @param processDefinitionKey
	 * @param bussinessKey
	 * @param variables
	 * @return
	 */
	int startProcess(String processDefinitionKey, String bussinessKey, Map<String, Object> variables) throws Exception;

	/**
	 * 重置success
	 * @param id
	 */
	void setSuccess(int id);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	int deleteById(int id);
}