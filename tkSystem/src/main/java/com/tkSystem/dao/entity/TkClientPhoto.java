package com.tkSystem.dao.entity;

public class TkClientPhoto {
    private String tkClientPhotoId;

    private String tkClientPhotoUrl;

    private String tkClientId;

    public String getTkClientPhotoId() {
        return tkClientPhotoId;
    }

    public void setTkClientPhotoId(String tkClientPhotoId) {
        this.tkClientPhotoId = tkClientPhotoId == null ? null : tkClientPhotoId.trim();
    }

    public String getTkClientPhotoUrl() {
        return tkClientPhotoUrl;
    }

    public void setTkClientPhotoUrl(String tkClientPhotoUrl) {
        this.tkClientPhotoUrl = tkClientPhotoUrl == null ? null : tkClientPhotoUrl.trim();
    }

    public String getTkClientId() {
        return tkClientId;
    }

    public void setTkClientId(String tkClientId) {
        this.tkClientId = tkClientId == null ? null : tkClientId.trim();
    }
}