package com.icbc.zsyw.hope3.enums;

/**
 * @ClassName ActivityLogEnum
 * @Description
 * @Author qinwankang
 * @Date 2020/6/2 22:00
 * @Version V1.0
 **/
public enum ActivityLogEnum {
    dianzan(1, "点赞"),
    fangwenliang(0, "访问量");

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

    ActivityLogEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }
}
