package com.partymasses.modules.dao.mybatis.mapper;

import com.partymasses.modules.dao.mybatis.entity.UserNotice;
import com.partymasses.modules.dao.mybatis.entity.UserNoticeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserNoticeMapper {
	List<Integer> selectByUserIdNoRead(@Param("userid")Integer userid);    
	List<Integer> selectByUserIdReaded(@Param("userid")Integer userid);    
	int quantityOfInformationNoRead(@Param("userid")Integer userid); 
	int quantityOfInformationReaded(@Param("userid")Integer userid); 
	int addUserNotice(@Param("userNotices") List<UserNotice>list);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_notice
     *
     * @mbggenerated Wed Mar 28 08:54:38 CST 2018
     */
    int countByExample(UserNoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_notice
     *
     * @mbggenerated Wed Mar 28 08:54:38 CST 2018
     */
    int deleteByExample(UserNoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_notice
     *
     * @mbggenerated Wed Mar 28 08:54:38 CST 2018
     */
    int deleteByPrimaryKey(UserNotice key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_notice
     *
     * @mbggenerated Wed Mar 28 08:54:38 CST 2018
     */
    int insert(UserNotice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_notice
     *
     * @mbggenerated Wed Mar 28 08:54:38 CST 2018
     */
    int insertSelective(UserNotice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_notice
     *
     * @mbggenerated Wed Mar 28 08:54:38 CST 2018
     */
    List<UserNotice> selectByExample(UserNoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_notice
     *
     * @mbggenerated Wed Mar 28 08:54:38 CST 2018
     */
    UserNotice selectByPrimaryKey(UserNotice key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_notice
     *
     * @mbggenerated Wed Mar 28 08:54:38 CST 2018
     */
    int updateByExampleSelective(@Param("record") UserNotice record, @Param("example") UserNoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_notice
     *
     * @mbggenerated Wed Mar 28 08:54:38 CST 2018
     */
    int updateByExample(@Param("record") UserNotice record, @Param("example") UserNoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_notice
     *
     * @mbggenerated Wed Mar 28 08:54:38 CST 2018
     */
    int updateByPrimaryKeySelective(UserNotice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_notice
     *
     * @mbggenerated Wed Mar 28 08:54:38 CST 2018
     */
    int updateByPrimaryKey(UserNotice record);
}