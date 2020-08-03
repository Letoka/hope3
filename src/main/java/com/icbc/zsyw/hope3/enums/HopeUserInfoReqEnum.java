package com.icbc.zsyw.hope3.enums;

/**
 * @ClassName HopeUserInfoReqEnum
 * @Description
 * @Author qinwankang
 * @Date 2020/7/9 22:15
 * @Version V1.0
 **/
public enum HopeUserInfoReqEnum {

    username("009001", true, "用户姓名参数为空"),
    deptname("009002", true, "用户部门名称参数为空"),
    odeptname("009002", true, "用户上级部门名称参数为空"),
    usermobile("009002", true, "用户电话参数为空"),
    useremail("009004", true, "用户邮箱参数为空"),
    tdeptname("009005", true, "tdeptname参数为空"),
    officephone("009006", true, "用户办公电话参数为空"),
    logtime("009007", true, "用户登录日期参数为空"),
    userpost("009008", true, "用户职级参数为空");


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

    HopeUserInfoReqEnum(String returnCode, boolean isNotEmpty, String msg) {
        this.returnCode = returnCode;
        this.isNotEmpty = isNotEmpty;
        this.msg = msg;
    }
}
