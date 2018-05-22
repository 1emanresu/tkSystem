package com.tkSystem.dao.entity;

public class TkFunction {
    private String tkFunctionId;

    private String tkFunctionName;

    private String tkFunctionPid;

    public String getTkFunctionId() {
        return tkFunctionId;
    }

    public void setTkFunctionId(String tkFunctionId) {
        this.tkFunctionId = tkFunctionId == null ? null : tkFunctionId.trim();
    }

    public String getTkFunctionName() {
        return tkFunctionName;
    }

    public void setTkFunctionName(String tkFunctionName) {
        this.tkFunctionName = tkFunctionName == null ? null : tkFunctionName.trim();
    }

    public String getTkFunctionPid() {
        return tkFunctionPid;
    }

    public void setTkFunctionPid(String tkFunctionPid) {
        this.tkFunctionPid = tkFunctionPid == null ? null : tkFunctionPid.trim();
    }
}