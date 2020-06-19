package com.icbc.zsyw.hope3.dto;

public class HopeModulePriv {
    private Integer moduleprivid;

    private Integer moduleid;

    private Integer privtype;

    private String aamid;

    private String deptid;

    private String privgroupid;

    public Integer getModuleprivid() {
        return moduleprivid;
    }

    public void setModuleprivid(Integer moduleprivid) {
        this.moduleprivid = moduleprivid;
    }

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

    public String getPrivgroupid() {
        return privgroupid;
    }

    public void setPrivgroupid(String privgroupid) {
        this.privgroupid = privgroupid == null ? null : privgroupid.trim();
    }
}