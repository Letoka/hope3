package com.icbc.zsyw.hope3.enums;

/**
 * @ClassName ModuleStatusEnum
 * @Description
 * @Author qinwankang
 * @Date 2020/5/29 11:25
 * @Version V1.0
 **/
public enum ModuleStatusEnum {
    yiguanzhu("01", "已关注"),
    weiguanzhu("02", "未关注");

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

    ModuleStatusEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
