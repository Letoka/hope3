package com.icbc.zsyw.hope3.service;

import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopeImagebar;

import java.util.List;

/**
 * @ClassName HopeImagebarService
 * @Description
 * @Author qinwankang
 * @Date 2020/5/18 15:27
 * @Version V1.0
 **/
public interface HopeImagebarService {
    BaseResponse<List<HopeImagebar>> queryHopeImagebar(String aamid, String deptid,String odeptid);
    /**
    * 功能描述:点击头图返回对应视图
     * @param imagebarid
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.String>
    * @Author: qinwankang
    * @Date: 2020/5/19 15:21
    */
    BaseResponse<String> queryModuleUrl(Integer imagebarid);
}
