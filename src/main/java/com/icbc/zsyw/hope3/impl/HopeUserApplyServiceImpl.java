package com.icbc.zsyw.hope3.impl;

import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopeUserApply;
import com.icbc.zsyw.hope3.enums.HopeModuleRequestEnum;
import com.icbc.zsyw.hope3.enums.HopePrivRequestEnum;
import com.icbc.zsyw.hope3.enums.HopeUserApplyReqEnum;
import com.icbc.zsyw.hope3.mapper.HopeUserApplyMapper;
import com.icbc.zsyw.hope3.service.HopeUserApplyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @ClassName HopeUserApplyServiceImpl
 * @Description
 * @Author qinwankang
 * @Date 2020/7/22 10:04
 * @Version V1.0
 **/
@Service
public class HopeUserApplyServiceImpl implements HopeUserApplyService {
    @Resource
    private HopeUserApplyMapper hopeUserApplyMapper;
    /**
    * 功能描述:用户申请权限
     * @param hopeUserApply
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Object>
    * @Author: qinwankang
    * @Date: 2020/7/22 10:12
    */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<Object> insertHopeUserApply(HopeUserApply hopeUserApply) {
        //入参校验
        BaseResponse checkresponse = checkApplyPara(hopeUserApply);
        if(!checkresponse.getStatus().equals(BaseResponse.STATUS_HANDLE_SUCCESS)||!checkresponse.getMessage().equals(BaseResponse.STATUS_HANDLER_SUCCESS)){
            return checkresponse;
        }
        hopeUserApplyMapper.insert(hopeUserApply);
        return new BaseResponse(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
/**
* 功能描述:用户申请权限参数校验
 * @param hopeUserApply
* @return: com.icbc.zsyw.hope3.common.BaseResponse
* @Author: qinwankang
* @Date: 2020/7/22 10:16
*/
    private BaseResponse checkApplyPara(HopeUserApply hopeUserApply) {
        if(StringUtils.isEmpty(hopeUserApply.getMessage())){
            return new BaseResponse(HopeUserApplyReqEnum.message.getReturnCode(),HopeUserApplyReqEnum.message.getMsg());
        }
        if(StringUtils.isEmpty(hopeUserApply.getAamid())){
            return new BaseResponse(HopePrivRequestEnum.aamid.getReturnCode(),HopePrivRequestEnum.aamid.getMsg());
        }
        if(StringUtils.isEmpty(hopeUserApply.getLogtime())){
            return new BaseResponse(HopePrivRequestEnum.logtime.getReturnCode(),HopePrivRequestEnum.logtime.getMsg());
        }
        if(StringUtils.isEmpty(hopeUserApply.getModuleid())){
            return new BaseResponse(HopeModuleRequestEnum.moduleid.getReturnCode(),HopeModuleRequestEnum.moduleid.getMsg());
        }
       return new BaseResponse(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
}
