package com.icbc.zsyw.hope3.dto;

import javax.persistence.Transient;
import java.util.Date;

public class HopeActicity {
    private Integer activityid;

    private String authorname;

    private String textname;

    private String textpath;

    private Integer textclass;

    private String imagename;

    private String imagepath;

    private Date timeori;

    private Integer activitytype;

    private Date starttime;

    private Date endtime;

    private Integer showed;

    private String privgroupid;
    @Transient//默认false未点赞
    private boolean dianzan=false;
    @Transient//默认false未收藏
    private boolean shoucang=false;
    @Transient
    private Integer fangwenCount;

    public boolean isDianzan() {
        return dianzan;
    }

    public boolean isShoucang() {
        return shoucang;
    }

    public Integer getFangwenCount() {
        return fangwenCount;
    }

    public void setDianzan(boolean dianzan) {
        this.dianzan = dianzan;
    }

    public void setShoucang(boolean shoucang) {
        this.shoucang = shoucang;
    }

    public void setFangwenCount(Integer fangwenCount) {
        this.fangwenCount = fangwenCount;
    }

    public Integer getActivityid() {
        return activityid;
    }

    public void setActivityid(Integer activityid) {
        this.activityid = activityid;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname == null ? null : authorname.trim();
    }

    public String getTextname() {
        return textname;
    }

    public void setTextname(String textname) {
        this.textname = textname == null ? null : textname.trim();
    }

    public String getTextpath() {
        return textpath;
    }

    public void setTextpath(String textpath) {
        this.textpath = textpath == null ? null : textpath.trim();
    }

    public Integer getTextclass() {
        return textclass;
    }

    public void setTextclass(Integer textclass) {
        this.textclass = textclass;
    }

    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename == null ? null : imagename.trim();
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath == null ? null : imagepath.trim();
    }

    public Date getTimeori() {
        return timeori;
    }

    public void setTimeori(Date timeori) {
        this.timeori = timeori;
    }

    public Integer getActivitytype() {
        return activitytype;
    }

    public void setActivitytype(Integer activitytype) {
        this.activitytype = activitytype;
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

    public Integer getShowed() {
        return showed;
    }

    public void setShowed(Integer showed) {
        this.showed = showed;
    }

    public String getPrivgroupid() {
        return privgroupid;
    }

    public void setPrivgroupid(String privgroupid) {
        this.privgroupid = privgroupid == null ? null : privgroupid.trim();
    }
}