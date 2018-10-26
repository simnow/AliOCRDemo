package cn.caecc.erp.modules.service.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.caecc.erp.modules.dao.ex.dto.EvaluateActivitiApplyDto;
import cn.caecc.erp.modules.dao.ex.mapper.EvaluateActivitiApplyExMapper;
import cn.caecc.erp.modules.dao.mybatis.entity.EvaluateActivitiApply;
import cn.caecc.erp.modules.dao.mybatis.mapper.EvaluateActivitiApplyMapper;
import cn.caecc.erp.modules.service.EvaluateActivitiApplyService;
import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.exception.CommonException;
import cn.caecc.erp.support.workflow.service.WorkflowService;

@Service
public class EvaluateActivitiApplyServiceImpl implements EvaluateActivitiApplyService {

	@Autowired
	private EvaluateActivitiApplyMapper evaluateActivitiApplyMapper;
	@Autowired
	private EvaluateActivitiApplyExMapper evaluateActivitiApplyExMapper;
	@Autowired
	private WorkflowService workflowService;

	/**
	 * 新建
	 */
	@Override
	public EvaluateActivitiApply create(EvaluateActivitiApply evaluateActivitiApply) {
		if (evaluateActivitiApply == null) {
			throw new CommonException("参数异常");
		} else {
			if (evaluateActivitiApply.getId() != null) {
				this.update(evaluateActivitiApply);
			}else {
				Integer loginUserId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(Contants.LOGINUSERID);
				evaluateActivitiApply.setCreateuserid(loginUserId);
				evaluateActivitiApply.setCreatetime(new Date());
				int result = evaluateActivitiApplyMapper.insertSelective(evaluateActivitiApply);
				if (result <= 0) {
					throw new CommonException("创建失败");
				}else {
					evaluateActivitiApply.setIssuccess(0);
				}
			}
		}
		return evaluateActivitiApply;
	}

	/**
	 * 查询详情
	 */
	@Override
	public EvaluateActivitiApplyDto findDetail(int id) {
		return evaluateActivitiApplyExMapper.findDetail(id);
	}

	/**
	 * 按条件获取分页列表
	 */
	@Override
	public PageInfo<EvaluateActivitiApplyDto> getList(Integer loginUserId, int pageNo, int pageSize, String drafts,
			int isSuccess) {
		PageHelper.orderBy("CreateTime DESC");
		PageHelper.startPage(pageNo, pageSize);
		List<EvaluateActivitiApplyDto> list = evaluateActivitiApplyExMapper.getList(loginUserId, drafts, isSuccess);
		PageInfo<EvaluateActivitiApplyDto> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	/**
	 * 开始流程
	 * @throws Exception 
	 */
	@Override
	public int startProcess(String processDefinitionKey, String bussinessKey, Map<String, Object> variables) throws Exception {
		if (StringUtils.isNotBlank(processDefinitionKey) && StringUtils.isNotBlank(bussinessKey)) {
			int parseInt = Integer.parseInt(bussinessKey);
			EvaluateActivitiApply evaluateActivitiApply = evaluateActivitiApplyMapper.selectByPrimaryKey(parseInt);

			String processInstanceId = workflowService.startProcess(processDefinitionKey, bussinessKey, variables);
			if (StringUtils.isNotBlank(processInstanceId)) {
				evaluateActivitiApply.setProcessinstanceid(processInstanceId);
				this.update(evaluateActivitiApply);
			} else {
				throw new CommonException("开始流程失败");
			}

		} else {
			throw new CommonException("参数异常");
		}
		return 1;
	}

	/**
	 * 更新
	 */
	@Override
	public EvaluateActivitiApply update(EvaluateActivitiApply evaluateActivitiApply) {
		if (evaluateActivitiApply == null || evaluateActivitiApply.getId() == null) {
			throw new CommonException("参数异常");

		} else {
			EvaluateActivitiApply selectByPrimaryKey = evaluateActivitiApplyMapper
					.selectByPrimaryKey(evaluateActivitiApply.getId());
			if (selectByPrimaryKey == null) {
				throw new CommonException("更新的对象不存在");

			} else {
				int result = evaluateActivitiApplyMapper.updateByPrimaryKey(evaluateActivitiApply);
				if (result <= 0) {
					throw new CommonException("更新失败");

				}
			}
		}
		return evaluateActivitiApply;
	}

	/**
	 * 重置success
	 * 
	 * @param id
	 */
	@Override
	public void setSuccess(int id) {
		EvaluateActivitiApply evaluateActivitiApply = new EvaluateActivitiApply();
		evaluateActivitiApply.setId(id);
		evaluateActivitiApply.setIssuccess(1);
		evaluateActivitiApplyMapper.updateByPrimaryKeySelective(evaluateActivitiApply);
	}

	/**
	 * 删除
	 */
	@Override
	public int deleteById(int id) {
		EvaluateActivitiApply evaluateActivitiApply = evaluateActivitiApplyMapper.selectByPrimaryKey(id);
		if (evaluateActivitiApply == null) {
			throw new CommonException("删除的数据不存在");
		}else {
			return evaluateActivitiApplyMapper.deleteByPrimaryKey(id);
		}
	}

}
