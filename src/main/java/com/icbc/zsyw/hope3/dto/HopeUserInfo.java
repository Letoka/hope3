package com.icbc.zsyw.hope3.dto;

import java.util.Date;

public class HopeUserInfo {
    private String aamid;

    private String username;

    private String deptid;

    private String deptname;

    private String odeptid;

    private String odeptname;

    private String usermobile;

    private String useremail;

    private String officephone;

    private String tdeptname;

    private Date logtime;

    private String userpost;

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

    public String getUsermobile() {
        return usermobile;
    }

    public void setUsermobile(String usermobile) {
        this.usermobile = usermobile == null ? null : usermobile.trim();
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail == null ? null : useremail.trim();
    }

    public String getOfficephone() {
        return officephone;
    }

    public void setOfficephone(String officephone) {
        this.officephone = officephone == null ? null : officephone.trim();
    }

    public String getTdeptname() {
        return tdeptname;
    }

    public void setTdeptname(String tdeptname) {
        this.tdeptname = tdeptname == null ? null : tdeptname.trim();
    }

    public Date getLogtime() {
        return logtime;
    }

    public void setLogtime(Date logtime) {
        this.logtime = logtime;
    }

    public String getUserpost() {
        return userpost;
    }

    public void setUserpost(String userpost) {
        this.userpost = userpost == null ? null : userpost.trim();
    }
}