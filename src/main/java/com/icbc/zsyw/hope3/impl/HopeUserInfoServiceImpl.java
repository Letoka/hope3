package com.icbc.zsyw.hope3.impl;

import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopeUserInfo;
import com.icbc.zsyw.hope3.enums.HopePrivRequestEnum;
import com.icbc.zsyw.hope3.enums.HopeUserInfoReqEnum;
import com.icbc.zsyw.hope3.mapper.HopeUserInfoMapper;
import com.icbc.zsyw.hope3.service.HopeUserInfoService;
import com.icbc.zsyw.hope3.util.FiltrateUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @ClassName HopeUserInfoServiceImpl
 * @Description
 * @Author qinwankang
 * @Date 2020/7/9 16:41
 * @Version V1.0
 **/
@Service
public class HopeUserInfoServiceImpl implements HopeUserInfoService {
  @Resource
    private HopeUserInfoMapper hopeUserInfoMapper;
   @Override
    public BaseResponse<Integer> insertHopeUserInfo(HopeUserInfo hopeUserInfo) {
        //入参校验
        BaseResponse checkresponse = checkParam(hopeUserInfo);
        if(!checkresponse.getStatus().equals(BaseResponse.STATUS_HANDLE_SUCCESS)||!checkresponse.getMessage().equals(BaseResponse.STATUS_HANDLER_SUCCESS))
            return checkresponse;
        try {
            hopeUserInfoMapper.insert(hopeUserInfo);
        }catch (Exception e){
            return new BaseResponse<>(BaseResponse.STATUS_SYSTEM_FAILURE,BaseResponse.STATUS_SYSTEM_FAILUREE);

        }
       return new BaseResponse<>(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
   }

    private BaseResponse checkParam(HopeUserInfo hopeUserInfo) {
        if(StringUtils.isEmpty(hopeUserInfo.getAamid())){
            return new BaseResponse(HopePrivRequestEnum.aamid.getReturnCode(),HopePrivRequestEnum.aamid.getMsg());
        }
        if(StringUtils.isEmpty(hopeUserInfo.getDeptid())){
            return new BaseResponse(HopePrivRequestEnum.deptid.getReturnCode(),HopePrivRequestEnum.deptid.getMsg());
        }
        if(StringUtils.isEmpty(hopeUserInfo.getDeptname())){
            return new BaseResponse(HopeUserInfoReqEnum.deptname.getReturnCode(),HopeUserInfoReqEnum.deptname.getMsg());
        }
        if(StringUtils.isEmpty(hopeUserInfo.getOdeptid())){
            return new BaseResponse(HopePrivRequestEnum.odeptid.getReturnCode(),HopePrivRequestEnum.odeptid.getMsg());
        }
        if(StringUtils.isEmpty(hopeUserInfo.getOdeptname())){
            return new BaseResponse(HopeUserInfoReqEnum.odeptname.getReturnCode(),HopeUserInfoReqEnum.odeptname.getMsg());
        }if(StringUtils.isEmpty(hopeUserInfo.getOfficephone())){
            return new BaseResponse(HopeUserInfoReqEnum.officephone.getReturnCode(),HopeUserInfoReqEnum.officephone.getMsg());
        }/*else if(!StringUtils.isEmpty(hopeUserInfo.getOfficephone())){
            BaseResponse response = FiltrateUtil.matchOfficeMobile(hopeUserInfo.getOfficephone());
            if(!response.getStatus().equals(BaseResponse.STATUS_HANDLE_SUCCESS)||!response.getMessage().equals(BaseResponse.STATUS_HANDLER_SUCCESS)){
                return response;
            }
        }*/
        if(StringUtils.isEmpty(hopeUserInfo.getTdeptname())){
            return new BaseResponse(HopeUserInfoReqEnum.tdeptname.getReturnCode(),HopeUserInfoReqEnum.tdeptname.getMsg());
        }if(StringUtils.isEmpty(hopeUserInfo.getUseremail())){
            return new BaseResponse(HopeUserInfoReqEnum.useremail.getReturnCode(),HopeUserInfoReqEnum.useremail.getMsg());
        }/*else if(!StringUtils.isEmpty(hopeUserInfo.getUseremail())){
            BaseResponse response = FiltrateUtil.matchmail(hopeUserInfo.getUseremail());
            if(!response.getStatus().equals(BaseResponse.STATUS_HANDLE_SUCCESS)||!response.getMessage().equals(BaseResponse.STATUS_HANDLER_SUCCESS)){
                return response;
            }
        }*/
        if(StringUtils.isEmpty(hopeUserInfo.getUsermobile())){
            return new BaseResponse(HopeUserInfoReqEnum.usermobile.getReturnCode(),HopeUserInfoReqEnum.usermobile.getMsg());
        }/*else if(!StringUtils.isEmpty(hopeUserInfo.getUsermobile())){
            String userMobile = hopeUserInfo.getUsermobile();
              BaseResponse response = FiltrateUtil.matchMobile(userMobile);
              if(!response.getStatus().equals(BaseResponse.STATUS_HANDLE_SUCCESS)||!response.getMessage().equals(BaseResponse.STATUS_HANDLER_SUCCESS)){
                  return response;
              }
        }*/
        if(StringUtils.isEmpty(hopeUserInfo.getUsername())){
            return new BaseResponse(HopeUserInfoReqEnum.username.getReturnCode(),HopeUserInfoReqEnum.username.getMsg());
        }
        return new BaseResponse(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);

    }
}
