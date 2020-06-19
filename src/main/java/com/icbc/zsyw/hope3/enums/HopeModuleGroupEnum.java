package com.icbc.zsyw.hope3.enums;

/**
 * @ClassName HopeModuleGroupEnum
 * @Description
 * @Author qinwankang
 * @Date 2020/5/22 9:49
 * @Version V1.0
 **/
public enum HopeModuleGroupEnum {
    quanbu("01","全部"),
    xitong("02", "系统"),
    yingyong("03", "应用"),
    fenhang("04", "分行");

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

    HopeModuleGroupEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
