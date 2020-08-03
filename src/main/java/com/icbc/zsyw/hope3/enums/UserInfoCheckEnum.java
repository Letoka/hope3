package com.icbc.zsyw.hope3.enums;

/**
 * @ClassName UserInfoCheckEnum
 * @Description
 * @Author qinwankang
 * @Date 2020/7/31 15:25
 * @Version V1.0
 **/
public enum UserInfoCheckEnum {
    newNo(0, "没有新手指导"),
    newExsit(1, "有新手指导");
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

    UserInfoCheckEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }
}
