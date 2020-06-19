package com.icbc.zsyw.hope3.dto;

import java.util.Date;

public class HopeBroadcast {
    private Integer broadcastid;

    private String broadcasttype;

    private String broadcastname;

    private Date starttime;

    private Date endtime;

    private Integer enabled;

    private Integer published;

    public Integer getBroadcastid() {
        return broadcastid;
    }

    public void setBroadcastid(Integer broadcastid) {
        this.broadcastid = broadcastid;
    }

    public String getBroadcasttype() {
        return broadcasttype;
    }

    public void setBroadcasttype(String broadcasttype) {
        this.broadcasttype = broadcasttype == null ? null : broadcasttype.trim();
    }

    public String getBroadcastname() {
        return broadcastname;
    }

    public void setBroadcastname(String broadcastname) {
        this.broadcastname = broadcastname == null ? null : broadcastname.trim();
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Integer getPublished() {
        return published;
    }

    public void setPublished(Integer published) {
        this.published = published;
    }
}