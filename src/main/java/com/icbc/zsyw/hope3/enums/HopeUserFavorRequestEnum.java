package com.icbc.zsyw.hope3.enums;

/**
 * @ClassName HopeUserFavorRequestEnum
 * @Description
 * @Author qinwankang
 * @Date 2020/5/21 14:03
 * @Version V1.0
 **/
public enum HopeUserFavorRequestEnum {
    moduleid("002001", true, "视图id参数为空"),
    aamid("001003", true, "用户id参数为空"),
    modulesequence("003001",true,"视图顺序权重参数为空"),
    activityid("003002",true,"文章id参数为空"),
    activitysequence("003003",true,"文章顺序权重参数为空");


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

    HopeUserFavorRequestEnum(String returnCode, boolean isNotEmpty, String msg) {
        this.returnCode = returnCode;
        this.isNotEmpty = isNotEmpty;
        this.msg = msg;
    }
}
