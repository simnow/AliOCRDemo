package cn.caecc.erp.modules.service;

import java.util.Map;
import com.github.pagehelper.PageInfo;
import cn.caecc.erp.modules.dao.ex.dto.FixedassetsActivitiApplyDto;
import cn.caecc.erp.modules.dao.mybatis.entity.FixedassetsActivitiApply;

public interface FixedassetsActivitiApplyService {

	/**
	 * 新建固定资产采购申请
	 * @param fixedassetsActivitiApply
	 * @return
	 */
	FixedassetsActivitiApply create(FixedassetsActivitiApply fixedassetsActivitiApply);

	/**
	 * 通过id查询
	 * @param id
	 * @return
	 */
	FixedassetsActivitiApply findById(int id);

	/**
	 * 查询详情
	 * @param id
	 * @return
	 */
	FixedassetsActivitiApplyDto findDetail(int id);

	/**
	 * 按条件获取分页列表
	 * @param loginUserId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	PageInfo<FixedassetsActivitiApplyDto> getList(Integer loginUserId, int pageNo, int pageSize, String drafts, int isSuccess);

	/**
	 * 更新
	 * @param fixedassetsActivitiApply
	 * @return
	 */
	FixedassetsActivitiApply update(FixedassetsActivitiApply fixedassetsActivitiApply);

	/**
	 * 开始流程
	 * @param processDefinitionKey
	 * @param bussinessKey
	 * @param variables
	 * @return
	 */
	int startProcess(String processDefinitionKey, String bussinessKey, Map<String, Object> variables) throws Exception;

	/**
	 * 重置Success
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
