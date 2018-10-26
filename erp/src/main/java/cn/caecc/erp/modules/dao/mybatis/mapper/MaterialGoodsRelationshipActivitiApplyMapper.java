package cn.caecc.erp.modules.dao.mybatis.mapper;

import cn.caecc.erp.modules.dao.mybatis.entity.MaterialGoodsRelationshipActivitiApply;
import cn.caecc.erp.modules.dao.mybatis.entity.MaterialGoodsRelationshipActivitiApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MaterialGoodsRelationshipActivitiApplyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table material_goods_relationship_activiti_apply
     *
     * @mbggenerated Wed Aug 01 18:31:25 CST 2018
     */
    int countByExample(MaterialGoodsRelationshipActivitiApplyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table material_goods_relationship_activiti_apply
     *
     * @mbggenerated Wed Aug 01 18:31:25 CST 2018
     */
    int deleteByExample(MaterialGoodsRelationshipActivitiApplyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table material_goods_relationship_activiti_apply
     *
     * @mbggenerated Wed Aug 01 18:31:25 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table material_goods_relationship_activiti_apply
     *
     * @mbggenerated Wed Aug 01 18:31:25 CST 2018
     */
    int insert(MaterialGoodsRelationshipActivitiApply record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table material_goods_relationship_activiti_apply
     *
     * @mbggenerated Wed Aug 01 18:31:25 CST 2018
     */
    int insertSelective(MaterialGoodsRelationshipActivitiApply record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table material_goods_relationship_activiti_apply
     *
     * @mbggenerated Wed Aug 01 18:31:25 CST 2018
     */
    List<MaterialGoodsRelationshipActivitiApply> selectByExample(MaterialGoodsRelationshipActivitiApplyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table material_goods_relationship_activiti_apply
     *
     * @mbggenerated Wed Aug 01 18:31:25 CST 2018
     */
    MaterialGoodsRelationshipActivitiApply selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table material_goods_relationship_activiti_apply
     *
     * @mbggenerated Wed Aug 01 18:31:25 CST 2018
     */
    int updateByExampleSelective(@Param("record") MaterialGoodsRelationshipActivitiApply record, @Param("example") MaterialGoodsRelationshipActivitiApplyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table material_goods_relationship_activiti_apply
     *
     * @mbggenerated Wed Aug 01 18:31:25 CST 2018
     */
    int updateByExample(@Param("record") MaterialGoodsRelationshipActivitiApply record, @Param("example") MaterialGoodsRelationshipActivitiApplyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table material_goods_relationship_activiti_apply
     *
     * @mbggenerated Wed Aug 01 18:31:25 CST 2018
     */
    int updateByPrimaryKeySelective(MaterialGoodsRelationshipActivitiApply record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table material_goods_relationship_activiti_apply
     *
     * @mbggenerated Wed Aug 01 18:31:25 CST 2018
     */
    int updateByPrimaryKey(MaterialGoodsRelationshipActivitiApply record);
}