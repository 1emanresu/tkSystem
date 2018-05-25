package com.tkSystem.dao.entity;

import java.io.Serializable;

public class TkLocation implements Serializable{
    private String tkLocationId;

    private String latitude;

    private String longitude;

    private String tkLocationDetail;

    public String getTkLocationId() {
        return tkLocationId;
    }

    public void setTkLocationId(String tkLocationId) {
        this.tkLocationId = tkLocationId == null ? null : tkLocationId.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getTkLocationDetail() {
        return tkLocationDetail;
    }

    public void setTkLocationDetail(String tkLocationDetail) {
        this.tkLocationDetail = tkLocationDetail == null ? null : tkLocationDetail.trim();
    }
}