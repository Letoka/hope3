package com.icbc.zsyw.hope3.impl;

import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopeSearchHistory;
import com.icbc.zsyw.hope3.dto.HopeSearchHistory_h;
import com.icbc.zsyw.hope3.enums.HopeModuleRequestEnum;
import com.icbc.zsyw.hope3.mapper.HopeSearchHistoryMapper;
import com.icbc.zsyw.hope3.mapper.HopeSearchHistory_hMapper;
import com.icbc.zsyw.hope3.service.HopeSearchHistory_hService;
import com.icbc.zsyw.hope3.util.FiltrateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName HopeSearchHistory_hServiceImpl
 * @Description
 * @Author qinwankang
 * @Date 2020/5/15 11:37
 * @Version V1.0
 **/
@Service
public class HopeSearchHistory_hServiceImpl implements HopeSearchHistory_hService {
    @Resource
    private HopeSearchHistory_hMapper hopeSearchHistoryMapper_h;
/**
* 功能描述:热搜（查询搜索记录，如果记录数大于30，取前30，否则全取，然后根据搜索字段次数多少从大到小排序取前5）
 * @param hopeSearchHistory_h
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeSearchHistory_h>>
* @Author: qinwankang
* @Date: 2020/5/18 8:52
*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<List<HopeSearchHistory_h>> queryHotSearch(HopeSearchHistory_h hopeSearchHistory_h) {
        List<HopeSearchHistory_h>hopeSearchHistory_hs = hopeSearchHistoryMapper_h.queryHotSearch(hopeSearchHistory_h);
        if(hopeSearchHistory_hs.size()>30){
            List<HopeSearchHistory_h>list = new ArrayList<HopeSearchHistory_h>();
            for(int i=0;i<30;i++){
                list.add(hopeSearchHistory_hs.get(i));
            }
            return queryHotSearchHanle(list);
            }else if(hopeSearchHistory_hs.size()<30 && hopeSearchHistory_hs.size()>0){
            return queryHotSearchHanle(hopeSearchHistory_hs);
        }else if(hopeSearchHistory_hs.size()==0){
            return new BaseResponse<List<HopeSearchHistory_h>>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);
        }
        return null;
    }
    /**
    * 功能描述:对搜索记录根据搜索字段次数取记录前5，其中重复字段，如“快捷”和“快捷支付”，统一算成“快捷支付”
     * @param list
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeSearchHistory_h>>
    * @Author: qinwankang
    * @Date: 2020/5/18 8:55
    */
    private BaseResponse<List<HopeSearchHistory_h>> queryHotSearchHanle(List<HopeSearchHistory_h> list){
        List<HopeSearchHistory_h> finalList = FiltrateUtil.filtrateRepetition(list);
        if(finalList==null || finalList.size()==0){
            return new BaseResponse<List<HopeSearchHistory_h>>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);
        }
        List<HopeSearchHistory_h> finalList1 = FiltrateUtil.sortList(finalList);
        if(finalList1.size()>5){
            List<HopeSearchHistory_h>list1 = new ArrayList<HopeSearchHistory_h>();
            for(int i=0;i<5;i++){
                list1.add(finalList1.get(i));
            }
            return new BaseResponse<List<HopeSearchHistory_h>>(BaseResponse.STATUS_HANDLE_SUCCESS,list1,BaseResponse.STATUS_HANDLER_SUCCESS);
        }
        return new BaseResponse<List<HopeSearchHistory_h>>(BaseResponse.STATUS_HANDLE_SUCCESS,finalList1,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
}
