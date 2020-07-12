package com.icbc.zsyw.hope3.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopeSearchHistory;
import com.icbc.zsyw.hope3.enums.HopeModuleRequestEnum;
import com.icbc.zsyw.hope3.enums.HopePrivRequestEnum;
import com.icbc.zsyw.hope3.enums.HopeSearchhistoryReqEnum;
import com.icbc.zsyw.hope3.mapper.HopeSearchHistoryMapper;
import com.icbc.zsyw.hope3.service.HopeSearchHistoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName HopeSearchHistoryServiceImpl
 * @Description 搜索记录
 * @Author qinwankang
 * @Date 2020/5/14 17:40
 * @Version V1.0
 **/
@Service
public class HopeSearchHistoryServiceImpl implements HopeSearchHistoryService {
    @Resource
    private HopeSearchHistoryMapper hopeSearchHistoryMapper;
    /**
    * 功能描述:搜索记录（如果多于10条取最近10条）
     * @param hopeSearchHistory
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeSearchHistory>>
    * @Author: qinwankang
    * @Date: 2020/5/18 17:50
    */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<List<HopeSearchHistory>> querySearchRecord(HopeSearchHistory hopeSearchHistory) {
        //入参非空校验
     //   for (HopePrivRequestEnum headCheckEnum : HopePrivRequestEnum.values()) {
            if (HopePrivRequestEnum.aamid.isNotEmpty() && StringUtils.isEmpty(hopeSearchHistory.getAamid()) )
                return new BaseResponse<>(HopePrivRequestEnum.aamid.getReturnCode(), null, HopePrivRequestEnum.aamid.getMsg());
      //  }
        List<HopeSearchHistory>hopeSearchHistories=hopeSearchHistoryMapper.querySearchRecord(hopeSearchHistory);
        if(hopeSearchHistories.size()>10){
            List<HopeSearchHistory>list = new ArrayList<HopeSearchHistory>();
            for(int i=0;i<10;i++){
                list.add(hopeSearchHistories.get(i));
            }
            return new BaseResponse<List<HopeSearchHistory>>(BaseResponse.STATUS_HANDLE_SUCCESS,list,BaseResponse.STATUS_HANDLER_SUCCESS);
        }
        if(hopeSearchHistories==null || hopeSearchHistories.size()==0)
            return new BaseResponse<List<HopeSearchHistory>>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);
        return new BaseResponse<List<HopeSearchHistory>>(BaseResponse.STATUS_HANDLE_SUCCESS,hopeSearchHistories,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
/**
* 功能描述:用户搜索记录落库
 * @param hopeSearchHistory
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeSearchHistory>>
* @Author: qinwankang
* @Date: 2020/7/1 14:11
*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<List<HopeSearchHistory>> insertSearchRecord(HopeSearchHistory hopeSearchHistory) {
        //入参校验
        BaseResponse checkresponse = checkSearParm(hopeSearchHistory);
        if(!checkresponse.getMessage().equals(BaseResponse.STATUS_HANDLER_SUCCESS)|| !checkresponse.getStatus().equals(BaseResponse.STATUS_HANDLE_SUCCESS)){
            return checkresponse;
        }
        hopeSearchHistoryMapper.insert(hopeSearchHistory);
        return new BaseResponse(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
/**
* 功能描述:用户删除搜索历史记录
 * @param jsonObject
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeSearchHistory>>
* @Author: qinwankang
* @Date: 2020/7/8 22:34
*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<List<HopeSearchHistory>> delSearchRecord(JSONObject jsonObject) {
        String aamid = jsonObject.getString("aamid");
        JSONArray jsonArray = jsonObject.getJSONArray("searchArr");
        if(StringUtils.isEmpty(aamid)){
            return new BaseResponse<>(HopePrivRequestEnum.aamid.getReturnCode(),HopePrivRequestEnum.aamid.getMsg());
        }
        if(StringUtils.isEmpty(jsonArray)||jsonArray.size()==0||jsonArray==null){
            return new BaseResponse<>(HopeSearchhistoryReqEnum.searchtext.getReturnCode(),HopeSearchhistoryReqEnum.searchtext.getMsg());
        }
        hopeSearchHistoryMapper.delSearchRecord(jsonArray,aamid);
        return new BaseResponse<>(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }

    private BaseResponse checkSearParm(HopeSearchHistory hopeSearchHistory) {
        if(StringUtils.isEmpty(hopeSearchHistory.getAamid())){
            return new BaseResponse<>(HopePrivRequestEnum.aamid.getReturnCode(), null, HopePrivRequestEnum.aamid.getMsg());
        }
        if(StringUtils.isEmpty(hopeSearchHistory.getSearchtext())){
            return new BaseResponse<>(HopeSearchhistoryReqEnum.searchtext.getReturnCode(), null, HopeSearchhistoryReqEnum.searchtext.getMsg());
        }
        if(null==hopeSearchHistory.getLogtime()){
            return new BaseResponse<>(HopePrivRequestEnum.logtime.getReturnCode(), null, HopePrivRequestEnum.logtime.getMsg());
        }
      return new BaseResponse(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }


}
