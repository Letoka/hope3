package com.icbc.zsyw.hope3.dto;

import javax.persistence.Transient;
import java.util.Date;

public class HopeUserHistory {
    private Integer userhistoryid;

    private String aamid;

    private Integer moduleid;

    private Date logtime;
    //status=0显示全部足迹，最多显示20条
    //status=1显示我的足迹，最多显示7条
    @Transient
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserhistoryid() {
        return userhistoryid;
    }

    public void setUserhistoryid(Integer userhistoryid) {
        this.userhistoryid = userhistoryid;
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

    public Date getLogtime() {
        return logtime;
    }

    public void setLogtime(Date logtime) {
        this.logtime = logtime;
    }
}