package com.icbc.zsyw.hope3.dto;

import javax.persistence.Transient;

public class HopeModule {
    private Integer moduleid;

    private String modulename;

    private Integer enabled;

    private String shortname;

    private String description;

    private String icon;

    private String image;

    private String modulegroupname;

    private String url;
    @Transient//表示视图关注状态，01已关注，02未关注；
    private String status;
    @Transient//表示排序权重
    private Integer sequence;
    private Integer useurltype;

    public Integer getUseurltype() {
        return useurltype;
    }

    public void setUseurltype(Integer useurltype) {
        this.useurltype = useurltype;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getModuleid() {
        return moduleid;
    }

    public void setModuleid(Integer moduleid) {
        this.moduleid = moduleid;
    }

    public String getModulename() {
        return modulename;
    }

    public void setModulename(String modulename) {
        this.modulename = modulename == null ? null : modulename.trim();
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname == null ? null : shortname.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getModulegroupname() {
        return modulegroupname;
    }

    public void setModulegroupname(String modulegroupname) {
        this.modulegroupname = modulegroupname == null ? null : modulegroupname.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}