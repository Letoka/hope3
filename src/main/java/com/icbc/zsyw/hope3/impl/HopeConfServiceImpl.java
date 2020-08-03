package com.icbc.zsyw.hope3.impl;

import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopePrivGroup;
import com.icbc.zsyw.hope3.enums.HopeConfEnum;
import com.icbc.zsyw.hope3.enums.HopePrivRequestEnum;
import com.icbc.zsyw.hope3.mapper.HopeConfMapper;
import com.icbc.zsyw.hope3.mapper.HopePrivGroupMapper;
import com.icbc.zsyw.hope3.service.HopeConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName HopeConfServiceImpl
 * @Description
 * @Author qinwankang
 * @Date 2020/7/3 17:01
 * @Version V1.0
 **/
@Service
public class HopeConfServiceImpl implements HopeConfService {
    @Resource
    private HopeConfMapper hopeConfMapper;
    @Resource
    private HopePrivGroupMapper hopePrivGroupMapper;
    /**
    * 功能描述:掌上运维首页加载获取url
     * @param
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.String>
    * @Author: qinwankang
    * @Date: 2020/7/4 8:45
    */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<String> queryHopeConfUrl() {
        String confUrl = hopeConfMapper.queryHopeConfUrl();
        if(StringUtils.isEmpty(confUrl)){
            return new BaseResponse<>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);
        }
        return new BaseResponse<String>(BaseResponse.STATUS_HANDLE_SUCCESS,confUrl,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
/**
* 功能描述:掌上运维首页加载获取对应用户
 * @param
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<java.lang.String>>
* @Author: qinwankang
* @Date: 2020/7/4 8:48
*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<String[]> queryHopeConfUser() {
        String userStr = hopeConfMapper.queryHopeConfUser();
        if(StringUtils.isEmpty(userStr)){
            return new BaseResponse<>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);
        }
        String[] userArr=userStr.split(",");
        return new BaseResponse<String[]>(BaseResponse.STATUS_HANDLE_SUCCESS,userArr,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
/*
* 功能描述:根据用户自身权限判断用户需要访问的掌上运维版本（旧版，新版，新版测试）
 * @param hopePrivGroup
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Integer>
* @Author: qinwankang
* @Date: 2020/7/27 14:33
*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<Integer> queryHopeConfUserByPriv(HopePrivGroup hopePrivGroup) {
        //入参校验
        BaseResponse response = checkPrivParam(hopePrivGroup);
        if(!response.getMessage().equals(BaseResponse.STATUS_HANDLER_SUCCESS)||!response.getStatus().equals(BaseResponse.STATUS_HANDLE_SUCCESS)){
            return response;
        }
        BaseResponse<String[]> userArrData= queryHopeConfUser();
        String[] userArr = userArrData.getData();
        if(!StringUtils.isEmpty(userArr)){
            for(String aamidStr:userArr){
                if(aamidStr.equals(hopePrivGroup.getAamid())){
                    return new BaseResponse<>(BaseResponse.STATUS_HANDLE_SUCCESS, HopeConfEnum.xinbanceshi.getKey(),BaseResponse.STATUS_HANDLER_SUCCESS);
                }
            }
        }
        //查询hopeconf部门
        BaseResponse<String[]> deptArrData= queryHopeConfNewhope();
        //查询aamid,deptid,odeptid对应的privgroup
        List<String> priList = hopePrivGroupMapper.queryHopePrivgroup(hopePrivGroup.getAamid(),hopePrivGroup.getDeptid(),hopePrivGroup.getOdeptid());
        String[] deptArr = deptArrData.getData();
        if(priList!=null&&priList.size()!=0){
            if(deptArr!=null&&deptArr.length!=0){
                int count=0;
                for(String priStr:priList){
                    for(String depStr:deptArr){
                        if(priStr.equals(depStr)){
                            return new BaseResponse<>(BaseResponse.STATUS_HANDLE_SUCCESS, HopeConfEnum.xinban.getKey(),BaseResponse.STATUS_HANDLER_SUCCESS);
                        }else {
                            count++;
                        }
                    }
                }
                Integer priSize=priList.size();
                Integer depLength=deptArr.length;
                if(count==priSize*depLength){
                    return new BaseResponse<>(BaseResponse.STATUS_HANDLE_SUCCESS, HopeConfEnum.jiuban.getKey(),BaseResponse.STATUS_HANDLER_SUCCESS);
                }
            }else {
                return new BaseResponse<>(BaseResponse.STATUS_HANDLE_SUCCESS, HopeConfEnum.jiuban.getKey(),BaseResponse.STATUS_HANDLER_SUCCESS);
            }
        }else {
            return new BaseResponse<>(BaseResponse.STATUS_HANDLE_SUCCESS, HopeConfEnum.jiuban.getKey(),BaseResponse.STATUS_HANDLER_SUCCESS);
        }
        return new BaseResponse<>(BaseResponse.STATUS_SYSTEM_FAILURE, null,BaseResponse.STATUS_SYSTEM_FAILUREE);
    }

    // //查询hopeconf部门
    private BaseResponse<String[]> queryHopeConfNewhope() {
        String deptStr = hopeConfMapper.queryHopeConfNewhope();
        if(StringUtils.isEmpty(deptStr)){
            return new BaseResponse<>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);
        }
        String[] deptArr=deptStr.split(",");
        return new BaseResponse<String[]>(BaseResponse.STATUS_HANDLE_SUCCESS,deptArr,BaseResponse.STATUS_HANDLER_SUCCESS);
    }

    /*
     * 功能描述:根据用户自身权限判断用户需要访问的掌上运维版本（旧版，新版，新版测试）参数校验
     * @param hopePrivGroup
     * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Integer>
     * @Author: qinwankang
     * @Date: 2020/7/27 14:33
     */
    private BaseResponse checkPrivParam(HopePrivGroup hopePrivGroup) {
        String aamid = hopePrivGroup.getAamid();
        String deptid = hopePrivGroup.getDeptid();
        String odeptid = hopePrivGroup.getOdeptid();
        if(StringUtils.isEmpty(aamid)){
            return new BaseResponse(HopePrivRequestEnum.aamid.getReturnCode(),HopePrivRequestEnum.aamid.getMsg());
        }
        if(StringUtils.isEmpty(deptid)){
            return new BaseResponse(HopePrivRequestEnum.deptid.getReturnCode(),HopePrivRequestEnum.deptid.getMsg());
        }
        if(StringUtils.isEmpty(odeptid)){
            return new BaseResponse(HopePrivRequestEnum.odeptid.getReturnCode(),HopePrivRequestEnum.odeptid.getMsg());
        }
       return new BaseResponse(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
}
