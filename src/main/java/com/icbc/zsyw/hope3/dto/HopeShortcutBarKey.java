package com.icbc.zsyw.hope3.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

//@Entity
public class HopeShortcutBarKey implements Serializable {
 //   @Id
    private Integer shortcutbarid;

    private String shortcutbarname;

    private Integer moduleid;

    public Integer getShortcutbarid() {
        return shortcutbarid;
    }

    public void setShortcutbarid(Integer shortcutbarid) {
        this.shortcutbarid = shortcutbarid;
    }

    public String getShortcutbarname() {
        return shortcutbarname;
    }

    public void setShortcutbarname(String shortcutbarname) {
        this.shortcutbarname = shortcutbarname == null ? null : shortcutbarname.trim();
    }

    public Integer getModuleid() {
        return moduleid;
    }

    public void setModuleid(Integer moduleid) {
        this.moduleid = moduleid;
    }
}