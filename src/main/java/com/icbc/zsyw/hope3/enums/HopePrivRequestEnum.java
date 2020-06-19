package com.icbc.zsyw.hope3.enums;

/**
 * @ClassName HopePrivRequestEnum
 * @Description
 * @Author qinwankang
 * @Date 2020/5/20 22:35
 * @Version V1.0
 **/
public enum HopePrivRequestEnum{
    aamid("001003", true, "用户id参数为空"),
    deptid("001004", true, "用户部门id参数为空"),
    logtime("001005",true,"我的足迹视图访问时间参数为空"),
    odeptid("001006", true, "用户部门的上级部门id参数为空"),
    shortcutbarname("001007", true, "三大块名称参数为空"),
    moduleid("002001", true, "视图id参数为空");
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

    HopePrivRequestEnum(String returnCode, boolean isNotEmpty, String msg) {
        this.returnCode = returnCode;
        this.isNotEmpty = isNotEmpty;
        this.msg = msg;
    }
}
