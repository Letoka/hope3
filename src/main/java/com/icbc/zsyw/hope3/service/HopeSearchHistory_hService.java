package com.icbc.zsyw.hope3.service;

import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopeSearchHistory;
import com.icbc.zsyw.hope3.dto.HopeSearchHistory_h;

import java.util.List;

/**
 * @ClassName HopeSearchHistory_hService
 * @Description
 * @Author qinwankang
 * @Date 2020/5/15 11:33
 * @Version V1.0
 **/
public interface HopeSearchHistory_hService {
/**
* 功能描述:热搜
 * @param
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeSearchHistory_h>>
* @Author: qinwankang
* @Date: 2020/5/18 17:53
*/
    //BaseResponse<List<HopeSearchHistory_h>> queryHotSearch(HopeSearchHistory_h hopeSearchHistory_h);

    BaseResponse<List<HopeSearchHistory>> queryHotSearch(HopeSearchHistory hopeSearchHistory);
}
