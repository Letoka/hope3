package com.icbc.zsyw.hope3.impl;

import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopeSearchHistory;
import com.icbc.zsyw.hope3.enums.HopeModuleRequestEnum;
import com.icbc.zsyw.hope3.enums.HopePrivRequestEnum;
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
        for (HopePrivRequestEnum headCheckEnum : HopePrivRequestEnum.values()) {
            if (headCheckEnum.isNotEmpty() && StringUtils.isEmpty(hopeSearchHistory.getAamid()) && headCheckEnum.name().toString().equals("aamid"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());
        }
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


}
