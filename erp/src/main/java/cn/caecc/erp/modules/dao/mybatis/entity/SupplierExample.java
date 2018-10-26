package cn.caecc.erp.modules.dao.mybatis.entity;

import java.util.ArrayList;
import java.util.List;

public class SupplierExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table supplier
	 * @mbggenerated  Fri Aug 17 14:47:28 CST 2018
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table supplier
	 * @mbggenerated  Fri Aug 17 14:47:28 CST 2018
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table supplier
	 * @mbggenerated  Fri Aug 17 14:47:28 CST 2018
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table supplier
	 * @mbggenerated  Fri Aug 17 14:47:28 CST 2018
	 */
	public SupplierExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table supplier
	 * @mbggenerated  Fri Aug 17 14:47:28 CST 2018
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table supplier
	 * @mbggenerated  Fri Aug 17 14:47:28 CST 2018
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table supplier
	 * @mbggenerated  Fri Aug 17 14:47:28 CST 2018
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table supplier
	 * @mbggenerated  Fri Aug 17 14:47:28 CST 2018
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table supplier
	 * @mbggenerated  Fri Aug 17 14:47:28 CST 2018
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table supplier
	 * @mbggenerated  Fri Aug 17 14:47:28 CST 2018
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table supplier
	 * @mbggenerated  Fri Aug 17 14:47:28 CST 2018
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table supplier
	 * @mbggenerated  Fri Aug 17 14:47:28 CST 2018
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table supplier
	 * @mbggenerated  Fri Aug 17 14:47:28 CST 2018
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table supplier
	 * @mbggenerated  Fri Aug 17 14:47:28 CST 2018
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table supplier
	 * @mbggenerated  Fri Aug 17 14:47:28 CST 2018
	 */
	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
		}

		public boolean isValid() {
			return criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria() {
			return criteria;
		}

		public List<Criterion> getCriteria() {
			return criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1, Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andIdIsNull() {
			addCriterion("Id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("Id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("Id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("Id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("Id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("Id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("Id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("Id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("Id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("Id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("Id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("Id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andNameIsNull() {
			addCriterion("Name is null");
			return (Criteria) this;
		}

		public Criteria andNameIsNotNull() {
			addCriterion("Name is not null");
			return (Criteria) this;
		}

		public Criteria andNameEqualTo(String value) {
			addCriterion("Name =", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotEqualTo(String value) {
			addCriterion("Name <>", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThan(String value) {
			addCriterion("Name >", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThanOrEqualTo(String value) {
			addCriterion("Name >=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThan(String value) {
			addCriterion("Name <", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThanOrEqualTo(String value) {
			addCriterion("Name <=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLike(String value) {
			addCriterion("Name like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotLike(String value) {
			addCriterion("Name not like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameIn(List<String> values) {
			addCriterion("Name in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotIn(List<String> values) {
			addCriterion("Name not in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameBetween(String value1, String value2) {
			addCriterion("Name between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotBetween(String value1, String value2) {
			addCriterion("Name not between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andServicecontentIsNull() {
			addCriterion("ServiceContent is null");
			return (Criteria) this;
		}

		public Criteria andServicecontentIsNotNull() {
			addCriterion("ServiceContent is not null");
			return (Criteria) this;
		}

		public Criteria andServicecontentEqualTo(String value) {
			addCriterion("ServiceContent =", value, "servicecontent");
			return (Criteria) this;
		}

		public Criteria andServicecontentNotEqualTo(String value) {
			addCriterion("ServiceContent <>", value, "servicecontent");
			return (Criteria) this;
		}

		public Criteria andServicecontentGreaterThan(String value) {
			addCriterion("ServiceContent >", value, "servicecontent");
			return (Criteria) this;
		}

		public Criteria andServicecontentGreaterThanOrEqualTo(String value) {
			addCriterion("ServiceContent >=", value, "servicecontent");
			return (Criteria) this;
		}

		public Criteria andServicecontentLessThan(String value) {
			addCriterion("ServiceContent <", value, "servicecontent");
			return (Criteria) this;
		}

		public Criteria andServicecontentLessThanOrEqualTo(String value) {
			addCriterion("ServiceContent <=", value, "servicecontent");
			return (Criteria) this;
		}

		public Criteria andServicecontentLike(String value) {
			addCriterion("ServiceContent like", value, "servicecontent");
			return (Criteria) this;
		}

		public Criteria andServicecontentNotLike(String value) {
			addCriterion("ServiceContent not like", value, "servicecontent");
			return (Criteria) this;
		}

		public Criteria andServicecontentIn(List<String> values) {
			addCriterion("ServiceContent in", values, "servicecontent");
			return (Criteria) this;
		}

		public Criteria andServicecontentNotIn(List<String> values) {
			addCriterion("ServiceContent not in", values, "servicecontent");
			return (Criteria) this;
		}

		public Criteria andServicecontentBetween(String value1, String value2) {
			addCriterion("ServiceContent between", value1, value2, "servicecontent");
			return (Criteria) this;
		}

		public Criteria andServicecontentNotBetween(String value1, String value2) {
			addCriterion("ServiceContent not between", value1, value2, "servicecontent");
			return (Criteria) this;
		}

		public Criteria andTelephoneIsNull() {
			addCriterion("Telephone is null");
			return (Criteria) this;
		}

		public Criteria andTelephoneIsNotNull() {
			addCriterion("Telephone is not null");
			return (Criteria) this;
		}

		public Criteria andTelephoneEqualTo(String value) {
			addCriterion("Telephone =", value, "telephone");
			return (Criteria) this;
		}

		public Criteria andTelephoneNotEqualTo(String value) {
			addCriterion("Telephone <>", value, "telephone");
			return (Criteria) this;
		}

		public Criteria andTelephoneGreaterThan(String value) {
			addCriterion("Telephone >", value, "telephone");
			return (Criteria) this;
		}

		public Criteria andTelephoneGreaterThanOrEqualTo(String value) {
			addCriterion("Telephone >=", value, "telephone");
			return (Criteria) this;
		}

		public Criteria andTelephoneLessThan(String value) {
			addCriterion("Telephone <", value, "telephone");
			return (Criteria) this;
		}

		public Criteria andTelephoneLessThanOrEqualTo(String value) {
			addCriterion("Telephone <=", value, "telephone");
			return (Criteria) this;
		}

		public Criteria andTelephoneLike(String value) {
			addCriterion("Telephone like", value, "telephone");
			return (Criteria) this;
		}

		public Criteria andTelephoneNotLike(String value) {
			addCriterion("Telephone not like", value, "telephone");
			return (Criteria) this;
		}

		public Criteria andTelephoneIn(List<String> values) {
			addCriterion("Telephone in", values, "telephone");
			return (Criteria) this;
		}

		public Criteria andTelephoneNotIn(List<String> values) {
			addCriterion("Telephone not in", values, "telephone");
			return (Criteria) this;
		}

		public Criteria andTelephoneBetween(String value1, String value2) {
			addCriterion("Telephone between", value1, value2, "telephone");
			return (Criteria) this;
		}

		public Criteria andTelephoneNotBetween(String value1, String value2) {
			addCriterion("Telephone not between", value1, value2, "telephone");
			return (Criteria) this;
		}

		public Criteria andAddressIsNull() {
			addCriterion("Address is null");
			return (Criteria) this;
		}

		public Criteria andAddressIsNotNull() {
			addCriterion("Address is not null");
			return (Criteria) this;
		}

		public Criteria andAddressEqualTo(String value) {
			addCriterion("Address =", value, "address");
			return (Criteria) this;
		}

		public Criteria andAddressNotEqualTo(String value) {
			addCriterion("Address <>", value, "address");
			return (Criteria) this;
		}

		public Criteria andAddressGreaterThan(String value) {
			addCriterion("Address >", value, "address");
			return (Criteria) this;
		}

		public Criteria andAddressGreaterThanOrEqualTo(String value) {
			addCriterion("Address >=", value, "address");
			return (Criteria) this;
		}

		public Criteria andAddressLessThan(String value) {
			addCriterion("Address <", value, "address");
			return (Criteria) this;
		}

		public Criteria andAddressLessThanOrEqualTo(String value) {
			addCriterion("Address <=", value, "address");
			return (Criteria) this;
		}

		public Criteria andAddressLike(String value) {
			addCriterion("Address like", value, "address");
			return (Criteria) this;
		}

		public Criteria andAddressNotLike(String value) {
			addCriterion("Address not like", value, "address");
			return (Criteria) this;
		}

		public Criteria andAddressIn(List<String> values) {
			addCriterion("Address in", values, "address");
			return (Criteria) this;
		}

		public Criteria andAddressNotIn(List<String> values) {
			addCriterion("Address not in", values, "address");
			return (Criteria) this;
		}

		public Criteria andAddressBetween(String value1, String value2) {
			addCriterion("Address between", value1, value2, "address");
			return (Criteria) this;
		}

		public Criteria andAddressNotBetween(String value1, String value2) {
			addCriterion("Address not between", value1, value2, "address");
			return (Criteria) this;
		}

		public Criteria andIsqualifiedIsNull() {
			addCriterion("IsQualified is null");
			return (Criteria) this;
		}

		public Criteria andIsqualifiedIsNotNull() {
			addCriterion("IsQualified is not null");
			return (Criteria) this;
		}

		public Criteria andIsqualifiedEqualTo(Integer value) {
			addCriterion("IsQualified =", value, "isqualified");
			return (Criteria) this;
		}

		public Criteria andIsqualifiedNotEqualTo(Integer value) {
			addCriterion("IsQualified <>", value, "isqualified");
			return (Criteria) this;
		}

		public Criteria andIsqualifiedGreaterThan(Integer value) {
			addCriterion("IsQualified >", value, "isqualified");
			return (Criteria) this;
		}

		public Criteria andIsqualifiedGreaterThanOrEqualTo(Integer value) {
			addCriterion("IsQualified >=", value, "isqualified");
			return (Criteria) this;
		}

		public Criteria andIsqualifiedLessThan(Integer value) {
			addCriterion("IsQualified <", value, "isqualified");
			return (Criteria) this;
		}

		public Criteria andIsqualifiedLessThanOrEqualTo(Integer value) {
			addCriterion("IsQualified <=", value, "isqualified");
			return (Criteria) this;
		}

		public Criteria andIsqualifiedIn(List<Integer> values) {
			addCriterion("IsQualified in", values, "isqualified");
			return (Criteria) this;
		}

		public Criteria andIsqualifiedNotIn(List<Integer> values) {
			addCriterion("IsQualified not in", values, "isqualified");
			return (Criteria) this;
		}

		public Criteria andIsqualifiedBetween(Integer value1, Integer value2) {
			addCriterion("IsQualified between", value1, value2, "isqualified");
			return (Criteria) this;
		}

		public Criteria andIsqualifiedNotBetween(Integer value1, Integer value2) {
			addCriterion("IsQualified not between", value1, value2, "isqualified");
			return (Criteria) this;
		}

		public Criteria andContactsIsNull() {
			addCriterion("Contacts is null");
			return (Criteria) this;
		}

		public Criteria andContactsIsNotNull() {
			addCriterion("Contacts is not null");
			return (Criteria) this;
		}

		public Criteria andContactsEqualTo(String value) {
			addCriterion("Contacts =", value, "contacts");
			return (Criteria) this;
		}

		public Criteria andContactsNotEqualTo(String value) {
			addCriterion("Contacts <>", value, "contacts");
			return (Criteria) this;
		}

		public Criteria andContactsGreaterThan(String value) {
			addCriterion("Contacts >", value, "contacts");
			return (Criteria) this;
		}

		public Criteria andContactsGreaterThanOrEqualTo(String value) {
			addCriterion("Contacts >=", value, "contacts");
			return (Criteria) this;
		}

		public Criteria andContactsLessThan(String value) {
			addCriterion("Contacts <", value, "contacts");
			return (Criteria) this;
		}

		public Criteria andContactsLessThanOrEqualTo(String value) {
			addCriterion("Contacts <=", value, "contacts");
			return (Criteria) this;
		}

		public Criteria andContactsLike(String value) {
			addCriterion("Contacts like", value, "contacts");
			return (Criteria) this;
		}

		public Criteria andContactsNotLike(String value) {
			addCriterion("Contacts not like", value, "contacts");
			return (Criteria) this;
		}

		public Criteria andContactsIn(List<String> values) {
			addCriterion("Contacts in", values, "contacts");
			return (Criteria) this;
		}

		public Criteria andContactsNotIn(List<String> values) {
			addCriterion("Contacts not in", values, "contacts");
			return (Criteria) this;
		}

		public Criteria andContactsBetween(String value1, String value2) {
			addCriterion("Contacts between", value1, value2, "contacts");
			return (Criteria) this;
		}

		public Criteria andContactsNotBetween(String value1, String value2) {
			addCriterion("Contacts not between", value1, value2, "contacts");
			return (Criteria) this;
		}

		public Criteria andBusinesslicenseurlIsNull() {
			addCriterion("BusinessLicenseUrl is null");
			return (Criteria) this;
		}

		public Criteria andBusinesslicenseurlIsNotNull() {
			addCriterion("BusinessLicenseUrl is not null");
			return (Criteria) this;
		}

		public Criteria andBusinesslicenseurlEqualTo(String value) {
			addCriterion("BusinessLicenseUrl =", value, "businesslicenseurl");
			return (Criteria) this;
		}

		public Criteria andBusinesslicenseurlNotEqualTo(String value) {
			addCriterion("BusinessLicenseUrl <>", value, "businesslicenseurl");
			return (Criteria) this;
		}

		public Criteria andBusinesslicenseurlGreaterThan(String value) {
			addCriterion("BusinessLicenseUrl >", value, "businesslicenseurl");
			return (Criteria) this;
		}

		public Criteria andBusinesslicenseurlGreaterThanOrEqualTo(String value) {
			addCriterion("BusinessLicenseUrl >=", value, "businesslicenseurl");
			return (Criteria) this;
		}

		public Criteria andBusinesslicenseurlLessThan(String value) {
			addCriterion("BusinessLicenseUrl <", value, "businesslicenseurl");
			return (Criteria) this;
		}

		public Criteria andBusinesslicenseurlLessThanOrEqualTo(String value) {
			addCriterion("BusinessLicenseUrl <=", value, "businesslicenseurl");
			return (Criteria) this;
		}

		public Criteria andBusinesslicenseurlLike(String value) {
			addCriterion("BusinessLicenseUrl like", value, "businesslicenseurl");
			return (Criteria) this;
		}

		public Criteria andBusinesslicenseurlNotLike(String value) {
			addCriterion("BusinessLicenseUrl not like", value, "businesslicenseurl");
			return (Criteria) this;
		}

		public Criteria andBusinesslicenseurlIn(List<String> values) {
			addCriterion("BusinessLicenseUrl in", values, "businesslicenseurl");
			return (Criteria) this;
		}

		public Criteria andBusinesslicenseurlNotIn(List<String> values) {
			addCriterion("BusinessLicenseUrl not in", values, "businesslicenseurl");
			return (Criteria) this;
		}

		public Criteria andBusinesslicenseurlBetween(String value1, String value2) {
			addCriterion("BusinessLicenseUrl between", value1, value2, "businesslicenseurl");
			return (Criteria) this;
		}

		public Criteria andBusinesslicenseurlNotBetween(String value1, String value2) {
			addCriterion("BusinessLicenseUrl not between", value1, value2, "businesslicenseurl");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table supplier
	 * @mbggenerated  Fri Aug 17 14:47:28 CST 2018
	 */
	public static class Criterion {
		private String condition;
		private Object value;
		private Object secondValue;
		private boolean noValue;
		private boolean singleValue;
		private boolean betweenValue;
		private boolean listValue;
		private String typeHandler;

		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}

		public boolean isNoValue() {
			return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		}

		public String getTypeHandler() {
			return typeHandler;
		}

		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table supplier
     *
     * @mbggenerated do_not_delete_during_merge Tue Jun 12 15:49:58 CST 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}