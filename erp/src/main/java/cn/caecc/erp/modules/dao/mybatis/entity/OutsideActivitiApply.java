package cn.caecc.erp.modules.dao.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

public class OutsideActivitiApply implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column outside_activiti_apply.Id
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column outside_activiti_apply.ProcessInstanceId
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    private String processinstanceid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column outside_activiti_apply.UId
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    private Integer uid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column outside_activiti_apply.DId
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    private Integer did;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column outside_activiti_apply.Content
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column outside_activiti_apply.Situation
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    private String situation;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column outside_activiti_apply.FinishUserId
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    private Integer finishuserid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column outside_activiti_apply.CreateUserId
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    private Integer createuserid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column outside_activiti_apply.CreateTime
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column outside_activiti_apply.IsSuccess
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    private Integer issuccess;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table outside_activiti_apply
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column outside_activiti_apply.Id
     *
     * @return the value of outside_activiti_apply.Id
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column outside_activiti_apply.Id
     *
     * @param id the value for outside_activiti_apply.Id
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column outside_activiti_apply.ProcessInstanceId
     *
     * @return the value of outside_activiti_apply.ProcessInstanceId
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    public String getProcessinstanceid() {
        return processinstanceid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column outside_activiti_apply.ProcessInstanceId
     *
     * @param processinstanceid the value for outside_activiti_apply.ProcessInstanceId
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    public void setProcessinstanceid(String processinstanceid) {
        this.processinstanceid = processinstanceid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column outside_activiti_apply.UId
     *
     * @return the value of outside_activiti_apply.UId
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column outside_activiti_apply.UId
     *
     * @param uid the value for outside_activiti_apply.UId
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column outside_activiti_apply.DId
     *
     * @return the value of outside_activiti_apply.DId
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    public Integer getDid() {
        return did;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column outside_activiti_apply.DId
     *
     * @param did the value for outside_activiti_apply.DId
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    public void setDid(Integer did) {
        this.did = did;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column outside_activiti_apply.Content
     *
     * @return the value of outside_activiti_apply.Content
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column outside_activiti_apply.Content
     *
     * @param content the value for outside_activiti_apply.Content
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column outside_activiti_apply.Situation
     *
     * @return the value of outside_activiti_apply.Situation
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    public String getSituation() {
        return situation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column outside_activiti_apply.Situation
     *
     * @param situation the value for outside_activiti_apply.Situation
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    public void setSituation(String situation) {
        this.situation = situation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column outside_activiti_apply.FinishUserId
     *
     * @return the value of outside_activiti_apply.FinishUserId
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    public Integer getFinishuserid() {
        return finishuserid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column outside_activiti_apply.FinishUserId
     *
     * @param finishuserid the value for outside_activiti_apply.FinishUserId
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    public void setFinishuserid(Integer finishuserid) {
        this.finishuserid = finishuserid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column outside_activiti_apply.CreateUserId
     *
     * @return the value of outside_activiti_apply.CreateUserId
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    public Integer getCreateuserid() {
        return createuserid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column outside_activiti_apply.CreateUserId
     *
     * @param createuserid the value for outside_activiti_apply.CreateUserId
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    public void setCreateuserid(Integer createuserid) {
        this.createuserid = createuserid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column outside_activiti_apply.CreateTime
     *
     * @return the value of outside_activiti_apply.CreateTime
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column outside_activiti_apply.CreateTime
     *
     * @param createtime the value for outside_activiti_apply.CreateTime
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column outside_activiti_apply.IsSuccess
     *
     * @return the value of outside_activiti_apply.IsSuccess
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    public Integer getIssuccess() {
        return issuccess;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column outside_activiti_apply.IsSuccess
     *
     * @param issuccess the value for outside_activiti_apply.IsSuccess
     *
     * @mbggenerated Wed Jun 20 13:33:34 CST 2018
     */
    public void setIssuccess(Integer issuccess) {
        this.issuccess = issuccess;
    }
}