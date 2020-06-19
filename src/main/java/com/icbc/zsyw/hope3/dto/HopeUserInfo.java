package com.icbc.zsyw.hope3.dto;

public class HopeUserInfo {
    private String aamid;

    private String username;

    private String deptid;

    private String deptname;

    private String odeptid;

    private String odeptname;

    public String getAamid() {
        return aamid;
    }

    public void setAamid(String aamid) {
        this.aamid = aamid == null ? null : aamid.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid == null ? null : deptid.trim();
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname == null ? null : deptname.trim();
    }

    public String getOdeptid() {
        return odeptid;
    }

    public void setOdeptid(String odeptid) {
        this.odeptid = odeptid == null ? null : odeptid.trim();
    }

    public String getOdeptname() {
        return odeptname;
    }

    public void setOdeptname(String odeptname) {
        this.odeptname = odeptname == null ? null : odeptname.trim();
    }
}