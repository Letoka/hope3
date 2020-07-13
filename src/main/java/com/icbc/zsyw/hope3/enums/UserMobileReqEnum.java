package com.icbc.zsyw.hope3.enums;

/**
 * @ClassName UserMobileReqEnum
 * @Description
 * @Author qinwankang
 * @Date 2020/7/12 11:06
 * @Version V1.0
 **/
public enum UserMobileReqEnum {
    mobileNumCount("011001", true, "手机号码位数不正确"),

    mobileNumStyle("011002", true, "手机号码含有不合法数字，请使用阿拉伯数字"),

    mobileNumType("011003", true, "手机号码格式不正确，请使用正确手机号码格式"),
    officemobileNumCount("011004", true, "办公室电话号码位数不正确"),

    officemobileNumStyle("011005", true, "办公室电话号码含有不合法数字，请使用阿拉伯数字");

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

    UserMobileReqEnum(String returnCode, boolean isNotEmpty, String msg) {
        this.returnCode = returnCode;
        this.isNotEmpty = isNotEmpty;
        this.msg = msg;
    }
}
