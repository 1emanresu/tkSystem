package com.tkSystem.dao.entity;

public class TkPlan {
    private String tkPlanId;

    private String tkPlanUserId;

    private String tkPlanUserName;

    private String tkClientTypeId;

    private String tkPlanLocId;

    private String tkPlanTime;

    private String tkPlanTarget;

    private String tkPlanState;
    private String tkPlanName;
    public String getTkPlanName() {
		return tkPlanName;
	}

	public void setTkPlanName(String tkPlanName) {
		this.tkPlanName = tkPlanName;
	}

	public String getTkPlanId() {
        return tkPlanId;
    }

    public void setTkPlanId(String tkPlanId) {
        this.tkPlanId = tkPlanId == null ? null : tkPlanId.trim();
    }

    public String getTkPlanUserId() {
        return tkPlanUserId;
    }

    public void setTkPlanUserId(String tkPlanUserId) {
        this.tkPlanUserId = tkPlanUserId == null ? null : tkPlanUserId.trim();
    }

    public String getTkPlanUserName() {
        return tkPlanUserName;
    }

    public void setTkPlanUserName(String tkPlanUserName) {
        this.tkPlanUserName = tkPlanUserName == null ? null : tkPlanUserName.trim();
    }

    public String getTkClientTypeId() {
        return tkClientTypeId;
    }

    public void setTkClientTypeId(String tkClientTypeId) {
        this.tkClientTypeId = tkClientTypeId == null ? null : tkClientTypeId.trim();
    }

    public String getTkPlanLocId() {
        return tkPlanLocId;
    }

    public void setTkPlanLocId(String tkPlanLocId) {
        this.tkPlanLocId = tkPlanLocId == null ? null : tkPlanLocId.trim();
    }

    public String getTkPlanTime() {
        return tkPlanTime;
    }

    public void setTkPlanTime(String tkPlanTime) {
        this.tkPlanTime = tkPlanTime == null ? null : tkPlanTime.trim();
    }

    public String getTkPlanTarget() {
        return tkPlanTarget;
    }

    public void setTkPlanTarget(String tkPlanTarget) {
        this.tkPlanTarget = tkPlanTarget == null ? null : tkPlanTarget.trim();
    }

    public String getTkPlanState() {
        return tkPlanState;
    }

    public void setTkPlanState(String tkPlanState) {
        this.tkPlanState = tkPlanState == null ? null : tkPlanState.trim();
    }
}