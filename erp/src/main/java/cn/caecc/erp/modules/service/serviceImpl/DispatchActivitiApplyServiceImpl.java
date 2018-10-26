package cn.caecc.erp.modules.service.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.caecc.erp.modules.dao.ex.dto.DispatchActivitiApplyDto;
import cn.caecc.erp.modules.dao.ex.mapper.DispatchActivitiApplyExMapper;
import cn.caecc.erp.modules.dao.mybatis.entity.DispatchActivitiApply;
import cn.caecc.erp.modules.dao.mybatis.entity.DispatchActivitiApplyExample;
import cn.caecc.erp.modules.dao.mybatis.entity.DispatchActivitiApplyExample.Criteria;
import cn.caecc.erp.modules.dao.mybatis.mapper.DispatchActivitiApplyMapper;
import cn.caecc.erp.modules.service.DispatchActivitiApplyService;
import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.exception.CommonException;
import cn.caecc.erp.support.util.DateUtil;
import cn.caecc.erp.support.workflow.service.WorkflowService;

@Service
public class DispatchActivitiApplyServiceImpl implements DispatchActivitiApplyService {

	@Autowired
	private DispatchActivitiApplyMapper disApplyMapper;
	@Autowired
	private DispatchActivitiApplyExMapper disExApplyMapper;
	@Autowired
	private WorkflowService workflowService;

	@Override
	public DispatchActivitiApply addDispatchApply(DispatchActivitiApply dispatchActivitiApply) {
		// TODO Auto-generated method stub
		Integer loginUserId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(Contants.LOGINUSERID);
		// 判断是否登录
		if (StringUtils.isBlank(dispatchActivitiApply.getContent())) {
			throw new CommonException("参数异常");
		}
		// 判断是否存在ID
		if (dispatchActivitiApply.getId() == null) {
			dispatchActivitiApply.setUid(loginUserId);
			dispatchActivitiApply.setCreateuserid(loginUserId);
			dispatchActivitiApply.setCreatetime(DateUtil.getcurrentDateTime());
			disApplyMapper.insertSelective(dispatchActivitiApply);
		}
		// 不存在进行修改操作
		else {
			DispatchActivitiApply exists = disApplyMapper.selectByPrimaryKey(dispatchActivitiApply.getId());
			/*if(!exists.getCode().equals(dispatchActivitiApply.getCode())){		
				List<DispatchActivitiApply> existList = getDispatchByCode(dispatchActivitiApply.getCode());
				if (existList != null && existList.size() > 0) {
					throw new CommonException("编码重复");
				}	
			}*/
			if (exists.getProcessinstanceid() == null) {
				disApplyMapper.updateByPrimaryKeySelective(dispatchActivitiApply);

			} else {
				throw new CommonException("流程中，不能进行修改操作");
			}

		}
		return dispatchActivitiApply;
	}

	@Override
	public DispatchActivitiApply updateDispatchApply(DispatchActivitiApply dispatchActivitiApply) {
		// TODO Auto-generated method stub
		if (StringUtils.isBlank(dispatchActivitiApply.getContent())) {
			throw new CommonException("参数异常");
		}
		disApplyMapper.updateByPrimaryKeySelective(dispatchActivitiApply);
		return dispatchActivitiApply;
	}

	@Override
	public DispatchActivitiApplyDto getDisApplyById(int id) {
		// TODO Auto-generated method stu
		return disExApplyMapper.getDispathcApplyById(id);
	}

	@Override
	public int deleteDispatchApply(int id) {
		// TODO Auto-generated method stub
		DispatchActivitiApply dispatchActivitiApply = disApplyMapper.selectByPrimaryKey(id);
		if (dispatchActivitiApply == null) {
			throw new CommonException("不存在此条记录");

		}
		if (dispatchActivitiApply.getProcessinstanceid() != null) {
			throw new CommonException("流程中，无法删除");

		}

		return disApplyMapper.deleteByPrimaryKey(id);

	}

	@Override
	public PageInfo<DispatchActivitiApply> getDisApplyList(int state,int pagesize,int pageno,Date startdate,Date enddate,Integer uid) {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<>();
		params.put("state",state);
		params.put("startdate",startdate);
		params.put("enddate",enddate);
		params.put("uid",uid);
		PageHelper.startPage(pageno,pagesize);
		List<DispatchActivitiApply> resultList = disExApplyMapper.getDispatchPageList(params);
		PageInfo<DispatchActivitiApply> pageInfo = new PageInfo<>(resultList);
		return pageInfo;
	}

	@Override
	public void setSuccess(int id) {
		// TODO Auto-generated method stub
		
		DispatchActivitiApply dispatchActivitiApply = disApplyMapper.selectByPrimaryKey(id);
		if (dispatchActivitiApply != null && dispatchActivitiApply.getIssuccess() == 1) {

			throw new CommonException("当前流程已结束");

		}
		dispatchActivitiApply.setId(id);
		dispatchActivitiApply.setIssuccess(1);
		disApplyMapper.updateByPrimaryKey(dispatchActivitiApply);
	

	}

	@Override
	public int startDispatchActivitiApply(String processDefinitionKey, String bussinessKey,
			Map<String, Object> variables) throws Exception {
		// TODO Auto-generated method stub
		// 判断参数是否存在
		int result = 0;
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
		DispatchActivitiApply dispatchActivitiApply = disApplyMapper.selectByPrimaryKey(Integer.parseInt(bussinessKey));
		dispatchActivitiApply.setProcessinstanceid(processInstanceId);
		// 更新
		result = disApplyMapper.updateByPrimaryKey(dispatchActivitiApply);

		return result;
	}

	@Override
	public List<DispatchActivitiApply> getDispatchByCode(String code) {
		// TODO Auto-generated method stub
		DispatchActivitiApplyExample dispatchActivit = new DispatchActivitiApplyExample();
		Criteria criteria = dispatchActivit.createCriteria();
		criteria.andCodeEqualTo(code);
		return disApplyMapper.selectByExample(dispatchActivit);
	}



}
