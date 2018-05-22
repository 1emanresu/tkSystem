package com.tkSystem.dao.entity;

public class TkPlanExecuteClient {
    private String tkPlanExecuteClientId;

    private String tkPlanExecuteClientName;

    public String getTkPlanExecuteClientId() {
        return tkPlanExecuteClientId;
    }

    public void setTkPlanExecuteClientId(String tkPlanExecuteClientId) {
        this.tkPlanExecuteClientId = tkPlanExecuteClientId == null ? null : tkPlanExecuteClientId.trim();
    }

    public String getTkPlanExecuteClientName() {
        return tkPlanExecuteClientName;
    }

    public void setTkPlanExecuteClientName(String tkPlanExecuteClientName) {
        this.tkPlanExecuteClientName = tkPlanExecuteClientName == null ? null : tkPlanExecuteClientName.trim();
    }
}