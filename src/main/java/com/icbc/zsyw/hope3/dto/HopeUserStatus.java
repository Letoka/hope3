package com.icbc.zsyw.hope3.dto;

public class HopeUserStatus {
    private String aamid;

    private Integer logstatus;

    public String getAamid() {
        return aamid;
    }

    public void setAamid(String aamid) {
        this.aamid = aamid == null ? null : aamid.trim();
    }

    public Integer getLogstatus() {
        return logstatus;
    }

    public void setLogstatus(Integer logstatus) {
        this.logstatus = logstatus;
    }
}