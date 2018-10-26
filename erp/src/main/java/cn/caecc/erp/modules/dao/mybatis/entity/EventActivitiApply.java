package cn.caecc.erp.modules.dao.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

public class EventActivitiApply implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column event_activiti_apply.Id
     *
     * @mbggenerated Fri Jul 06 08:53:42 CST 2018
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column event_activiti_apply.ProcessInstanceId
     *
     * @mbggenerated Fri Jul 06 08:53:42 CST 2018
     */
    private String processinstanceid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column event_activiti_apply.UId
     *
     * @mbggenerated Fri Jul 06 08:53:42 CST 2018
     */
    private Integer uid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column event_activiti_apply.DId
     *
     * @mbggenerated Fri Jul 06 08:53:42 CST 2018
     */
    private Integer did;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column event_activiti_apply.EventDescription
     *
     * @mbggenerated Fri Jul 06 08:53:42 CST 2018
     */
    private String eventdescription;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column event_activiti_apply.HandlingOpinions
     *
     * @mbggenerated Fri Jul 06 08:53:42 CST 2018
     */
    private String handlingopinions;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column event_activiti_apply.CreateUserId
     *
     * @mbggenerated Fri Jul 06 08:53:42 CST 2018
     */
    private Integer createuserid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column event_activiti_apply.CreateTime
     *
     * @mbggenerated Fri Jul 06 08:53:42 CST 2018
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column event_activiti_apply.IsSuccess
     *
     * @mbggenerated Fri Jul 06 08:53:42 CST 2018
     */
    private Integer issuccess;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table event_activiti_apply
     *
     * @mbggenerated Fri Jul 06 08:53:42 CST 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column event_activiti_apply.Id
     *
     * @return the value of event_activiti_apply.Id
     *
     * @mbggenerated Fri Jul 06 08:53:42 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column event_activiti_apply.Id
     *
     * @param id the value for event_activiti_apply.Id
     *
     * @mbggenerated Fri Jul 06 08:53:42 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column event_activiti_apply.ProcessInstanceId
     *
     * @return the value of event_activiti_apply.ProcessInstanceId
     *
     * @mbggenerated Fri Jul 06 08:53:42 CST 2018
     */
    public String getProcessinstanceid() {
        return processinstanceid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column event_activiti_apply.ProcessInstanceId
     *
     * @param processinstanceid the value for event_activiti_apply.ProcessInstanceId
     *
     * @mbggenerated Fri Jul 06 08:53:42 CST 2018
     */
    public void setProcessinstanceid(String processinstanceid) {
        this.processinstanceid = processinstanceid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column event_activiti_apply.UId
     *
     * @return the value of event_activiti_apply.UId
     *
     * @mbggenerated Fri Jul 06 08:53:42 CST 2018
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column event_activiti_apply.UId
     *
     * @param uid the value for event_activiti_apply.UId
     *
     * @mbggenerated Fri Jul 06 08:53:42 CST 2018
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column event_activiti_apply.DId
     *
     * @return the value of event_activiti_apply.DId
     *
     * @mbggenerated Fri Jul 06 08:53:42 CST 2018
     */
    public Integer getDid() {
        return did;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column event_activiti_apply.DId
     *
     * @param did the value for event_activiti_apply.DId
     *
     * @mbggenerated Fri Jul 06 08:53:42 CST 2018
     */
    public void setDid(Integer did) {
        this.did = did;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column event_activiti_apply.EventDescription
     *
     * @return the value of event_activiti_apply.EventDescription
     *
     * @mbggenerated Fri Jul 06 08:53:42 CST 2018
     */
    public String getEventdescription() {
        return eventdescription;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column event_activiti_apply.EventDescription
     *
     * @param eventdescription the value for event_activiti_apply.EventDescription
     *
     * @mbggenerated Fri Jul 06 08:53:42 CST 2018
     */
    public void setEventdescription(String eventdescription) {
        this.eventdescription = eventdescription;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column event_activiti_apply.HandlingOpinions
     *
     * @return the value of event_activiti_apply.HandlingOpinions
     *
     * @mbggenerated Fri Jul 06 08:53:42 CST 2018
     */
    public String getHandlingopinions() {
        return handlingopinions;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column event_activiti_apply.HandlingOpinions
     *
     * @param handlingopinions the value for event_activiti_apply.HandlingOpinions
     *
     * @mbggenerated Fri Jul 06 08:53:42 CST 2018
     */
    public void setHandlingopinions(String handlingopinions) {
        this.handlingopinions = handlingopinions;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column event_activiti_apply.CreateUserId
     *
     * @return the value of event_activiti_apply.CreateUserId
     *
     * @mbggenerated Fri Jul 06 08:53:42 CST 2018
     */
    public Integer getCreateuserid() {
        return createuserid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column event_activiti_apply.CreateUserId
     *
     * @param createuserid the value for event_activiti_apply.CreateUserId
     *
     * @mbggenerated Fri Jul 06 08:53:42 CST 2018
     */
    public void setCreateuserid(Integer createuserid) {
        this.createuserid = createuserid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column event_activiti_apply.CreateTime
     *
     * @return the value of event_activiti_apply.CreateTime
     *
     * @mbggenerated Fri Jul 06 08:53:42 CST 2018
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column event_activiti_apply.CreateTime
     *
     * @param createtime the value for event_activiti_apply.CreateTime
     *
     * @mbggenerated Fri Jul 06 08:53:42 CST 2018
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column event_activiti_apply.IsSuccess
     *
     * @return the value of event_activiti_apply.IsSuccess
     *
     * @mbggenerated Fri Jul 06 08:53:42 CST 2018
     */
    public Integer getIssuccess() {
        return issuccess;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column event_activiti_apply.IsSuccess
     *
     * @param issuccess the value for event_activiti_apply.IsSuccess
     *
     * @mbggenerated Fri Jul 06 08:53:42 CST 2018
     */
    public void setIssuccess(Integer issuccess) {
        this.issuccess = issuccess;
    }
}