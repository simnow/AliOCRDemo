package cn.caecc.erp.modules.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;
import com.github.pagehelper.PageInfo;
import cn.caecc.erp.modules.dao.ex.dto.ContractActivitiApplyDto;
import cn.caecc.erp.modules.dao.mybatis.entity.ContractActivitiApply;

public interface ContractActivitiApplyService {

	public ContractActivitiApply create(ContractActivitiApply contractActivitiApply);
	
	public ContractActivitiApply findById(int id);
	
	public ContractActivitiApplyDto findDetail(int id);
	
	public ContractActivitiApply findByCode(String code);
	
	public PageInfo<ContractActivitiApplyDto> getList(Integer userid, int pageNo, int pageSize, String drafts, int isSuccess, String operation, String name);
	
	public ContractActivitiApply update(ContractActivitiApply contractActivitiApply);
	
	public int startProcess(String processDefinitionKey, String bussinessKey, Map<String, Object> variables) throws Exception ;
	
	public void setSuccess(int id);

	public ByteArrayInputStream exportContractGoods(int id) throws IOException;
	
	public void checkContractDaily();

	public int deleteById(int id);

}