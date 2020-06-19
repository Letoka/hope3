package com.icbc.zsyw.hope3.service;

import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopeBroadcast;
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
 * @param hopeUserLog_h
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Integer>
* @Author: qinwankang
* @Date: 2020/5/21 16:55
*/
    BaseResponse<Integer> insertUserLog_h(HopeUserLog_h hopeUserLog_h);

}
