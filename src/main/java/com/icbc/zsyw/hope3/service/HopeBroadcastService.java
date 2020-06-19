package com.icbc.zsyw.hope3.service;

import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopeBroadcast;
import com.icbc.zsyw.hope3.dto.HopeImagebar;
import com.icbc.zsyw.hope3.dto.TcenterLog;
import com.icbc.zsyw.hope3.impl.HopeBroadcastServiceImpl;

import java.util.List;

/**
 * @ClassName HopeBroadcastService
 * @Description
 * @Author qinwankang
 * @Date 2020/5/14 16:31
 * @Version V1.0
 **/
public interface HopeBroadcastService {
    HopeBroadcast checkUserSex(TcenterLog tcenterLog);
    /**
    * 功能描述:查询“掌上播报”
     * @param aamid
     * @param deptid
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeImagebar>>
    * @Author: qinwankang
    * @Date: 2020/5/20 16:05
    */
    BaseResponse<List<HopeBroadcast>> queryHopeBroadcast(String aamid, String deptid);
/**
* 功能描述:查询“掌上播报”详情
 * @param aamid
 * @param deptid
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeBroadcast>>
* @Author: qinwankang
* @Date: 2020/5/21 10:12
*/
    BaseResponse<List<HopeBroadcastServiceImpl.BroadcastNameResult>> queryHopeBroadcastDetail(String aamid, String deptid);
}
