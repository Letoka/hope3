package com.icbc.zsyw.hope3.dto;

import java.util.Date;

public class HopeActivityLogKey {
    private Integer activityid;

    private Date viewtime;

    public Integer getActivityid() {
        return activityid;
    }

    public void setActivityid(Integer activityid) {
        this.activityid = activityid;
    }

    public Date getViewtime() {
        return viewtime;
    }

    public void setViewtime(Date viewtime) {
        this.viewtime = viewtime;
    }
}