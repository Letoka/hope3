package com.icbc.zsyw.hope3.controller;

import com.alibaba.fastjson.JSON;
import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopeSearchHistory;
import com.icbc.zsyw.hope3.dto.HopeSearchHistory_h;
import com.icbc.zsyw.hope3.service.HopeSearchHistory_hService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName HopeSearchHistory_hController
 * @Description
 * @Author qinwankang
 * @Date 2020/5/15 11:30
 * @Version V1.0
 **/
@RestController
@RequestMapping("/hopeSearchHistory_h")
@Slf4j
public class HopeSearchHistory_hController {
    @Resource
    private HopeSearchHistory_hService hopeSearchHistory_hService;
/**
* 功能描述:热搜
 * @param request
 * @param hopeSearchHistory_h
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeSearchHistory_h>>
* @Author: qinwankang
* @Date: 2020/5/16 12:32
*/
   /* @RequestMapping(path = {"/queryHotSearch"},method = RequestMethod.POST)
    public BaseResponse<List<HopeSearchHistory_h>>  queryHotSearch(HttpServletRequest request, @RequestBody HopeSearchHistory_h hopeSearchHistory_h){
      //  log.info("queryHotSearchStart hopeSearchHistory_h:"+JSON.toJSONString(hopeSearchHistory_h));
        BaseResponse<List<HopeSearchHistory_h>> response = hopeSearchHistory_hService.queryHotSearch(hopeSearchHistory_h);
       // log.info("queryHotSearchEnd Result:"+ JSON.toJSONString(response));
        return response;
    }*/
    /**
     * 功能描述:热搜
     * @param request
     * @param
     * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeSearchHistory_h>>
     * @Author: qinwankang
     * @Date: 2020/5/16 12:32
     */
    @RequestMapping(path = {"/queryHotSearch"},method = RequestMethod.POST)
    public BaseResponse<List<HopeSearchHistory>>  queryHotSearch(HttpServletRequest request, @RequestBody HopeSearchHistory hopeSearchHistory){
          log.info("queryHotSearchStart hopeSearchHistory_h:"+JSON.toJSONString(hopeSearchHistory));
        BaseResponse<List<HopeSearchHistory>> response = hopeSearchHistory_hService.queryHotSearch(hopeSearchHistory);
         log.info("queryHotSearchEnd Result:"+ JSON.toJSONString(response));
        return response;
    }


}
