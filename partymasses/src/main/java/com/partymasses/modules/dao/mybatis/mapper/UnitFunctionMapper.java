package com.partymasses.modules.dao.mybatis.mapper;

import com.partymasses.modules.dao.mybatis.entity.UnitFunction;
import com.partymasses.modules.dao.mybatis.entity.UnitFunctionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UnitFunctionMapper {
	List<UnitFunction> getByUnitID(@Param("unitid")Integer unitid);
	//List<Function>getByUnitIDFunction(@Param("unitid")Integer unitid);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unit_function
     *
     * @mbggenerated Thu Mar 22 13:51:41 CST 2018
     */
    int countByExample(UnitFunctionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unit_function
     *
     * @mbggenerated Thu Mar 22 13:51:41 CST 2018
     */
    int deleteByExample(UnitFunctionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unit_function
     *
     * @mbggenerated Thu Mar 22 13:51:41 CST 2018
     */
    int insert(UnitFunction record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unit_function
     *
     * @mbggenerated Thu Mar 22 13:51:41 CST 2018
     */
    int insertSelective(UnitFunction record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unit_function
     *
     * @mbggenerated Thu Mar 22 13:51:41 CST 2018
     */
    List<UnitFunction> selectByExample(UnitFunctionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unit_function
     *
     * @mbggenerated Thu Mar 22 13:51:41 CST 2018
     */
    int updateByExampleSelective(@Param("record") UnitFunction record, @Param("example") UnitFunctionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table unit_function
     *
     * @mbggenerated Thu Mar 22 13:51:41 CST 2018
     */
    int updateByExample(@Param("record") UnitFunction record, @Param("example") UnitFunctionExample example);
}