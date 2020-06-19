package com.icbc.zsyw.hope3.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopeActicity;
import com.icbc.zsyw.hope3.dto.HopeActivityLog;
import com.icbc.zsyw.hope3.dto.HopePrivGroup;
import com.icbc.zsyw.hope3.dto.HopeUserFavor;
import com.icbc.zsyw.hope3.enums.ActivityClassEnum;
import com.icbc.zsyw.hope3.enums.HopeActiviLogResEnum;
import com.icbc.zsyw.hope3.enums.HopePrivRequestEnum;
import com.icbc.zsyw.hope3.mapper.HopeActicityMapper;
import com.icbc.zsyw.hope3.mapper.HopeActivityLogMapper;
import com.icbc.zsyw.hope3.mapper.HopeUserFavorMapper;
import com.icbc.zsyw.hope3.service.HopeactivityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName HopeactivityServiceImpl
 * @Description
 * @Author qinwankang
 * @Date 2020/6/2 9:10
 * @Version V1.0
 **/
@Service
public class HopeactivityServiceImpl implements HopeactivityService {
    @Resource
    private HopeActicityMapper hopeActicityMapper;
    @Resource
    private HopeActivityLogMapper hopeActivityLogMapper;
    @Resource
    private HopeUserFavorMapper hopeUserFavorMapper;
    /**
    * 功能描述:发现页文章展示（分成案例培训，技术分享，视图上新三个模块进行）
     * @param hopePrivGroup
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.impl.HopeactivityServiceImpl.ActivityClass>>
    * @Author: qinwankang
    * @Date: 2020/6/2 16:52
    */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<List<ActivityClass>> queryHopeActivity(HopePrivGroup hopePrivGroup) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //入参校验
        BaseResponse checkResoponse =   checkparameter(hopePrivGroup);
        if(!BaseResponse.STATUS_HANDLE_SUCCESS.equals(checkResoponse.getStatus()) || !BaseResponse.STATUS_HANDLER_SUCCESS.equals(checkResoponse.getMessage())){
            return checkResoponse;
        }
        List<HopeActicity>activtilist =  hopeActicityMapper.queryWatchActivity(hopePrivGroup);
        if(activtilist==null || activtilist.size()==0)
            return new BaseResponse<>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);
        List<ActivityClass> relist = new ArrayList<ActivityClass>();
        for(ActivityClassEnum activityClassEnum:ActivityClassEnum.values()){
            ActivityClass activityClass = new ActivityClass();
            activityClass.setAclass(activityClassEnum.getValue());
            List<HopeActicity>aclist = new ArrayList<HopeActicity>();
            for(HopeActicity hopeActicity:activtilist){
                if(activityClassEnum.getKey()==hopeActicity.getTextclass()){
                    Date now =new Date();
                    if(hopeActicity.getStarttime().before(now)){
                    //是否点赞
                    HopeActivityLog hopeActivityLog = hopeActivityLogMapper.checkDianzan(hopeActicity.getActivityid(),hopePrivGroup.getAamid());
                    if(hopeActivityLog!=null){
                        hopeActicity.setDianzan(true);
                    }
                    //是否收藏
                    HopeUserFavor hopeUserFavor =  hopeUserFavorMapper.checkShoucang(hopeActicity.getActivityid(),hopePrivGroup.getAamid());
                    if(hopeUserFavor!=null){
                        hopeActicity.setShoucang(true);
                    }
                    //访问量
                    Integer acount =  hopeActivityLogMapper.queryActiviCount(hopeActicity.getActivityid(),hopePrivGroup.getAamid());
                    hopeActicity.setFangwenCount(acount);
                    //pdf路径
                     String webUrlq = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/hope3/static/upload/";
                     String webUrlend = webUrlq+hopeActicity.getImagename();
                     hopeActicity.setImagepath(webUrlend);
                     aclist.add(hopeActicity);
                    }
                }
            }
            activityClass.setList(aclist);
            relist.add(activityClass);
        }
        if(relist==null || relist.size()==0)
            return new BaseResponse<>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);
        return new BaseResponse<List<ActivityClass>>(BaseResponse.STATUS_HANDLE_SUCCESS,relist,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
/**
* 功能描述:发现页文章点赞功能
 * @param hopeActivityLog
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.impl.HopeactivityServiceImpl.ActivityClass>>
* @Author: qinwankang
* @Date: 2020/6/3 9:43
*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<List<ActivityClass>> commendhopeActivity(HopeActivityLog hopeActivityLog) {
        //参数校验
        BaseResponse aclogresponse = checkactivilog(hopeActivityLog);
        if(!BaseResponse.STATUS_HANDLE_SUCCESS.equals(aclogresponse.getStatus()) || !BaseResponse.STATUS_HANDLER_SUCCESS.equals(aclogresponse.getMessage()))
            return aclogresponse;
        HopeActivityLog hopeActivityLog1 = hopeActivityLogMapper.checkDianzan(hopeActivityLog.getActivityid(),hopeActivityLog.getAamid());
        if(hopeActivityLog1!=null)
            return new BaseResponse<>(BaseResponse.DATA_STATUS_EXIST,BaseResponse.DATA_STATUS_EXISTER);
        hopeActivityLogMapper.insert(hopeActivityLog);
        return new BaseResponse<>(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
/**
* 功能描述:发现页收藏功能
 * @param hopeUserFavor
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.impl.HopeactivityServiceImpl.ActivityClass>>
* @Author: qinwankang
* @Date: 2020/6/3 10:23
*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<List<ActivityClass>> colletcthopeActivity(HopeUserFavor hopeUserFavor) {
        //参数校验
        BaseResponse actfavorresponse = checkacfavor(hopeUserFavor);
        if(!BaseResponse.STATUS_HANDLE_SUCCESS.equals(actfavorresponse.getStatus()) || !BaseResponse.STATUS_HANDLER_SUCCESS.equals(actfavorresponse.getMessage()))
            return actfavorresponse;
        HopeUserFavor hopeUserFavor1= hopeUserFavorMapper.checkShoucang(hopeUserFavor.getActivityid(),hopeUserFavor.getAamid());
        if(hopeUserFavor1!=null)
            return new BaseResponse<>(BaseResponse.DATA_STATUS_EXIST,BaseResponse.DATA_STATUS_EXISTER);
        hopeUserFavorMapper.insert(hopeUserFavor);
        return new BaseResponse<>(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
/**
* 功能描述:发现页点击文章增加访问量
 * @param hopeActivityLog
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.impl.HopeactivityServiceImpl.ActivityClass>>
* @Author: qinwankang
* @Date: 2020/6/3 11:06
*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<List<ActivityClass>> clickhopeActivity(HopeActivityLog hopeActivityLog) {
        //参数校验
        BaseResponse aclogresponse = checkactivilog(hopeActivityLog);
        if(!BaseResponse.STATUS_HANDLE_SUCCESS.equals(aclogresponse.getStatus()) || !BaseResponse.STATUS_HANDLER_SUCCESS.equals(aclogresponse.getMessage()))
            return aclogresponse;
        hopeActivityLogMapper.insert(hopeActivityLog);
        return new BaseResponse<>(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
/**
* 功能描述:首页三大块点击第三块文章更新广告跳转到文章列表（该文章列表不用分类，按照发表时间降序排列）
 * @param
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeActicity>>
* @Author: qinwankang
* @Date: 2020/6/8 17:11
*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<List<HopeActicity>> getHopeActivitys() {
        List<HopeActicity>hlist = hopeActicityMapper.getHopeActivitys();
        if (hlist==null || hlist.size()==0)
            return new BaseResponse<List<HopeActicity>>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);
        return new BaseResponse<List<HopeActicity>>(BaseResponse.STATUS_HANDLE_SUCCESS,hlist,BaseResponse.STATUS_HANDLER_SUCCESS);
    }

    /**
* 功能描述:发现页收藏功能入参校验
 * @param hopeUserFavor
* @return: com.icbc.zsyw.hope3.common.BaseResponse
* @Author: qinwankang
* @Date: 2020/6/4 10:32
*/
    private BaseResponse checkacfavor(HopeUserFavor hopeUserFavor) {
        //body请求体校验
        JSONObject input = JSONObject.parseObject(JSON.toJSONString(hopeUserFavor));
        if (input.size() == 0) {
            return new BaseResponse(BaseResponse.ALL_BLANK, null,
                    BaseResponse.ALL_BLANKER);
        } else {
            for (HopeActiviLogResEnum applyCreditQuotaRequestEnum : HopeActiviLogResEnum.values()) {
                if (applyCreditQuotaRequestEnum.isNotEmpty()
                        && StringUtils.isEmpty(input.getString(applyCreditQuotaRequestEnum.name()))
                        && null!=input.getString(applyCreditQuotaRequestEnum.name())) {
                    return new BaseResponse(applyCreditQuotaRequestEnum.getReturnCode(), null,
                            applyCreditQuotaRequestEnum.getMsg());
                }
            }
        }
        return new BaseResponse(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }

    /**
* 功能描述:发现页点赞功能参数校验
 * @param hopeActivityLog
* @return: com.icbc.zsyw.hope3.common.BaseResponse
* @Author: qinwankang
* @Date: 2020/6/3 9:45
*/
    private BaseResponse checkactivilog(HopeActivityLog hopeActivityLog) {
        //body请求体校验
        JSONObject input = JSONObject.parseObject(JSON.toJSONString(hopeActivityLog));
        if (input.size() == 0) {
            return new BaseResponse(BaseResponse.ALL_BLANK, null,
                    BaseResponse.ALL_BLANKER);
        } else {
            for (HopeActiviLogResEnum applyCreditQuotaRequestEnum : HopeActiviLogResEnum.values()) {
                if (applyCreditQuotaRequestEnum.isNotEmpty()
                        && StringUtils.isEmpty(input.getString(applyCreditQuotaRequestEnum.name()))
                        && null!=input.getString(applyCreditQuotaRequestEnum.name())) {
                    return new BaseResponse(applyCreditQuotaRequestEnum.getReturnCode(), null,
                            applyCreditQuotaRequestEnum.getMsg());
                }
            }
        }
        return new BaseResponse(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }

    /**
    * 功能描述:发现页文章展示（分成案例培训，技术分享，视图上新三个模块进行）辅助类
     * @param
    * @return:
    * @Author: qinwankang
    * @Date: 2020/6/2 16:52
    */
    public class ActivityClass<List>{
        private String aclass;
        private List list;
        public String getAclass() {
            return aclass;
        }
        public List getList() {
            return list;
        }
        public void setAclass(String aclass) {
            this.aclass = aclass;
        }

        public void setList(List list) {
            this.list = list;
        }
    }
    /**
    * 功能描述:发现页文章展示（分成案例培训，技术分享，视图上新三个模块进行）入参校验
     * @param hopePrivGroup
    * @return: com.icbc.zsyw.hope3.common.BaseResponse
    * @Author: qinwankang
    * @Date: 2020/6/2 16:53
    */
    private BaseResponse checkparameter(HopePrivGroup hopePrivGroup) {
        //body请求体校验
        JSONObject input = JSONObject.parseObject(JSON.toJSONString(hopePrivGroup));
        if (input.size() == 0) {
            return new BaseResponse(BaseResponse.ALL_BLANK, null,
                    BaseResponse.ALL_BLANKER);
        } else {
            for (HopePrivRequestEnum applyCreditQuotaRequestEnum : HopePrivRequestEnum.values()) {
                if (applyCreditQuotaRequestEnum.isNotEmpty()
                        && StringUtils.isEmpty(input.getString(applyCreditQuotaRequestEnum.name()))
                        && null!=input.getString(applyCreditQuotaRequestEnum.name())) {
                    return new BaseResponse(applyCreditQuotaRequestEnum.getReturnCode(), null,
                            applyCreditQuotaRequestEnum.getMsg());
                }
            }
        }
        return new BaseResponse(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
}
