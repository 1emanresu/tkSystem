package com.tkSystem.dao.entity;

public class TkClientType {
    private String tkTypeId ;

    private String tkTypeName;

    private String tkTypeTime;

    public String getTkTypeId() {
        return tkTypeId;
    }

    public void setTkTypeId(String tkTypeId) {
        this.tkTypeId = tkTypeId == null ? null : tkTypeId.trim();
    }

    public String getTkTypeName() {
        return tkTypeName;
    }

    public void setTkTypeName(String tkTypeName) {
        this.tkTypeName = tkTypeName == null ? null : tkTypeName.trim();
    }

    public String getTkTypeTime() {
        return tkTypeTime;
    }

    public void setTkTypeTime(String tkTypeTime) {
        this.tkTypeTime = tkTypeTime == null ? null : tkTypeTime.trim();
    }
}