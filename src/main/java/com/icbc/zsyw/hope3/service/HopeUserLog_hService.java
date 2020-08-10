package com.icbc.zsyw.hope3.service;

import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopeBroadcast;
import com.icbc.zsyw.hope3.dto.HopeUserLog;
import com.icbc.zsyw.hope3.dto.HopeUserLog_h;

import java.util.List;

/**
 * @ClassName HopeUserLog_hService
 * @Description
 * @Author qinwankang
 * @Date 2020/5/21 16:16
 * @Version V1.0
 **/
public interface HopeUserLog_hService {
    /**
    * 功能描述:查询视图访问量
     * @param hopeUserLog_h
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeBroadcast>>
    * @Author: qinwankang
    * @Date: 2020/5/21 16:24
    */
    BaseResponse<Integer> queryUserLog_h(HopeUserLog_h hopeUserLog_h);
/**
* 功能描述:增加访问量，主要参数，用户id,视图id,访问时间；
 * @param  hopeUserLog
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Integer>
* @Author: qinwankang
* @Date: 2020/5/21 16:55
*/
    BaseResponse<Integer> insertUserLog_h(HopeUserLog hopeUserLog);
/**
* 功能描述:记录用户点击四大项（首页-1，消息-2，发现-3，我的-4）
 * @param hopeUserLog
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Integer>
* @Author: qinwankang
* @Date: 2020/7/9 16:14
*/
    BaseResponse<Integer> insertFourCh(HopeUserLog hopeUserLog);
/*
* 功能描述:往userlog,userlog_h添加用户访问视图记录，往userhistory添加足迹，viewtimes记录访问量
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Integer>
* @Author: qinwankang
* @Date: 2020/8/8 17:50
*/
    BaseResponse<Integer> insertUserLog_hV2(HopeUserLog hopeUserLog);
/**
* 功能描述:往userlog,userlog_h添加记录，首页-1，消息-2，发现-3，推荐-4，三大块，第一块-5，第二块-6，第三块-7
 * @param hopeUserLog
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Integer>
* @Author: qinwankang
* @Date: 2020/8/9 0:16
*/
    BaseResponse<Integer> insertFourChV2(HopeUserLog hopeUserLog);
}
