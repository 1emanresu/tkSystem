package com.tkSystem.dao.entity;

public class TkGoodApplyGood {
    private String tkGoodApplyId;

    private String tkGoodApplyName;

    private String tkGoodApplyUserId;

    private String tkGoodApplyGoodId;

    private String tkGoodApplyGoodAmount;

    private String tkGoodApplyGoodTime;

    private String tkGoodApplyState;

    private String tkGoodApplyFeedbackTime;
    private String tkGoodApplyRemark;
     

	public String getTkGoodApplyRemark() {
		return tkGoodApplyRemark;
	}

	public void setTkGoodApplyRemark(String tkGoodApplyRemark) {
		this.tkGoodApplyRemark = tkGoodApplyRemark;
	}

	public String getTkGoodApplyId() {
        return tkGoodApplyId;
    }

    public void setTkGoodApplyId(String tkGoodApplyId) {
        this.tkGoodApplyId = tkGoodApplyId == null ? null : tkGoodApplyId.trim();
    }

    public String getTkGoodApplyName() {
        return tkGoodApplyName;
    }

    public void setTkGoodApplyName(String tkGoodApplyName) {
        this.tkGoodApplyName = tkGoodApplyName == null ? null : tkGoodApplyName.trim();
    }

    public String getTkGoodApplyUserId() {
        return tkGoodApplyUserId;
    }

    public void setTkGoodApplyUserId(String tkGoodApplyUserId) {
        this.tkGoodApplyUserId = tkGoodApplyUserId == null ? null : tkGoodApplyUserId.trim();
    }

    public String getTkGoodApplyGoodId() {
        return tkGoodApplyGoodId;
    }

    public void setTkGoodApplyGoodId(String tkGoodApplyGoodId) {
        this.tkGoodApplyGoodId = tkGoodApplyGoodId == null ? null : tkGoodApplyGoodId.trim();
    }

    public String getTkGoodApplyGoodAmount() {
        return tkGoodApplyGoodAmount;
    }

    public void setTkGoodApplyGoodAmount(String tkGoodApplyGoodAmount) {
        this.tkGoodApplyGoodAmount = tkGoodApplyGoodAmount == null ? null : tkGoodApplyGoodAmount.trim();
    }

    public String getTkGoodApplyGoodTime() {
        return tkGoodApplyGoodTime;
    }

    public void setTkGoodApplyGoodTime(String tkGoodApplyGoodTime) {
        this.tkGoodApplyGoodTime = tkGoodApplyGoodTime == null ? null : tkGoodApplyGoodTime.trim();
    }

    public String getTkGoodApplyState() {
        return tkGoodApplyState;
    }

    public void setTkGoodApplyState(String tkGoodApplyState) {
        this.tkGoodApplyState = tkGoodApplyState == null ? null : tkGoodApplyState.trim();
    }

    public String getTkGoodApplyFeedbackTime() {
        return tkGoodApplyFeedbackTime;
    }

    public void setTkGoodApplyFeedbackTime(String tkGoodApplyFeedbackTime) {
        this.tkGoodApplyFeedbackTime = tkGoodApplyFeedbackTime == null ? null : tkGoodApplyFeedbackTime.trim();
    }
}