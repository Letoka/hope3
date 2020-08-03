package com.icbc.zsyw.hope3.controller;

import com.alibaba.fastjson.JSON;
import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopeBroadcast;
import com.icbc.zsyw.hope3.dto.HopeComments;
import com.icbc.zsyw.hope3.service.HopeCommentsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName HopeCommentsController
 * @Description
 * @Author qinwankang
 * @Date 2020/5/22 10:49
 * @Version V1.0
 **/
@RestController
@RequestMapping("/hopeComments")
@Slf4j
public class HopeCommentsController {
    @Resource
    private HopeCommentsService hopeCommentsService;
    /**
    * 功能描述:点赞+点踩
     * @param request
     * @param hopeComments
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeBroadcast>>
    * @Author: qinwankang
    * @Date: 2020/5/22 10:53
    */
    @RequestMapping(path = {"/insertHopeComments"},method = RequestMethod.POST)
    public BaseResponse<List<HopeBroadcast>> insertHopeComments(HttpServletRequest request, @RequestBody HopeComments hopeComments){
        log.info("insertHopeCommentsStart hopeComments:"+JSON.toJSONString(hopeComments));
        BaseResponse<List<HopeBroadcast>> response = hopeCommentsService.insertHopeComments(hopeComments);
        log.info("insertHopeCommentsEnd Result:"+ JSON.toJSONString(response));
        return response;

    }
}
