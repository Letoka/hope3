package com.icbc.zsyw.hope3.dto;

public class HopeviewBroadcastPriv {
    private Integer broadcastid;

    private Integer privtype;

    private String aamid;

    private String deptid;

    public Integer getBroadcastid() {
        return broadcastid;
    }

    public void setBroadcastid(Integer broadcastid) {
        this.broadcastid = broadcastid;
    }

    public Integer getPrivtype() {
        return privtype;
    }

    public void setPrivtype(Integer privtype) {
        this.privtype = privtype;
    }

    public String getAamid() {
        return aamid;
    }

    public void setAamid(String aamid) {
        this.aamid = aamid == null ? null : aamid.trim();
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid == null ? null : deptid.trim();
    }
}