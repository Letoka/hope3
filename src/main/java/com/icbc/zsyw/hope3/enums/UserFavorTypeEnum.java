package com.icbc.zsyw.hope3.enums;

/**
 * @ClassName ModuleTypeEnum
 * @Description
 * @Author qinwankang
 * @Date 2020/6/2 10:28
 * @Version V1.0
 **/
public enum UserFavorTypeEnum {
    shitu(0, "视图"),
    wenzhang(1, "文章");
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

    UserFavorTypeEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }
}
