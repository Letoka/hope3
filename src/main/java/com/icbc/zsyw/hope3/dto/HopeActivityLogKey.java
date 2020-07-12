package com.icbc.zsyw.hope3.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
//@Entity
public class HopeActivityLogKey implements Serializable {
    private Integer activityid;

    private Date viewtime;
//@Id
    public Integer getActivityid() {
        return activityid;
    }

    public void setActivityid(Integer activityid) {
        this.activityid = activityid;
    }
//@Id
    public Date getViewtime() {
        return viewtime;
    }
    public HopeActivityLogKey(){

    }

    public void setViewtime(Date viewtime) {
        this.viewtime = viewtime;
    }
}