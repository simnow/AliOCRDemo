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
import cn.caecc.erp.modules.dao.ex.dto.TenderingActivitiApplyDto;
import cn.caecc.erp.modules.dao.ex.mapper.TenderingActivitiApplyExMapper;
import cn.caecc.erp.modules.dao.mybatis.entity.TenderingActivitiApply;
import cn.caecc.erp.modules.dao.mybatis.mapper.TenderingActivitiApplyMapper;
import cn.caecc.erp.modules.service.TenderingActivitiApplyService;
import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.exception.CommonException;
import cn.caecc.erp.support.workflow.service.WorkflowService;

@Service
public class TenderingActivitiApplyServiceImpl implements TenderingActivitiApplyService {

	@Autowired
	private TenderingActivitiApplyMapper tenderingActivitiApplyMapper;
	@Autowired
	private TenderingActivitiApplyExMapper tenderingActivitiApplyExMapper;
	@Autowired
	private WorkflowService workflowService;

	/**
	 * 新建
	 */
	@Override
	public TenderingActivitiApply create(TenderingActivitiApply tenderingActivitiApply) {
		if (tenderingActivitiApply == null) {
			throw new CommonException("参数异常");
		} else {
			if (tenderingActivitiApply.getId() != null) {
				this.update(tenderingActivitiApply);
			}else {
				Integer loginUserId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(Contants.LOGINUSERID);
				tenderingActivitiApply.setCreateuserid(loginUserId);
				tenderingActivitiApply.setCreatetime(new Date());
				int result = tenderingActivitiApplyMapper.insertSelective(tenderingActivitiApply);
				if (result <= 0) {
					throw new CommonException("创建失败");
				}else {
					tenderingActivitiApply.setIssuccess(0);
				}
			}
		}
		return tenderingActivitiApply;
	}

	/**
	 * 查询详情
	 */
	@Override
	public TenderingActivitiApplyDto findDeatil(int id) {
		return tenderingActivitiApplyExMapper.findDetail(id);
	}

	/**
	 * 按条件获取分页列表
	 */
	@Override
	public PageInfo<TenderingActivitiApplyDto> getList(Integer loginUserId, int pageNo, int pageSize, String drafts,
			int isSuccess) {
		PageHelper.orderBy("CreateTime DESC");
		PageHelper.startPage(pageNo, pageSize);
		List<TenderingActivitiApplyDto> list = tenderingActivitiApplyExMapper.getList(loginUserId, drafts, isSuccess);
		PageInfo<TenderingActivitiApplyDto> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	/**
	 * 更新
	 */
	@Override
	public TenderingActivitiApply update(TenderingActivitiApply tenderingActivitiApply) {
		if (tenderingActivitiApply == null || tenderingActivitiApply.getId() == null) {
			throw new CommonException("参数异常");

		} else {
			TenderingActivitiApply selectByPrimaryKey = tenderingActivitiApplyMapper
					.selectByPrimaryKey(tenderingActivitiApply.getId());
			if (selectByPrimaryKey == null) {
				throw new CommonException("更新的对象不存在");

			} else {
				int result = tenderingActivitiApplyMapper.updateByPrimaryKey(tenderingActivitiApply);
				if (result <= 0) {
					throw new CommonException("更新失败");
				}
			}
		}
		return tenderingActivitiApply;
	}

	/**
	 * 开始流程
	 * @throws Exception 
	 */
	@Override
	public int startProcess(String processDefinitionKey, String bussinessKey, Map<String, Object> variables) throws Exception {
		if (StringUtils.isNotBlank(processDefinitionKey) && StringUtils.isNotBlank(bussinessKey)) {
			int parseInt = Integer.parseInt(bussinessKey);
			TenderingActivitiApply tenderingActivitiApply = tenderingActivitiApplyMapper.selectByPrimaryKey(parseInt);

			String processInstanceId = workflowService.startProcess(processDefinitionKey, bussinessKey, variables);
			if (StringUtils.isNotBlank(processInstanceId)) {
				tenderingActivitiApply.setProcessinstanceid(processInstanceId);
				this.update(tenderingActivitiApply);
			} else {
				throw new CommonException("开始流程失败");

			}

		} else {
			throw new CommonException("参数异常");

		}
		return 1;
	}

	/**
	 * 重置success
	 */
	@Override
	public void setSuccess(int id) {
		TenderingActivitiApply tenderingActivitiApply = new TenderingActivitiApply();
		tenderingActivitiApply.setId(id);
		tenderingActivitiApply.setIssuccess(1);
		tenderingActivitiApplyMapper.updateByPrimaryKeySelective(tenderingActivitiApply);
	}

	/**
	 * 删除
	 */
	@Override
	public int deleteById(int id) {
		TenderingActivitiApply tenderingActivitiApply = tenderingActivitiApplyMapper.selectByPrimaryKey(id);
		if (tenderingActivitiApply == null) {
			throw new CommonException("删除的数据不存在");
		}else {
			return tenderingActivitiApplyMapper.deleteByPrimaryKey(id);
		}
	}

}
