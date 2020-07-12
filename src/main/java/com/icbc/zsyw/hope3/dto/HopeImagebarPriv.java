package com.icbc.zsyw.hope3.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

//@Entity
public class HopeImagebarPriv {
  //  @Id
    private Integer imagebarprivid;

    private Integer privtype;

    private String aamid;

    private String deptid;

    private String privgroupid;

    public Integer getImagebarprivid() {
        return imagebarprivid;
    }

    public void setImagebarprivid(Integer imagebarprivid) {
        this.imagebarprivid = imagebarprivid;
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