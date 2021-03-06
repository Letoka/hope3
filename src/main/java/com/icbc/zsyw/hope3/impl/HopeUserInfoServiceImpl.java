package com.icbc.zsyw.hope3.impl;

import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopeUserInfo;
import com.icbc.zsyw.hope3.dto.HopeUserStatus;
import com.icbc.zsyw.hope3.enums.HopePrivRequestEnum;
import com.icbc.zsyw.hope3.enums.HopeUserInfoReqEnum;
import com.icbc.zsyw.hope3.enums.UserInfoCheckEnum;
import com.icbc.zsyw.hope3.enums.UserInfoEnum;
import com.icbc.zsyw.hope3.mapper.HopeUserInfoMapper;
import com.icbc.zsyw.hope3.mapper.HopeUserStatusMapper;
import com.icbc.zsyw.hope3.service.HopeUserInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
    @Resource
    private HopeUserStatusMapper hopeUserStatusMapper;
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<Integer> insertHopeUserInfo(HopeUserInfo hopeUserInfo) {
        //入参校验
        checkParam(hopeUserInfo);
        /*if(!checkresponse.getStatus().equals(BaseResponse.STATUS_HANDLE_SUCCESS)||!checkresponse.getMessage().equals(BaseResponse.STATUS_HANDLER_SUCCESS))
            return checkresponse;*/
        HopeUserInfo userInfo=hopeUserInfoMapper.selectByPrimaryKey(hopeUserInfo.getAamid());
        if(userInfo==null) {
            try {
                hopeUserInfoMapper.insert(hopeUserInfo);
            }catch (Exception e){
                return new BaseResponse<>(BaseResponse.STATUS_SYSTEM_FAILURE,BaseResponse.STATUS_SYSTEM_FAILUREE);

            }
            HopeUserStatus hopeUserStatus=new HopeUserStatus();
            hopeUserStatus.setAamid(hopeUserInfo.getAamid());
            hopeUserStatus.setLogstatus(UserInfoEnum.firstLog.getKey());
            hopeUserStatusMapper.insert(hopeUserStatus);
        }
        if(userInfo!=null){
            HopeUserStatus hopeUserStatus=new HopeUserStatus();
            hopeUserStatus.setAamid(hopeUserInfo.getAamid());
            hopeUserStatus.setLogstatus(UserInfoEnum.firstLogNo.getKey());
            hopeUserStatusMapper.updateByPrimaryKey(hopeUserStatus);
        }

       return new BaseResponse<>(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
   }
/**
* 功能描述:查询首页用户信息，有返回1，无返回0
 * @param hopeUserInfo
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Integer>
* @Author: qinwankang
* @Date: 2020/7/31 14:29
*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<Integer> queryHopeUserInfo(HopeUserInfo hopeUserInfo) {
        if(StringUtils.isEmpty(hopeUserInfo.getAamid())){
            return new BaseResponse(HopePrivRequestEnum.aamid.getReturnCode(),HopePrivRequestEnum.aamid.getMsg());
        }
        Integer logStatus = hopeUserStatusMapper.queryStatusByAamid(hopeUserInfo.getAamid());
        if(logStatus==null||logStatus==UserInfoEnum.firstLog.getKey()){
            return new BaseResponse(BaseResponse.STATUS_HANDLE_SUCCESS,UserInfoCheckEnum.newExsit.getKey(),BaseResponse.STATUS_HANDLER_SUCCESS);

        }
        return new BaseResponse(BaseResponse.STATUS_HANDLE_SUCCESS,UserInfoCheckEnum.newNo.getKey(),BaseResponse.STATUS_HANDLER_SUCCESS);

       /* String aamid = hopeUserInfoMapper.queryHopeUserInfo(hopeUserInfo.getAamid());
        if(StringUtils.isEmpty(aamid)){
            return new BaseResponse(BaseResponse.STATUS_HANDLE_SUCCESS,UserInfoCheckEnum.newExsit.getKey(),BaseResponse.STATUS_HANDLER_SUCCESS);
        }
        return new BaseResponse(BaseResponse.STATUS_HANDLE_SUCCESS,UserInfoCheckEnum.newNo.getKey(),BaseResponse.STATUS_HANDLER_SUCCESS);
*/
    }

    private void checkParam(HopeUserInfo hopeUserInfo) {
       /* if(StringUtils.isEmpty(hopeUserInfo.getAamid())){
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
        }*//*else if(!StringUtils.isEmpty(hopeUserInfo.getOfficephone())){
            BaseResponse response = FiltrateUtil.matchOfficeMobile(hopeUserInfo.getOfficephone());
            if(!response.getStatus().equals(BaseResponse.STATUS_HANDLE_SUCCESS)||!response.getMessage().equals(BaseResponse.STATUS_HANDLER_SUCCESS)){
                return response;
            }
        }*//*
        if(StringUtils.isEmpty(hopeUserInfo.getTdeptname())){
            return new BaseResponse(HopeUserInfoReqEnum.tdeptname.getReturnCode(),HopeUserInfoReqEnum.tdeptname.getMsg());
        }if(StringUtils.isEmpty(hopeUserInfo.getUseremail())){
            return new BaseResponse(HopeUserInfoReqEnum.useremail.getReturnCode(),HopeUserInfoReqEnum.useremail.getMsg());
        }*//*else if(!StringUtils.isEmpty(hopeUserInfo.getUseremail())){
            BaseResponse response = FiltrateUtil.matchmail(hopeUserInfo.getUseremail());
            if(!response.getStatus().equals(BaseResponse.STATUS_HANDLE_SUCCESS)||!response.getMessage().equals(BaseResponse.STATUS_HANDLER_SUCCESS)){
                return response;
            }
        }*//*
        if(StringUtils.isEmpty(hopeUserInfo.getUsermobile())){
            return new BaseResponse(HopeUserInfoReqEnum.usermobile.getReturnCode(),HopeUserInfoReqEnum.usermobile.getMsg());
        }*//*else if(!StringUtils.isEmpty(hopeUserInfo.getUsermobile())){
            String userMobile = hopeUserInfo.getUsermobile();
              BaseResponse response = FiltrateUtil.matchMobile(userMobile);
              if(!response.getStatus().equals(BaseResponse.STATUS_HANDLE_SUCCESS)||!response.getMessage().equals(BaseResponse.STATUS_HANDLER_SUCCESS)){
                  return response;
              }
        }*//*
        if(StringUtils.isEmpty(hopeUserInfo.getUsername())){
            return new BaseResponse(HopeUserInfoReqEnum.username.getReturnCode(),HopeUserInfoReqEnum.username.getMsg());
        }
        if(StringUtils.isEmpty(hopeUserInfo.getLogtime())){
            return new BaseResponse(HopeUserInfoReqEnum.logtime.getReturnCode(),HopeUserInfoReqEnum.logtime.getMsg());
        }
        if(StringUtils.isEmpty(hopeUserInfo.getUserpost())){
            return new BaseResponse(HopeUserInfoReqEnum.userpost.getReturnCode(),HopeUserInfoReqEnum.userpost.getMsg());
        }
        return new BaseResponse(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
*/
        if(StringUtils.isEmpty(hopeUserInfo.getAamid())){
            hopeUserInfo.setAamid(null);
        }
        if(StringUtils.isEmpty(hopeUserInfo.getDeptid())){
            hopeUserInfo.setDeptid(null);
        }
        if(StringUtils.isEmpty(hopeUserInfo.getDeptname())){
            hopeUserInfo.setDeptname(null);
        }
        if(StringUtils.isEmpty(hopeUserInfo.getOdeptid())){
            hopeUserInfo.setOdeptid(null);
        }
        if(StringUtils.isEmpty(hopeUserInfo.getOdeptname())){
            hopeUserInfo.setOdeptname(null);
        }if(StringUtils.isEmpty(hopeUserInfo.getOfficephone())){
            hopeUserInfo.setOfficephone(null);
        }/*else if(!StringUtils.isEmpty(hopeUserInfo.getOfficephone())){
        BaseResponse response = FiltrateUtil.matchOfficeMobile(hopeUserInfo.getOfficephone());
        if(!response.getStatus().equals(BaseResponse.STATUS_HANDLE_SUCCESS)||!response.getMessage().equals(BaseResponse.STATUS_HANDLER_SUCCESS)){
            return response;
        }
    }*/
            if(StringUtils.isEmpty(hopeUserInfo.getTdeptname())){
                hopeUserInfo.setTdeptname(null);
    }if(StringUtils.isEmpty(hopeUserInfo.getUseremail())){
            hopeUserInfo.setUseremail(null);    }
            /*else if(!StringUtils.isEmpty(hopeUserInfo.getUseremail())){
    BaseResponse response = FiltrateUtil.matchmail(hopeUserInfo.getUseremail());
            if(!response.getStatus().equals(BaseResponse.STATUS_HANDLE_SUCCESS)||!response.getMessage().equals(BaseResponse.STATUS_HANDLER_SUCCESS)){
        return response;
    }
}*/
        if(StringUtils.isEmpty(hopeUserInfo.getUsermobile())){
            hopeUserInfo.setUsermobile(null);
        }/*else if(!StringUtils.isEmpty(hopeUserInfo.getUsermobile())){
        String userMobile = hopeUserInfo.getUsermobile();
        BaseResponse response = FiltrateUtil.matchMobile(userMobile);
        if(!response.getStatus().equals(BaseResponse.STATUS_HANDLE_SUCCESS)||!response.getMessage().equals(BaseResponse.STATUS_HANDLER_SUCCESS)){
        return response;
        }
        }*/
        if(StringUtils.isEmpty(hopeUserInfo.getUsername())){
            hopeUserInfo.setUsername(null);
        }
        if(StringUtils.isEmpty(hopeUserInfo.getLogtime())){
            hopeUserInfo.setLogtime(null);
        }
        if(StringUtils.isEmpty(hopeUserInfo.getUserpost())){
            hopeUserInfo.setUserpost(null);
        }

    }
}
