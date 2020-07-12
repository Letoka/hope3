package com.icbc.zsyw.hope3.enums;

/**
 * @ClassName HopeModuleTypeEnum
 * @Description
 * @Author qinwankang
 * @Date 2020/6/23 15:16
 * @Version V1.0
 **/
public enum HopeModuleTypeEnum {
    yihang("1","一行视图"),
    fenzu("0", "分组视图");


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

    HopeModuleTypeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
