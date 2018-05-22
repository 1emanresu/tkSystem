package com.tkSystem.dao.entity;

public class TkPlanPhoto {
    private String tkPlanPhotoId;

    private String tkPlanPhotoUrl;

    private String tkPlanId;

    public String getTkPlanPhotoId() {
        return tkPlanPhotoId;
    }

    public void setTkPlanPhotoId(String tkPlanPhotoId) {
        this.tkPlanPhotoId = tkPlanPhotoId == null ? null : tkPlanPhotoId.trim();
    }

    public String getTkPlanPhotoUrl() {
        return tkPlanPhotoUrl;
    }

    public void setTkPlanPhotoUrl(String tkPlanPhotoUrl) {
        this.tkPlanPhotoUrl = tkPlanPhotoUrl == null ? null : tkPlanPhotoUrl.trim();
    }

    public String getTkPlanId() {
        return tkPlanId;
    }

    public void setTkPlanId(String tkPlanId) {
        this.tkPlanId = tkPlanId == null ? null : tkPlanId.trim();
    }
}