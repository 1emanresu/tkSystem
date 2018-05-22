package com.tkSystem.dao.entity;

public class TkLoginHistory {
    private String tkLoginHistoryId;

    private String tkLoginHistoryName;

    private String tkLoginHistoryTime;

    private String tkLoginHistoryDate;

    private String tkUserId;

    public String getTkLoginHistoryId() {
        return tkLoginHistoryId;
    }

    public void setTkLoginHistoryId(String tkLoginHistoryId) {
        this.tkLoginHistoryId = tkLoginHistoryId == null ? null : tkLoginHistoryId.trim();
    }

    public String getTkLoginHistoryName() {
        return tkLoginHistoryName;
    }

    public void setTkLoginHistoryName(String tkLoginHistoryName) {
        this.tkLoginHistoryName = tkLoginHistoryName == null ? null : tkLoginHistoryName.trim();
    }

    public String getTkLoginHistoryTime() {
        return tkLoginHistoryTime;
    }

    public void setTkLoginHistoryTime(String tkLoginHistoryTime) {
        this.tkLoginHistoryTime = tkLoginHistoryTime == null ? null : tkLoginHistoryTime.trim();
    }

    public String getTkLoginHistoryDate() {
        return tkLoginHistoryDate;
    }

    public void setTkLoginHistoryDate(String tkLoginHistoryDate) {
        this.tkLoginHistoryDate = tkLoginHistoryDate == null ? null : tkLoginHistoryDate.trim();
    }

    public String getTkUserId() {
        return tkUserId;
    }

    public void setTkUserId(String tkUserId) {
        this.tkUserId = tkUserId == null ? null : tkUserId.trim();
    }
}