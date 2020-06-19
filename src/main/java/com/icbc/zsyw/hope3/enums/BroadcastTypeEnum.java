package com.icbc.zsyw.hope3.enums;

/**
 * @ClassName BroadcastTypeEnum
 * @Description
 * @Author qinwankang
 * @Date 2020/5/21 10:23
 * @Version V1.0
 **/
public enum BroadcastTypeEnum {
    gonggao("01", "公告"),
    shangxin("02", "上新"),
    youhua("03", "优化"),
    tuiguang("04", "推广");

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

    BroadcastTypeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
