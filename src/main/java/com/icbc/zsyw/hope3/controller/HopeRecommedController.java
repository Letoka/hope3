package com.icbc.zsyw.hope3.controller;

import com.alibaba.fastjson.JSON;
import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.*;
import com.icbc.zsyw.hope3.service.HopeRecommedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName HopeRecommedController
 * @Description
 * @Author qinwankang
 * @Date 2020/7/22 21:26
 * @Version V1.0
 **/
@RestController
@RequestMapping("/hopeRecommed")
@Slf4j
public class HopeRecommedController {
    @Resource
    private HopeRecommedService hopeRecommedService;
    /**
    * 功能描述:查询视图推荐
     * @param request
     * @param hopePrivGroup
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Integer>
    * @Author: qinwankang
    * @Date: 2020/7/22 22:53
    */
    @RequestMapping(path = {"/queryRecommedModule"},method = RequestMethod.POST)
    public BaseResponse<List<HopeRecommend>> queryRecommedModule(HttpServletRequest request){
        log.info("queryRecommedModuleStart");
        BaseResponse<List<HopeRecommend>> response = hopeRecommedService.queryRecommedModule();
        log.info("queryRecommedModuleEnd Result:"+ JSON.toJSONString(response));
        return response;

    }
}
