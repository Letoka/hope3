package com.icbc.zsyw.hope3.dto;

import java.util.Date;

public class HopeApiToken {
    private String apicode;

    private String apitoken;

    private String apiname;

    private Date createtime;

    public String getApicode() {
        return apicode;
    }

    public void setApicode(String apicode) {
        this.apicode = apicode == null ? null : apicode.trim();
    }

    public String getApitoken() {
        return apitoken;
    }

    public void setApitoken(String apitoken) {
        this.apitoken = apitoken == null ? null : apitoken.trim();
    }

    public String getApiname() {
        return apiname;
    }

    public void setApiname(String apiname) {
        this.apiname = apiname == null ? null : apiname.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}