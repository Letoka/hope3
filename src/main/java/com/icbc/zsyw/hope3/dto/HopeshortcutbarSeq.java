package com.icbc.zsyw.hope3.dto;

public class HopeshortcutbarSeq {
    private Integer shortcutbarid;

    private String shortcutbarname;

    private Integer privtype;

    private String aamid;

    private String deptid;

    private String deptname;

    private String privgroupid;

    private String odeptid;

    private String odeptname;

    private Integer weight;

    public Integer getShortcutbarid() {
        return shortcutbarid;
    }

    public void setShortcutbarid(Integer shortcutbarid) {
        this.shortcutbarid = shortcutbarid;
    }

    public String getShortcutbarname() {
        return shortcutbarname;
    }

    public void setShortcutbarname(String shortcutbarname) {
        this.shortcutbarname = shortcutbarname == null ? null : shortcutbarname.trim();
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

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname == null ? null : deptname.trim();
    }

    public String getPrivgroupid() {
        return privgroupid;
    }

    public void setPrivgroupid(String privgroupid) {
        this.privgroupid = privgroupid == null ? null : privgroupid.trim();
    }

    public String getOdeptid() {
        return odeptid;
    }

    public void setOdeptid(String odeptid) {
        this.odeptid = odeptid == null ? null : odeptid.trim();
    }

    public String getOdeptname() {
        return odeptname;
    }

    public void setOdeptname(String odeptname) {
        this.odeptname = odeptname == null ? null : odeptname.trim();
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}