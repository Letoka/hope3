package com.icbc.zsyw.hope3.enums;

/**
 * @ClassName HopeCommentsEnum
 * @Description
 * @Author qinwankang
 * @Date 2020/5/22 14:35
 * @Version V1.0
 **/
public enum HopeCommentsEnum {
    dianzan(1,"点赞"),
    diancai(0,"点踩"),
    guanzhu(2,"关注");
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

    HopeCommentsEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }
}
