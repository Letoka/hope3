package com.icbc.zsyw.hope3.service;

import com.alibaba.fastjson.JSONObject;
import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopeSearchHistory;

import java.util.List;

/**
 * @ClassName HopeSearchHistoryService
 * @Description
 * @Author qinwankang
 * @Date 2020/5/14 17:40
 * @Version V1.0
 **/
public interface HopeSearchHistoryService {
    /**
    * 功能描述:搜索记录
     * @param hopeSearchHistory
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeSearchHistory>>
    * @Author: qinwankang
    * @Date: 2020/5/18 17:49
    */
    BaseResponse<List<HopeSearchHistory>> querySearchRecord(HopeSearchHistory hopeSearchHistory);
/**
* 功能描述:用户搜索记录落库
 * @param hopeSearchHistory
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeSearchHistory>>
* @Author: qinwankang
* @Date: 2020/7/1 14:10
*/
    BaseResponse<List<HopeSearchHistory>> insertSearchRecord(HopeSearchHistory hopeSearchHistory);
/**
* 功能描述:用户删除搜索历史记录
 * @param jsonObject
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeSearchHistory>>
* @Author: qinwankang
* @Date: 2020/7/8 22:12
*/
    BaseResponse<List<HopeSearchHistory>> delSearchRecord(JSONObject jsonObject);
}
