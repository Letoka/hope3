package com.icbc.zsyw.hope3.enums;

/**
 * @ClassName UserInfoEnum
 * @Description
 * @Author qinwankang
 * @Date 2020/7/31 20:32
 * @Version V1.0
 **/
public enum  UserInfoEnum {
    firstLog(0, "首次登录"),
    firstLogNo(1, "非首次登录");
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

    UserInfoEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }
}
