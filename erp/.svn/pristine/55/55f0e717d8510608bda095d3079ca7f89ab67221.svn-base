package cn.caecc.erp.modules.service;


import java.io.InputStream;
import java.util.List;

import com.github.pagehelper.PageInfo;

import cn.caecc.erp.modules.dao.ex.dto.ContractActivitiApplyExDto;
import cn.caecc.erp.modules.dao.ex.dto.ContractGoodsRelationshipDto;
import cn.caecc.erp.modules.dao.mybatis.entity.ContractGoodsRelationship;
import cn.caecc.erp.support.message.Message;

public interface ContractGoodsRelationshipService {

	public List<ContractGoodsRelationship> create(List<ContractGoodsRelationship> list);
	
	ContractGoodsRelationship findById(int id);

	ContractGoodsRelationship findDetail(int id);

	PageInfo<ContractGoodsRelationshipDto> getList(int pageNo, int pageSize, String gcode);

	Message batchUpdate(ContractActivitiApplyExDto contractActivitiApplyExDto);

	int deleteById(int id);
	
	List<ContractGoodsRelationship> exprotExcel(InputStream in,String fileName,int contractId) throws Exception;

	public ContractActivitiApplyExDto update(ContractActivitiApplyExDto contractActivitiApplyExDto);
}
