package com.icbc.zsyw.hope3.common;

/**
 * @ClassName BaseResponse
 * @Description 公共响应体
 * @Author qinwankang
 * @Date 2020/5/15 10:58
 * @Version V1.0
 **/
public class BaseResponse<T> {
    /**
     * @fieldName: STATUS_HANDLE_SUCCESS
     * @fieldType: String
     * @Description: 成功
     */
    public static final String STATUS_HANDLE_SUCCESS    = "000000";
    /**
     * @fieldName: STATUS_HANDLE_SUCCESS
     * @fieldType: String
     * @Description: 成功
     */
    public static final String STATUS_HANDLER_SUCCESS    = "成功";
    /**
     * @fieldName: STATUS_SYSTEM_FAILURE
     * @fieldType: String
     * @Description: 系统异常
     */
    public static final String STATUS_SYSTEM_FAILURE    = "999999";
    /**
     * @fieldName: STATUS_SYSTEM_FAILURE
     * @fieldType: String
     * @Description: 系统异常
     */
    public static final String STATUS_SYSTEM_FAILUREE    = "系统异常,提示：参数数据和数据库主键冲突或者别的异常";
    /**
     * @fieldName: DATA_STATUS_NULL
     * @fieldType: String
     * @Description: 无数据
     */
    public static final String DATA_STATUS_NULL    = "111111";
    /**
     * @fieldName: DATA_STATUS_NULL
     * @fieldType: String
     * @Description: 无数据
     */
    public static final String DATA_STATUS_NULLR    = "无数据";
    /**
     * @fieldName: DATA_STATUS_EXIST
     * @fieldType: String
     * @Description: 数据已存在
     */
    public static final String DATA_STATUS_EXIST    = "222222";
    /**
     * @fieldName: DATA_STATUS_EXISTER
     * @fieldType: String
     * @Description: 数据已存在
     */
    public static final String DATA_STATUS_EXISTER    = "数据已存在";
    /**
     * @fieldName: PARAMETER_EXIST
     * @fieldType: String
     * @Description: 参数有空
     */
    public static final String PARAMETER_EXIST    = "333333";
    /**
     * @fieldName: PARAMETER_EXISTR
     * @fieldType: String
     * @Description: 参数有空
     */
    public static final String PARAMETER__EXISTER    = "参数有空";
    /**
     * @fieldName: ALL_BLANK
     * @fieldType: String
     * @Description: 参数有空
     */
    public static final String ALL_BLANK    = "555555";
    /**
     * @fieldName: ALL_BLANKER
     * @fieldType: String
     * @Description: 参数有空
     */
    public static final String ALL_BLANKER    = "参数为空";

    /**
     * @fieldName: status
     * @fieldType: String
     * @Description: 响应状态码
     * @author: stable97
     */
    private String             status;
    /**
     * @fieldName: data
     * @fieldType: T
     * @Description: 业务参数
     * @author: stable97
     */
    private T                  data;
    /**
     * @fieldName: message
     * @fieldType: String
     * @Description: 响应信息
     * @author: stable97
     */
    private String             message;

    public BaseResponse() {
    }

    public BaseResponse(T data) {
        this(data, null);
    }

    public BaseResponse(T data, String message) {
        this(STATUS_HANDLE_SUCCESS, data, message);
    }

    public BaseResponse(String status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(String status, String message) {
        this(status, null, message);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
