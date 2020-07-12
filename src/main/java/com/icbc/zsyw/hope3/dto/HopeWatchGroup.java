package com.icbc.zsyw.hope3.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
//@Entity
public class HopeWatchGroup {
  //  @Id
    private String watchgroupid;

    private Integer privtype;

    private String aamid;

    private String deptid;

    public String getWatchgroupid() {
        return watchgroupid;
    }

    public void setWatchgroupid(String watchgroupid) {
        this.watchgroupid = watchgroupid == null ? null : watchgroupid.trim();
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
}