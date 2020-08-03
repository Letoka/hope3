package com.icbc.zsyw.hope3.dto;

public class HopeRecommend {
    private Integer moduleid;

    private String aamid;

    private String deptid;

    private String odeptid;

    private String privgroupid;

    private Float weight;

    public Integer getModuleid() {
        return moduleid;
    }

    public void setModuleid(Integer moduleid) {
        this.moduleid = moduleid;
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

    public String getPrivgroupid() {
        return privgroupid;
    }

    public void setPrivgroupid(String privgroupid) {
        this.privgroupid = privgroupid == null ? null : privgroupid.trim();
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }
}