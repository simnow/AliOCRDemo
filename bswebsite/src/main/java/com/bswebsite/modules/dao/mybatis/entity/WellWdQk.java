package com.bswebsite.modules.dao.mybatis.entity;

import java.util.Date;

public class WellWdQk {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column well_wd_qk.Id
     *
     * @mbggenerated Tue Jun 19 16:30:41 CST 2018
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column well_wd_qk.QkName
     *
     * @mbggenerated Tue Jun 19 16:30:41 CST 2018
     */
    private String qkname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column well_wd_qk.CreateTime
     *
     * @mbggenerated Tue Jun 19 16:30:41 CST 2018
     */
    private Date createtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column well_wd_qk.Id
     *
     * @return the value of well_wd_qk.Id
     *
     * @mbggenerated Tue Jun 19 16:30:41 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column well_wd_qk.Id
     *
     * @param id the value for well_wd_qk.Id
     *
     * @mbggenerated Tue Jun 19 16:30:41 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column well_wd_qk.QkName
     *
     * @return the value of well_wd_qk.QkName
     *
     * @mbggenerated Tue Jun 19 16:30:41 CST 2018
     */
    public String getQkname() {
        return qkname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column well_wd_qk.QkName
     *
     * @param qkname the value for well_wd_qk.QkName
     *
     * @mbggenerated Tue Jun 19 16:30:41 CST 2018
     */
    public void setQkname(String qkname) {
        this.qkname = qkname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column well_wd_qk.CreateTime
     *
     * @return the value of well_wd_qk.CreateTime
     *
     * @mbggenerated Tue Jun 19 16:30:41 CST 2018
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column well_wd_qk.CreateTime
     *
     * @param createtime the value for well_wd_qk.CreateTime
     *
     * @mbggenerated Tue Jun 19 16:30:41 CST 2018
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}