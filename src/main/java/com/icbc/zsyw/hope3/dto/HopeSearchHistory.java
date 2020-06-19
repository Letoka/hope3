package com.icbc.zsyw.hope3.dto;



import javax.persistence.Transient;
import java.util.Date;

public class HopeSearchHistory {
    private Integer searchhistoryid;

    private String aamid;

    private String searchtext;

    private Date logtime;
    @Transient
    private Integer hotSearchcount;

    public Integer getHotSearchcount() {
        return hotSearchcount;
    }

    public void setHotSearchcount(Integer hotSearchcount) {
        this.hotSearchcount = hotSearchcount;
    }

    public Integer getSearchhistoryid() {
        return searchhistoryid;
    }

    public void setSearchhistoryid(Integer searchhistoryid) {
        this.searchhistoryid = searchhistoryid;
    }

    public String getAamid() {
        return aamid;
    }

    public void setAamid(String aamid) {
        this.aamid = aamid == null ? null : aamid.trim();
    }

    public String getSearchtext() {
        return searchtext;
    }

    public void setSearchtext(String searchtext) {
        this.searchtext = searchtext == null ? null : searchtext.trim();
    }

    public Date getLogtime() {
        return logtime;
    }

    public void setLogtime(Date logtime) {
        this.logtime = logtime;
    }
}