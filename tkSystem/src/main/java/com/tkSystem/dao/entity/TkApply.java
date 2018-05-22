package com.tkSystem.dao.entity;

public class TkApply {
    private String tkApplyId;

    private String tkApplyUserId;

    private String tkApplyGoodId;

    private String tkApplyGoodAmount;

    private String tkApplyGoodTime;

    private String tkApplyGoodState;

    public String getTkApplyId() {
        return tkApplyId;
    }

    public void setTkApplyId(String tkApplyId) {
        this.tkApplyId = tkApplyId == null ? null : tkApplyId.trim();
    }

    public String getTkApplyUserId() {
        return tkApplyUserId;
    }

    public void setTkApplyUserId(String tkApplyUserId) {
        this.tkApplyUserId = tkApplyUserId == null ? null : tkApplyUserId.trim();
    }

    public String getTkApplyGoodId() {
        return tkApplyGoodId;
    }

    public void setTkApplyGoodId(String tkApplyGoodId) {
        this.tkApplyGoodId = tkApplyGoodId == null ? null : tkApplyGoodId.trim();
    }

    public String getTkApplyGoodAmount() {
        return tkApplyGoodAmount;
    }

    public void setTkApplyGoodAmount(String tkApplyGoodAmount) {
        this.tkApplyGoodAmount = tkApplyGoodAmount == null ? null : tkApplyGoodAmount.trim();
    }

    public String getTkApplyGoodTime() {
        return tkApplyGoodTime;
    }

    public void setTkApplyGoodTime(String tkApplyGoodTime) {
        this.tkApplyGoodTime = tkApplyGoodTime == null ? null : tkApplyGoodTime.trim();
    }

    public String getTkApplyGoodState() {
        return tkApplyGoodState;
    }

    public void setTkApplyGoodState(String tkApplyGoodState) {
        this.tkApplyGoodState = tkApplyGoodState == null ? null : tkApplyGoodState.trim();
    }
}