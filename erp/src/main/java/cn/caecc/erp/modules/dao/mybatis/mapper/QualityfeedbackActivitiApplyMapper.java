package cn.caecc.erp.modules.dao.mybatis.mapper;

import cn.caecc.erp.modules.dao.mybatis.entity.QualityfeedbackActivitiApply;
import cn.caecc.erp.modules.dao.mybatis.entity.QualityfeedbackActivitiApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QualityfeedbackActivitiApplyMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table qualityfeedback_activiti_apply
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	int countByExample(QualityfeedbackActivitiApplyExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table qualityfeedback_activiti_apply
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	int deleteByExample(QualityfeedbackActivitiApplyExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table qualityfeedback_activiti_apply
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table qualityfeedback_activiti_apply
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	int insert(QualityfeedbackActivitiApply record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table qualityfeedback_activiti_apply
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	int insertSelective(QualityfeedbackActivitiApply record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table qualityfeedback_activiti_apply
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	List<QualityfeedbackActivitiApply> selectByExample(QualityfeedbackActivitiApplyExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table qualityfeedback_activiti_apply
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	QualityfeedbackActivitiApply selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table qualityfeedback_activiti_apply
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	int updateByExampleSelective(@Param("record") QualityfeedbackActivitiApply record,
			@Param("example") QualityfeedbackActivitiApplyExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table qualityfeedback_activiti_apply
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	int updateByExample(@Param("record") QualityfeedbackActivitiApply record,
			@Param("example") QualityfeedbackActivitiApplyExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table qualityfeedback_activiti_apply
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	int updateByPrimaryKeySelective(QualityfeedbackActivitiApply record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table qualityfeedback_activiti_apply
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	int updateByPrimaryKey(QualityfeedbackActivitiApply record);
}