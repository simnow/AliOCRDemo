package com.partymasses.modules.dao.mybatis.mapper;

import com.partymasses.modules.dao.mybatis.entity.Limit;
import com.partymasses.modules.dao.mybatis.entity.LimitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LimitMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table limit
     *
     * @mbggenerated Sun Apr 08 11:10:43 CST 2018
     */
    int countByExample(LimitExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table limit
     *
     * @mbggenerated Sun Apr 08 11:10:43 CST 2018
     */
    int deleteByExample(LimitExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table limit
     *
     * @mbggenerated Sun Apr 08 11:10:43 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table limit
     *
     * @mbggenerated Sun Apr 08 11:10:43 CST 2018
     */
    int insert(Limit record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table limit
     *
     * @mbggenerated Sun Apr 08 11:10:43 CST 2018
     */
    int insertSelective(Limit record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table limit
     *
     * @mbggenerated Sun Apr 08 11:10:43 CST 2018
     */
    List<Limit> selectByExample(LimitExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table limit
     *
     * @mbggenerated Sun Apr 08 11:10:43 CST 2018
     */
    Limit selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table limit
     *
     * @mbggenerated Sun Apr 08 11:10:43 CST 2018
     */
    int updateByExampleSelective(@Param("record") Limit record, @Param("example") LimitExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table limit
     *
     * @mbggenerated Sun Apr 08 11:10:43 CST 2018
     */
    int updateByExample(@Param("record") Limit record, @Param("example") LimitExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table limit
     *
     * @mbggenerated Sun Apr 08 11:10:43 CST 2018
     */
    int updateByPrimaryKeySelective(Limit record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table limit
     *
     * @mbggenerated Sun Apr 08 11:10:43 CST 2018
     */
    int updateByPrimaryKey(Limit record);
}