package com.icbc.zsyw.hope3.impl;

import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopeBroadcast;
import com.icbc.zsyw.hope3.dto.HopeComments;
import com.icbc.zsyw.hope3.enums.HopeCommentsRequestEnum;
import com.icbc.zsyw.hope3.enums.HopeModuleRequestEnum;
import com.icbc.zsyw.hope3.enums.HopeviewImagebarPrivRequestEnum;
import com.icbc.zsyw.hope3.mapper.HopeCommentsMapper;
import com.icbc.zsyw.hope3.service.HopeCommentsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ClassName HopeCommentsServiceImpl
 * @Description
 * @Author qinwankang
 * @Date 2020/5/22 10:51
 * @Version V1.0
 **/
@Service
public class HopeCommentsServiceImpl implements HopeCommentsService {
    @Resource
    private HopeCommentsMapper hopeCommentsMapper;
    /**
    * 功能描述:点赞+点踩
     * @param hopeComments
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeBroadcast>>
    * @Author: qinwankang
    * @Date: 2020/5/22 11:14
    */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<List<HopeBroadcast>> insertHopeComments(HopeComments hopeComments) {
        //入参非空校验
        String aamid = hopeComments.getAamid();
        Integer moduleid = hopeComments.getModuleid();
        Date logtime = hopeComments.getLogtime();
        Integer comments = hopeComments.getComments();
        String des = hopeComments.getDescription();
        if(StringUtils.isEmpty(aamid)){
            return new BaseResponse<>(HopeviewImagebarPrivRequestEnum.aamid.getReturnCode(),HopeviewImagebarPrivRequestEnum.aamid.getMsg());
        }
        if(moduleid==null){
            return new BaseResponse<>(HopeModuleRequestEnum.moduleid.getReturnCode(),HopeModuleRequestEnum.moduleid.getMsg());
        }
        //for(HopeCommentsRequestEnum hopeCommentsRequestEnum: HopeCommentsRequestEnum.values()){
            if (HopeCommentsRequestEnum.logtime.isNotEmpty() && logtime==null )
                return new BaseResponse<>(HopeCommentsRequestEnum.logtime.getReturnCode(), null, HopeCommentsRequestEnum.logtime.getMsg());
            if(HopeCommentsRequestEnum.comments.isNotEmpty() && comments==null)
                return new BaseResponse<>(HopeCommentsRequestEnum.comments.getReturnCode(), null, HopeCommentsRequestEnum.comments.getMsg());
        //}

        try {
            hopeCommentsMapper.insert(hopeComments);
        }catch (Exception e){
           return new BaseResponse<>(BaseResponse.STATUS_SYSTEM_FAILURE,BaseResponse.STATUS_SYSTEM_FAILUREE);
        }
        return new BaseResponse<>(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
}
