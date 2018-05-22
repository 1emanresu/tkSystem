package com.tkSystem.dao.entity;

public class TkUserGrade {
    private String tkUserGradeId;

    private String tkUserGradeName;

    private String tkUserGradeTime;

    private String tkUserGradePid;

    private String tkUserGradeGnid;
    private String tkUserState;

    public String getTkUserState() {
		return tkUserState;
	}

	public void setTkUserState(String tkUserState) {
		this.tkUserState = tkUserState;
	}

	public String getTkUserGradeId() {
        return tkUserGradeId;
    }
    
    public void setTkUserGradeId(String tkUserGradeId) {
        this.tkUserGradeId = tkUserGradeId == null ? null : tkUserGradeId.trim();
    }

    public String getTkUserGradeName() {
        return tkUserGradeName;
    }

    public void setTkUserGradeName(String tkUserGradeName) {
        this.tkUserGradeName = tkUserGradeName == null ? null : tkUserGradeName.trim();
    }

    public String getTkUserGradeTime() {
        return tkUserGradeTime;
    }

    public void setTkUserGradeTime(String tkUserGradeTime) {
        this.tkUserGradeTime = tkUserGradeTime == null ? null : tkUserGradeTime.trim();
    }

    public String getTkUserGradePid() {
        return tkUserGradePid;
    }

    public void setTkUserGradePid(String tkUserGradePid) {
        this.tkUserGradePid = tkUserGradePid == null ? null : tkUserGradePid.trim();
    }

    public String getTkUserGradeGnid() {
        return tkUserGradeGnid;
    }

    public void setTkUserGradeGnid(String tkUserGradeGnid) {
        this.tkUserGradeGnid = tkUserGradeGnid == null ? null : tkUserGradeGnid.trim();
    }
}