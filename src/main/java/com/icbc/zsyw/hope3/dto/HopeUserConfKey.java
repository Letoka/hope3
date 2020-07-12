package com.icbc.zsyw.hope3.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

//@Entity
public class HopeUserConfKey implements Serializable {
    private String aamid;

    private String confid;
//@Id
    public String getAamid() {
        return aamid;
    }

    public void setAamid(String aamid) {
        this.aamid = aamid == null ? null : aamid.trim();
    }
  //  @Id
    public String getConfid() {
        return confid;
    }

    public void setConfid(String confid) {
        this.confid = confid == null ? null : confid.trim();
    }
}