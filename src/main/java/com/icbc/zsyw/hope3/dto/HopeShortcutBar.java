package com.icbc.zsyw.hope3.dto;

import org.springframework.data.relational.core.sql.In;

import javax.persistence.Transient;
import java.util.List;

public class HopeShortcutBar extends HopeShortcutBarKey {
    private String shortcutbardescript;

    private String shortcutImage;
    @Transient//用来装moduleid
    private List<Integer> list;
    @Transient//用来装moduleurl
    private String shortModuleUrl;
    @Transient//用来装moduleurl
    private Integer weight;

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getShortModuleUrl() {
        return shortModuleUrl;
    }

    public void setShortModuleUrl(String shortModuleUrl) {
        this.shortModuleUrl = shortModuleUrl;
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

    public String getShortcutbardescript() {
        return shortcutbardescript;
    }

    public void setShortcutbardescript(String shortcutbardescript) {
        this.shortcutbardescript = shortcutbardescript == null ? null : shortcutbardescript.trim();
    }

    public String getShortcutImage() {
        return shortcutImage;
    }

    public void setShortcutImage(String shortcutImage) {
        this.shortcutImage = shortcutImage == null ? null : shortcutImage.trim();
    }
}