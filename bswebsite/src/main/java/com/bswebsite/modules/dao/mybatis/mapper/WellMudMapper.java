package com.bswebsite.modules.dao.mybatis.mapper;

import com.bswebsite.modules.dao.mybatis.entity.WellMud;
import com.bswebsite.modules.dao.mybatis.entity.WellMudExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WellMudMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table well_mud
     *
     * @mbggenerated Tue Jun 19 16:30:41 CST 2018
     */
    int countByExample(WellMudExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table well_mud
     *
     * @mbggenerated Tue Jun 19 16:30:41 CST 2018
     */
    int deleteByExample(WellMudExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table well_mud
     *
     * @mbggenerated Tue Jun 19 16:30:41 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table well_mud
     *
     * @mbggenerated Tue Jun 19 16:30:41 CST 2018
     */
    int insert(WellMud record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table well_mud
     *
     * @mbggenerated Tue Jun 19 16:30:41 CST 2018
     */
    int insertSelective(WellMud record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table well_mud
     *
     * @mbggenerated Tue Jun 19 16:30:41 CST 2018
     */
    List<WellMud> selectByExample(WellMudExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table well_mud
     *
     * @mbggenerated Tue Jun 19 16:30:41 CST 2018
     */
    WellMud selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table well_mud
     *
     * @mbggenerated Tue Jun 19 16:30:41 CST 2018
     */
    int updateByExampleSelective(@Param("record") WellMud record, @Param("example") WellMudExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table well_mud
     *
     * @mbggenerated Tue Jun 19 16:30:41 CST 2018
     */
    int updateByExample(@Param("record") WellMud record, @Param("example") WellMudExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table well_mud
     *
     * @mbggenerated Tue Jun 19 16:30:41 CST 2018
     */
    int updateByPrimaryKeySelective(WellMud record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table well_mud
     *
     * @mbggenerated Tue Jun 19 16:30:41 CST 2018
     */
    int updateByPrimaryKey(WellMud record);
}