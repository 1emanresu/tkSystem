package com.tkSystem.dao.entity;

public class TkPower {
    private String tkPowerId;

    private String tkUserId;

    private String tkFunctionId;

    public String getTkPowerId() {
        return tkPowerId;
    }

    public void setTkPowerId(String tkPowerId) {
        this.tkPowerId = tkPowerId == null ? null : tkPowerId.trim();
    }

    public String getTkUserId() {
        return tkUserId;
    }

    public void setTkUserId(String tkUserId) {
        this.tkUserId = tkUserId == null ? null : tkUserId.trim();
    }

    public String getTkFunctionId() {
        return tkFunctionId;
    }

    public void setTkFunctionId(String tkFunctionId) {
        this.tkFunctionId = tkFunctionId == null ? null : tkFunctionId.trim();
    }
}