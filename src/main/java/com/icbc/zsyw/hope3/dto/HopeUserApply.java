package com.icbc.zsyw.hope3.dto;

public class HopeUserApply extends HopeUserApplyKey {
    private String message;

    private Integer privinfo;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public Integer getPrivinfo() {
        return privinfo;
    }

    public void setPrivinfo(Integer privinfo) {
        this.privinfo = privinfo;
    }
}