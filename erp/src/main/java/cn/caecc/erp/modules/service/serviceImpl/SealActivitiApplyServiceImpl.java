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
import cn.caecc.erp.modules.dao.ex.dto.SealActivitiApplyDto;
import cn.caecc.erp.modules.dao.ex.mapper.SealActivitiApplyExMapper;
import cn.caecc.erp.modules.dao.mybatis.entity.SealActivitiApply;
import cn.caecc.erp.modules.dao.mybatis.mapper.SealActivitiApplyMapper;
import cn.caecc.erp.modules.service.SealActivitiApplyService;
import cn.caecc.erp.support.constant.Contants;
import cn.caecc.erp.support.exception.CommonException;
import cn.caecc.erp.support.util.DateUtil;
import cn.caecc.erp.support.workflow.service.WorkflowService;

@Service
public class SealActivitiApplyServiceImpl implements SealActivitiApplyService {
	@Autowired
	private SealActivitiApplyMapper saMapper;
	@Autowired
	private SealActivitiApplyExMapper saExMapper;
	@Autowired
	private WorkflowService workflowService;

	@Override
	public SealActivitiApply addSealApply(SealActivitiApply sealActivitiApply) {

		Integer loginUserId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(Contants.LOGINUSERID);

		// 判断是否第一次保存
		if (sealActivitiApply.getId() == null) {
			sealActivitiApply.setCreateuserid(loginUserId);
			// 设置创建时间
			sealActivitiApply.setCreatetime(DateUtil.getcurrentDateTime());
			// 保存数据

			saMapper.insertSelective(sealActivitiApply);

		}
		// 修改操作
		else {
			SealActivitiApply exists = saMapper.selectByPrimaryKey(sealActivitiApply.getId());
			if (StringUtils.isBlank(exists.getProcessinstanceid())) {

				saMapper.updateByPrimaryKeySelective(sealActivitiApply);

			}
			else{
				throw new CommonException("流程中，无法进行修改操作");
			}
		}

		return sealActivitiApply;

	}

	@Override
	public int startSealApply(String processDefinitionKey, String bussinessKey, Map<String, Object> variables) throws Exception {
		// TODO Auto-generated method stub
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
		SealActivitiApply sealActivitiApply = saMapper.selectByPrimaryKey(Integer.parseInt(bussinessKey));
		sealActivitiApply.setProcessinstanceid(processInstanceId);
		// 更新
		saMapper.updateByPrimaryKey(sealActivitiApply);
		// 返回成功
		return 1;

	}

	@Override
	public SealActivitiApplyDto getSealApplyById(int id) {
		// TODO Auto-generated method stub
		return saExMapper.getCardApplyById(id);

	}

	@Override
	public PageInfo<SealActivitiApply> getSealApplyList(int state,int pagesize,int pageno,int uid) {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("state",state);
		params.put("uid",uid);
		PageHelper.startPage(pageno, pagesize);
		List<SealActivitiApply> resultList = saExMapper.getSealPageList(params);
		PageInfo<SealActivitiApply> pageInfo = new PageInfo<>(resultList);
		return pageInfo;
	}

	@Override
	public SealActivitiApply updateSealApply(SealActivitiApply sealActivitiApply) {
		// TODO Auto-generated method stub

		saMapper.updateByPrimaryKeySelective(sealActivitiApply);
		return sealActivitiApply;

	}

	@Override
	public void setSuccess(int id) {
		// TODO Auto-generated method stub
		SealActivitiApply sealActivitiApply = new SealActivitiApply();
		sealActivitiApply.setId(id);
		sealActivitiApply.setIssuccess(1);
		saMapper.updateByPrimaryKeySelective(sealActivitiApply);

	}

	@Override
	public void deleteSealApply(int id) {
		// TODO Auto-generated method stub
		SealActivitiApply sealActivitiApply = saMapper.selectByPrimaryKey(id);
		if (StringUtils.isBlank(sealActivitiApply.getProcessinstanceid())) {
			 int i=saMapper.deleteByPrimaryKey(id);
			 if(i<=0){
				 throw new CommonException("未找到此条记录，请刷新重试"); 
			 }
		} else {
			throw new CommonException("存在流程信息，无法删除");
		}
	}

}
