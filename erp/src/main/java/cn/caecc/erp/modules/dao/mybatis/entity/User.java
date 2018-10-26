package cn.caecc.erp.modules.dao.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "password" })
public class User implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.Id
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.LoginName
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	private String loginname;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.Password
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	private String password;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.Name
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	private String name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.DId
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	private Integer did;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.UnitTelephone
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	private String unittelephone;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.Telephone
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	private String telephone;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.Landline
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	private String landline;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.NetworkCard
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	private String networkcard;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.Fax
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	private String fax;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.Email
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	private String email;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.Sex
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	private Integer sex;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.IdNo
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	private String idno;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.JobNo
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	private String jobno;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.PositionId
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	private Integer positionid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.CreateTime
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	private Date createtime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.CreateUserId
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	private Integer createuserid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.UpdateTime
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	private Date updatetime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.UpdateUserId
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	private Integer updateuserid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.EmployTime
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	private Date employtime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.QuitTime
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	private Date quittime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.POId
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	private Integer poid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.HeadPortraitPath
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	private String headportraitpath;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column user.ElectronicSealPath
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	private String electronicsealpath;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table user
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.Id
	 * @return  the value of user.Id
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.Id
	 * @param id  the value for user.Id
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.LoginName
	 * @return  the value of user.LoginName
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public String getLoginname() {
		return loginname;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.LoginName
	 * @param loginname  the value for user.LoginName
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.Password
	 * @return  the value of user.Password
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.Password
	 * @param password  the value for user.Password
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.Name
	 * @return  the value of user.Name
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.Name
	 * @param name  the value for user.Name
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.DId
	 * @return  the value of user.DId
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public Integer getDid() {
		return did;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.DId
	 * @param did  the value for user.DId
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public void setDid(Integer did) {
		this.did = did;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.UnitTelephone
	 * @return  the value of user.UnitTelephone
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public String getUnittelephone() {
		return unittelephone;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.UnitTelephone
	 * @param unittelephone  the value for user.UnitTelephone
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public void setUnittelephone(String unittelephone) {
		this.unittelephone = unittelephone;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.Telephone
	 * @return  the value of user.Telephone
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.Telephone
	 * @param telephone  the value for user.Telephone
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.Landline
	 * @return  the value of user.Landline
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public String getLandline() {
		return landline;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.Landline
	 * @param landline  the value for user.Landline
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public void setLandline(String landline) {
		this.landline = landline;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.NetworkCard
	 * @return  the value of user.NetworkCard
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public String getNetworkcard() {
		return networkcard;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.NetworkCard
	 * @param networkcard  the value for user.NetworkCard
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public void setNetworkcard(String networkcard) {
		this.networkcard = networkcard;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.Fax
	 * @return  the value of user.Fax
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.Fax
	 * @param fax  the value for user.Fax
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.Email
	 * @return  the value of user.Email
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.Email
	 * @param email  the value for user.Email
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.Sex
	 * @return  the value of user.Sex
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public Integer getSex() {
		return sex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.Sex
	 * @param sex  the value for user.Sex
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.IdNo
	 * @return  the value of user.IdNo
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public String getIdno() {
		return idno;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.IdNo
	 * @param idno  the value for user.IdNo
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public void setIdno(String idno) {
		this.idno = idno;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.JobNo
	 * @return  the value of user.JobNo
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public String getJobno() {
		return jobno;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.JobNo
	 * @param jobno  the value for user.JobNo
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public void setJobno(String jobno) {
		this.jobno = jobno;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.PositionId
	 * @return  the value of user.PositionId
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public Integer getPositionid() {
		return positionid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.PositionId
	 * @param positionid  the value for user.PositionId
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public void setPositionid(Integer positionid) {
		this.positionid = positionid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.CreateTime
	 * @return  the value of user.CreateTime
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public Date getCreatetime() {
		return createtime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.CreateTime
	 * @param createtime  the value for user.CreateTime
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.CreateUserId
	 * @return  the value of user.CreateUserId
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public Integer getCreateuserid() {
		return createuserid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.CreateUserId
	 * @param createuserid  the value for user.CreateUserId
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public void setCreateuserid(Integer createuserid) {
		this.createuserid = createuserid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.UpdateTime
	 * @return  the value of user.UpdateTime
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public Date getUpdatetime() {
		return updatetime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.UpdateTime
	 * @param updatetime  the value for user.UpdateTime
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.UpdateUserId
	 * @return  the value of user.UpdateUserId
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public Integer getUpdateuserid() {
		return updateuserid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.UpdateUserId
	 * @param updateuserid  the value for user.UpdateUserId
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public void setUpdateuserid(Integer updateuserid) {
		this.updateuserid = updateuserid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.EmployTime
	 * @return  the value of user.EmployTime
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public Date getEmploytime() {
		return employtime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.EmployTime
	 * @param employtime  the value for user.EmployTime
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public void setEmploytime(Date employtime) {
		this.employtime = employtime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.QuitTime
	 * @return  the value of user.QuitTime
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public Date getQuittime() {
		return quittime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.QuitTime
	 * @param quittime  the value for user.QuitTime
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public void setQuittime(Date quittime) {
		this.quittime = quittime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.POId
	 * @return  the value of user.POId
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public Integer getPoid() {
		return poid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.POId
	 * @param poid  the value for user.POId
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public void setPoid(Integer poid) {
		this.poid = poid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.HeadPortraitPath
	 * @return  the value of user.HeadPortraitPath
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public String getHeadportraitpath() {
		return headportraitpath;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.HeadPortraitPath
	 * @param headportraitpath  the value for user.HeadPortraitPath
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public void setHeadportraitpath(String headportraitpath) {
		this.headportraitpath = headportraitpath;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user.ElectronicSealPath
	 * @return  the value of user.ElectronicSealPath
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public String getElectronicsealpath() {
		return electronicsealpath;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user.ElectronicSealPath
	 * @param electronicsealpath  the value for user.ElectronicSealPath
	 * @mbggenerated  Wed Jun 06 17:04:59 CST 2018
	 */
	public void setElectronicsealpath(String electronicsealpath) {
		this.electronicsealpath = electronicsealpath;
	}
}