package com.icbc.zsyw.hope3.controller;

import com.alibaba.fastjson.JSON;
import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopeUserInfo;
import com.icbc.zsyw.hope3.service.HopeUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName HopeUserInfoController
 * @Description
 * @Author qinwankang
 * @Date 2020/7/9 16:35
 * @Version V1.0
 **/
@RestController
@RequestMapping("/hopeUserInfo")
@Slf4j
public class HopeUserInfoController {
    @Resource
    private HopeUserInfoService hopeUserInfoService;
    /**
     * 功能描述:记录进入首页用户信息
     * @param request
     * @param
     * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeBroadcast>>
     * @Author: qinwankang
     * @Date: 2020/5/22 10:53
     */
  @RequestMapping(path = {"/insertHopeUserInfo"},method = RequestMethod.POST)
   public BaseResponse<Integer> insertHopeUserInfo(HttpServletRequest request, @RequestBody HopeUserInfo hopeUserInfo){
        log.info("insertHopeUserInfoStart hopeComments:"+ JSON.toJSONString(hopeUserInfo));
       BaseResponse<Integer> response = hopeUserInfoService.insertHopeUserInfo(hopeUserInfo);
        log.info("insertHopeUserInfoEnd Result:"+ JSON.toJSONString(response));
      return response;

    }
   /**
   * 功能描述:查询首页用户信息，有返回1，无返回0
    * @param request
    * @param hopeUserInfo
   * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Integer>
   * @Author: qinwankang
   * @Date: 2020/7/31 14:24
   */
    @RequestMapping(path = {"/queryHopeUserInfo"},method = RequestMethod.POST)
    public BaseResponse<Integer> queryHopeUserInfo(HttpServletRequest request, @RequestBody HopeUserInfo hopeUserInfo){
        log.info("queryHopeUserInfoStart hopeUserInfo:"+ JSON.toJSONString(hopeUserInfo));
        BaseResponse<Integer> response = hopeUserInfoService.queryHopeUserInfo(hopeUserInfo);
        log.info("queryHopeUserInfoEnd Result:"+ JSON.toJSONString(response));
        return response;

    }
}
