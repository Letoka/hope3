package com.icbc.zsyw.hope3.service;

import com.icbc.zsyw.hope3.common.BaseResponse;

import java.util.List;

/**
 * @ClassName HopeConfService
 * @Description
 * @Author qinwankang
 * @Date 2020/7/3 17:01
 * @Version V1.0
 **/
public interface HopeConfService {
    /**
    * 功能描述:掌上运维首页加载获取url
     * @param
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.String>
    * @Author: qinwankang
    * @Date: 2020/7/4 8:44
    */
    BaseResponse<String> queryHopeConfUrl();
/**
* 功能描述:掌上运维首页加载获取对应用户
 * @param
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<java.lang.String>>
* @Author: qinwankang
* @Date: 2020/7/4 8:47
*/
    BaseResponse<String[]> queryHopeConfUser();
}
