package cn.caecc.erp.modules.dao.mybatis.mapper;

import cn.caecc.erp.modules.dao.mybatis.entity.ConferenceArrangement;
import cn.caecc.erp.modules.dao.mybatis.entity.ConferenceArrangementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConferenceArrangementMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table conference_arrangement
     *
     * @mbggenerated Tue Jul 03 08:57:00 CST 2018
     */
    int countByExample(ConferenceArrangementExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table conference_arrangement
     *
     * @mbggenerated Tue Jul 03 08:57:00 CST 2018
     */
    int deleteByExample(ConferenceArrangementExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table conference_arrangement
     *
     * @mbggenerated Tue Jul 03 08:57:00 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table conference_arrangement
     *
     * @mbggenerated Tue Jul 03 08:57:00 CST 2018
     */
    int insert(ConferenceArrangement record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table conference_arrangement
     *
     * @mbggenerated Tue Jul 03 08:57:00 CST 2018
     */
    int insertSelective(ConferenceArrangement record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table conference_arrangement
     *
     * @mbggenerated Tue Jul 03 08:57:00 CST 2018
     */
    List<ConferenceArrangement> selectByExample(ConferenceArrangementExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table conference_arrangement
     *
     * @mbggenerated Tue Jul 03 08:57:00 CST 2018
     */
    ConferenceArrangement selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table conference_arrangement
     *
     * @mbggenerated Tue Jul 03 08:57:00 CST 2018
     */
    int updateByExampleSelective(@Param("record") ConferenceArrangement record, @Param("example") ConferenceArrangementExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table conference_arrangement
     *
     * @mbggenerated Tue Jul 03 08:57:00 CST 2018
     */
    int updateByExample(@Param("record") ConferenceArrangement record, @Param("example") ConferenceArrangementExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table conference_arrangement
     *
     * @mbggenerated Tue Jul 03 08:57:00 CST 2018
     */
    int updateByPrimaryKeySelective(ConferenceArrangement record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table conference_arrangement
     *
     * @mbggenerated Tue Jul 03 08:57:00 CST 2018
     */
    int updateByPrimaryKey(ConferenceArrangement record);
}