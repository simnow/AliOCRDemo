package com.partymasses.modules.dao.mybatis.mapper;

import com.partymasses.modules.dao.mybatis.entity.Function;
import com.partymasses.modules.dao.mybatis.entity.FunctionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FunctionMapper {
	Function getByUnitIDFunction(@Param("functionid")Integer functionid);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table function
     *
     * @mbggenerated Thu Mar 22 13:51:41 CST 2018
     */
    int countByExample(FunctionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table function
     *
     * @mbggenerated Thu Mar 22 13:51:41 CST 2018
     */
    int deleteByExample(FunctionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table function
     *
     * @mbggenerated Thu Mar 22 13:51:41 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table function
     *
     * @mbggenerated Thu Mar 22 13:51:41 CST 2018
     */
    int insert(Function record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table function
     *
     * @mbggenerated Thu Mar 22 13:51:41 CST 2018
     */
    int insertSelective(Function record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table function
     *
     * @mbggenerated Thu Mar 22 13:51:41 CST 2018
     */
    List<Function> selectByExampleWithBLOBs(FunctionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table function
     *
     * @mbggenerated Thu Mar 22 13:51:41 CST 2018
     */
    List<Function> selectByExample(FunctionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table function
     *
     * @mbggenerated Thu Mar 22 13:51:41 CST 2018
     */
    Function selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table function
     *
     * @mbggenerated Thu Mar 22 13:51:41 CST 2018
     */
    int updateByExampleSelective(@Param("record") Function record, @Param("example") FunctionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table function
     *
     * @mbggenerated Thu Mar 22 13:51:41 CST 2018
     */
    int updateByExampleWithBLOBs(@Param("record") Function record, @Param("example") FunctionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table function
     *
     * @mbggenerated Thu Mar 22 13:51:41 CST 2018
     */
    int updateByExample(@Param("record") Function record, @Param("example") FunctionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table function
     *
     * @mbggenerated Thu Mar 22 13:51:41 CST 2018
     */
    int updateByPrimaryKeySelective(Function record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table function
     *
     * @mbggenerated Thu Mar 22 13:51:41 CST 2018
     */
    int updateByPrimaryKeyWithBLOBs(Function record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table function
     *
     * @mbggenerated Thu Mar 22 13:51:41 CST 2018
     */
    int updateByPrimaryKey(Function record);
}