package cn.caecc.erp.modules.dao.mybatis.mapper;

import cn.caecc.erp.modules.dao.mybatis.entity.ContractActivitiApply;
import cn.caecc.erp.modules.dao.mybatis.entity.ContractActivitiApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ContractActivitiApplyMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table contract_activiti_apply
	 * @mbggenerated  Thu Aug 09 11:54:46 CST 2018
	 */
	int countByExample(ContractActivitiApplyExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table contract_activiti_apply
	 * @mbggenerated  Thu Aug 09 11:54:46 CST 2018
	 */
	int deleteByExample(ContractActivitiApplyExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table contract_activiti_apply
	 * @mbggenerated  Thu Aug 09 11:54:46 CST 2018
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table contract_activiti_apply
	 * @mbggenerated  Thu Aug 09 11:54:46 CST 2018
	 */
	int insert(ContractActivitiApply record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table contract_activiti_apply
	 * @mbggenerated  Thu Aug 09 11:54:46 CST 2018
	 */
	int insertSelective(ContractActivitiApply record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table contract_activiti_apply
	 * @mbggenerated  Thu Aug 09 11:54:46 CST 2018
	 */
	List<ContractActivitiApply> selectByExample(ContractActivitiApplyExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table contract_activiti_apply
	 * @mbggenerated  Thu Aug 09 11:54:46 CST 2018
	 */
	ContractActivitiApply selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table contract_activiti_apply
	 * @mbggenerated  Thu Aug 09 11:54:46 CST 2018
	 */
	int updateByExampleSelective(@Param("record") ContractActivitiApply record,
			@Param("example") ContractActivitiApplyExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table contract_activiti_apply
	 * @mbggenerated  Thu Aug 09 11:54:46 CST 2018
	 */
	int updateByExample(@Param("record") ContractActivitiApply record,
			@Param("example") ContractActivitiApplyExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table contract_activiti_apply
	 * @mbggenerated  Thu Aug 09 11:54:46 CST 2018
	 */
	int updateByPrimaryKeySelective(ContractActivitiApply record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table contract_activiti_apply
	 * @mbggenerated  Thu Aug 09 11:54:46 CST 2018
	 */
	int updateByPrimaryKey(ContractActivitiApply record);
}