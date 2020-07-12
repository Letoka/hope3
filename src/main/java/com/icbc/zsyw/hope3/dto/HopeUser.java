package com.icbc.zsyw.hope3.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
//@Entity
public class HopeUser {
    //@Id
    private String aamid;

    private String username;

    private Date registtime;

    private Integer redirectgroup;

    private Integer userlevel;

    private Integer userwatchlevel;

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

    public Date getRegisttime() {
        return registtime;
    }

    public void setRegisttime(Date registtime) {
        this.registtime = registtime;
    }

    public Integer getRedirectgroup() {
        return redirectgroup;
    }

    public void setRedirectgroup(Integer redirectgroup) {
        this.redirectgroup = redirectgroup;
    }

    public Integer getUserlevel() {
        return userlevel;
    }

    public void setUserlevel(Integer userlevel) {
        this.userlevel = userlevel;
    }

    public Integer getUserwatchlevel() {
        return userwatchlevel;
    }

    public void setUserwatchlevel(Integer userwatchlevel) {
        this.userwatchlevel = userwatchlevel;
    }
}