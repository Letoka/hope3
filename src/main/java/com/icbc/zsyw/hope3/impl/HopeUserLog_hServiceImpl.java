package com.icbc.zsyw.hope3.impl;

import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopeBroadcast;
import com.icbc.zsyw.hope3.dto.HopeUserHistory;
import com.icbc.zsyw.hope3.dto.HopeUserLog;
import com.icbc.zsyw.hope3.dto.HopeUserLog_h;
import com.icbc.zsyw.hope3.enums.HopeModuleRequestEnum;
import com.icbc.zsyw.hope3.enums.HopePrivRequestEnum;
import com.icbc.zsyw.hope3.enums.HopeUserFavorRequestEnum;
import com.icbc.zsyw.hope3.enums.HopeUserLog_hEnum;
import com.icbc.zsyw.hope3.mapper.HopeUserHistoryMapper;
import com.icbc.zsyw.hope3.mapper.HopeUserLogMapper;
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
    private HopeUserLogMapper hopeUserLogMapper;
    @Resource
    private HopeUserHistoryMapper hopeUserHistoryMapper;
    @Resource
    private HopeUserLog_hMapper hopeUserLog_hMapper;
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
        //for (HopeUserFavorRequestEnum headCheckEnum : HopeUserFavorRequestEnum.values()) {
            if(HopeUserFavorRequestEnum.moduleid.isNotEmpty() && StringUtils.isEmpty(hopeUserLog_h.getModuleid()) )
                return new BaseResponse<>(HopeUserFavorRequestEnum.moduleid.getReturnCode(), null, HopeUserFavorRequestEnum.moduleid.getMsg());
        //}
        Integer count = hopeUserLogMapper.queryUserLog_h(hopeUserLog_h.getModuleid());
        return new BaseResponse<Integer>(BaseResponse.STATUS_HANDLE_SUCCESS,count,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
/**
* 功能描述:增加访问量，主要参数，用户id,视图id,访问时间；
 * @param hopeUserLog
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Integer>
* @Author: qinwankang
* @Date: 2020/5/21 16:56
*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<Integer> insertUserLog_h(HopeUserLog hopeUserLog) {
        //入参非空校验
       // for (HopeUserFavorRequestEnum headCheckEnum : HopeUserFavorRequestEnum.values()) {
            if (HopeUserFavorRequestEnum.aamid.isNotEmpty() && StringUtils.isEmpty(hopeUserLog.getAamid()) )
                return new BaseResponse<>(HopeUserFavorRequestEnum.aamid.getReturnCode(), null, HopeUserFavorRequestEnum.aamid.getMsg());
            if (HopeUserFavorRequestEnum.moduleid.isNotEmpty() && hopeUserLog.getModuleid()==null)
                return new BaseResponse<>(HopeUserFavorRequestEnum.moduleid.getReturnCode(), null, HopeUserFavorRequestEnum.moduleid.getMsg());
      //  }
       // for (HopeUserLog_hEnum headCheckEnum : HopeUserLog_hEnum.values()) {
            if (HopeUserLog_hEnum.logtime.isNotEmpty() && hopeUserLog.getLogtime()==null )
                return new BaseResponse<>(HopeUserLog_hEnum.logtime.getReturnCode(), null, HopeUserLog_hEnum.logtime.getMsg());
       // }
        hopeUserLogMapper.insert(hopeUserLog);
        //新增hopeUserLog_h表
        HopeUserLog_h hopeUserLog_h = new HopeUserLog_h();
        hopeUserLog_h.setAamid(hopeUserLog.getAamid());
        hopeUserLog_h.setLogtime(hopeUserLog.getLogtime());
        hopeUserLog_h.setModuleid(hopeUserLog.getModuleid());
        hopeUserLog_hMapper.insert(hopeUserLog_h);
  //新增我的足迹

         List<HopeUserHistory>hopeUserHistories=   hopeUserHistoryMapper.queryByaamidAndModuleid(hopeUserLog.getAamid(),hopeUserLog.getModuleid());
         if(hopeUserHistories!=null || hopeUserHistories.size()!=0){
             hopeUserHistoryMapper.updateUserHistory(hopeUserLog.getAamid(),hopeUserLog.getModuleid(),hopeUserLog.getLogtime());
         }
         if(hopeUserHistories==null || hopeUserHistories.size()==0){
             HopeUserHistory hopeUserHistory = new HopeUserHistory();
             hopeUserHistory.setAamid(hopeUserLog.getAamid());
             hopeUserHistory.setModuleid(hopeUserLog.getModuleid());
             hopeUserHistory.setLogtime(hopeUserLog.getLogtime());
             hopeUserHistoryMapper.insert(hopeUserHistory);
         }

         return new BaseResponse<>(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
/**
* 功能描述:记录用户点击四大项（首页-1，消息-2，发现-3，我的-4）
 * @param hopeUserLog
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Integer>
* @Author: qinwankang
* @Date: 2020/7/9 16:15
*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<Integer> insertFourCh(HopeUserLog hopeUserLog) {
        //入参校验
     BaseResponse checkresponse=checkFourPara(hopeUserLog);
     if(!checkresponse.getStatus().equals(BaseResponse.STATUS_HANDLE_SUCCESS)||!checkresponse.getMessage().equals(BaseResponse.STATUS_HANDLER_SUCCESS))
        return checkresponse;
     hopeUserLogMapper.insert(hopeUserLog);
     HopeUserLog_h h = new HopeUserLog_h();
     h.setModuleid(hopeUserLog.getModuleid());
     h.setLogtime(hopeUserLog.getLogtime());
     h.setAamid(hopeUserLog.getAamid());
     hopeUserLog_hMapper.insert(h);
     return new BaseResponse<>(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }

    private BaseResponse checkFourPara(HopeUserLog hopeUserLog) {
        if(StringUtils.isEmpty(hopeUserLog.getAamid())){
            return new BaseResponse(HopePrivRequestEnum.aamid.getReturnCode(),HopePrivRequestEnum.aamid.getMsg());
        }
        if(StringUtils.isEmpty(hopeUserLog.getModuleid())){
            return new BaseResponse(HopeModuleRequestEnum.moduleid.getReturnCode(),HopeModuleRequestEnum.moduleid.getMsg());
        }
        if(StringUtils.isEmpty(hopeUserLog.getLogtime())){
            return new BaseResponse(HopePrivRequestEnum.logtime.getReturnCode(),HopePrivRequestEnum.logtime.getMsg());
        }
        return new BaseResponse(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }

}
