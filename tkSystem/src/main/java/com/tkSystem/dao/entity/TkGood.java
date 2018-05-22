package com.tkSystem.dao.entity;

public class TkGood {
    private String tkGoodId;

    private String tkGoodName;

    private String tkGoodType;

    private String tkGoodPrice;

    private String tkGoodInsertTime;

    private String tkGoodAmount;

    public String getTkGoodId() {
        return tkGoodId;
    }

    public void setTkGoodId(String tkGoodId) {
        this.tkGoodId = tkGoodId == null ? null : tkGoodId.trim();
    }

    public String getTkGoodName() {
        return tkGoodName;
    }

    public void setTkGoodName(String tkGoodName) {
        this.tkGoodName = tkGoodName == null ? null : tkGoodName.trim();
    }

    public String getTkGoodType() {
        return tkGoodType;
    }

    public void setTkGoodType(String tkGoodType) {
        this.tkGoodType = tkGoodType == null ? null : tkGoodType.trim();
    }

    public String getTkGoodPrice() {
        return tkGoodPrice;
    }

    public void setTkGoodPrice(String tkGoodPrice) {
        this.tkGoodPrice = tkGoodPrice == null ? null : tkGoodPrice.trim();
    }

    public String getTkGoodInsertTime() {
        return tkGoodInsertTime;
    }

    public void setTkGoodInsertTime(String tkGoodInsertTime) {
        this.tkGoodInsertTime = tkGoodInsertTime == null ? null : tkGoodInsertTime.trim();
    }

    public String getTkGoodAmount() {
        return tkGoodAmount;
    }

    public void setTkGoodAmount(String tkGoodAmount) {
        this.tkGoodAmount = tkGoodAmount == null ? null : tkGoodAmount.trim();
    }
}