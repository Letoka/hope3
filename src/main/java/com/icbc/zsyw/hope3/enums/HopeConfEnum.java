package com.icbc.zsyw.hope3.enums;

/**
 * @ClassName HopeConfEnum
 * @Description
 * @Author qinwankang
 * @Date 2020/7/27 14:23
 * @Version V1.0
 **/
public enum HopeConfEnum {
    jiuban(0, "掌上运维旧版"),
    xinban(1, "掌上运维新版"),
    xinbanceshi(2, "掌上运维新版测试");
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

    HopeConfEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }
}
