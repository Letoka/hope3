package com.icbc.zsyw.hope3.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
//@Entity
public class HopeModule {
    //@Id
    private Integer moduleid;

    private String modulename;

    private Integer enabled;

    private String shortname;

    private String description;

    private String icon;

    private String image;

    private String modulegroupname;

    private String modulestatus;
    //hl.contentclass,hl.weight,hl.keyname
    private Float weight;

    private String url;
    @Transient//表示视图关注状态，01已关注，02未关注；
    private String wstatus;
    @Transient//表示排序权重
    private Integer sequence;
    @Transient//表示有无权限 0表示无权限，1表示有权限
    private Integer quanxianC=0;
    @Transient//关键字名称
    private String keyname;
    @Transient//视图访问量
    private Integer fangwenTel;
    @Transient//视图/文章分类区分
    private Integer contentclass;

    public Integer getContentclass() {
        return contentclass;
    }

    public void setContentclass(Integer contentclass) {
        this.contentclass = contentclass;
    }

    public String getModulestatus() {
        return modulestatus;
    }

    public void setModulestatus(String modulestatus) {
        this.modulestatus = modulestatus;
    }

    public Integer getFangwenTel() {
        return fangwenTel;
    }

    public void setFangwenTel(Integer fangwenTel) {
        this.fangwenTel = fangwenTel;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getKeyname() {
        return keyname;
    }

    public void setKeyname(String keyname) {
        this.keyname = keyname;
    }

    private Integer useurltype;

    public Integer getQuanxianC() {
        return quanxianC;
    }

    public void setQuanxianC(Integer quanxianC) {
        this.quanxianC = quanxianC;
    }

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

    public String getWstatus() {
        return wstatus;
    }

    public void setWstatus(String wstatus) {
        this.wstatus = wstatus;
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