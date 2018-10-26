package cn.caecc.erp.modules.service;

import java.util.Map;
import com.github.pagehelper.PageInfo;
import cn.caecc.erp.modules.dao.ex.dto.TenderingActivitiApplyDto;
import cn.caecc.erp.modules.dao.mybatis.entity.TenderingActivitiApply;

public interface TenderingActivitiApplyService {

	/**
	 * 新建
	 * @param tenderingActivitiApply
	 * @return
	 */
	TenderingActivitiApply create(TenderingActivitiApply tenderingActivitiApply);

	/**
	 * 查询详情
	 * @param id
	 * @return
	 */
	TenderingActivitiApplyDto findDeatil(int id);

	/**
	 * 按条件获取分页列表
	 * @param loginUserId
	 * @param pageNo
	 * @param pageSize
	 * @param drafts
	 * @param isSuccess
	 * @return
	 */
	PageInfo<TenderingActivitiApplyDto> getList(Integer loginUserId, int pageNo, int pageSize, String drafts,
			int isSuccess);

	/**
	 * 更新
	 * @param tenderingActivitiApply
	 * @return
	 */
	TenderingActivitiApply update(TenderingActivitiApply tenderingActivitiApply);

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
