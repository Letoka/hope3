package com.icbc.zsyw.hope3.dto;

public class HopeUserConf {
    private Integer confid;

    private String aamid;

    private Integer confvalue;

    private String setname;

    public Integer getConfid() {
        return confid;
    }

    public void setConfid(Integer confid) {
        this.confid = confid;
    }

    public String getAamid() {
        return aamid;
    }

    public void setAamid(String aamid) {
        this.aamid = aamid == null ? null : aamid.trim();
    }

    public Integer getConfvalue() {
        return confvalue;
    }

    public void setConfvalue(Integer confvalue) {
        this.confvalue = confvalue;
    }

    public String getSetname() {
        return setname;
    }

    public void setSetname(String setname) {
        this.setname = setname == null ? null : setname.trim();
    }
}