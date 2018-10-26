package com.partymasses.modules.dao.mybatis.mapper;

import com.partymasses.modules.dao.mybatis.entity.QuestionStyle;
import com.partymasses.modules.dao.mybatis.entity.QuestionStyleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QuestionStyleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question_style
     *
     * @mbggenerated Mon Apr 02 14:51:13 CST 2018
     */
    int countByExample(QuestionStyleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question_style
     *
     * @mbggenerated Mon Apr 02 14:51:13 CST 2018
     */
    int deleteByExample(QuestionStyleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question_style
     *
     * @mbggenerated Mon Apr 02 14:51:13 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question_style
     *
     * @mbggenerated Mon Apr 02 14:51:13 CST 2018
     */
    int insert(QuestionStyle record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question_style
     *
     * @mbggenerated Mon Apr 02 14:51:13 CST 2018
     */
    int insertSelective(QuestionStyle record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question_style
     *
     * @mbggenerated Mon Apr 02 14:51:13 CST 2018
     */
    List<QuestionStyle> selectByExample(QuestionStyleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question_style
     *
     * @mbggenerated Mon Apr 02 14:51:13 CST 2018
     */
    QuestionStyle selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question_style
     *
     * @mbggenerated Mon Apr 02 14:51:13 CST 2018
     */
    int updateByExampleSelective(@Param("record") QuestionStyle record, @Param("example") QuestionStyleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question_style
     *
     * @mbggenerated Mon Apr 02 14:51:13 CST 2018
     */
    int updateByExample(@Param("record") QuestionStyle record, @Param("example") QuestionStyleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question_style
     *
     * @mbggenerated Mon Apr 02 14:51:13 CST 2018
     */
    int updateByPrimaryKeySelective(QuestionStyle record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question_style
     *
     * @mbggenerated Mon Apr 02 14:51:13 CST 2018
     */
    int updateByPrimaryKey(QuestionStyle record);
}