package com.tkSystem.dao.entity;

public class TkUser {
    private String tkUserId;

    private String tkUserName;

    private String tkUserType;

    private String tkUserPassword;

    private String tkUserTime;

    private String tkUserToken;

    private String tkUserLoginIp;
    private String tkUserPhone;

    private String tkUserHead;
    private String openid;
    private String tkUserTypeId;
    public String getTkUserTypeId() {
		return tkUserTypeId;
	}

	public void setTkUserTypeId(String tkUserTypeId) {
		this.tkUserTypeId = tkUserTypeId;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getTkClientAmount() {
		return tkClientAmount;
	}

	public void setTkClientAmount(String tkClientAmount) {
		this.tkClientAmount = tkClientAmount;
	}

	private String tkClientAmount;
    
    public String getTkUserPhone() {
		return tkUserPhone;
	}

	public void setTkUserPhone(String tkUserPhone) {
		this.tkUserPhone = tkUserPhone;
	}

	public String getTkUserHead() {
		return tkUserHead;
	}

	public void setTkUserHead(String tkUserHead) {
		this.tkUserHead = tkUserHead;
	}

	public String getTkUserId() {
        return tkUserId;
    }

    public void setTkUserId(String tkUserId) {
        this.tkUserId = tkUserId == null ? null : tkUserId.trim();
    }

    public String getTkUserName() {
        return tkUserName;
    }

    public void setTkUserName(String tkUserName) {
        this.tkUserName = tkUserName == null ? null : tkUserName.trim();
    }

    public String getTkUserType() {
        return tkUserType;
    }

    public void setTkUserType(String tkUserType) {
        this.tkUserType = tkUserType == null ? null : tkUserType.trim();
    }

    public String getTkUserPassword() {
        return tkUserPassword;
    }

    public void setTkUserPassword(String tkUserPassword) {
        this.tkUserPassword = tkUserPassword == null ? null : tkUserPassword.trim();
    }

    public String getTkUserTime() {
        return tkUserTime;
    }

    public void setTkUserTime(String tkUserTime) {
        this.tkUserTime = tkUserTime == null ? null : tkUserTime.trim();
    }

    public String getTkUserToken() {
        return tkUserToken;
    }

    public void setTkUserToken(String tkUserToken) {
        this.tkUserToken = tkUserToken == null ? null : tkUserToken.trim();
    }

    public String getTkUserLoginIp() {
        return tkUserLoginIp;
    }

    public void setTkUserLoginIp(String tkUserLoginIp) {
        this.tkUserLoginIp = tkUserLoginIp == null ? null : tkUserLoginIp.trim();
    }
}