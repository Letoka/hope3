package com.icbc.zsyw.hope3.controller;

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
        //log.info("insertHopeCommentsStart hopeComments:"+JSON.toJSONString(hopeComments));
       BaseResponse<Integer> response = hopeUserInfoService.insertHopeUserInfo(hopeUserInfo);
        //log.info("insertHopeCommentsEnd Result:"+ JSON.toJSONString(response));
      return response;

    }
}
