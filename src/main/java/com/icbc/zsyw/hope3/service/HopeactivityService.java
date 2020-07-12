package com.icbc.zsyw.hope3.service;

import com.alibaba.fastjson.JSONObject;
import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.*;
import com.icbc.zsyw.hope3.impl.HopeactivityServiceImpl;

import java.util.List;

/**
 * @ClassName HopeactivityService
 * @Description
 * @Author qinwankang
 * @Date 2020/6/2 9:10
 * @Version V1.0
 **/
public interface HopeactivityService {
    /**
    * 功能描述:发现页文章展示（分成案例培训，技术分享，视图上新三个模块进行）
     * @param  jsonObject
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeBroadcast>>
    * @Author: qinwankang
    * @Date: 2020/6/2 15:12
    */
    BaseResponse<List<HopeactivityServiceImpl.ActivityClass>> queryHopeActivity(JSONObject jsonObject);
/**
* 功能描述:发现页文章点赞功能
 * @param hopeActivityLog
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.impl.HopeactivityServiceImpl.ActivityClass>>
* @Author: qinwankang
* @Date: 2020/6/3 9:13
*/
    BaseResponse<List<HopeactivityServiceImpl.ActivityClass>> commendhopeActivity(HopeActivityLog hopeActivityLog);
/**
* 功能描述:发现页文章收藏功能
 * @param hopeUserFavor
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.impl.HopeactivityServiceImpl.ActivityClass>>
* @Author: qinwankang
* @Date: 2020/6/3 10:11
*/
    BaseResponse<List<HopeactivityServiceImpl.ActivityClass>> colletcthopeActivity(HopeUserFavor hopeUserFavor);
/**
* 功能描述:发现页点击文章增加访问量
 * @param hopeActivityLog
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.impl.HopeactivityServiceImpl.ActivityClass>>
* @Author: qinwankang
* @Date: 2020/6/3 11:00
*/
    BaseResponse<List<HopeactivityServiceImpl.ActivityClass>> clickhopeActivity(HopeActivityLog hopeActivityLog);
/**
* 功能描述:首页三大块点击第三块文章更新广告跳转到文章列表（该文章列表不用分类，按照发表时间降序排列）
 * @param
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeActicity>>
* @Author: qinwankang
* @Date: 2020/6/8 17:02
*/
    BaseResponse<List<HopeActicity>> getHopeActivitys();
/**
* 功能描述:发现页取消收藏功能
 * @param hopeUserFavor
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Object>
* @Author: qinwankang
* @Date: 2020/7/1 19:34
*/
    BaseResponse<Object> deleteCollActivi(HopeUserFavor hopeUserFavor);
}
