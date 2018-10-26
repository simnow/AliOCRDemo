package cn.caecc.erp.modules.dao.mybatis.mapper;

import cn.caecc.erp.modules.dao.mybatis.entity.WellLog;
import cn.caecc.erp.modules.dao.mybatis.entity.WellLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WellLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table well_log
     *
     * @mbggenerated Tue Jun 26 17:52:59 CST 2018
     */
    int countByExample(WellLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table well_log
     *
     * @mbggenerated Tue Jun 26 17:52:59 CST 2018
     */
    int deleteByExample(WellLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table well_log
     *
     * @mbggenerated Tue Jun 26 17:52:59 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table well_log
     *
     * @mbggenerated Tue Jun 26 17:52:59 CST 2018
     */
    int insert(WellLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table well_log
     *
     * @mbggenerated Tue Jun 26 17:52:59 CST 2018
     */
    int insertSelective(WellLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table well_log
     *
     * @mbggenerated Tue Jun 26 17:52:59 CST 2018
     */
    List<WellLog> selectByExample(WellLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table well_log
     *
     * @mbggenerated Tue Jun 26 17:52:59 CST 2018
     */
    WellLog selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table well_log
     *
     * @mbggenerated Tue Jun 26 17:52:59 CST 2018
     */
    int updateByExampleSelective(@Param("record") WellLog record, @Param("example") WellLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table well_log
     *
     * @mbggenerated Tue Jun 26 17:52:59 CST 2018
     */
    int updateByExample(@Param("record") WellLog record, @Param("example") WellLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table well_log
     *
     * @mbggenerated Tue Jun 26 17:52:59 CST 2018
     */
    int updateByPrimaryKeySelective(WellLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table well_log
     *
     * @mbggenerated Tue Jun 26 17:52:59 CST 2018
     */
    int updateByPrimaryKey(WellLog record);
}