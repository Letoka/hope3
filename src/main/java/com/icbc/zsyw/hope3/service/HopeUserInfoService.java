package com.icbc.zsyw.hope3.service;

import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopeUserInfo;

/**
 * @ClassName HopeUserInfoService
 * @Description
 * @Author qinwankang
 * @Date 2020/7/9 16:41
 * @Version V1.0
 **/
public interface HopeUserInfoService {
    /**
     * 功能描述:记录进入首页用户信息
     * @param
     * @param
     * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeBroadcast>>
     * @Author: qinwankang
     * @Date: 2020/5/22 10:53
     */
    BaseResponse<Integer> insertHopeUserInfo(HopeUserInfo hopeUserInfo);
}
