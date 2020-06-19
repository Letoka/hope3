package com.icbc.zsyw.hope3.impl;

import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopeBroadcast;
import com.icbc.zsyw.hope3.dto.HopeUserHistory;
import com.icbc.zsyw.hope3.dto.HopeUserLog_h;
import com.icbc.zsyw.hope3.enums.HopePrivRequestEnum;
import com.icbc.zsyw.hope3.enums.HopeUserFavorRequestEnum;
import com.icbc.zsyw.hope3.enums.HopeUserLog_hEnum;
import com.icbc.zsyw.hope3.mapper.HopeUserHistoryMapper;
import com.icbc.zsyw.hope3.mapper.HopeUserLog_hMapper;
import com.icbc.zsyw.hope3.service.HopeUserLog_hService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName HopeUserLog_hServiceImpl
 * @Description
 * @Author qinwankang
 * @Date 2020/5/21 16:16
 * @Version V1.0
 **/
@Service
public class HopeUserLog_hServiceImpl implements HopeUserLog_hService {
    @Resource
    private HopeUserLog_hMapper hopeUserLog_hMapper;
    @Resource
    private HopeUserHistoryMapper hopeUserHistoryMapper;
    /**
    * 功能描述:查询视图访问量
     * @param hopeUserLog_h
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Integer>
    * @Author: qinwankang
    * @Date: 2020/5/21 16:41
    */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<Integer> queryUserLog_h(HopeUserLog_h hopeUserLog_h) {
        //入参非空校验
        for (HopeUserFavorRequestEnum headCheckEnum : HopeUserFavorRequestEnum.values()) {
            if(headCheckEnum.isNotEmpty() && StringUtils.isEmpty(hopeUserLog_h.getModuleid()) && headCheckEnum.name().toString().equals("moduleid"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());
        }
        Integer count = hopeUserLog_hMapper.queryUserLog_h(hopeUserLog_h.getModuleid());
        return new BaseResponse<Integer>(BaseResponse.STATUS_HANDLE_SUCCESS,count,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
/**
* 功能描述:增加访问量，主要参数，用户id,视图id,访问时间；
 * @param hopeUserLog_h
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Integer>
* @Author: qinwankang
* @Date: 2020/5/21 16:56
*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<Integer> insertUserLog_h(HopeUserLog_h hopeUserLog_h) {
        //入参非空校验
        for (HopeUserFavorRequestEnum headCheckEnum : HopeUserFavorRequestEnum.values()) {
            if (headCheckEnum.isNotEmpty() && StringUtils.isEmpty(hopeUserLog_h.getAamid()) && headCheckEnum.name().toString().equals("aamid"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());
            if (headCheckEnum.isNotEmpty() && StringUtils.isEmpty(hopeUserLog_h.getModuleid()) && headCheckEnum.name().toString().equals("moduleid"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());
        }
        for (HopeUserLog_hEnum headCheckEnum : HopeUserLog_hEnum.values()) {
            if (headCheckEnum.isNotEmpty() && StringUtils.isEmpty(hopeUserLog_h.getLogtime()) && headCheckEnum.name().toString().equals("logtime"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());
        }
         hopeUserLog_hMapper.insert(hopeUserLog_h);
         HopeUserHistory hopeUserHistory = new HopeUserHistory();
         hopeUserHistory.setAamid(hopeUserLog_h.getAamid());
         hopeUserHistory.setLogtime(hopeUserLog_h.getLogtime());
         hopeUserHistory.setModuleid(hopeUserLog_h.getModuleid());
         hopeUserHistoryMapper.insert(hopeUserHistory);
         return new BaseResponse<>(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }

}
