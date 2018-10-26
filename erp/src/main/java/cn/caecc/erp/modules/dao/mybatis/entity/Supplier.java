package cn.caecc.erp.modules.dao.mybatis.entity;

import java.io.Serializable;

public class Supplier implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column supplier.Id
	 * @mbggenerated  Fri Aug 17 14:47:28 CST 2018
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column supplier.Name
	 * @mbggenerated  Fri Aug 17 14:47:28 CST 2018
	 */
	private String name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column supplier.ServiceContent
	 * @mbggenerated  Fri Aug 17 14:47:28 CST 2018
	 */
	private String servicecontent;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column supplier.Telephone
	 * @mbggenerated  Fri Aug 17 14:47:28 CST 2018
	 */
	private String telephone;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column supplier.Address
	 * @mbggenerated  Fri Aug 17 14:47:28 CST 2018
	 */
	private String address;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column supplier.IsQualified
	 * @mbggenerated  Fri Aug 17 14:47:28 CST 2018
	 */
	private Integer isqualified;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column supplier.Contacts
	 * @mbggenerated  Fri Aug 17 14:47:28 CST 2018
	 */
	private String contacts;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column supplier.BusinessLicenseUrl
	 * @mbggenerated  Fri Aug 17 14:47:28 CST 2018
	 */
	private String businesslicenseurl;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table supplier
	 * @mbggenerated  Fri Aug 17 14:47:28 CST 2018
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column supplier.Id
	 * @return  the value of supplier.Id
	 * @mbggenerated  Fri Aug 17 14:47:28 CST 2018
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column supplier.Id
	 * @param id  the value for supplier.Id
	 * @mbggenerated  Fri Aug 17 14:47:28 CST 2018
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column supplier.Name
	 * @return  the value of supplier.Name
	 * @mbggenerated  Fri Aug 17 14:47:28 CST 2018
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column supplier.Name
	 * @param name  the value for supplier.Name
	 * @mbggenerated  Fri Aug 17 14:47:28 CST 2018
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column supplier.ServiceContent
	 * @return  the value of supplier.ServiceContent
	 * @mbggenerated  Fri Aug 17 14:47:28 CST 2018
	 */
	public String getServicecontent() {
		return servicecontent;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column supplier.ServiceContent
	 * @param servicecontent  the value for supplier.ServiceContent
	 * @mbggenerated  Fri Aug 17 14:47:28 CST 2018
	 */
	public void setServicecontent(String servicecontent) {
		this.servicecontent = servicecontent;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column supplier.Telephone
	 * @return  the value of supplier.Telephone
	 * @mbggenerated  Fri Aug 17 14:47:28 CST 2018
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column supplier.Telephone
	 * @param telephone  the value for supplier.Telephone
	 * @mbggenerated  Fri Aug 17 14:47:28 CST 2018
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column supplier.Address
	 * @return  the value of supplier.Address
	 * @mbggenerated  Fri Aug 17 14:47:28 CST 2018
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column supplier.Address
	 * @param address  the value for supplier.Address
	 * @mbggenerated  Fri Aug 17 14:47:28 CST 2018
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column supplier.IsQualified
	 * @return  the value of supplier.IsQualified
	 * @mbggenerated  Fri Aug 17 14:47:28 CST 2018
	 */
	public Integer getIsqualified() {
		return isqualified;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column supplier.IsQualified
	 * @param isqualified  the value for supplier.IsQualified
	 * @mbggenerated  Fri Aug 17 14:47:28 CST 2018
	 */
	public void setIsqualified(Integer isqualified) {
		this.isqualified = isqualified;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column supplier.Contacts
	 * @return  the value of supplier.Contacts
	 * @mbggenerated  Fri Aug 17 14:47:28 CST 2018
	 */
	public String getContacts() {
		return contacts;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column supplier.Contacts
	 * @param contacts  the value for supplier.Contacts
	 * @mbggenerated  Fri Aug 17 14:47:28 CST 2018
	 */
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column supplier.BusinessLicenseUrl
	 * @return  the value of supplier.BusinessLicenseUrl
	 * @mbggenerated  Fri Aug 17 14:47:28 CST 2018
	 */
	public String getBusinesslicenseurl() {
		return businesslicenseurl;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column supplier.BusinessLicenseUrl
	 * @param businesslicenseurl  the value for supplier.BusinessLicenseUrl
	 * @mbggenerated  Fri Aug 17 14:47:28 CST 2018
	 */
	public void setBusinesslicenseurl(String businesslicenseurl) {
		this.businesslicenseurl = businesslicenseurl;
	}
}