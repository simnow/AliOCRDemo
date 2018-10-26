package cn.caecc.erp.modules.dao.mybatis.mapper;

import cn.caecc.erp.modules.dao.mybatis.entity.OutsideActivitiApply;
import cn.caecc.erp.modules.dao.mybatis.entity.OutsideActivitiApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OutsideActivitiApplyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table outside_activiti_apply
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    int countByExample(OutsideActivitiApplyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table outside_activiti_apply
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    int deleteByExample(OutsideActivitiApplyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table outside_activiti_apply
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table outside_activiti_apply
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    int insert(OutsideActivitiApply record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table outside_activiti_apply
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    int insertSelective(OutsideActivitiApply record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table outside_activiti_apply
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    List<OutsideActivitiApply> selectByExample(OutsideActivitiApplyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table outside_activiti_apply
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    OutsideActivitiApply selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table outside_activiti_apply
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    int updateByExampleSelective(@Param("record") OutsideActivitiApply record, @Param("example") OutsideActivitiApplyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table outside_activiti_apply
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    int updateByExample(@Param("record") OutsideActivitiApply record, @Param("example") OutsideActivitiApplyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table outside_activiti_apply
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    int updateByPrimaryKeySelective(OutsideActivitiApply record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table outside_activiti_apply
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    int updateByPrimaryKey(OutsideActivitiApply record);
}