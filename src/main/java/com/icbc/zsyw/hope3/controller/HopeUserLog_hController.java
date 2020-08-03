package com.icbc.zsyw.hope3.controller;

import com.alibaba.fastjson.JSON;
import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopeBroadcast;
import com.icbc.zsyw.hope3.dto.HopeUserLog;
import com.icbc.zsyw.hope3.dto.HopeUserLog_h;
import com.icbc.zsyw.hope3.dto.HopeviewBroadcastPriv;
import com.icbc.zsyw.hope3.service.HopeUserLog_hService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName HopeUserLog_hController
 * @Description
 * @Author qinwankang
 * @Date 2020/5/21 16:15
 * @Version V1.0
 **/
@RestController
@RequestMapping("/hopeUserLog_h")
@Slf4j
public class HopeUserLog_hController {
    @Resource
    private HopeUserLog_hService hopeUserLog_hService;
    /**
    * 功能描述:查询视图访问量
     * @param request
     * @param hopeUserLog_h
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeBroadcast>>
    * @Author: qinwankang
    * @Date: 2020/5/21 16:24
    */
    @RequestMapping(path = {"/queryUserLog_h"},method = RequestMethod.POST)
    public BaseResponse<Integer> queryUserLog_h(HttpServletRequest request, @RequestBody HopeUserLog_h hopeUserLog_h){
        log.info("queryUserLog_hStart hopeUserLog_h:"+JSON.toJSONString(hopeUserLog_h));
        BaseResponse<Integer> response = hopeUserLog_hService.queryUserLog_h(hopeUserLog_h);
        log.info("queryUserLog_hEnd Result:"+JSON.toJSONString(response));
        return response;

    }
    /**
    * 功能描述:增加访问量，主要参数，用户id,视图id,访问时间；
     * @param request
     * @param hopeUserLog
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Integer>
    * @Author: qinwankang
    * @Date: 2020/5/21 16:54
    */
    @RequestMapping(path = {"/insertUserLog_h"},method = RequestMethod.POST)
    public BaseResponse<Integer> insertUserLog_h(HttpServletRequest request, @RequestBody HopeUserLog hopeUserLog){
        log.info("insertUserLog_hStart hopeUserLog_h:"+JSON.toJSONString(hopeUserLog));
        BaseResponse<Integer> response = hopeUserLog_hService.insertUserLog_h(hopeUserLog);
        log.info("insertUserLog_hEnd Result:"+ JSON.toJSONString(response));
        return response;

    }
    /**
    * 功能描述:记录用户点击四大项（首页-1，消息-2，发现-3，我的-4）
     * @param request
     * @param hopeUserLog
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Integer>
    * @Author: qinwankang
    * @Date: 2020/7/9 16:13
    */
    @RequestMapping(path = {"/insertFourCh"},method = RequestMethod.POST)
    public BaseResponse<Integer> insertFourCh(HttpServletRequest request, @RequestBody HopeUserLog hopeUserLog){
          log.info("insertUserLog_hStart hopeUserLog_h:"+JSON.toJSONString(hopeUserLog));
        BaseResponse<Integer> response = hopeUserLog_hService.insertFourCh(hopeUserLog);
          log.info("insertUserLog_hEnd Result:"+ JSON.toJSONString(response));
        return response;

    }
}
