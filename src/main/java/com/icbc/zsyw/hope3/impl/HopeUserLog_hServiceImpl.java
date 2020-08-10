package com.icbc.zsyw.hope3.impl;

import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.*;
import com.icbc.zsyw.hope3.enums.HopeModuleRequestEnum;
import com.icbc.zsyw.hope3.enums.HopePrivRequestEnum;
import com.icbc.zsyw.hope3.enums.HopeUserFavorRequestEnum;
import com.icbc.zsyw.hope3.enums.HopeUserLog_hEnum;
import com.icbc.zsyw.hope3.mapper.HopeUserHistoryMapper;
import com.icbc.zsyw.hope3.mapper.HopeUserLogMapper;
import com.icbc.zsyw.hope3.mapper.HopeUserLog_hMapper;
import com.icbc.zsyw.hope3.mapper.HopeViewTimesMapper;
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
    @Resource
    private HopeViewTimesMapper hopeViewTimesMapper;
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
        //新增hopeviewtimes表
        Integer vTimes = hopeViewTimesMapper.queryTimeByaaAndmoid(hopeUserLog.getAamid(),hopeUserLog.getModuleid());
        if(vTimes==null){
            HopeViewTimes hopeViewTimes = new HopeViewTimes();
            hopeViewTimes.setAamid(hopeUserLog.getAamid());
            hopeViewTimes.setLastviewtime(hopeUserLog.getLogtime());
            hopeViewTimes.setModuleid(hopeUserLog.getModuleid());
            hopeViewTimes.setViewtimes(1);
            hopeViewTimesMapper.insert(hopeViewTimes);
        }else{
            Integer reVtimes=vTimes+1;
            hopeViewTimesMapper.updateViewTimes(reVtimes,hopeUserLog.getLogtime(),hopeUserLog.getAamid(),hopeUserLog.getModuleid());
        }

  //新增我的足迹
//查询该用户的足迹，如果足迹超过20条，则删除最早的足迹
        List<HopeUserHistory>userHistories= hopeUserHistoryMapper.queryFootByAamid(hopeUserLog.getAamid());
        if(userHistories!=null&&userHistories.size()>19){
            hopeUserHistoryMapper.deleteMinFoot(hopeUserLog.getAamid(),userHistories.get(0).getLogtime());
        }
         List<Integer>hopeUserHistories=   hopeUserHistoryMapper.queryByaamidAndModuleid(hopeUserLog.getAamid(),hopeUserLog.getModuleid());
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
    /*
    * 功能描述:往userlog,userlog_h添加用户访问视图记录，往userhistory添加足迹，viewtimes记录访问量
     * @param hopeUserLog
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Integer>
    * @Author: qinwankang
    * @Date: 2020/8/8 17:51
    */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<Integer> insertUserLog_hV2(HopeUserLog hopeUserLog) {
        //入参校验
        if (StringUtils.isEmpty(hopeUserLog.getAamid()) )
            return new BaseResponse<>(HopeUserFavorRequestEnum.aamid.getReturnCode(), null, HopeUserFavorRequestEnum.aamid.getMsg());
        if (hopeUserLog.getModuleid()==null)
            return new BaseResponse<>(HopeUserFavorRequestEnum.moduleid.getReturnCode(), null, HopeUserFavorRequestEnum.moduleid.getMsg());
        if (HopeUserLog_hEnum.logtime.isNotEmpty() && hopeUserLog.getLogtime()==null )
            return new BaseResponse<>(HopeUserLog_hEnum.logtime.getReturnCode(), null, HopeUserLog_hEnum.logtime.getMsg());
      //往userlog表里面添加记录
       try {
           hopeUserLogMapper.insert(hopeUserLog);
       }catch (Exception e){
           return new BaseResponse<>(BaseResponse.STATUS_SYSTEM_FAILURE, null,BaseResponse.STATUS_SYSTEM_FAILUREE);
       }
       //往userlog_h表添加记录
        HopeUserLog_h hopeUserLog_h = new HopeUserLog_h();
        hopeUserLog_h.setAamid(hopeUserLog.getAamid());
        hopeUserLog_h.setLogtime(hopeUserLog.getLogtime());
        hopeUserLog_h.setModuleid(hopeUserLog.getModuleid());
        try {
            hopeUserLog_hMapper.insert(hopeUserLog_h);
        }catch (Exception e){
            return new BaseResponse<>(BaseResponse.STATUS_SYSTEM_FAILURE, null,BaseResponse.STATUS_SYSTEM_FAILUREE);
        }
        //新增hopeviewtimes表
        Integer vTimes = hopeViewTimesMapper.queryTimeByaaAndmoid(hopeUserLog.getAamid(),hopeUserLog.getModuleid());
        if(vTimes==null){
            HopeViewTimes hopeViewTimes = new HopeViewTimes();
            hopeViewTimes.setAamid(hopeUserLog.getAamid());
            hopeViewTimes.setLastviewtime(hopeUserLog.getLogtime());
            hopeViewTimes.setModuleid(hopeUserLog.getModuleid());
            hopeViewTimes.setViewtimes(1);
            try {
                hopeViewTimesMapper.insert(hopeViewTimes);
            }catch (Exception e){
                return new BaseResponse<>(BaseResponse.STATUS_SYSTEM_FAILURE, null,BaseResponse.STATUS_SYSTEM_FAILUREE);
            }
        }else{
            Integer reVtimes=vTimes+1;
            try {
                hopeViewTimesMapper.updateViewTimes(reVtimes,hopeUserLog.getLogtime(),hopeUserLog.getAamid(),hopeUserLog.getModuleid());
            }catch (Exception e){
                return new BaseResponse<>(BaseResponse.STATUS_SYSTEM_FAILURE, null,BaseResponse.STATUS_SYSTEM_FAILUREE);
            }
        }
        //往userhistory表添加记录
        //首先查询userhistory某用户足迹是否不小于20条记录，如果是这样，则删除时间最早一条记录
        List<HopeUserHistory>userHistories= hopeUserHistoryMapper.queryFootByAamid(hopeUserLog.getAamid());
        if(userHistories!=null&&userHistories.size()>19){
            try {
                hopeUserHistoryMapper.deleteMinFoot(hopeUserLog.getAamid(),userHistories.get(0).getLogtime());
            }catch (Exception e){
                return new BaseResponse<>(BaseResponse.STATUS_SYSTEM_FAILURE, null,BaseResponse.STATUS_SYSTEM_FAILUREE);
            }
        }
        List<Integer>hopeUserHistories=   hopeUserHistoryMapper.queryByaamidAndModuleid(hopeUserLog.getAamid(),hopeUserLog.getModuleid());
        if(hopeUserHistories!=null || hopeUserHistories.size()!=0){
            hopeUserHistoryMapper.updateUserHistory(hopeUserLog.getAamid(),hopeUserLog.getModuleid(),hopeUserLog.getLogtime());
        }
        if(hopeUserHistories==null || hopeUserHistories.size()==0){
            HopeUserHistory hopeUserHistory = new HopeUserHistory();
            hopeUserHistory.setAamid(hopeUserLog.getAamid());
            hopeUserHistory.setModuleid(hopeUserLog.getModuleid());
            hopeUserHistory.setLogtime(hopeUserLog.getLogtime());
            try {
                hopeUserHistoryMapper.insert(hopeUserHistory);
            }catch (Exception e){
                return new BaseResponse<>(BaseResponse.STATUS_SYSTEM_FAILURE, null,BaseResponse.STATUS_SYSTEM_FAILUREE);
            }
        }

        return new BaseResponse<>(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
/**
* 功能描述:往userlog,userlog_h添加记录，首页-1，消息-2，发现-3，推荐-4，三大块，第一块-5，第二块-6，第三块-7
 * @param hopeUserLog
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Integer>
* @Author: qinwankang
* @Date: 2020/8/9 0:17
*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<Integer> insertFourChV2(HopeUserLog hopeUserLog) {
        //入参校验
        BaseResponse checkresponse=checkFourPara(hopeUserLog);
        if(!checkresponse.getStatus().equals(BaseResponse.STATUS_HANDLE_SUCCESS)||!checkresponse.getMessage().equals(BaseResponse.STATUS_HANDLER_SUCCESS))
            return checkresponse;
        //往userlog表记录-1，-2，-3，-4，-5，-6，-7
        try {
            hopeUserLogMapper.insert(hopeUserLog);
        }catch (Exception e){
            return new BaseResponse<>(BaseResponse.STATUS_SYSTEM_FAILURE, null,BaseResponse.STATUS_SYSTEM_FAILUREE);
        }
        //往userlog_h表记录-1，-2，-3，-4，-5，-6，-7
        HopeUserLog_h h = new HopeUserLog_h();
        h.setModuleid(hopeUserLog.getModuleid());
        h.setLogtime(hopeUserLog.getLogtime());
        h.setAamid(hopeUserLog.getAamid());
        try {
            hopeUserLog_hMapper.insert(h);
        }catch (Exception e){
            return new BaseResponse<>(BaseResponse.STATUS_SYSTEM_FAILURE, null,BaseResponse.STATUS_SYSTEM_FAILUREE);
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
        //新增我的足迹
//查询该用户的足迹，如果足迹超过20条，则删除最早的足迹
      /*  List<HopeUserHistory>userHistories= hopeUserHistoryMapper.queryFootByAamid(hopeUserLog.getAamid());
        if(userHistories!=null&&userHistories.size()>19){
            hopeUserHistoryMapper.deleteMinFoot(hopeUserLog.getAamid(),userHistories.get(0).getLogtime());
        }
        List<Integer>hopeUserHistories=   hopeUserHistoryMapper.queryByaamidAndModuleid(hopeUserLog.getAamid(),hopeUserLog.getModuleid());
        if(hopeUserHistories!=null || hopeUserHistories.size()!=0){
            hopeUserHistoryMapper.updateUserHistory(hopeUserLog.getAamid(),hopeUserLog.getModuleid(),hopeUserLog.getLogtime());
        }
        if(hopeUserHistories==null || hopeUserHistories.size()==0){
            HopeUserHistory hopeUserHistory = new HopeUserHistory();
            hopeUserHistory.setAamid(hopeUserLog.getAamid());
            hopeUserHistory.setModuleid(hopeUserLog.getModuleid());
            hopeUserHistory.setLogtime(hopeUserLog.getLogtime());
            hopeUserHistoryMapper.insert(hopeUserHistory);
        }*/
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
