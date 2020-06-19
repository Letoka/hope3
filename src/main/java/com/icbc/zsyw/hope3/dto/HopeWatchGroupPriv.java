package com.icbc.zsyw.hope3.dto;

public class HopeWatchGroupPriv {
    private String watchgroupprivid;

    private String aamid;

    public String getWatchgroupprivid() {
        return watchgroupprivid;
    }

    public void setWatchgroupprivid(String watchgroupprivid) {
        this.watchgroupprivid = watchgroupprivid == null ? null : watchgroupprivid.trim();
    }

    public String getAamid() {
        return aamid;
    }

    public void setAamid(String aamid) {
        this.aamid = aamid == null ? null : aamid.trim();
    }
}