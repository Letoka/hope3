package com.icbc.zsyw.hope3.enums;

/**
 * @ClassName ActivityClassEnum
 * @Description
 * @Author qinwankang
 * @Date 2020/6/2 16:28
 * @Version V1.0
 **/
public enum  ActivityClassEnum {
    anlifenxiang(1, "案例分享"),
    jishushangxin(2, "技术上新"),
    shitushangxin(3, "视图上新");
    private Integer  key;

    private String  value;

    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    ActivityClassEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }
}
