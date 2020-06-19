package com.icbc.zsyw.hope3.enums;

import java.util.Date;
/**
 * @ClassName HopeviewImagebarPrivRequestEnum
 * @Description
 * @Author qinwankang
 * @Date 2020/5/20 10:46
 * @Version V1.0
 **/
public enum HopeImagebarRequestEnum {
    imagebarid("001001", true, "头图id参数为空"),

    icon("001002", true, "头图名称参数为空"),

    starttime("001009", true, "播放开始时间参数为空"),

    endtime("001010", true, "播放结束时间参数为空"),

    weight("001005", true, "头图顺序权重参数为空"),

    moduleurl("001008", true, "视图url参数为空");
    /**
    * 功能描述:请求参数校验返回码
     * @param null
    * @return: 
    * @Author: qinwankang
    * @Date: 2020/5/20 9:04
    */
    private String  returnCode;
    /**
    * 功能描述:请求参数是否必传，true为必传，false为非必传
     * @param null
    * @return: 
    * @Author: qinwankang
    * @Date: 2020/5/20 9:04
    */
    private boolean isNotEmpty;
    /**
    * 功能描述:请求参数校验返回信息
     * @param null
    * @return: 
    * @Author: qinwankang
    * @Date: 2020/5/20 9:04
    */
    private String  msg;

    public String getReturnCode() {
        return returnCode;
    }

    public boolean isNotEmpty() {
        return isNotEmpty;
    }

    public String getMsg() {
        return msg;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public void setNotEmpty(boolean notEmpty) {
        isNotEmpty = notEmpty;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    HopeImagebarRequestEnum(String returnCode, boolean isNotEmpty, String msg) {
        this.returnCode = returnCode;
        this.isNotEmpty = isNotEmpty;
        this.msg = msg;
    }
}
