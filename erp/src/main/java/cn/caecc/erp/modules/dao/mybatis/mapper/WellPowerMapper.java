package cn.caecc.erp.modules.dao.mybatis.mapper;

import cn.caecc.erp.modules.dao.mybatis.entity.WellPower;
import cn.caecc.erp.modules.dao.mybatis.entity.WellPowerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WellPowerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table well_power
     *
     * @mbggenerated Thu Aug 09 11:27:31 CST 2018
     */
    int countByExample(WellPowerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table well_power
     *
     * @mbggenerated Thu Aug 09 11:27:31 CST 2018
     */
    int deleteByExample(WellPowerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table well_power
     *
     * @mbggenerated Thu Aug 09 11:27:31 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table well_power
     *
     * @mbggenerated Thu Aug 09 11:27:31 CST 2018
     */
    int insert(WellPower record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table well_power
     *
     * @mbggenerated Thu Aug 09 11:27:31 CST 2018
     */
    int insertSelective(WellPower record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table well_power
     *
     * @mbggenerated Thu Aug 09 11:27:31 CST 2018
     */
    List<WellPower> selectByExample(WellPowerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table well_power
     *
     * @mbggenerated Thu Aug 09 11:27:31 CST 2018
     */
    WellPower selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table well_power
     *
     * @mbggenerated Thu Aug 09 11:27:31 CST 2018
     */
    int updateByExampleSelective(@Param("record") WellPower record, @Param("example") WellPowerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table well_power
     *
     * @mbggenerated Thu Aug 09 11:27:31 CST 2018
     */
    int updateByExample(@Param("record") WellPower record, @Param("example") WellPowerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table well_power
     *
     * @mbggenerated Thu Aug 09 11:27:31 CST 2018
     */
    int updateByPrimaryKeySelective(WellPower record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table well_power
     *
     * @mbggenerated Thu Aug 09 11:27:31 CST 2018
     */
    int updateByPrimaryKey(WellPower record);
}