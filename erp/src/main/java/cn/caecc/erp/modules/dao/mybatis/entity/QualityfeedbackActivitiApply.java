package cn.caecc.erp.modules.dao.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

public class QualityfeedbackActivitiApply implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column qualityfeedback_activiti_apply.Id
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column qualityfeedback_activiti_apply.ProcessInstanceId
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	private String processinstanceid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column qualityfeedback_activiti_apply.UId
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	private Integer uid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column qualityfeedback_activiti_apply.SId
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	private Integer sid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column qualityfeedback_activiti_apply.DId
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	private Integer did;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column qualityfeedback_activiti_apply.ProblemDescription1
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	private String problemdescription1;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column qualityfeedback_activiti_apply.ProblemDescription2
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	private String problemdescription2;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column qualityfeedback_activiti_apply.CreateUserId
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	private Integer createuserid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column qualityfeedback_activiti_apply.CreateTime
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	private Date createtime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column qualityfeedback_activiti_apply.IsSuccess
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	private Integer issuccess;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table qualityfeedback_activiti_apply
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column qualityfeedback_activiti_apply.Id
	 * @return  the value of qualityfeedback_activiti_apply.Id
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column qualityfeedback_activiti_apply.Id
	 * @param id  the value for qualityfeedback_activiti_apply.Id
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column qualityfeedback_activiti_apply.ProcessInstanceId
	 * @return  the value of qualityfeedback_activiti_apply.ProcessInstanceId
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	public String getProcessinstanceid() {
		return processinstanceid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column qualityfeedback_activiti_apply.ProcessInstanceId
	 * @param processinstanceid  the value for qualityfeedback_activiti_apply.ProcessInstanceId
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	public void setProcessinstanceid(String processinstanceid) {
		this.processinstanceid = processinstanceid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column qualityfeedback_activiti_apply.UId
	 * @return  the value of qualityfeedback_activiti_apply.UId
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	public Integer getUid() {
		return uid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column qualityfeedback_activiti_apply.UId
	 * @param uid  the value for qualityfeedback_activiti_apply.UId
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column qualityfeedback_activiti_apply.SId
	 * @return  the value of qualityfeedback_activiti_apply.SId
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	public Integer getSid() {
		return sid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column qualityfeedback_activiti_apply.SId
	 * @param sid  the value for qualityfeedback_activiti_apply.SId
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	public void setSid(Integer sid) {
		this.sid = sid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column qualityfeedback_activiti_apply.DId
	 * @return  the value of qualityfeedback_activiti_apply.DId
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	public Integer getDid() {
		return did;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column qualityfeedback_activiti_apply.DId
	 * @param did  the value for qualityfeedback_activiti_apply.DId
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	public void setDid(Integer did) {
		this.did = did;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column qualityfeedback_activiti_apply.ProblemDescription1
	 * @return  the value of qualityfeedback_activiti_apply.ProblemDescription1
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	public String getProblemdescription1() {
		return problemdescription1;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column qualityfeedback_activiti_apply.ProblemDescription1
	 * @param problemdescription1  the value for qualityfeedback_activiti_apply.ProblemDescription1
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	public void setProblemdescription1(String problemdescription1) {
		this.problemdescription1 = problemdescription1;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column qualityfeedback_activiti_apply.ProblemDescription2
	 * @return  the value of qualityfeedback_activiti_apply.ProblemDescription2
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	public String getProblemdescription2() {
		return problemdescription2;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column qualityfeedback_activiti_apply.ProblemDescription2
	 * @param problemdescription2  the value for qualityfeedback_activiti_apply.ProblemDescription2
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	public void setProblemdescription2(String problemdescription2) {
		this.problemdescription2 = problemdescription2;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column qualityfeedback_activiti_apply.CreateUserId
	 * @return  the value of qualityfeedback_activiti_apply.CreateUserId
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	public Integer getCreateuserid() {
		return createuserid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column qualityfeedback_activiti_apply.CreateUserId
	 * @param createuserid  the value for qualityfeedback_activiti_apply.CreateUserId
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	public void setCreateuserid(Integer createuserid) {
		this.createuserid = createuserid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column qualityfeedback_activiti_apply.CreateTime
	 * @return  the value of qualityfeedback_activiti_apply.CreateTime
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	public Date getCreatetime() {
		return createtime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column qualityfeedback_activiti_apply.CreateTime
	 * @param createtime  the value for qualityfeedback_activiti_apply.CreateTime
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column qualityfeedback_activiti_apply.IsSuccess
	 * @return  the value of qualityfeedback_activiti_apply.IsSuccess
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	public Integer getIssuccess() {
		return issuccess;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column qualityfeedback_activiti_apply.IsSuccess
	 * @param issuccess  the value for qualityfeedback_activiti_apply.IsSuccess
	 * @mbggenerated  Wed Jul 25 08:40:26 CST 2018
	 */
	public void setIssuccess(Integer issuccess) {
		this.issuccess = issuccess;
	}
}