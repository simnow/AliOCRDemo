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
import cn.caecc.erp.modules.dao.ex.dto.FixedassetsActivitiApplyDto;
import cn.caecc.erp.modules.dao.ex.mapper.FixedassetsActivitiApplyExMapper;
import cn.caecc.erp.modules.dao.mybatis.entity.FixedassetsActivitiApply;
import cn.caecc.erp.modules.dao.mybatis.mapper.FixedassetsActivitiApplyMapper;
import cn.caecc.erp.modules.service.FixedassetsActivitiApplyService;
import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.exception.CommonException;
import cn.caecc.erp.support.workflow.service.WorkflowService;

@Service
public class FixedassetsActivitiApplyServiceImpl implements FixedassetsActivitiApplyService {

	@Autowired
	private FixedassetsActivitiApplyMapper fixedassetsActivitiApplyMapper;
	@Autowired
	private FixedassetsActivitiApplyExMapper fixedassetsActivitiApplyExMapper;
	@Autowired
	private WorkflowService workflowService;

	@Override
	public FixedassetsActivitiApply create(FixedassetsActivitiApply fixedassetsActivitiApply) {
		if (fixedassetsActivitiApply == null) {
			throw new CommonException("参数异常");

		} else {
			if (fixedassetsActivitiApply.getId() != null) {
				this.update(fixedassetsActivitiApply);
			} else {
				Integer loginUserId = (Integer) SecurityUtils.getSubject().getSession()
						.getAttribute(Contants.LOGINUSERID);

				fixedassetsActivitiApply.setCreateuserid(loginUserId);
				fixedassetsActivitiApply.setCreatetime(new Date());
				int result = fixedassetsActivitiApplyMapper.insertSelective(fixedassetsActivitiApply);
				if (result <= 0) {
					throw new CommonException("创建失败");
				}else {
					fixedassetsActivitiApply.setIssuccess(0);
				}
			}
		}
		return fixedassetsActivitiApply;
	}

	@Override
	public FixedassetsActivitiApply findById(int id) {
		return fixedassetsActivitiApplyMapper.selectByPrimaryKey(id);
	}

	@Override
	public FixedassetsActivitiApplyDto findDetail(int id) {
		return fixedassetsActivitiApplyExMapper.findDetail(id);
	}

	@Override
	public PageInfo<FixedassetsActivitiApplyDto> getList(Integer loginUserId, int pageNo, int pageSize, String drafts,
			int isSuccess) {
		PageHelper.orderBy("CreateTime DESC");
		PageHelper.startPage(pageNo, pageSize);
		List<FixedassetsActivitiApplyDto> list = fixedassetsActivitiApplyExMapper.getList(loginUserId, drafts,
				isSuccess);
		PageInfo<FixedassetsActivitiApplyDto> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public FixedassetsActivitiApply update(FixedassetsActivitiApply fixedassetsActivitiApply) {
		if (fixedassetsActivitiApply == null) {
			throw new CommonException("参数异常");

		} else {
			FixedassetsActivitiApply findById = this.findById(fixedassetsActivitiApply.getId());
			if (findById == null) {
				throw new CommonException("更新的对象不存在");

			} else {
				int result = fixedassetsActivitiApplyMapper.updateByPrimaryKey(fixedassetsActivitiApply);
				if (result <= 0) {
					throw new CommonException("更新失败");

				}
			}
		}
		return fixedassetsActivitiApply;
	}

	@Override
	public int startProcess(String processDefinitionKey, String bussinessKey, Map<String, Object> variables) throws Exception {
		if (StringUtils.isNotBlank(processDefinitionKey) && StringUtils.isNotBlank(bussinessKey)) {
			int parseInt = Integer.parseInt(bussinessKey);
			FixedassetsActivitiApply fixedassetsActivitiApply = this.findById(parseInt);

			String processInstanceId = workflowService.startProcess(processDefinitionKey, bussinessKey, variables);
			if (StringUtils.isNotBlank(processInstanceId)) {
				fixedassetsActivitiApply.setProcessinstanceid(processInstanceId);
				this.update(fixedassetsActivitiApply);
			} else {
				throw new CommonException("开始流程失败");

			}

		} else {
			throw new CommonException("参数异常");

		}
		return 1;
	}

	@Override
	public void setSuccess(int id) {
		FixedassetsActivitiApply fixedassetsActivitiApply = new FixedassetsActivitiApply();
		fixedassetsActivitiApply.setId(id);
		fixedassetsActivitiApply.setIssuccess(1);
		fixedassetsActivitiApplyMapper.updateByPrimaryKeySelective(fixedassetsActivitiApply);
	}

	@Override
	public int deleteById(int id) {
		FixedassetsActivitiApply findById = this.findById(id);
		if (findById == null) {
			throw new CommonException("删除的数据不存在");
		}else {
			return fixedassetsActivitiApplyMapper.deleteByPrimaryKey(id);
		}
	}

}
