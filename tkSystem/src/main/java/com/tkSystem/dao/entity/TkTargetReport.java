package com.tkSystem.dao.entity;

public class TkTargetReport {
    private String tkTargetReportId;

    private String tkTargetReportName;

    private String tkTargetReportTime;

    private String tkUserId;

    private String tkTargetReuserId;

      
    public String getTkTargetReuserId() {
		return tkTargetReuserId;
	}

	public void setTkTargetReuserId(String tkTargetReuserId) {
		this.tkTargetReuserId = tkTargetReuserId;
	}

	public String getTkTargetReportId() {
        return tkTargetReportId;
    }

    public void setTkTargetReportId(String tkTargetReportId) {
        this.tkTargetReportId = tkTargetReportId == null ? null : tkTargetReportId.trim();
    }

    public String getTkTargetReportName() {
        return tkTargetReportName;
    }

    public void setTkTargetReportName(String tkTargetReportName) {
        this.tkTargetReportName = tkTargetReportName == null ? null : tkTargetReportName.trim();
    }

    public String getTkTargetReportTime() {
        return tkTargetReportTime;
    }

    public void setTkTargetReportTime(String tkTargetReportTime) {
        this.tkTargetReportTime = tkTargetReportTime == null ? null : tkTargetReportTime.trim();
    }

    public String getTkUserId() {
        return tkUserId;
    }

    public void setTkUserId(String tkUserId) {
        this.tkUserId = tkUserId == null ? null : tkUserId.trim();
    }
}