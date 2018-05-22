package com.tkSystem.dao.entity;

public class TkPlanExecute {
    private String tkPlanExecuteId;

    private String tkPlanExecuteName;

    private String tkPlanExecuteContent;

    private String tkPlanId;

    private String tkPlanExecuteStartTime;

    private String tkPlanExecuteEndTime;

    private String tkPlanExecuteCutormId;

    private String tkUserId;

    private String tkPlanExecuteState;

    private String tkPlanExecuteRemark;

    public String getTkPlanExecuteId() {
        return tkPlanExecuteId;
    }

    public void setTkPlanExecuteId(String tkPlanExecuteId) {
        this.tkPlanExecuteId = tkPlanExecuteId == null ? null : tkPlanExecuteId.trim();
    }

    public String getTkPlanExecuteName() {
        return tkPlanExecuteName;
    }

    public void setTkPlanExecuteName(String tkPlanExecuteName) {
        this.tkPlanExecuteName = tkPlanExecuteName == null ? null : tkPlanExecuteName.trim();
    }

    public String getTkPlanExecuteContent() {
        return tkPlanExecuteContent;
    }

    public void setTkPlanExecuteContent(String tkPlanExecuteContent) {
        this.tkPlanExecuteContent = tkPlanExecuteContent == null ? null : tkPlanExecuteContent.trim();
    }

    public String getTkPlanId() {
        return tkPlanId;
    }

    public void setTkPlanId(String tkPlanId) {
        this.tkPlanId = tkPlanId == null ? null : tkPlanId.trim();
    }

    public String getTkPlanExecuteStartTime() {
        return tkPlanExecuteStartTime;
    }

    public void setTkPlanExecuteStartTime(String tkPlanExecuteStartTime) {
        this.tkPlanExecuteStartTime = tkPlanExecuteStartTime == null ? null : tkPlanExecuteStartTime.trim();
    }

    public String getTkPlanExecuteEndTime() {
        return tkPlanExecuteEndTime;
    }

    public void setTkPlanExecuteEndTime(String tkPlanExecuteEndTime) {
        this.tkPlanExecuteEndTime = tkPlanExecuteEndTime == null ? null : tkPlanExecuteEndTime.trim();
    }

    public String getTkPlanExecuteCutormId() {
        return tkPlanExecuteCutormId;
    }

    public void setTkPlanExecuteCutormId(String tkPlanExecuteCutormId) {
        this.tkPlanExecuteCutormId = tkPlanExecuteCutormId == null ? null : tkPlanExecuteCutormId.trim();
    }

    public String getTkUserId() {
        return tkUserId;
    }

    public void setTkUserId(String tkUserId) {
        this.tkUserId = tkUserId == null ? null : tkUserId.trim();
    }

    public String getTkPlanExecuteState() {
        return tkPlanExecuteState;
    }

    public void setTkPlanExecuteState(String tkPlanExecuteState) {
        this.tkPlanExecuteState = tkPlanExecuteState == null ? null : tkPlanExecuteState.trim();
    }

    public String getTkPlanExecuteRemark() {
        return tkPlanExecuteRemark;
    }

    public void setTkPlanExecuteRemark(String tkPlanExecuteRemark) {
        this.tkPlanExecuteRemark = tkPlanExecuteRemark == null ? null : tkPlanExecuteRemark.trim();
    }
}