package cn.caecc.erp.modules.service;

import java.util.Map;
import com.github.pagehelper.PageInfo;
import cn.caecc.erp.modules.dao.mybatis.entity.EventActivitiApply;

public interface EventActivitiApplyService {
	
	public  EventActivitiApply  addEventActivitiApply(EventActivitiApply eventActivitiApply);
	
	
	public  int  deleteEventActivitiApply(int id);
	
	
	public  PageInfo<EventActivitiApply>  getEventApplyPageList(int state,int pagesize,int pageno,int uid);
	
	
	public  int  startEventActivitiApply(String processDefinitionKey,String bussinessKey,Map<String, Object> variables) throws Exception;
	
	
	public  EventActivitiApply  getEventApplyByid(int id);
	
	
	public  EventActivitiApply updateEventApply(EventActivitiApply eventActivitiApply);
	
	
	public void    setSuccess(int id);

}
