package com.icbc.zsyw.hope3.dto;

public class HopeUserConfKey {
    private String aamid;

    private String confid;

    public String getAamid() {
        return aamid;
    }

    public void setAamid(String aamid) {
        this.aamid = aamid == null ? null : aamid.trim();
    }

    public String getConfid() {
        return confid;
    }

    public void setConfid(String confid) {
        this.confid = confid == null ? null : confid.trim();
    }
}