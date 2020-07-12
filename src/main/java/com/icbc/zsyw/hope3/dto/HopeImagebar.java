package com.icbc.zsyw.hope3.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
//@Entity
public class HopeImagebar {
   // @Id
    private Integer imagebarid;

    private String icon;

    private Date starttime;

    private Date endtime;

    private Integer weight;

    private Integer enabled;

    private Integer published;

    private Integer moduleid;

    private String imagebarurl;

    public Integer getImagebarid() {
        return imagebarid;
    }

    public void setImagebarid(Integer imagebarid) {
        this.imagebarid = imagebarid;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Integer getPublished() {
        return published;
    }

    public void setPublished(Integer published) {
        this.published = published;
    }

    public Integer getModuleid() {
        return moduleid;
    }

    public String getImagebarurl() {
        return imagebarurl;
    }

    public void setModuleid(Integer moduleid) {
        this.moduleid = moduleid;
    }

    public void setImagebarurl(String imagebarurl) {
        this.imagebarurl = imagebarurl;
    }
}