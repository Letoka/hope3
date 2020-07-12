package com.icbc.zsyw.hope3.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
//@Entity
public class HopeUserLogdetail_h {
 //   @Id
    private Integer userlogdetailid;

    private String aamid;

    private String targetid;

    private String targetname;

    private String operation;

    private Date logtime;

    public Integer getUserlogdetailid() {
        return userlogdetailid;
    }

    public void setUserlogdetailid(Integer userlogdetailid) {
        this.userlogdetailid = userlogdetailid;
    }

    public String getAamid() {
        return aamid;
    }

    public void setAamid(String aamid) {
        this.aamid = aamid == null ? null : aamid.trim();
    }

    public String getTargetid() {
        return targetid;
    }

    public void setTargetid(String targetid) {
        this.targetid = targetid == null ? null : targetid.trim();
    }

    public String getTargetname() {
        return targetname;
    }

    public void setTargetname(String targetname) {
        this.targetname = targetname == null ? null : targetname.trim();
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
    }

    public Date getLogtime() {
        return logtime;
    }

    public void setLogtime(Date logtime) {
        this.logtime = logtime;
    }
}