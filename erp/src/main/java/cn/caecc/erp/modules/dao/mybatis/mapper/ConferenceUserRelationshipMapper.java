package cn.caecc.erp.modules.dao.mybatis.mapper;

import cn.caecc.erp.modules.dao.mybatis.entity.ConferenceUserRelationship;
import cn.caecc.erp.modules.dao.mybatis.entity.ConferenceUserRelationshipExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConferenceUserRelationshipMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table conference_user_relationship
     *
     * @mbggenerated Wed Jul 18 09:28:43 CST 2018
     */
    int countByExample(ConferenceUserRelationshipExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table conference_user_relationship
     *
     * @mbggenerated Wed Jul 18 09:28:43 CST 2018
     */
    int deleteByExample(ConferenceUserRelationshipExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table conference_user_relationship
     *
     * @mbggenerated Wed Jul 18 09:28:43 CST 2018
     */
    int insert(ConferenceUserRelationship record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table conference_user_relationship
     *
     * @mbggenerated Wed Jul 18 09:28:43 CST 2018
     */
    int insertSelective(ConferenceUserRelationship record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table conference_user_relationship
     *
     * @mbggenerated Wed Jul 18 09:28:43 CST 2018
     */
    List<ConferenceUserRelationship> selectByExample(ConferenceUserRelationshipExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table conference_user_relationship
     *
     * @mbggenerated Wed Jul 18 09:28:43 CST 2018
     */
    int updateByExampleSelective(@Param("record") ConferenceUserRelationship record, @Param("example") ConferenceUserRelationshipExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table conference_user_relationship
     *
     * @mbggenerated Wed Jul 18 09:28:43 CST 2018
     */
    int updateByExample(@Param("record") ConferenceUserRelationship record, @Param("example") ConferenceUserRelationshipExample example);
}