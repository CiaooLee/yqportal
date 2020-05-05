package com.yaqiu.entity;

import java.util.ArrayList;
import java.util.List;

public class CommentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CommentExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMainContentIsNull() {
            addCriterion("main_content is null");
            return (Criteria) this;
        }

        public Criteria andMainContentIsNotNull() {
            addCriterion("main_content is not null");
            return (Criteria) this;
        }

        public Criteria andMainContentEqualTo(String value) {
            addCriterion("main_content =", value, "mainContent");
            return (Criteria) this;
        }

        public Criteria andMainContentNotEqualTo(String value) {
            addCriterion("main_content <>", value, "mainContent");
            return (Criteria) this;
        }

        public Criteria andMainContentGreaterThan(String value) {
            addCriterion("main_content >", value, "mainContent");
            return (Criteria) this;
        }

        public Criteria andMainContentGreaterThanOrEqualTo(String value) {
            addCriterion("main_content >=", value, "mainContent");
            return (Criteria) this;
        }

        public Criteria andMainContentLessThan(String value) {
            addCriterion("main_content <", value, "mainContent");
            return (Criteria) this;
        }

        public Criteria andMainContentLessThanOrEqualTo(String value) {
            addCriterion("main_content <=", value, "mainContent");
            return (Criteria) this;
        }

        public Criteria andMainContentLike(String value) {
            addCriterion("main_content like", value, "mainContent");
            return (Criteria) this;
        }

        public Criteria andMainContentNotLike(String value) {
            addCriterion("main_content not like", value, "mainContent");
            return (Criteria) this;
        }

        public Criteria andMainContentIn(List<String> values) {
            addCriterion("main_content in", values, "mainContent");
            return (Criteria) this;
        }

        public Criteria andMainContentNotIn(List<String> values) {
            addCriterion("main_content not in", values, "mainContent");
            return (Criteria) this;
        }

        public Criteria andMainContentBetween(String value1, String value2) {
            addCriterion("main_content between", value1, value2, "mainContent");
            return (Criteria) this;
        }

        public Criteria andMainContentNotBetween(String value1, String value2) {
            addCriterion("main_content not between", value1, value2, "mainContent");
            return (Criteria) this;
        }

        public Criteria andContentIdIsNull() {
            addCriterion("content_id is null");
            return (Criteria) this;
        }

        public Criteria andContentIdIsNotNull() {
            addCriterion("content_id is not null");
            return (Criteria) this;
        }

        public Criteria andContentIdEqualTo(String value) {
            addCriterion("content_id =", value, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdNotEqualTo(String value) {
            addCriterion("content_id <>", value, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdGreaterThan(String value) {
            addCriterion("content_id >", value, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdGreaterThanOrEqualTo(String value) {
            addCriterion("content_id >=", value, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdLessThan(String value) {
            addCriterion("content_id <", value, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdLessThanOrEqualTo(String value) {
            addCriterion("content_id <=", value, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdLike(String value) {
            addCriterion("content_id like", value, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdNotLike(String value) {
            addCriterion("content_id not like", value, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdIn(List<String> values) {
            addCriterion("content_id in", values, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdNotIn(List<String> values) {
            addCriterion("content_id not in", values, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdBetween(String value1, String value2) {
            addCriterion("content_id between", value1, value2, "contentId");
            return (Criteria) this;
        }

        public Criteria andContentIdNotBetween(String value1, String value2) {
            addCriterion("content_id not between", value1, value2, "contentId");
            return (Criteria) this;
        }

        public Criteria andCreatorNicknameIsNull() {
            addCriterion("creator_nickname is null");
            return (Criteria) this;
        }

        public Criteria andCreatorNicknameIsNotNull() {
            addCriterion("creator_nickname is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorNicknameEqualTo(String value) {
            addCriterion("creator_nickname =", value, "creatorNickname");
            return (Criteria) this;
        }

        public Criteria andCreatorNicknameNotEqualTo(String value) {
            addCriterion("creator_nickname <>", value, "creatorNickname");
            return (Criteria) this;
        }

        public Criteria andCreatorNicknameGreaterThan(String value) {
            addCriterion("creator_nickname >", value, "creatorNickname");
            return (Criteria) this;
        }

        public Criteria andCreatorNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("creator_nickname >=", value, "creatorNickname");
            return (Criteria) this;
        }

        public Criteria andCreatorNicknameLessThan(String value) {
            addCriterion("creator_nickname <", value, "creatorNickname");
            return (Criteria) this;
        }

        public Criteria andCreatorNicknameLessThanOrEqualTo(String value) {
            addCriterion("creator_nickname <=", value, "creatorNickname");
            return (Criteria) this;
        }

        public Criteria andCreatorNicknameLike(String value) {
            addCriterion("creator_nickname like", value, "creatorNickname");
            return (Criteria) this;
        }

        public Criteria andCreatorNicknameNotLike(String value) {
            addCriterion("creator_nickname not like", value, "creatorNickname");
            return (Criteria) this;
        }

        public Criteria andCreatorNicknameIn(List<String> values) {
            addCriterion("creator_nickname in", values, "creatorNickname");
            return (Criteria) this;
        }

        public Criteria andCreatorNicknameNotIn(List<String> values) {
            addCriterion("creator_nickname not in", values, "creatorNickname");
            return (Criteria) this;
        }

        public Criteria andCreatorNicknameBetween(String value1, String value2) {
            addCriterion("creator_nickname between", value1, value2, "creatorNickname");
            return (Criteria) this;
        }

        public Criteria andCreatorNicknameNotBetween(String value1, String value2) {
            addCriterion("creator_nickname not between", value1, value2, "creatorNickname");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(String value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(String value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(String value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(String value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(String value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLike(String value) {
            addCriterion("create_time like", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotLike(String value) {
            addCriterion("create_time not like", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<String> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<String> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(String value1, String value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(String value1, String value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberIsNull() {
            addCriterion("phone_number is null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberIsNotNull() {
            addCriterion("phone_number is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberEqualTo(String value) {
            addCriterion("phone_number =", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotEqualTo(String value) {
            addCriterion("phone_number <>", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberGreaterThan(String value) {
            addCriterion("phone_number >", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberGreaterThanOrEqualTo(String value) {
            addCriterion("phone_number >=", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberLessThan(String value) {
            addCriterion("phone_number <", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberLessThanOrEqualTo(String value) {
            addCriterion("phone_number <=", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberLike(String value) {
            addCriterion("phone_number like", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotLike(String value) {
            addCriterion("phone_number not like", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberIn(List<String> values) {
            addCriterion("phone_number in", values, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotIn(List<String> values) {
            addCriterion("phone_number not in", values, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberBetween(String value1, String value2) {
            addCriterion("phone_number between", value1, value2, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotBetween(String value1, String value2) {
            addCriterion("phone_number not between", value1, value2, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andProfilePictureUrlIsNull() {
            addCriterion("profile_picture_url is null");
            return (Criteria) this;
        }

        public Criteria andProfilePictureUrlIsNotNull() {
            addCriterion("profile_picture_url is not null");
            return (Criteria) this;
        }

        public Criteria andProfilePictureUrlEqualTo(String value) {
            addCriterion("profile_picture_url =", value, "profilePictureUrl");
            return (Criteria) this;
        }

        public Criteria andProfilePictureUrlNotEqualTo(String value) {
            addCriterion("profile_picture_url <>", value, "profilePictureUrl");
            return (Criteria) this;
        }

        public Criteria andProfilePictureUrlGreaterThan(String value) {
            addCriterion("profile_picture_url >", value, "profilePictureUrl");
            return (Criteria) this;
        }

        public Criteria andProfilePictureUrlGreaterThanOrEqualTo(String value) {
            addCriterion("profile_picture_url >=", value, "profilePictureUrl");
            return (Criteria) this;
        }

        public Criteria andProfilePictureUrlLessThan(String value) {
            addCriterion("profile_picture_url <", value, "profilePictureUrl");
            return (Criteria) this;
        }

        public Criteria andProfilePictureUrlLessThanOrEqualTo(String value) {
            addCriterion("profile_picture_url <=", value, "profilePictureUrl");
            return (Criteria) this;
        }

        public Criteria andProfilePictureUrlLike(String value) {
            addCriterion("profile_picture_url like", value, "profilePictureUrl");
            return (Criteria) this;
        }

        public Criteria andProfilePictureUrlNotLike(String value) {
            addCriterion("profile_picture_url not like", value, "profilePictureUrl");
            return (Criteria) this;
        }

        public Criteria andProfilePictureUrlIn(List<String> values) {
            addCriterion("profile_picture_url in", values, "profilePictureUrl");
            return (Criteria) this;
        }

        public Criteria andProfilePictureUrlNotIn(List<String> values) {
            addCriterion("profile_picture_url not in", values, "profilePictureUrl");
            return (Criteria) this;
        }

        public Criteria andProfilePictureUrlBetween(String value1, String value2) {
            addCriterion("profile_picture_url between", value1, value2, "profilePictureUrl");
            return (Criteria) this;
        }

        public Criteria andProfilePictureUrlNotBetween(String value1, String value2) {
            addCriterion("profile_picture_url not between", value1, value2, "profilePictureUrl");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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