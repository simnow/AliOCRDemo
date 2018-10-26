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
import cn.caecc.erp.modules.dao.ex.dto.ProjectActivitiApplyDto;
import cn.caecc.erp.modules.dao.ex.mapper.ProjectActivitiApplyExMapper;
import cn.caecc.erp.modules.dao.mybatis.entity.ProjectActivitiApply;
import cn.caecc.erp.modules.dao.mybatis.entity.ProjectActivitiApplyExample;
import cn.caecc.erp.modules.dao.mybatis.entity.ProjectActivitiApplyExample.Criteria;
import cn.caecc.erp.modules.dao.mybatis.mapper.ProjectActivitiApplyMapper;
import cn.caecc.erp.modules.service.ProjectActivitiApplyService;
import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.exception.CommonException;
import cn.caecc.erp.support.util.DateUtil;
import cn.caecc.erp.support.workflow.service.WorkflowService;

@Service
public class ProjectActivitiApplyServiceImpl implements ProjectActivitiApplyService {

	@Autowired
	private ProjectActivitiApplyMapper projectActivitiApplyMapper;
	@Autowired
	private ProjectActivitiApplyExMapper projectActivitiApplyExMapper;
	@Autowired
	private WorkflowService workflowService;

	/**
	 * 创建立项申请
	 */
	@Override
	public ProjectActivitiApply create(ProjectActivitiApply projectActivitiApply) {
		if (projectActivitiApply == null) {
			throw new CommonException("参数异常");

		} else {
			// 如果有id
			if (projectActivitiApply.getId() != null) {
				// 更新
				this.updateProjectApproval(projectActivitiApply);
			} else {
				// 否则，保存
				Integer loginUserId = (Integer) SecurityUtils.getSubject().getSession()
						.getAttribute(Contants.LOGINUSERID);

				projectActivitiApply.setCode(DateUtil.currentDate("yyyyMMddHHmmssSSS"));
				projectActivitiApply.setCreateuserid(loginUserId);
				projectActivitiApply.setCreatetime(new Date());
				int result = projectActivitiApplyMapper.insertSelective(projectActivitiApply);
				if (result <= 0) {
					throw new CommonException("创建失败");
				}else {
					projectActivitiApply.setIssuccess(0);
				}
			}
		}
		return projectActivitiApply;
	}

	/**
	 * 通过id查询立项申请
	 */
	@Override
	public ProjectActivitiApply findById(int id) {
		return projectActivitiApplyMapper.selectByPrimaryKey(id);
	}

	/**
	 * 通过id查询立项详情
	 */
	@Override
	public ProjectActivitiApplyDto findDetail(int id) {
		return projectActivitiApplyExMapper.findDetail(id);
	}

	/**
	 * 通过立项编码查询立项
	 */
	@Override
	public ProjectActivitiApply findByCode(String code) {
		if (code != null && !"".equals(code)) {
			ProjectActivitiApplyExample projectActivitiApplyExample = new ProjectActivitiApplyExample();
			Criteria criteria = projectActivitiApplyExample.createCriteria();
			criteria.andCodeEqualTo(code);
			List<ProjectActivitiApply> list = projectActivitiApplyMapper.selectByExample(projectActivitiApplyExample);
			if (list != null && list.size() == 1) {
				return list.get(0);
			}
		}
		return null;
	}

	/**
	 * 按条件查询立项分页列表
	 */
	@Override
	public PageInfo<ProjectActivitiApplyDto> getList(int pageNo, int pageSize, String drafts,
			int isSuccess, Integer userid, String name) {
		PageHelper.orderBy("CreateTime DESC");
		PageHelper.startPage(pageNo, pageSize);
		List<ProjectActivitiApplyDto> list = projectActivitiApplyExMapper.getList(userid, drafts, isSuccess, name);
		PageInfo<ProjectActivitiApplyDto> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	/**
	 * 更新立项申请表
	 */
	@Override
	public ProjectActivitiApply updateProjectApproval(ProjectActivitiApply projectActivitiApply) {
		if (projectActivitiApply == null) {
			throw new CommonException("请求参数为空");

		} else {
			ProjectActivitiApply pActivitiApply = this.findById(projectActivitiApply.getId());
			if (pActivitiApply == null) {
				throw new CommonException("修改的立项申请单不存在");

			} else {
				int result = projectActivitiApplyMapper.updateByPrimaryKey(projectActivitiApply);
				if (result <= 0) {
					throw new CommonException("修改失败");

				}
			}
		}
		return projectActivitiApply;
	}

	/**
	 * 根据id删除立项
	 */
	@Override
	public int delProjectApprovalById(int id) {
		int result = 0;
		ProjectActivitiApply approval = this.findById(id);
		if (approval == null) {
			throw new CommonException("要删除的立项不存在");

		} else {
			result = projectActivitiApplyMapper.deleteByPrimaryKey(id);
			if (result <= 0) {
				throw new CommonException("删除失败");

			}
		}
		return result;
	}

	/**
	 * 根据立项编码删除立项
	 */
	@Override
	public int delProjectApprovalByCode(String code) {
		int result = 0;
		if (code == null || "".equals(code)) {
			throw new CommonException("请求参数为空");

		} else {
			ProjectActivitiApply projectActivitiApply = this.findByCode(code);
			if (projectActivitiApply == null) {
				throw new CommonException("要删除的立项不存在");

			} else {
				ProjectActivitiApplyExample example = new ProjectActivitiApplyExample();
				Criteria criteria = example.createCriteria();
				criteria.andCodeEqualTo(code);
				result = projectActivitiApplyMapper.deleteByExample(example);
				if (result <= 0) {
					throw new CommonException("删除失败");

				}
			}
		}
		return result;
	}

	/**
	 * 开始流程
	 * 
	 * @param projectActivitiApply
	 * @return
	 * @throws Exception 
	 */
	@Override
	public int startProcess(String processDefinitionKey, String bussinessKey, Map<String, Object> variables) throws Exception {
		if (StringUtils.isNotBlank(processDefinitionKey) && StringUtils.isNotBlank(bussinessKey)) {
			int parseInt = Integer.parseInt(bussinessKey);
			ProjectActivitiApply projectActivitiApply = this.findById(parseInt);

			String processInstanceId = workflowService.startProcess(processDefinitionKey, bussinessKey, variables);
			if (StringUtils.isNotBlank(processInstanceId)) {
				projectActivitiApply.setProcessinstanceid(processInstanceId);
				this.updateProjectApproval(projectActivitiApply);
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
		ProjectActivitiApply projectActivitiApply = new ProjectActivitiApply();
		projectActivitiApply.setId(id);
		projectActivitiApply.setIssuccess(1);
		projectActivitiApplyMapper.updateByPrimaryKeySelective(projectActivitiApply);
	}
}
