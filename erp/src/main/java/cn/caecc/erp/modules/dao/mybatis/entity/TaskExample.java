package cn.caecc.erp.modules.dao.mybatis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table task
     *
     * @mbggenerated Wed Jun 27 08:57:03 CST 2018
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table task
     *
     * @mbggenerated Wed Jun 27 08:57:03 CST 2018
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table task
     *
     * @mbggenerated Wed Jun 27 08:57:03 CST 2018
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task
     *
     * @mbggenerated Wed Jun 27 08:57:03 CST 2018
     */
    public TaskExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task
     *
     * @mbggenerated Wed Jun 27 08:57:03 CST 2018
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task
     *
     * @mbggenerated Wed Jun 27 08:57:03 CST 2018
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task
     *
     * @mbggenerated Wed Jun 27 08:57:03 CST 2018
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task
     *
     * @mbggenerated Wed Jun 27 08:57:03 CST 2018
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task
     *
     * @mbggenerated Wed Jun 27 08:57:03 CST 2018
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task
     *
     * @mbggenerated Wed Jun 27 08:57:03 CST 2018
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task
     *
     * @mbggenerated Wed Jun 27 08:57:03 CST 2018
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task
     *
     * @mbggenerated Wed Jun 27 08:57:03 CST 2018
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task
     *
     * @mbggenerated Wed Jun 27 08:57:03 CST 2018
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table task
     *
     * @mbggenerated Wed Jun 27 08:57:03 CST 2018
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table task
     *
     * @mbggenerated Wed Jun 27 08:57:03 CST 2018
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

        public Criteria andAgencyaffairsIsNull() {
            addCriterion("AgencyAffairs is null");
            return (Criteria) this;
        }

        public Criteria andAgencyaffairsIsNotNull() {
            addCriterion("AgencyAffairs is not null");
            return (Criteria) this;
        }

        public Criteria andAgencyaffairsEqualTo(String value) {
            addCriterion("AgencyAffairs =", value, "agencyaffairs");
            return (Criteria) this;
        }

        public Criteria andAgencyaffairsNotEqualTo(String value) {
            addCriterion("AgencyAffairs <>", value, "agencyaffairs");
            return (Criteria) this;
        }

        public Criteria andAgencyaffairsGreaterThan(String value) {
            addCriterion("AgencyAffairs >", value, "agencyaffairs");
            return (Criteria) this;
        }

        public Criteria andAgencyaffairsGreaterThanOrEqualTo(String value) {
            addCriterion("AgencyAffairs >=", value, "agencyaffairs");
            return (Criteria) this;
        }

        public Criteria andAgencyaffairsLessThan(String value) {
            addCriterion("AgencyAffairs <", value, "agencyaffairs");
            return (Criteria) this;
        }

        public Criteria andAgencyaffairsLessThanOrEqualTo(String value) {
            addCriterion("AgencyAffairs <=", value, "agencyaffairs");
            return (Criteria) this;
        }

        public Criteria andAgencyaffairsLike(String value) {
            addCriterion("AgencyAffairs like", value, "agencyaffairs");
            return (Criteria) this;
        }

        public Criteria andAgencyaffairsNotLike(String value) {
            addCriterion("AgencyAffairs not like", value, "agencyaffairs");
            return (Criteria) this;
        }

        public Criteria andAgencyaffairsIn(List<String> values) {
            addCriterion("AgencyAffairs in", values, "agencyaffairs");
            return (Criteria) this;
        }

        public Criteria andAgencyaffairsNotIn(List<String> values) {
            addCriterion("AgencyAffairs not in", values, "agencyaffairs");
            return (Criteria) this;
        }

        public Criteria andAgencyaffairsBetween(String value1, String value2) {
            addCriterion("AgencyAffairs between", value1, value2, "agencyaffairs");
            return (Criteria) this;
        }

        public Criteria andAgencyaffairsNotBetween(String value1, String value2) {
            addCriterion("AgencyAffairs not between", value1, value2, "agencyaffairs");
            return (Criteria) this;
        }

        public Criteria andAgencystarttimeIsNull() {
            addCriterion("AgencyStartTime is null");
            return (Criteria) this;
        }

        public Criteria andAgencystarttimeIsNotNull() {
            addCriterion("AgencyStartTime is not null");
            return (Criteria) this;
        }

        public Criteria andAgencystarttimeEqualTo(Date value) {
            addCriterion("AgencyStartTime =", value, "agencystarttime");
            return (Criteria) this;
        }

        public Criteria andAgencystarttimeNotEqualTo(Date value) {
            addCriterion("AgencyStartTime <>", value, "agencystarttime");
            return (Criteria) this;
        }

        public Criteria andAgencystarttimeGreaterThan(Date value) {
            addCriterion("AgencyStartTime >", value, "agencystarttime");
            return (Criteria) this;
        }

        public Criteria andAgencystarttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("AgencyStartTime >=", value, "agencystarttime");
            return (Criteria) this;
        }

        public Criteria andAgencystarttimeLessThan(Date value) {
            addCriterion("AgencyStartTime <", value, "agencystarttime");
            return (Criteria) this;
        }

        public Criteria andAgencystarttimeLessThanOrEqualTo(Date value) {
            addCriterion("AgencyStartTime <=", value, "agencystarttime");
            return (Criteria) this;
        }

        public Criteria andAgencystarttimeIn(List<Date> values) {
            addCriterion("AgencyStartTime in", values, "agencystarttime");
            return (Criteria) this;
        }

        public Criteria andAgencystarttimeNotIn(List<Date> values) {
            addCriterion("AgencyStartTime not in", values, "agencystarttime");
            return (Criteria) this;
        }

        public Criteria andAgencystarttimeBetween(Date value1, Date value2) {
            addCriterion("AgencyStartTime between", value1, value2, "agencystarttime");
            return (Criteria) this;
        }

        public Criteria andAgencystarttimeNotBetween(Date value1, Date value2) {
            addCriterion("AgencyStartTime not between", value1, value2, "agencystarttime");
            return (Criteria) this;
        }

        public Criteria andAgencyendtimeIsNull() {
            addCriterion("AgencyEndTime is null");
            return (Criteria) this;
        }

        public Criteria andAgencyendtimeIsNotNull() {
            addCriterion("AgencyEndTime is not null");
            return (Criteria) this;
        }

        public Criteria andAgencyendtimeEqualTo(Date value) {
            addCriterion("AgencyEndTime =", value, "agencyendtime");
            return (Criteria) this;
        }

        public Criteria andAgencyendtimeNotEqualTo(Date value) {
            addCriterion("AgencyEndTime <>", value, "agencyendtime");
            return (Criteria) this;
        }

        public Criteria andAgencyendtimeGreaterThan(Date value) {
            addCriterion("AgencyEndTime >", value, "agencyendtime");
            return (Criteria) this;
        }

        public Criteria andAgencyendtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("AgencyEndTime >=", value, "agencyendtime");
            return (Criteria) this;
        }

        public Criteria andAgencyendtimeLessThan(Date value) {
            addCriterion("AgencyEndTime <", value, "agencyendtime");
            return (Criteria) this;
        }

        public Criteria andAgencyendtimeLessThanOrEqualTo(Date value) {
            addCriterion("AgencyEndTime <=", value, "agencyendtime");
            return (Criteria) this;
        }

        public Criteria andAgencyendtimeIn(List<Date> values) {
            addCriterion("AgencyEndTime in", values, "agencyendtime");
            return (Criteria) this;
        }

        public Criteria andAgencyendtimeNotIn(List<Date> values) {
            addCriterion("AgencyEndTime not in", values, "agencyendtime");
            return (Criteria) this;
        }

        public Criteria andAgencyendtimeBetween(Date value1, Date value2) {
            addCriterion("AgencyEndTime between", value1, value2, "agencyendtime");
            return (Criteria) this;
        }

        public Criteria andAgencyendtimeNotBetween(Date value1, Date value2) {
            addCriterion("AgencyEndTime not between", value1, value2, "agencyendtime");
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

        public Criteria andProgressIsNull() {
            addCriterion("Progress is null");
            return (Criteria) this;
        }

        public Criteria andProgressIsNotNull() {
            addCriterion("Progress is not null");
            return (Criteria) this;
        }

        public Criteria andProgressEqualTo(String value) {
            addCriterion("Progress =", value, "progress");
            return (Criteria) this;
        }

        public Criteria andProgressNotEqualTo(String value) {
            addCriterion("Progress <>", value, "progress");
            return (Criteria) this;
        }

        public Criteria andProgressGreaterThan(String value) {
            addCriterion("Progress >", value, "progress");
            return (Criteria) this;
        }

        public Criteria andProgressGreaterThanOrEqualTo(String value) {
            addCriterion("Progress >=", value, "progress");
            return (Criteria) this;
        }

        public Criteria andProgressLessThan(String value) {
            addCriterion("Progress <", value, "progress");
            return (Criteria) this;
        }

        public Criteria andProgressLessThanOrEqualTo(String value) {
            addCriterion("Progress <=", value, "progress");
            return (Criteria) this;
        }

        public Criteria andProgressLike(String value) {
            addCriterion("Progress like", value, "progress");
            return (Criteria) this;
        }

        public Criteria andProgressNotLike(String value) {
            addCriterion("Progress not like", value, "progress");
            return (Criteria) this;
        }

        public Criteria andProgressIn(List<String> values) {
            addCriterion("Progress in", values, "progress");
            return (Criteria) this;
        }

        public Criteria andProgressNotIn(List<String> values) {
            addCriterion("Progress not in", values, "progress");
            return (Criteria) this;
        }

        public Criteria andProgressBetween(String value1, String value2) {
            addCriterion("Progress between", value1, value2, "progress");
            return (Criteria) this;
        }

        public Criteria andProgressNotBetween(String value1, String value2) {
            addCriterion("Progress not between", value1, value2, "progress");
            return (Criteria) this;
        }

        public Criteria andIsfinishedIsNull() {
            addCriterion("IsFinished is null");
            return (Criteria) this;
        }

        public Criteria andIsfinishedIsNotNull() {
            addCriterion("IsFinished is not null");
            return (Criteria) this;
        }

        public Criteria andIsfinishedEqualTo(Integer value) {
            addCriterion("IsFinished =", value, "isfinished");
            return (Criteria) this;
        }

        public Criteria andIsfinishedNotEqualTo(Integer value) {
            addCriterion("IsFinished <>", value, "isfinished");
            return (Criteria) this;
        }

        public Criteria andIsfinishedGreaterThan(Integer value) {
            addCriterion("IsFinished >", value, "isfinished");
            return (Criteria) this;
        }

        public Criteria andIsfinishedGreaterThanOrEqualTo(Integer value) {
            addCriterion("IsFinished >=", value, "isfinished");
            return (Criteria) this;
        }

        public Criteria andIsfinishedLessThan(Integer value) {
            addCriterion("IsFinished <", value, "isfinished");
            return (Criteria) this;
        }

        public Criteria andIsfinishedLessThanOrEqualTo(Integer value) {
            addCriterion("IsFinished <=", value, "isfinished");
            return (Criteria) this;
        }

        public Criteria andIsfinishedIn(List<Integer> values) {
            addCriterion("IsFinished in", values, "isfinished");
            return (Criteria) this;
        }

        public Criteria andIsfinishedNotIn(List<Integer> values) {
            addCriterion("IsFinished not in", values, "isfinished");
            return (Criteria) this;
        }

        public Criteria andIsfinishedBetween(Integer value1, Integer value2) {
            addCriterion("IsFinished between", value1, value2, "isfinished");
            return (Criteria) this;
        }

        public Criteria andIsfinishedNotBetween(Integer value1, Integer value2) {
            addCriterion("IsFinished not between", value1, value2, "isfinished");
            return (Criteria) this;
        }

        public Criteria andFinishedtimeIsNull() {
            addCriterion("FinishedTime is null");
            return (Criteria) this;
        }

        public Criteria andFinishedtimeIsNotNull() {
            addCriterion("FinishedTime is not null");
            return (Criteria) this;
        }

        public Criteria andFinishedtimeEqualTo(Date value) {
            addCriterion("FinishedTime =", value, "finishedtime");
            return (Criteria) this;
        }

        public Criteria andFinishedtimeNotEqualTo(Date value) {
            addCriterion("FinishedTime <>", value, "finishedtime");
            return (Criteria) this;
        }

        public Criteria andFinishedtimeGreaterThan(Date value) {
            addCriterion("FinishedTime >", value, "finishedtime");
            return (Criteria) this;
        }

        public Criteria andFinishedtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("FinishedTime >=", value, "finishedtime");
            return (Criteria) this;
        }

        public Criteria andFinishedtimeLessThan(Date value) {
            addCriterion("FinishedTime <", value, "finishedtime");
            return (Criteria) this;
        }

        public Criteria andFinishedtimeLessThanOrEqualTo(Date value) {
            addCriterion("FinishedTime <=", value, "finishedtime");
            return (Criteria) this;
        }

        public Criteria andFinishedtimeIn(List<Date> values) {
            addCriterion("FinishedTime in", values, "finishedtime");
            return (Criteria) this;
        }

        public Criteria andFinishedtimeNotIn(List<Date> values) {
            addCriterion("FinishedTime not in", values, "finishedtime");
            return (Criteria) this;
        }

        public Criteria andFinishedtimeBetween(Date value1, Date value2) {
            addCriterion("FinishedTime between", value1, value2, "finishedtime");
            return (Criteria) this;
        }

        public Criteria andFinishedtimeNotBetween(Date value1, Date value2) {
            addCriterion("FinishedTime not between", value1, value2, "finishedtime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table task
     *
     * @mbggenerated do_not_delete_during_merge Wed Jun 27 08:57:03 CST 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table task
     *
     * @mbggenerated Wed Jun 27 08:57:03 CST 2018
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
}