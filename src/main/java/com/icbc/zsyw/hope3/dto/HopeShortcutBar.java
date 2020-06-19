package com.icbc.zsyw.hope3.dto;

public class HopeShortcutBar extends HopeShortcutBarKey {
    private String shortcutbardescript;

    private String shortcutImage;

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