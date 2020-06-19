package com.icbc.zsyw.hope3.dto;

public class HopeShortcutBarPriv {
    private Integer shortcutbarprivid;

    private String shortcutbarname;

    private Integer privtype;

    private String aamid;

    private String deptid;

    private String privgroupid;

    private String odeptid;

    public Integer getShortcutbarprivid() {
        return shortcutbarprivid;
    }

    public void setShortcutbarprivid(Integer shortcutbarprivid) {
        this.shortcutbarprivid = shortcutbarprivid;
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
}