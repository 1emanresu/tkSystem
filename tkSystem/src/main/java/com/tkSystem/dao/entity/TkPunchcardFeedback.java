package com.tkSystem.dao.entity;

public class TkPunchcardFeedback {
    private String tkPunchcardFeedbackId;

    private String tkPunchcardFeedbackUserId;

    private String tkPunchcardFeedbackTime;

    private String tkPunchcardFeedbackLatetime;

    private String tkPunchcardFeedbackManagerid;

    private String tkPunchcardFeedbackPlanId;

    public String getTkPunchcardFeedbackId() {
        return tkPunchcardFeedbackId;
    }

    public void setTkPunchcardFeedbackId(String tkPunchcardFeedbackId) {
        this.tkPunchcardFeedbackId = tkPunchcardFeedbackId == null ? null : tkPunchcardFeedbackId.trim();
    }

    public String getTkPunchcardFeedbackUserId() {
        return tkPunchcardFeedbackUserId;
    }

    public void setTkPunchcardFeedbackUserId(String tkPunchcardFeedbackUserId) {
        this.tkPunchcardFeedbackUserId = tkPunchcardFeedbackUserId == null ? null : tkPunchcardFeedbackUserId.trim();
    }

    public String getTkPunchcardFeedbackTime() {
        return tkPunchcardFeedbackTime;
    }

    public void setTkPunchcardFeedbackTime(String tkPunchcardFeedbackTime) {
        this.tkPunchcardFeedbackTime = tkPunchcardFeedbackTime == null ? null : tkPunchcardFeedbackTime.trim();
    }

    public String getTkPunchcardFeedbackLatetime() {
        return tkPunchcardFeedbackLatetime;
    }

    public void setTkPunchcardFeedbackLatetime(String tkPunchcardFeedbackLatetime) {
        this.tkPunchcardFeedbackLatetime = tkPunchcardFeedbackLatetime == null ? null : tkPunchcardFeedbackLatetime.trim();
    }

    public String getTkPunchcardFeedbackManagerid() {
        return tkPunchcardFeedbackManagerid;
    }

    public void setTkPunchcardFeedbackManagerid(String tkPunchcardFeedbackManagerid) {
        this.tkPunchcardFeedbackManagerid = tkPunchcardFeedbackManagerid == null ? null : tkPunchcardFeedbackManagerid.trim();
    }

    public String getTkPunchcardFeedbackPlanId() {
        return tkPunchcardFeedbackPlanId;
    }

    public void setTkPunchcardFeedbackPlanId(String tkPunchcardFeedbackPlanId) {
        this.tkPunchcardFeedbackPlanId = tkPunchcardFeedbackPlanId == null ? null : tkPunchcardFeedbackPlanId.trim();
    }
}