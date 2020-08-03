package com.icbc.zsyw.hope3.enums;

/**
 * @ClassName ActivityClassEnum
 * @Description
 * @Author qinwankang
 * @Date 2020/6/2 16:28
 * @Version V1.0
 **/
public enum  ActivityClassEnum {
    anlifenxiang(0, "技术上新"),
    jishushangxin(1, "视图上新"),
    shitushangxin(2, "案例分享");
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
