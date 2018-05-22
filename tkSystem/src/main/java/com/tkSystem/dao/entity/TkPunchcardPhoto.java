package com.tkSystem.dao.entity;

public class TkPunchcardPhoto {
    private String tkPunchcardPhotoId;

    private String tkPunchcardPhotoUrl;

    private String tkPunchcardId;

    public String getTkPunchcardPhotoId() {
        return tkPunchcardPhotoId;
    }

    public void setTkPunchcardPhotoId(String tkPunchcardPhotoId) {
        this.tkPunchcardPhotoId = tkPunchcardPhotoId == null ? null : tkPunchcardPhotoId.trim();
    }

    public String getTkPunchcardPhotoUrl() {
        return tkPunchcardPhotoUrl;
    }

    public void setTkPunchcardPhotoUrl(String tkPunchcardPhotoUrl) {
        this.tkPunchcardPhotoUrl = tkPunchcardPhotoUrl == null ? null : tkPunchcardPhotoUrl.trim();
    }

    public String getTkPunchcardId() {
        return tkPunchcardId;
    }

    public void setTkPunchcardId(String tkPunchcardId) {
        this.tkPunchcardId = tkPunchcardId == null ? null : tkPunchcardId.trim();
    }
}