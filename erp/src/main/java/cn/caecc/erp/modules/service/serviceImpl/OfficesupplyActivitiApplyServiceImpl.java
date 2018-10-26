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
import cn.caecc.erp.modules.dao.ex.dto.OfficesupplyActivitiApplyDto;
import cn.caecc.erp.modules.dao.ex.dto.OfficesupplyActivitiApplyExDto;
import cn.caecc.erp.modules.dao.ex.mapper.OfficesupplyActivitiApplyExMapper;
import cn.caecc.erp.modules.dao.mybatis.entity.OfficesupplyActivitiApply;
import cn.caecc.erp.modules.dao.mybatis.entity.OfficesupplyGoodsRelationshipActivitiApply;
import cn.caecc.erp.modules.dao.mybatis.mapper.OfficesupplyActivitiApplyMapper;
import cn.caecc.erp.modules.service.OfficesupplyActivitiApplyService;
import cn.caecc.erp.modules.service.OfficesupplyGoodsRelationshipActivitiApplyService;
import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.exception.CommonException;
import cn.caecc.erp.support.workflow.service.WorkflowService;

@Service
public class OfficesupplyActivitiApplyServiceImpl implements OfficesupplyActivitiApplyService {

	@Autowired
	private OfficesupplyActivitiApplyMapper officesupplyActivitiApplyMapper;
	@Autowired
	private OfficesupplyActivitiApplyExMapper officesupplyActivitiApplyExMapper;
	@Autowired
	private WorkflowService workflowService;
	@Autowired
	private OfficesupplyGoodsRelationshipActivitiApplyService ogrActivitiApplyService;

	/**
	 * 新建办公用品采购申请
	 */
	@Override
	public OfficesupplyActivitiApply create(OfficesupplyActivitiApply officesupplyActivitiApply) {
		if (officesupplyActivitiApply == null) {
			throw new CommonException("参数异常");

		} else {
			// 如果id不为空
			if (officesupplyActivitiApply.getId() != null) {
				// 更新
				this.update(officesupplyActivitiApply);
			} else {
				// 否则，保存
				Integer loginUserId = (Integer) SecurityUtils.getSubject().getSession()
						.getAttribute(Contants.LOGINUSERID);

				officesupplyActivitiApply.setCreateuserid(loginUserId);
				officesupplyActivitiApply.setCreatetime(new Date());
				int result = officesupplyActivitiApplyMapper.insertSelective(officesupplyActivitiApply);
				if (result <= 0) {
					throw new CommonException("创建失败");
				}else {
					officesupplyActivitiApply.setIssuccess(0);
				}
			}
		}
		return officesupplyActivitiApply;
	}

	/**
	 * 通过id查询
	 */
	@Override
	public OfficesupplyActivitiApply findById(int id) {
		return officesupplyActivitiApplyMapper.selectByPrimaryKey(id);
	}

	/**
	 * 查询详情
	 */
	@Override
	public OfficesupplyActivitiApplyDto findDetail(int id) {
		return officesupplyActivitiApplyExMapper.findDetail(id);
	}

	/**
	 * 按条件获取分页列表
	 */
	@Override
	public PageInfo<OfficesupplyActivitiApplyDto> getList(Integer loginUserId, int pageNo, int pageSize, String drafts,
			int isSuccess) {
		PageHelper.orderBy("Did DESC");
		PageHelper.startPage(pageNo, pageSize);
		List<OfficesupplyActivitiApplyDto> list = officesupplyActivitiApplyExMapper.getList(loginUserId, drafts,
				isSuccess);
		PageInfo<OfficesupplyActivitiApplyDto> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	/**
	 * 更新
	 */
	@Override
	public OfficesupplyActivitiApplyExDto update(OfficesupplyActivitiApplyExDto officesupplyActivitiApplyExDto) {
		if (officesupplyActivitiApplyExDto == null || officesupplyActivitiApplyExDto.getList() == null 
				|| officesupplyActivitiApplyExDto.getList().size() == 0) {
			return officesupplyActivitiApplyExDto;

		}else {
			boolean flag = false;
			for (OfficesupplyGoodsRelationshipActivitiApply iterable : officesupplyActivitiApplyExDto.getList()) {
				if (iterable == null) {
					flag = true;
				}
			}
			if (flag) {
				throw new CommonException("参数异常");

			}else {
				OfficesupplyActivitiApplyDto findDetail = this.findDetail(officesupplyActivitiApplyExDto.getId());
				if (findDetail == null) {
					throw new CommonException("更新的对象不存在");

				}else {
					OfficesupplyActivitiApply officesupplyActivitiApply = this.setOfficesupplyActivitiApply(officesupplyActivitiApplyExDto);
					int result = officesupplyActivitiApplyMapper.updateByPrimaryKey(officesupplyActivitiApply);
					if (result > 0) {
						boolean isAdd = true;
						for (OfficesupplyGoodsRelationshipActivitiApply item : officesupplyActivitiApplyExDto.getList()) {
								if (item.getId() != null) {
									isAdd = false;
								}else {
									item.setOid(officesupplyActivitiApply.getId());
								}
						}
						if (isAdd) {
							List<OfficesupplyGoodsRelationshipActivitiApply> list = officesupplyActivitiApplyExDto.getList();
							ogrActivitiApplyService.batchAdd(list);
							officesupplyActivitiApplyExDto.setList(list);
						}else {
							result = ogrActivitiApplyService.deleteByOid(officesupplyActivitiApply.getId());
							if (result > 0) {
								ogrActivitiApplyService.batchAdd(officesupplyActivitiApplyExDto.getList());
							}else {
								throw new CommonException("数据异常");

							}
						}
					}else {
						throw new CommonException("更新失败");

					}
				}
			}
		}
		return officesupplyActivitiApplyExDto;
	}

	/**
	 * 开始流程
	 * @throws Exception 
	 */
	@Override
	public int startProcess(String processDefinitionKey, String bussinessKey, Map<String, Object> variables) throws Exception {
		if (StringUtils.isNotBlank(processDefinitionKey) && StringUtils.isNotBlank(bussinessKey)) {
			int parseInt = Integer.parseInt(bussinessKey);
			OfficesupplyActivitiApply officesupplyActivitiApply = this.findById(parseInt);

			String processInstanceId = workflowService.startProcess(processDefinitionKey, bussinessKey, variables);
			if (StringUtils.isNotBlank(processInstanceId)) {
				officesupplyActivitiApply.setProcessinstanceid(processInstanceId);
				this.update(officesupplyActivitiApply);
			} else {
				throw new CommonException("开始流程失败");

			}

		} else {
			throw new CommonException("参数异常");

		}
		return 1;
	}

	/**
	 * 重置isSuccess
	 * 
	 * @param id
	 */
	@Override
	public void setSuccess(int id) {
		OfficesupplyActivitiApply officesupplyActivitiApply = new OfficesupplyActivitiApply();
		officesupplyActivitiApply.setId(id);
		officesupplyActivitiApply.setIssuccess(1);
		officesupplyActivitiApplyMapper.updateByPrimaryKeySelective(officesupplyActivitiApply);
	}

	/**
	 * 提取OfficesupplyActivitiApply对象
	 * 
	 * @param officesupplyActivitiApplyExDto
	 * @return
	 */
	private OfficesupplyActivitiApply setOfficesupplyActivitiApply(
			OfficesupplyActivitiApplyExDto officesupplyActivitiApplyExDto) {
		OfficesupplyActivitiApply officesupplyActivitiApply = new OfficesupplyActivitiApply();
		officesupplyActivitiApply.setId(officesupplyActivitiApplyExDto.getId());
		officesupplyActivitiApply.setProcessinstanceid(officesupplyActivitiApplyExDto.getProcessinstanceid());
		officesupplyActivitiApply.setUid(officesupplyActivitiApplyExDto.getUid());
		officesupplyActivitiApply.setDid(officesupplyActivitiApplyExDto.getDid());
		officesupplyActivitiApply.setDate(officesupplyActivitiApplyExDto.getDate());
		officesupplyActivitiApply.setCreateuserid(officesupplyActivitiApplyExDto.getCreateuserid());
		officesupplyActivitiApply.setCreatetime(officesupplyActivitiApplyExDto.getCreatetime());
		officesupplyActivitiApply.setIssuccess(officesupplyActivitiApplyExDto.getIssuccess());
		return officesupplyActivitiApply;
	}

	/**
	 * 只更新OfficesupplyActivitiApply
	 */
	@Override
	public OfficesupplyActivitiApply update(OfficesupplyActivitiApply officesupplyActivitiApply) {
		if (officesupplyActivitiApply == null) {
			throw new CommonException("参数异常");

		} else {
			OfficesupplyActivitiApply findById = this.findById(officesupplyActivitiApply.getId());
			if (findById == null) {
				throw new CommonException("更新的对象不存在");

			} else {
				int result = officesupplyActivitiApplyMapper.updateByPrimaryKey(officesupplyActivitiApply);
				if (result <= 0) {
					throw new CommonException("更新失败");

				}
			}
		}
		return officesupplyActivitiApply;
	}

	/**
	 * 删除
	 */
	@Override
	public int deleteById(int id) {
		OfficesupplyActivitiApply officesupplyActivitiApply = this.findById(id);
		if (officesupplyActivitiApply == null) {
			throw new CommonException("删除的数据不存在");
		}else {
			return officesupplyActivitiApplyMapper.deleteByPrimaryKey(id);
		}
	}
}
