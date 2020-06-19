package com.icbc.zsyw.hope3.enums;

/**
 * @ClassName HopeModuleRequestEnum
 * @Description
 * @Author qinwankang
 * @Date 2020/5/20 23:03
 * @Version V1.0
 **/
public enum HopeModuleRequestEnum {
    moduleid("002001", true, "视图id参数为空"),

    modulename("002002", true, "视图名称参数为空"),

    shortname("002003", true, "视图简称参数为空"),

    description("002004", true, "视图描述参数为空"),

    icon("002005", true, "视图图标名称参数为空"),

    image("002006", true, "视图图片名称参数为空"),

    modulegroupname("002007", true, "视图分类属组参数为空"),

    url("002008", true, "视图url为空");

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

    HopeModuleRequestEnum(String returnCode, boolean isNotEmpty, String msg) {
        this.returnCode = returnCode;
        this.isNotEmpty = isNotEmpty;
        this.msg = msg;
    }
}
