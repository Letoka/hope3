package com.icbc.zsyw.hope3.service;

import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopeRecommend;

import java.util.List;

/**
 * @ClassName HopeRecommedService
 * @Description
 * @Author qinwankang
 * @Date 2020/7/22 21:28
 * @Version V1.0
 **/
public interface HopeRecommedService {
    /**
    * 功能描述:查询视图推荐
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Integer>
    * @Author: qinwankang
    * @Date: 2020/7/22 22:54
    */
    BaseResponse<List<HopeRecommend>> queryRecommedModule();
}
