package cn.caecc.erp.modules.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import cn.caecc.erp.modules.dao.ex.dto.SealActivitiApplyDto;
import cn.caecc.erp.modules.dao.mybatis.entity.SealActivitiApply;
public interface SealActivitiApplyService {
	//保存流程
	public  SealActivitiApply  addSealApply(SealActivitiApply sealApply);
	
	//开始流程
	public int   startSealApply(String processDefinitionKey,String bussinessKey,Map<String, Object> variables) throws Exception ;
	
	//通过ID获取印章详情
	public SealActivitiApplyDto   getSealApplyById(int id);
	
	//通过ID获取印章详情
	public PageInfo<SealActivitiApply>   getSealApplyList(int state,int pagesize,int pageno,int uid);
	
	//修改
	public SealActivitiApply   updateSealApply(SealActivitiApply sealActivitiApply);
	
	//删除
	public void deleteSealApply(int id);
	
	//
	public void  setSuccess(int id);
	
	
}
