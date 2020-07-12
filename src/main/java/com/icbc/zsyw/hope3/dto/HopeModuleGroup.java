package com.icbc.zsyw.hope3.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

//@Entity
public class HopeModuleGroup {
   // @Id
    private String modulegroupname;

    private Integer moduleid;

    private Integer sequence;

    public String getModulegroupname() {
        return modulegroupname;
    }

    public void setModulegroupname(String modulegroupname) {
        this.modulegroupname = modulegroupname == null ? null : modulegroupname.trim();
    }

    public Integer getModuleid() {
        return moduleid;
    }

    public void setModuleid(Integer moduleid) {
        this.moduleid = moduleid;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}