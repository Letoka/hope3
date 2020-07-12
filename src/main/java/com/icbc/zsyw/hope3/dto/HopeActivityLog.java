package com.icbc.zsyw.hope3.dto;

public class HopeActivityLog extends HopeActivityLogKey {
    private String aamid;

    private Integer liked;
    public HopeActivityLog(){

    }

    public String getAamid() {
        return aamid;
    }

    public void setAamid(String aamid) {
        this.aamid = aamid == null ? null : aamid.trim();
    }

    public Integer getLiked() {
        return liked;
    }

    public void setLiked(Integer liked) {
        this.liked = liked;
    }
}