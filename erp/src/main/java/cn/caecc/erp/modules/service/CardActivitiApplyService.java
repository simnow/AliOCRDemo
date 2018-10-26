package cn.caecc.erp.modules.service;

import java.util.Map;
import com.github.pagehelper.PageInfo;
import cn.caecc.erp.modules.dao.ex.dto.CardActivitiApplyDto;
import cn.caecc.erp.modules.dao.mybatis.entity.CardActivitiApply;

public interface CardActivitiApplyService {
	
	public  int  addCardActivitiApply(CardActivitiApply cardActivitiApply);
	
	
	public  int  deleteCardActivitiApply(int id);
	
	
	public  PageInfo<CardActivitiApplyDto>  getAcApplyPageList(int state,int pagesize,int pageno,int uid);
	
	
	public  int  startCardActivitiApply(String processDefinitionKey,String bussinessKey,Map<String, Object> variables) throws Exception;
	
	
	public  CardActivitiApplyDto  getCardApplyByid(int id);
	
	
	public  CardActivitiApply     updateCardApply(CardActivitiApply cardActivitiApply);
	
	
	public void    setSuccess(int id);
	

}
