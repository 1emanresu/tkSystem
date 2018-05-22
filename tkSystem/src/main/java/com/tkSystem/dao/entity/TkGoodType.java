package com.tkSystem.dao.entity;

public class TkGoodType {
    private String tkGoodTypeId;

    private String tkGoodTypeName;

    private String tkGoodTypeTime;

    public String getTkGoodTypeId() {
        return tkGoodTypeId;
    }

    public void setTkGoodTypeId(String tkGoodTypeId) {
        this.tkGoodTypeId = tkGoodTypeId == null ? null : tkGoodTypeId.trim();
    }

    public String getTkGoodTypeName() {
        return tkGoodTypeName;
    }

    public void setTkGoodTypeName(String tkGoodTypeName) {
        this.tkGoodTypeName = tkGoodTypeName == null ? null : tkGoodTypeName.trim();
    }

    public String getTkGoodTypeTime() {
        return tkGoodTypeTime;
    }

    public void setTkGoodTypeTime(String tkGoodTypeTime) {
        this.tkGoodTypeTime = tkGoodTypeTime == null ? null : tkGoodTypeTime.trim();
    }
}