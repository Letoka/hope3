package com.icbc.zsyw.hope3.dto;

public class HopeviewModulePriv {
    private Integer moduleid;

    private Integer privtype;

    private String aamid;

    private String deptid;

    private String odeptid;

    public Integer getModuleid() {
        return moduleid;
    }

    public void setModuleid(Integer moduleid) {
        this.moduleid = moduleid;
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

    public String getOdeptid() {
        return odeptid;
    }

    public void setOdeptid(String odeptid) {
        this.odeptid = odeptid == null ? null : odeptid.trim();
    }
}