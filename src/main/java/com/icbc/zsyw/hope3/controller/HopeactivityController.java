package com.icbc.zsyw.hope3.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopeActicity;
import com.icbc.zsyw.hope3.dto.HopeActivityLog;
import com.icbc.zsyw.hope3.dto.HopePrivGroup;
import com.icbc.zsyw.hope3.dto.HopeUserFavor;
import com.icbc.zsyw.hope3.impl.HopeactivityServiceImpl;
import com.icbc.zsyw.hope3.service.HopeactivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName HopeactivityController
 * @Description
 * @Author qinwankang
 * @Date 2020/6/2 9:08
 * @Version V1.0
 **/
@RestController
@RequestMapping("/hopeActivity")
@Slf4j
public class HopeactivityController {
    @Resource
    private HopeactivityService hopeactivityService;
    /**
    * 功能描述:发现页文章展示（分成案例培训，技术分享，视图上新三个模块进行）
     * @param request
     * @param jsonObject
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeBroadcast>>
    * @Author: qinwankang
    * @Date: 2020/6/2 15:08
    */
    @RequestMapping(path = {"/queryHopeActivity"},method = RequestMethod.POST)
    public BaseResponse<List<HopeactivityServiceImpl.ActivityClass>> queryHopeActivity(HttpServletRequest request, @RequestBody JSONObject jsonObject, HttpServletResponse httpresponse){
       // httpresponse.setHeader("Access-Control-Allow-Origin","*");
        log.info("queryHopeActivityStart hopePrivGroup {}", JSON.toJSONString(jsonObject));
        BaseResponse<List<HopeactivityServiceImpl.ActivityClass>> response = hopeactivityService.queryHopeActivity(jsonObject);
        log.info("queryHopeActivityEnd Result:"+JSON.toJSONString(response));
        return response;
    }
    /**
    * 功能描述:发现页文章点赞功能
     * @param request
     * @param hopeActivityLog
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.impl.HopeactivityServiceImpl.ActivityClass>>
    * @Author: qinwankang
    * @Date: 2020/6/3 9:12
    */
    @RequestMapping(path = {"/commendhopeActivity"},method = RequestMethod.POST)
    public BaseResponse<List<HopeactivityServiceImpl.ActivityClass>> commendhopeActivity(HttpServletRequest request, @RequestBody HopeActivityLog hopeActivityLog){
        log.info("commendhopeActivityStart hopeActivityLog:"+JSON.toJSONString(hopeActivityLog));
        BaseResponse<List<HopeactivityServiceImpl.ActivityClass>> response = hopeactivityService.commendhopeActivity(hopeActivityLog);
        log.info("commendhopeActivityEnd Result:"+JSON.toJSONString(response));
        return response;
    }
    /**
    * 功能描述:发现页文章收藏功能
     * @param request
     * @param hopeUserFavor
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.impl.HopeactivityServiceImpl.ActivityClass>>
    * @Author: qinwankang
    * @Date: 2020/6/3 10:11
    */
    @RequestMapping(path = {"/colletcthopeActivity"},method = RequestMethod.POST)
    public BaseResponse<List<HopeactivityServiceImpl.ActivityClass>> colletcthopeActivity(HttpServletRequest request, @RequestBody HopeUserFavor hopeUserFavor){
        log.info("colletcthopeActivityStart hopeUserFavor:"+JSON.toJSONString(hopeUserFavor));
        BaseResponse<List<HopeactivityServiceImpl.ActivityClass>> response = hopeactivityService.colletcthopeActivity(hopeUserFavor);
        log.info("colletcthopeActivityEnd Result:"+JSON.toJSONString(response));
        return response;
    }
    /**
    * 功能描述:发现页点击文章增加访问量
     * @param request
     * @param hopeActivityLog
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.impl.HopeactivityServiceImpl.ActivityClass>>
    * @Author: qinwankang
    * @Date: 2020/6/3 10:59
    */
    @RequestMapping(path = {"/clickhopeActivity"},method = RequestMethod.POST)
    public BaseResponse<List<HopeactivityServiceImpl.ActivityClass>> clickhopeActivity(HttpServletRequest request, @RequestBody HopeActivityLog hopeActivityLog){
        log.info("clickhopeActivityStart hopeActivityLog:"+JSON.toJSONString(hopeActivityLog));
        BaseResponse<List<HopeactivityServiceImpl.ActivityClass>> response = hopeactivityService.clickhopeActivity(hopeActivityLog);
        log.info("clickhopeActivityEnd Result:"+JSON.toJSONString(response));
        return response;
    }
    /**
    * 功能描述:首页三大块点击第三块文章更新广告跳转到文章列表（该文章列表不用分类，按照发表时间降序排列）
     * @param request
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeActicity>>
    * @Author: qinwankang
    * @Date: 2020/6/8 17:01
    */
    @RequestMapping(path = {"/getHopeActivitys"},method = RequestMethod.POST)
    public BaseResponse<List<HopeActicity>> getHopeActivitys(HttpServletRequest request){
        log.info("getHopeActivitysStart");
        BaseResponse<List<HopeActicity>> response = hopeactivityService.getHopeActivitys();
        log.info("getHopeActivitysEnd Result:"+JSON.toJSONString(response));
        return response;
    }
    /**
    * 功能描述:发现页取消收藏功能
     * @param request
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeActicity>>
    * @Author: qinwankang
    * @Date: 2020/7/1 19:31
    */
    @RequestMapping(path = {"/deleteCollActivi"},method = RequestMethod.POST)
    public BaseResponse<Object> deleteCollActivi(HttpServletRequest request,@RequestBody HopeUserFavor hopeUserFavor){
        log.info("deleteCollActiviStart hopeUserFavor:"+JSON.toJSONString(hopeUserFavor));
        BaseResponse<Object> response = hopeactivityService.deleteCollActivi(hopeUserFavor);
        log.info("deleteCollActiviEnd Result:"+JSON.toJSONString(response));
        return response;
    }
    /**
     * 功能描述:发现页搜索功能
     * @param request
     * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeActicity>>
     * @Author: qinwankang
     * @Date: 2020/7/1 19:31
     */
    @RequestMapping(path = {"/searchActByName"},method = RequestMethod.POST)
    public BaseResponse<Object> searchActByName(HttpServletRequest request,@RequestBody HopeUserFavor hopeUserFavor){
       /* log.info("deleteCollActiviStart"+JSON.toJSONString(hopeUserFavor));
        BaseResponse<Object> response = hopeactivityService.searchActByName(hopeUserFavor);
        log.info("deleteCollActiviEnd Result:"+JSON.toJSONString(response));*/
      //  return response;
        return null;
    }

}
