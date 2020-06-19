package com.icbc.zsyw.hope3.enums;

/**
 * @ClassName HopeActiviLogEnum
 * @Description
 * @Author qinwankang
 * @Date 2020/6/3 9:22
 * @Version V1.0
 **/
public enum  HopeActiviLogResEnum {
    activityid("007001", true, "文章id参数为空"),
    viewtime("007002", true, "文章点赞/访问时间参数为空"),
    liked("007003", true, "点赞/访问标志参数为空"),
    aamid("001003", true, "用户id参数为空");

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

    HopeActiviLogResEnum(String returnCode, boolean isNotEmpty, String msg) {
        this.returnCode = returnCode;
        this.isNotEmpty = isNotEmpty;
        this.msg = msg;
    }
}
