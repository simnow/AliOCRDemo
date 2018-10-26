package cn.caecc.erp.modules.dao.mybatis.mapper;

import cn.caecc.erp.modules.dao.mybatis.entity.SealActivitiApply;
import cn.caecc.erp.modules.dao.mybatis.entity.SealActivitiApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SealActivitiApplyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seal_activiti_apply
     *
     * @mbggenerated Wed Jun 13 11:12:18 CST 2018
     */
    int countByExample(SealActivitiApplyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seal_activiti_apply
     *
     * @mbggenerated Wed Jun 13 11:12:18 CST 2018
     */
    int deleteByExample(SealActivitiApplyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seal_activiti_apply
     *
     * @mbggenerated Wed Jun 13 11:12:18 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seal_activiti_apply
     *
     * @mbggenerated Wed Jun 13 11:12:18 CST 2018
     */
    int insert(SealActivitiApply record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seal_activiti_apply
     *
     * @mbggenerated Wed Jun 13 11:12:18 CST 2018
     */
    int insertSelective(SealActivitiApply record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seal_activiti_apply
     *
     * @mbggenerated Wed Jun 13 11:12:18 CST 2018
     */
    List<SealActivitiApply> selectByExample(SealActivitiApplyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seal_activiti_apply
     *
     * @mbggenerated Wed Jun 13 11:12:18 CST 2018
     */
    SealActivitiApply selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seal_activiti_apply
     *
     * @mbggenerated Wed Jun 13 11:12:18 CST 2018
     */
    int updateByExampleSelective(@Param("record") SealActivitiApply record, @Param("example") SealActivitiApplyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seal_activiti_apply
     *
     * @mbggenerated Wed Jun 13 11:12:18 CST 2018
     */
    int updateByExample(@Param("record") SealActivitiApply record, @Param("example") SealActivitiApplyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seal_activiti_apply
     *
     * @mbggenerated Wed Jun 13 11:12:18 CST 2018
     */
    int updateByPrimaryKeySelective(SealActivitiApply record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seal_activiti_apply
     *
     * @mbggenerated Wed Jun 13 11:12:18 CST 2018
     */
    int updateByPrimaryKey(SealActivitiApply record);
}