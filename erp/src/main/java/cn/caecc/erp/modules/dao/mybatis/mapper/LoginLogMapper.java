package cn.caecc.erp.modules.dao.mybatis.mapper;

import cn.caecc.erp.modules.dao.mybatis.entity.LoginLog;
import cn.caecc.erp.modules.dao.mybatis.entity.LoginLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LoginLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_log
     *
     * @mbggenerated Mon Jul 30 23:43:34 CST 2018
     */
    int countByExample(LoginLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_log
     *
     * @mbggenerated Mon Jul 30 23:43:34 CST 2018
     */
    int deleteByExample(LoginLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_log
     *
     * @mbggenerated Mon Jul 30 23:43:34 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_log
     *
     * @mbggenerated Mon Jul 30 23:43:34 CST 2018
     */
    int insert(LoginLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_log
     *
     * @mbggenerated Mon Jul 30 23:43:34 CST 2018
     */
    int insertSelective(LoginLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_log
     *
     * @mbggenerated Mon Jul 30 23:43:34 CST 2018
     */
    List<LoginLog> selectByExample(LoginLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_log
     *
     * @mbggenerated Mon Jul 30 23:43:34 CST 2018
     */
    LoginLog selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_log
     *
     * @mbggenerated Mon Jul 30 23:43:34 CST 2018
     */
    int updateByExampleSelective(@Param("record") LoginLog record, @Param("example") LoginLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_log
     *
     * @mbggenerated Mon Jul 30 23:43:34 CST 2018
     */
    int updateByExample(@Param("record") LoginLog record, @Param("example") LoginLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_log
     *
     * @mbggenerated Mon Jul 30 23:43:34 CST 2018
     */
    int updateByPrimaryKeySelective(LoginLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_log
     *
     * @mbggenerated Mon Jul 30 23:43:34 CST 2018
     */
    int updateByPrimaryKey(LoginLog record);
}