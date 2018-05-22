package com.tkSystem.dao.entity;

public class TkPunchcard {
    private String tkPunchcardId;

    private String tkPunchcardTime;

    private String tkPunchcardPerson;

    private String tkPunchcardLoc;
    
    private String tkPunchCardPhoto;
    private String tkPlanId;

    public String getTkPlanId() {
		return tkPlanId;
	}

	public void setTkPlanId(String tkPlanId) {
		this.tkPlanId = tkPlanId;
	}

	public String getTkPunchCardPhoto() {
		return tkPunchCardPhoto;
	}

	public void setTkPunchCardPhoto(String tkPunchCardPhoto) {
		this.tkPunchCardPhoto = tkPunchCardPhoto;
	}

	public String getTkPunchcardId() {
        return tkPunchcardId;
    }

    public void setTkPunchcardId(String tkPunchcardId) {
        this.tkPunchcardId = tkPunchcardId == null ? null : tkPunchcardId.trim();
    }

    public String getTkPunchcardTime() {
        return tkPunchcardTime;
    }

    public void setTkPunchcardTime(String tkPunchcardTime) {
        this.tkPunchcardTime = tkPunchcardTime == null ? null : tkPunchcardTime.trim();
    }

    public String getTkPunchcardPerson() {
        return tkPunchcardPerson;
    }

    public void setTkPunchcardPerson(String tkPunchcardPerson) {
        this.tkPunchcardPerson = tkPunchcardPerson == null ? null : tkPunchcardPerson.trim();
    }

    public String getTkPunchcardLoc() {
        return tkPunchcardLoc;
    }

    public void setTkPunchcardLoc(String tkPunchcardLoc) {
        this.tkPunchcardLoc = tkPunchcardLoc == null ? null : tkPunchcardLoc.trim();
    }
}