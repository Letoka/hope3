package com.icbc.zsyw.hope3.dto;

import java.util.Date;

public class HopeViewTimes {
    private Integer viewtimeid;

    private String aamid;

    private Integer moduleid;

    private Integer viewtimes;

    private Date lastviewtime;

    public Integer getViewtimeid() {
        return viewtimeid;
    }

    public void setViewtimeid(Integer viewtimeid) {
        this.viewtimeid = viewtimeid;
    }

    public String getAamid() {
        return aamid;
    }

    public void setAamid(String aamid) {
        this.aamid = aamid == null ? null : aamid.trim();
    }

    public Integer getModuleid() {
        return moduleid;
    }

    public void setModuleid(Integer moduleid) {
        this.moduleid = moduleid;
    }

    public Integer getViewtimes() {
        return viewtimes;
    }

    public void setViewtimes(Integer viewtimes) {
        this.viewtimes = viewtimes;
    }

    public Date getLastviewtime() {
        return lastviewtime;
    }

    public void setLastviewtime(Date lastviewtime) {
        this.lastviewtime = lastviewtime;
    }
}