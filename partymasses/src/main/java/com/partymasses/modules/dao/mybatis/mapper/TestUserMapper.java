package com.partymasses.modules.dao.mybatis.mapper;

import com.partymasses.modules.dao.mybatis.entity.TestUser;
import com.partymasses.modules.dao.mybatis.entity.TestUserExample;
import com.partymasses.modules.dao.mybatis.entity.TestUserKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TestUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_user
     *
     * @mbggenerated Mon Apr 02 14:51:13 CST 2018
     */
    int countByExample(TestUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_user
     *
     * @mbggenerated Mon Apr 02 14:51:13 CST 2018
     */
    int deleteByExample(TestUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_user
     *
     * @mbggenerated Mon Apr 02 14:51:13 CST 2018
     */
    int deleteByPrimaryKey(TestUserKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_user
     *
     * @mbggenerated Mon Apr 02 14:51:13 CST 2018
     */
    int insert(TestUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_user
     *
     * @mbggenerated Mon Apr 02 14:51:13 CST 2018
     */
    int insertSelective(TestUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_user
     *
     * @mbggenerated Mon Apr 02 14:51:13 CST 2018
     */
    List<TestUser> selectByExample(TestUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_user
     *
     * @mbggenerated Mon Apr 02 14:51:13 CST 2018
     */
    TestUser selectByPrimaryKey(TestUserKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_user
     *
     * @mbggenerated Mon Apr 02 14:51:13 CST 2018
     */
    int updateByExampleSelective(@Param("record") TestUser record, @Param("example") TestUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_user
     *
     * @mbggenerated Mon Apr 02 14:51:13 CST 2018
     */
    int updateByExample(@Param("record") TestUser record, @Param("example") TestUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_user
     *
     * @mbggenerated Mon Apr 02 14:51:13 CST 2018
     */
    int updateByPrimaryKeySelective(TestUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table test_user
     *
     * @mbggenerated Mon Apr 02 14:51:13 CST 2018
     */
    int updateByPrimaryKey(TestUser record);
}