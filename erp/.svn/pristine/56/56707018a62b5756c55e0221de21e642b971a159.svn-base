package cn.caecc.erp.modules.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import cn.caecc.erp.modules.dao.mybatis.entity.DispatchActivitiApply;

public interface DispatchActivitiApplyService {
	
	public DispatchActivitiApply addDispatchApply(DispatchActivitiApply dispatchActivitiApply);
	
	public DispatchActivitiApply updateDispatchApply(DispatchActivitiApply dispatchActivitiApply);
	
	public DispatchActivitiApply getDisApplyById(int id);
	
	public int deleteDispatchApply(int id);
	
	public PageInfo<DispatchActivitiApply> getDisApplyList(int state,int pagesize,int pageno,Date startdate,Date enddate,Integer uid);
	
	public void    setSuccess(int id);
	
	public int  startDispatchActivitiApply(String processDefinitionKey,String bussinessKey,Map<String, Object> variables)  throws Exception;
	
	public List<DispatchActivitiApply> getDispatchByCode(String code);
	
	

}
