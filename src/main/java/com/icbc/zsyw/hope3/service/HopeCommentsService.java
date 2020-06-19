package com.icbc.zsyw.hope3.service;

import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopeBroadcast;
import com.icbc.zsyw.hope3.dto.HopeComments;

import java.util.List;

/**
 * @ClassName HopeCommentsService
 * @Description
 * @Author qinwankang
 * @Date 2020/5/22 10:51
 * @Version V1.0
 **/
public interface HopeCommentsService {
    /**
    * 功能描述:点赞+点踩
     * @param hopeComments
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeBroadcast>>
    * @Author: qinwankang
    * @Date: 2020/5/22 11:13
    */
    BaseResponse<List<HopeBroadcast>> insertHopeComments(HopeComments hopeComments);
}
