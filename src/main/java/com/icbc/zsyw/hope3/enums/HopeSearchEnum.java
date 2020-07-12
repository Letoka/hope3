package com.icbc.zsyw.hope3.enums;

/**
 * @ClassName HopeSearchEnum
 * @Description
 * @Author qinwankang
 * @Date 2020/6/30 9:35
 * @Version V1.0
 **/
public enum HopeSearchEnum {
    chabiao("01", "checkChart"),
    keyname("02", "keyName"),
    module("03", "module"),
    article("04", "article");

    private String  key;

    private String  value;

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    HopeSearchEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
