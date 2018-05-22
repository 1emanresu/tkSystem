package com.tkSystem.dao.entity;

public class TkChannel {
    private String tkChannelId;

    private String tkChannelName;

    private String tkUserId;

    public String getTkChannelId() {
        return tkChannelId;
    }

    public void setTkChannelId(String tkChannelId) {
        this.tkChannelId = tkChannelId == null ? null : tkChannelId.trim();
    }

    public String getTkChannelName() {
        return tkChannelName;
    }

    public void setTkChannelName(String tkChannelName) {
        this.tkChannelName = tkChannelName == null ? null : tkChannelName.trim();
    }

    public String getTkUserId() {
        return tkUserId;
    }

    public void setTkUserId(String tkUserId) {
        this.tkUserId = tkUserId == null ? null : tkUserId.trim();
    }
}