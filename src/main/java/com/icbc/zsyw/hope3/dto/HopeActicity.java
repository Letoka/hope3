package com.icbc.zsyw.hope3.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;
//@Entity
public class HopeActicity {
   // @Id
    private Integer activityid;

    private String authorname;

    private String textname;

    private String textpath;

    private Integer textclass;

    private String imagename;

    private String imagepath;



    private Integer activitytype;

    private Date starttime;

    private Date endtime;

    private Integer showed;

    private String brief;



    @Transient//点赞量
    private Integer dianzanliang;
    @Transient//收藏量
    private Integer shoucangliang;
    @Transient//文章内容
    private String articleContent;


    public HopeActicity(){

    }

    public Integer getDianzanliang() {
        return dianzanliang;
    }

    public Integer getShoucangliang() {
        return shoucangliang;
    }

    public void setDianzanliang(Integer dianzanliang) {
        this.dianzanliang = dianzanliang;
    }

    public void setShoucangliang(Integer shoucangliang) {
        this.shoucangliang = shoucangliang;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    private String privgroupid;
    @Transient//默认false未点赞
    private boolean dianzan=false;
    @Transient//默认false未收藏
    private boolean shoucang=false;
    @Transient
    private Integer fangwenCount;
    @Transient//表示权限，0无权限，1有权限
    private Integer quanxianC=0;
    @Transient//关键字名称
    private String keyname;
    @Transient//关键字名称
    private Float weight;
    @Transient//1案例分享，2技术上新，3视图上新
    private String textclassName;

    public String getTextclassName() {
        return textclassName;
    }

    public void setTextclassName(String textclassName) {
        this.textclassName = textclassName;
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

    public Integer getQuanxianC() {
        return quanxianC;
    }

    public void setQuanxianC(Integer quanxianC) {
        this.quanxianC = quanxianC;
    }

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

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }
}