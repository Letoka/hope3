package com.icbc.zsyw.hope3.dto;

public class HopeModuleGroup {
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