package com.icbc.zsyw.hope3.dto;

public class HopeComments extends HopeCommentsKey {
    private Integer comments;

    private String description;
//用来记录每张视图对应点赞量
    private Integer modcont;

    public Integer getModcont() {
        return modcont;
    }

    public void setModcont(Integer modcont) {
        this.modcont = modcont;
    }

    public HopeComments(){

    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}