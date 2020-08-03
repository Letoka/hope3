package com.icbc.zsyw.hope3.service;

import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopeUserApply;

/**
 * @ClassName HopeUserApplyService
 * @Description
 * @Author qinwankang
 * @Date 2020/7/22 10:04
 * @Version V1.0
 **/
public interface HopeUserApplyService {
   /**
    * * 功能描述:用户申请权限
    * @param hopeUserApply
   * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Object>
   * @Author: qinwankang
   * @Date: 2020/7/22 10:11
   */
    BaseResponse<Object> insertHopeUserApply(HopeUserApply hopeUserApply);
}
