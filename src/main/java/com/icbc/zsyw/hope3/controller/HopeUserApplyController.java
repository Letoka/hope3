package com.icbc.zsyw.hope3.controller;

import com.alibaba.fastjson.JSON;
import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopeBroadcast;
import com.icbc.zsyw.hope3.dto.HopeUserApply;
import com.icbc.zsyw.hope3.dto.HopeviewBroadcastPriv;
import com.icbc.zsyw.hope3.service.HopeBroadcastService;
import com.icbc.zsyw.hope3.service.HopeUserApplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName HopeUserApplyController
 * @Description
 * @Author qinwankang
 * @Date 2020/7/22 10:03
 * @Version V1.0
 **/
@RestController
@RequestMapping("/hopeUserApply")
@Slf4j
public class HopeUserApplyController {
    @Resource
    private HopeUserApplyService hopeUserApplyService;
/**
* 功能描述:用户申请权限
 * @param request
 * @param hopeUserApply
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Object>
* @Author: qinwankang
* @Date: 2020/7/22 10:10
*/
    @RequestMapping(path = {"/insertHopeUserApply"},method = RequestMethod.POST)
    public BaseResponse<Object> insertHopeUserApply(HttpServletRequest request, @RequestBody HopeUserApply hopeUserApply){
        log.info("insertHopeUserApplyStart hopeUserApply:"+ JSON.toJSONString(hopeUserApply));
        BaseResponse<Object> response = hopeUserApplyService.insertHopeUserApply(hopeUserApply);
        log.info("insertHopeUserApplytEnd Result:"+ JSON.toJSONString(response));
        return response;

    }

}
