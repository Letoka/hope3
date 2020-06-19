package com.icbc.zsyw.hope3.service;

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

}
