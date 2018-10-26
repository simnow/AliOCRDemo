package cn.caecc.erp.modules.service.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.caecc.erp.modules.dao.ex.dto.CardActivitiApplyDto;
import cn.caecc.erp.modules.dao.ex.mapper.CardActivitiApplyExMapper;
import cn.caecc.erp.modules.dao.mybatis.entity.CardActivitiApply;
import cn.caecc.erp.modules.dao.mybatis.mapper.CardActivitiApplyMapper;
import cn.caecc.erp.modules.service.CardActivitiApplyService;
import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.exception.CommonException;
import cn.caecc.erp.support.util.DateUtil;
import cn.caecc.erp.support.workflow.service.WorkflowService;

@Service
public class CardActivitiApplyServiceImpl implements CardActivitiApplyService {
	@Autowired
	private CardActivitiApplyMapper caMapper;

	@Autowired
	private CardActivitiApplyExMapper caExMapper;

	@Autowired
	private WorkflowService workflowService;

	@Override
	public int addCardActivitiApply(CardActivitiApply cardActivitiApply) {
		int result = 0;
		Integer loginUserId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(Contants.LOGINUSERID);
		// 判断传入参数
		if (cardActivitiApply.getUid() == null || cardActivitiApply.getType() == null) {
			throw new CommonException("参数异常");
		}
		// 判断是保存还是修改
		if (cardActivitiApply.getId() == null) {
			// 设置创建人
			cardActivitiApply.setCreateuserid(loginUserId);
			cardActivitiApply.setCreatetime(DateUtil.getcurrentDateTime());
			result = caMapper.insertSelective(cardActivitiApply);
		}
		// 不存在进行修改操作
		else {
			// 查询数据库记录
			CardActivitiApply exists = caMapper.selectByPrimaryKey(cardActivitiApply.getId());
			// 判断是否已开始流程
			if (StringUtils.isBlank(exists.getProcessinstanceid())) {
				result = caMapper.updateByPrimaryKeySelective(cardActivitiApply);
			} else {
				throw new CommonException("流程中，不能进行修改操作");
			}
		}
		return result;
	}

	@Override
	public int deleteCardActivitiApply(int id) {

		// TODO Auto-generated method stub
		int result = 0;
		CardActivitiApply cardActivitiApply = caMapper.selectByPrimaryKey(id);
		if (StringUtils.isBlank(cardActivitiApply.getProcessinstanceid())) {
			result = caMapper.deleteByPrimaryKey(id);
		} else {
			throw new CommonException("存在流程信息，无法删除");
		}
		return result;
	}

	@Override
	public PageInfo<CardActivitiApplyDto> getAcApplyPageList(int state,int pagesize,int pageno,int uid) {
		// TODO Auto-generated method stub
		PageInfo<CardActivitiApplyDto> pageInfo = null;
		// 设置为当前用户
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("state",state);
		params.put("uid",uid);
		//activitiDto.setUid(loginUserId);
		PageHelper.startPage(pageno,pagesize);
		List<CardActivitiApplyDto> resultList = caExMapper.getCaPageList(params);
		pageInfo = new PageInfo<>(resultList);
		return pageInfo;
	}

	@Override
	public int startCardActivitiApply(String processDefinitionKey, String bussinessKey, Map<String, Object> variables) throws Exception {
		int result = 0;
		// 判断参数是否存在
		if (StringUtils.isBlank(bussinessKey) || StringUtils.isBlank(processDefinitionKey)) {
			throw new CommonException("参数异常");
		}

		// 开启流程
		String processInstanceId = workflowService.startProcess(processDefinitionKey, bussinessKey, variables);
		// 判断流程开启成功
		if (StringUtils.isBlank(processInstanceId)) {
			throw new CommonException("流程开启失败");
		}
		// 获取业务表信息
		CardActivitiApply cardActivitiApply = caMapper.selectByPrimaryKey(Integer.parseInt(bussinessKey));
		cardActivitiApply.setProcessinstanceid(processInstanceId);
		// 更新
		result = caMapper.updateByPrimaryKey(cardActivitiApply);
		// 返回成功

		return result;

	}

	@Override
	public CardActivitiApplyDto getCardApplyByid(int id) {
		// TODO Auto-generated method stub

		CardActivitiApplyDto cardActivitiApplyDto = caExMapper.getCardApplyById(id);

		return cardActivitiApplyDto;

	}

	@Override
	public CardActivitiApply updateCardApply(CardActivitiApply cardActivitiApply) {
		// TODO Auto-generated method stub
		if (cardActivitiApply.getId() == null || cardActivitiApply.getUid() == null
				|| cardActivitiApply.getType() == null) {
			throw new CommonException("参数异常");

		}
		caMapper.updateByPrimaryKeySelective(cardActivitiApply);
		return cardActivitiApply;

	}

	@Override
	public void setSuccess(int id) {
		// TODO Auto-generated method stub
		CardActivitiApply cardActivitiApply = new CardActivitiApply();
		cardActivitiApply.setId(id);
		cardActivitiApply.setIssuccess(1);
		caMapper.updateByPrimaryKeySelective(cardActivitiApply);
	}

}
