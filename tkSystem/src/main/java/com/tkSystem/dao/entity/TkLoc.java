package com.tkSystem.dao.entity;

public class TkLoc {
    private String tkLocId;

    private String tkLocPid;

    private String tkLocLatitude;

    private String tkLocLongitude;

    private String tkLocName;

    private String tkLocTime;

    public String getTkLocId() {
        return tkLocId;
    }

    public void setTkLocId(String tkLocId) {
        this.tkLocId = tkLocId == null ? null : tkLocId.trim();
    }

    public String getTkLocPid() {
        return tkLocPid;
    }

    public void setTkLocPid(String tkLocPid) {
        this.tkLocPid = tkLocPid == null ? null : tkLocPid.trim();
    }

    public String getTkLocLatitude() {
        return tkLocLatitude;
    }

    public void setTkLocLatitude(String tkLocLatitude) {
        this.tkLocLatitude = tkLocLatitude == null ? null : tkLocLatitude.trim();
    }

    public String getTkLocLongitude() {
        return tkLocLongitude;
    }

    public void setTkLocLongitude(String tkLocLongitude) {
        this.tkLocLongitude = tkLocLongitude == null ? null : tkLocLongitude.trim();
    }

    public String getTkLocName() {
        return tkLocName;
    }

    public void setTkLocName(String tkLocName) {
        this.tkLocName = tkLocName == null ? null : tkLocName.trim();
    }

    public String getTkLocTime() {
        return tkLocTime;
    }

    public void setTkLocTime(String tkLocTime) {
        this.tkLocTime = tkLocTime == null ? null : tkLocTime.trim();
    }
}