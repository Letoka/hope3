package com.icbc.zsyw.hope3.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
//@Entity
public class HopeMedalInfo {
   // @Id
    private Integer medalid;

    private String medalname;

    private Integer medallevel;

    private String medaldescript;

    public Integer getMedalid() {
        return medalid;
    }

    public void setMedalid(Integer medalid) {
        this.medalid = medalid;
    }

    public String getMedalname() {
        return medalname;
    }

    public void setMedalname(String medalname) {
        this.medalname = medalname == null ? null : medalname.trim();
    }

    public Integer getMedallevel() {
        return medallevel;
    }

    public void setMedallevel(Integer medallevel) {
        this.medallevel = medallevel;
    }

    public String getMedaldescript() {
        return medaldescript;
    }

    public void setMedaldescript(String medaldescript) {
        this.medaldescript = medaldescript == null ? null : medaldescript.trim();
    }
}