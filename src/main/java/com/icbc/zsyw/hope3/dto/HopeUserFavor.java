package com.icbc.zsyw.hope3.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
//@Entity
public class HopeUserFavor {
   // @Id
    private Integer favorid;

    private String aamid;

    private Integer favortype;

    private Integer moduleid;

    private Integer activityid;

    private Integer modulesequence;

    private Date activitysequence;

    public Integer getFavorid() {
        return favorid;
    }

    public void setFavorid(Integer favorid) {
        this.favorid = favorid;
    }

    public String getAamid() {
        return aamid;
    }

    public void setAamid(String aamid) {
        this.aamid = aamid == null ? null : aamid.trim();
    }

    public Integer getFavortype() {
        return favortype;
    }

    public void setFavortype(Integer favortype) {
        this.favortype = favortype;
    }

    public Integer getModuleid() {
        return moduleid;
    }

    public void setModuleid(Integer moduleid) {
        this.moduleid = moduleid;
    }

    public Integer getActivityid() {
        return activityid;
    }

    public void setActivityid(Integer activityid) {
        this.activityid = activityid;
    }

    public Integer getModulesequence() {
        return modulesequence;
    }

    public void setModulesequence(Integer modulesequence) {
        this.modulesequence = modulesequence;
    }

    public Date getActivitysequence() {
        return activitysequence;
    }

    public void setActivitysequence(Date activitysequence) {
        this.activitysequence = activitysequence;
    }
}