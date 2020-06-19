package com.icbc.zsyw.hope3.dto;

import java.util.Date;

public class HopeImagebar {
    private Integer imagebarid;

    private String icon;

    private Date starttime;

    private Date endtime;

    private Integer weight;

    private Integer enabled;

    private Integer published;

    private String moduleurl;

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

    public String getModuleurl() {
        return moduleurl;
    }

    public void setModuleurl(String moduleurl) {
        this.moduleurl = moduleurl == null ? null : moduleurl.trim();
    }
}