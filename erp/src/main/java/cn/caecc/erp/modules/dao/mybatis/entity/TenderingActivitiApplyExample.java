package cn.caecc.erp.modules.dao.mybatis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TenderingActivitiApplyExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table tendering_activiti_apply
	 * @mbggenerated  Wed Aug 08 17:08:48 CST 2018
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table tendering_activiti_apply
	 * @mbggenerated  Wed Aug 08 17:08:48 CST 2018
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table tendering_activiti_apply
	 * @mbggenerated  Wed Aug 08 17:08:48 CST 2018
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tendering_activiti_apply
	 * @mbggenerated  Wed Aug 08 17:08:48 CST 2018
	 */
	public TenderingActivitiApplyExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tendering_activiti_apply
	 * @mbggenerated  Wed Aug 08 17:08:48 CST 2018
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tendering_activiti_apply
	 * @mbggenerated  Wed Aug 08 17:08:48 CST 2018
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tendering_activiti_apply
	 * @mbggenerated  Wed Aug 08 17:08:48 CST 2018
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tendering_activiti_apply
	 * @mbggenerated  Wed Aug 08 17:08:48 CST 2018
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tendering_activiti_apply
	 * @mbggenerated  Wed Aug 08 17:08:48 CST 2018
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tendering_activiti_apply
	 * @mbggenerated  Wed Aug 08 17:08:48 CST 2018
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tendering_activiti_apply
	 * @mbggenerated  Wed Aug 08 17:08:48 CST 2018
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tendering_activiti_apply
	 * @mbggenerated  Wed Aug 08 17:08:48 CST 2018
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tendering_activiti_apply
	 * @mbggenerated  Wed Aug 08 17:08:48 CST 2018
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tendering_activiti_apply
	 * @mbggenerated  Wed Aug 08 17:08:48 CST 2018
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table tendering_activiti_apply
	 * @mbggenerated  Wed Aug 08 17:08:48 CST 2018
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

		public Criteria andProcessinstanceidIsNull() {
			addCriterion("ProcessInstanceId is null");
			return (Criteria) this;
		}

		public Criteria andProcessinstanceidIsNotNull() {
			addCriterion("ProcessInstanceId is not null");
			return (Criteria) this;
		}

		public Criteria andProcessinstanceidEqualTo(String value) {
			addCriterion("ProcessInstanceId =", value, "processinstanceid");
			return (Criteria) this;
		}

		public Criteria andProcessinstanceidNotEqualTo(String value) {
			addCriterion("ProcessInstanceId <>", value, "processinstanceid");
			return (Criteria) this;
		}

		public Criteria andProcessinstanceidGreaterThan(String value) {
			addCriterion("ProcessInstanceId >", value, "processinstanceid");
			return (Criteria) this;
		}

		public Criteria andProcessinstanceidGreaterThanOrEqualTo(String value) {
			addCriterion("ProcessInstanceId >=", value, "processinstanceid");
			return (Criteria) this;
		}

		public Criteria andProcessinstanceidLessThan(String value) {
			addCriterion("ProcessInstanceId <", value, "processinstanceid");
			return (Criteria) this;
		}

		public Criteria andProcessinstanceidLessThanOrEqualTo(String value) {
			addCriterion("ProcessInstanceId <=", value, "processinstanceid");
			return (Criteria) this;
		}

		public Criteria andProcessinstanceidLike(String value) {
			addCriterion("ProcessInstanceId like", value, "processinstanceid");
			return (Criteria) this;
		}

		public Criteria andProcessinstanceidNotLike(String value) {
			addCriterion("ProcessInstanceId not like", value, "processinstanceid");
			return (Criteria) this;
		}

		public Criteria andProcessinstanceidIn(List<String> values) {
			addCriterion("ProcessInstanceId in", values, "processinstanceid");
			return (Criteria) this;
		}

		public Criteria andProcessinstanceidNotIn(List<String> values) {
			addCriterion("ProcessInstanceId not in", values, "processinstanceid");
			return (Criteria) this;
		}

		public Criteria andProcessinstanceidBetween(String value1, String value2) {
			addCriterion("ProcessInstanceId between", value1, value2, "processinstanceid");
			return (Criteria) this;
		}

		public Criteria andProcessinstanceidNotBetween(String value1, String value2) {
			addCriterion("ProcessInstanceId not between", value1, value2, "processinstanceid");
			return (Criteria) this;
		}

		public Criteria andUidIsNull() {
			addCriterion("UId is null");
			return (Criteria) this;
		}

		public Criteria andUidIsNotNull() {
			addCriterion("UId is not null");
			return (Criteria) this;
		}

		public Criteria andUidEqualTo(Integer value) {
			addCriterion("UId =", value, "uid");
			return (Criteria) this;
		}

		public Criteria andUidNotEqualTo(Integer value) {
			addCriterion("UId <>", value, "uid");
			return (Criteria) this;
		}

		public Criteria andUidGreaterThan(Integer value) {
			addCriterion("UId >", value, "uid");
			return (Criteria) this;
		}

		public Criteria andUidGreaterThanOrEqualTo(Integer value) {
			addCriterion("UId >=", value, "uid");
			return (Criteria) this;
		}

		public Criteria andUidLessThan(Integer value) {
			addCriterion("UId <", value, "uid");
			return (Criteria) this;
		}

		public Criteria andUidLessThanOrEqualTo(Integer value) {
			addCriterion("UId <=", value, "uid");
			return (Criteria) this;
		}

		public Criteria andUidIn(List<Integer> values) {
			addCriterion("UId in", values, "uid");
			return (Criteria) this;
		}

		public Criteria andUidNotIn(List<Integer> values) {
			addCriterion("UId not in", values, "uid");
			return (Criteria) this;
		}

		public Criteria andUidBetween(Integer value1, Integer value2) {
			addCriterion("UId between", value1, value2, "uid");
			return (Criteria) this;
		}

		public Criteria andUidNotBetween(Integer value1, Integer value2) {
			addCriterion("UId not between", value1, value2, "uid");
			return (Criteria) this;
		}

		public Criteria andDidIsNull() {
			addCriterion("DId is null");
			return (Criteria) this;
		}

		public Criteria andDidIsNotNull() {
			addCriterion("DId is not null");
			return (Criteria) this;
		}

		public Criteria andDidEqualTo(Integer value) {
			addCriterion("DId =", value, "did");
			return (Criteria) this;
		}

		public Criteria andDidNotEqualTo(Integer value) {
			addCriterion("DId <>", value, "did");
			return (Criteria) this;
		}

		public Criteria andDidGreaterThan(Integer value) {
			addCriterion("DId >", value, "did");
			return (Criteria) this;
		}

		public Criteria andDidGreaterThanOrEqualTo(Integer value) {
			addCriterion("DId >=", value, "did");
			return (Criteria) this;
		}

		public Criteria andDidLessThan(Integer value) {
			addCriterion("DId <", value, "did");
			return (Criteria) this;
		}

		public Criteria andDidLessThanOrEqualTo(Integer value) {
			addCriterion("DId <=", value, "did");
			return (Criteria) this;
		}

		public Criteria andDidIn(List<Integer> values) {
			addCriterion("DId in", values, "did");
			return (Criteria) this;
		}

		public Criteria andDidNotIn(List<Integer> values) {
			addCriterion("DId not in", values, "did");
			return (Criteria) this;
		}

		public Criteria andDidBetween(Integer value1, Integer value2) {
			addCriterion("DId between", value1, value2, "did");
			return (Criteria) this;
		}

		public Criteria andDidNotBetween(Integer value1, Integer value2) {
			addCriterion("DId not between", value1, value2, "did");
			return (Criteria) this;
		}

		public Criteria andPidIsNull() {
			addCriterion("PId is null");
			return (Criteria) this;
		}

		public Criteria andPidIsNotNull() {
			addCriterion("PId is not null");
			return (Criteria) this;
		}

		public Criteria andPidEqualTo(Integer value) {
			addCriterion("PId =", value, "pid");
			return (Criteria) this;
		}

		public Criteria andPidNotEqualTo(Integer value) {
			addCriterion("PId <>", value, "pid");
			return (Criteria) this;
		}

		public Criteria andPidGreaterThan(Integer value) {
			addCriterion("PId >", value, "pid");
			return (Criteria) this;
		}

		public Criteria andPidGreaterThanOrEqualTo(Integer value) {
			addCriterion("PId >=", value, "pid");
			return (Criteria) this;
		}

		public Criteria andPidLessThan(Integer value) {
			addCriterion("PId <", value, "pid");
			return (Criteria) this;
		}

		public Criteria andPidLessThanOrEqualTo(Integer value) {
			addCriterion("PId <=", value, "pid");
			return (Criteria) this;
		}

		public Criteria andPidIn(List<Integer> values) {
			addCriterion("PId in", values, "pid");
			return (Criteria) this;
		}

		public Criteria andPidNotIn(List<Integer> values) {
			addCriterion("PId not in", values, "pid");
			return (Criteria) this;
		}

		public Criteria andPidBetween(Integer value1, Integer value2) {
			addCriterion("PId between", value1, value2, "pid");
			return (Criteria) this;
		}

		public Criteria andPidNotBetween(Integer value1, Integer value2) {
			addCriterion("PId not between", value1, value2, "pid");
			return (Criteria) this;
		}

		public Criteria andBiddingplanIsNull() {
			addCriterion("BiddingPlan is null");
			return (Criteria) this;
		}

		public Criteria andBiddingplanIsNotNull() {
			addCriterion("BiddingPlan is not null");
			return (Criteria) this;
		}

		public Criteria andBiddingplanEqualTo(String value) {
			addCriterion("BiddingPlan =", value, "biddingplan");
			return (Criteria) this;
		}

		public Criteria andBiddingplanNotEqualTo(String value) {
			addCriterion("BiddingPlan <>", value, "biddingplan");
			return (Criteria) this;
		}

		public Criteria andBiddingplanGreaterThan(String value) {
			addCriterion("BiddingPlan >", value, "biddingplan");
			return (Criteria) this;
		}

		public Criteria andBiddingplanGreaterThanOrEqualTo(String value) {
			addCriterion("BiddingPlan >=", value, "biddingplan");
			return (Criteria) this;
		}

		public Criteria andBiddingplanLessThan(String value) {
			addCriterion("BiddingPlan <", value, "biddingplan");
			return (Criteria) this;
		}

		public Criteria andBiddingplanLessThanOrEqualTo(String value) {
			addCriterion("BiddingPlan <=", value, "biddingplan");
			return (Criteria) this;
		}

		public Criteria andBiddingplanLike(String value) {
			addCriterion("BiddingPlan like", value, "biddingplan");
			return (Criteria) this;
		}

		public Criteria andBiddingplanNotLike(String value) {
			addCriterion("BiddingPlan not like", value, "biddingplan");
			return (Criteria) this;
		}

		public Criteria andBiddingplanIn(List<String> values) {
			addCriterion("BiddingPlan in", values, "biddingplan");
			return (Criteria) this;
		}

		public Criteria andBiddingplanNotIn(List<String> values) {
			addCriterion("BiddingPlan not in", values, "biddingplan");
			return (Criteria) this;
		}

		public Criteria andBiddingplanBetween(String value1, String value2) {
			addCriterion("BiddingPlan between", value1, value2, "biddingplan");
			return (Criteria) this;
		}

		public Criteria andBiddingplanNotBetween(String value1, String value2) {
			addCriterion("BiddingPlan not between", value1, value2, "biddingplan");
			return (Criteria) this;
		}

		public Criteria andMarketresearchIsNull() {
			addCriterion("MarketResearch is null");
			return (Criteria) this;
		}

		public Criteria andMarketresearchIsNotNull() {
			addCriterion("MarketResearch is not null");
			return (Criteria) this;
		}

		public Criteria andMarketresearchEqualTo(String value) {
			addCriterion("MarketResearch =", value, "marketresearch");
			return (Criteria) this;
		}

		public Criteria andMarketresearchNotEqualTo(String value) {
			addCriterion("MarketResearch <>", value, "marketresearch");
			return (Criteria) this;
		}

		public Criteria andMarketresearchGreaterThan(String value) {
			addCriterion("MarketResearch >", value, "marketresearch");
			return (Criteria) this;
		}

		public Criteria andMarketresearchGreaterThanOrEqualTo(String value) {
			addCriterion("MarketResearch >=", value, "marketresearch");
			return (Criteria) this;
		}

		public Criteria andMarketresearchLessThan(String value) {
			addCriterion("MarketResearch <", value, "marketresearch");
			return (Criteria) this;
		}

		public Criteria andMarketresearchLessThanOrEqualTo(String value) {
			addCriterion("MarketResearch <=", value, "marketresearch");
			return (Criteria) this;
		}

		public Criteria andMarketresearchLike(String value) {
			addCriterion("MarketResearch like", value, "marketresearch");
			return (Criteria) this;
		}

		public Criteria andMarketresearchNotLike(String value) {
			addCriterion("MarketResearch not like", value, "marketresearch");
			return (Criteria) this;
		}

		public Criteria andMarketresearchIn(List<String> values) {
			addCriterion("MarketResearch in", values, "marketresearch");
			return (Criteria) this;
		}

		public Criteria andMarketresearchNotIn(List<String> values) {
			addCriterion("MarketResearch not in", values, "marketresearch");
			return (Criteria) this;
		}

		public Criteria andMarketresearchBetween(String value1, String value2) {
			addCriterion("MarketResearch between", value1, value2, "marketresearch");
			return (Criteria) this;
		}

		public Criteria andMarketresearchNotBetween(String value1, String value2) {
			addCriterion("MarketResearch not between", value1, value2, "marketresearch");
			return (Criteria) this;
		}

		public Criteria andCreateuseridIsNull() {
			addCriterion("CreateUserId is null");
			return (Criteria) this;
		}

		public Criteria andCreateuseridIsNotNull() {
			addCriterion("CreateUserId is not null");
			return (Criteria) this;
		}

		public Criteria andCreateuseridEqualTo(Integer value) {
			addCriterion("CreateUserId =", value, "createuserid");
			return (Criteria) this;
		}

		public Criteria andCreateuseridNotEqualTo(Integer value) {
			addCriterion("CreateUserId <>", value, "createuserid");
			return (Criteria) this;
		}

		public Criteria andCreateuseridGreaterThan(Integer value) {
			addCriterion("CreateUserId >", value, "createuserid");
			return (Criteria) this;
		}

		public Criteria andCreateuseridGreaterThanOrEqualTo(Integer value) {
			addCriterion("CreateUserId >=", value, "createuserid");
			return (Criteria) this;
		}

		public Criteria andCreateuseridLessThan(Integer value) {
			addCriterion("CreateUserId <", value, "createuserid");
			return (Criteria) this;
		}

		public Criteria andCreateuseridLessThanOrEqualTo(Integer value) {
			addCriterion("CreateUserId <=", value, "createuserid");
			return (Criteria) this;
		}

		public Criteria andCreateuseridIn(List<Integer> values) {
			addCriterion("CreateUserId in", values, "createuserid");
			return (Criteria) this;
		}

		public Criteria andCreateuseridNotIn(List<Integer> values) {
			addCriterion("CreateUserId not in", values, "createuserid");
			return (Criteria) this;
		}

		public Criteria andCreateuseridBetween(Integer value1, Integer value2) {
			addCriterion("CreateUserId between", value1, value2, "createuserid");
			return (Criteria) this;
		}

		public Criteria andCreateuseridNotBetween(Integer value1, Integer value2) {
			addCriterion("CreateUserId not between", value1, value2, "createuserid");
			return (Criteria) this;
		}

		public Criteria andCreatetimeIsNull() {
			addCriterion("CreateTime is null");
			return (Criteria) this;
		}

		public Criteria andCreatetimeIsNotNull() {
			addCriterion("CreateTime is not null");
			return (Criteria) this;
		}

		public Criteria andCreatetimeEqualTo(Date value) {
			addCriterion("CreateTime =", value, "createtime");
			return (Criteria) this;
		}

		public Criteria andCreatetimeNotEqualTo(Date value) {
			addCriterion("CreateTime <>", value, "createtime");
			return (Criteria) this;
		}

		public Criteria andCreatetimeGreaterThan(Date value) {
			addCriterion("CreateTime >", value, "createtime");
			return (Criteria) this;
		}

		public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
			addCriterion("CreateTime >=", value, "createtime");
			return (Criteria) this;
		}

		public Criteria andCreatetimeLessThan(Date value) {
			addCriterion("CreateTime <", value, "createtime");
			return (Criteria) this;
		}

		public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
			addCriterion("CreateTime <=", value, "createtime");
			return (Criteria) this;
		}

		public Criteria andCreatetimeIn(List<Date> values) {
			addCriterion("CreateTime in", values, "createtime");
			return (Criteria) this;
		}

		public Criteria andCreatetimeNotIn(List<Date> values) {
			addCriterion("CreateTime not in", values, "createtime");
			return (Criteria) this;
		}

		public Criteria andCreatetimeBetween(Date value1, Date value2) {
			addCriterion("CreateTime between", value1, value2, "createtime");
			return (Criteria) this;
		}

		public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
			addCriterion("CreateTime not between", value1, value2, "createtime");
			return (Criteria) this;
		}

		public Criteria andIssuccessIsNull() {
			addCriterion("IsSuccess is null");
			return (Criteria) this;
		}

		public Criteria andIssuccessIsNotNull() {
			addCriterion("IsSuccess is not null");
			return (Criteria) this;
		}

		public Criteria andIssuccessEqualTo(Integer value) {
			addCriterion("IsSuccess =", value, "issuccess");
			return (Criteria) this;
		}

		public Criteria andIssuccessNotEqualTo(Integer value) {
			addCriterion("IsSuccess <>", value, "issuccess");
			return (Criteria) this;
		}

		public Criteria andIssuccessGreaterThan(Integer value) {
			addCriterion("IsSuccess >", value, "issuccess");
			return (Criteria) this;
		}

		public Criteria andIssuccessGreaterThanOrEqualTo(Integer value) {
			addCriterion("IsSuccess >=", value, "issuccess");
			return (Criteria) this;
		}

		public Criteria andIssuccessLessThan(Integer value) {
			addCriterion("IsSuccess <", value, "issuccess");
			return (Criteria) this;
		}

		public Criteria andIssuccessLessThanOrEqualTo(Integer value) {
			addCriterion("IsSuccess <=", value, "issuccess");
			return (Criteria) this;
		}

		public Criteria andIssuccessIn(List<Integer> values) {
			addCriterion("IsSuccess in", values, "issuccess");
			return (Criteria) this;
		}

		public Criteria andIssuccessNotIn(List<Integer> values) {
			addCriterion("IsSuccess not in", values, "issuccess");
			return (Criteria) this;
		}

		public Criteria andIssuccessBetween(Integer value1, Integer value2) {
			addCriterion("IsSuccess between", value1, value2, "issuccess");
			return (Criteria) this;
		}

		public Criteria andIssuccessNotBetween(Integer value1, Integer value2) {
			addCriterion("IsSuccess not between", value1, value2, "issuccess");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table tendering_activiti_apply
	 * @mbggenerated  Wed Aug 08 17:08:48 CST 2018
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
     * This class corresponds to the database table tendering_activiti_apply
     *
     * @mbggenerated do_not_delete_during_merge Wed Jul 11 09:32:29 CST 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}