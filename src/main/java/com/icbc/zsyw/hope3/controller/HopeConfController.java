package com.icbc.zsyw.hope3.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopePrivGroup;
import com.icbc.zsyw.hope3.dto.HopeviewImagebarPriv;
import com.icbc.zsyw.hope3.impl.HopeactivityServiceImpl;
import com.icbc.zsyw.hope3.service.HopeConfService;
import com.icbc.zsyw.hope3.service.HopeactivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName HopeConfController
 * @Description
 * @Author qinwankang
 * @Date 2020/7/3 16:59
 * @Version V1.0
 **/
@RestController
@RequestMapping("/hopeConf")
@Slf4j
public class HopeConfController {
    @Resource
    private HopeConfService hopeConfService;
    /**
     * 功能描述:掌上运维首页加载获取url
     * @param request
     * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeBroadcast>>
     * @Author: qinwankang
     * @Date: 2020/7/3 15:08
     */
    @RequestMapping(path = {"/queryHopeConfUrl"},method = RequestMethod.POST)
    public BaseResponse<String> queryHopeConfUrl(HttpServletRequest request){
        log.info("queryHopeConfUrlStart");
        BaseResponse<String> response = hopeConfService.queryHopeConfUrl();
        log.info("queryHopeActivityEnd Result:"+JSON.toJSONString(response));
        return response;
    }
    /**
    * 功能描述:掌上运维首页加载获取对应用户
     * @param request
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<java.lang.String>>
    * @Author: qinwankang
    * @Date: 2020/7/4 8:47
    */
    @RequestMapping(path = {"/queryHopeConfUser"},method = RequestMethod.POST)
    public BaseResponse<String[]> queryHopeConfUser(HttpServletRequest request){
        log.info("queryHopeConfUserStart");
        BaseResponse<String[]> response = hopeConfService.queryHopeConfUser();
       log.info("queryHopeConfUserEnd Result:"+JSON.toJSONString(response));
        return response;
    }
    /**
    * 功能描述:根据用户自身权限判断用户需要访问的掌上运维版本（旧版，新版，新版测试）
     * @param request
     * @param hopePrivGroup
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Integer>
    * @Author: qinwankang
    * @Date: 2020/7/27 14:28
    */
    @RequestMapping(path = {"/queryHopeConfUserByPriv"},method = RequestMethod.POST)
    public BaseResponse<Integer> queryHopeConfUserByPriv(HttpServletRequest request, @RequestBody HopePrivGroup hopePrivGroup){
        log.info("queryHopeConfUserByPrivStart");
        BaseResponse<Integer> response = hopeConfService.queryHopeConfUserByPriv(hopePrivGroup);
        log.info("queryHopeConfUserByPrivEnd Result:"+JSON.toJSONString(response));
        return response;
    }
}
