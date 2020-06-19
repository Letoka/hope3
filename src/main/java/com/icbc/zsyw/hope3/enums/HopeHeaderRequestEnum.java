package com.icbc.zsyw.hope3.enums;

/**
 * @ClassName HopeHeaderRequestEnum
 * @Description
 * @Author qinwankang
 * @Date 2020/5/26 9:27
 * @Version V1.0
 **/
public enum HopeHeaderRequestEnum {

    apiCode("1", true, "接口编号参数为空"),
    apiToken("2", true, "接口token参数为空"),
    apiNoToken("3", true, "系统接口token不存在，请重新发起请求！"),
    apiTokenFalse("4", true, "接口token参数错误"),
    version("5", true, "版本号参数为空"),
    versionFalse("6", true, "版本号参数错误");
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

    HopeHeaderRequestEnum(String returnCode, boolean isNotEmpty, String msg) {
        this.returnCode = returnCode;
        this.isNotEmpty = isNotEmpty;
        this.msg = msg;
    }
}
