package com.icbc.zsyw.hope3.dto;

public class HopeComments extends HopeCommentsKey {
    private Integer comments;

    private String description;

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