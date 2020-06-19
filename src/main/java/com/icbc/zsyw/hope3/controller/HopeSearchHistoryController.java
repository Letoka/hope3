package com.icbc.zsyw.hope3.controller;

import com.alibaba.fastjson.JSON;
import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopeSearchHistory;
import com.icbc.zsyw.hope3.service.HopeSearchHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName HopeSearchHistoryController
 * @Description
 * @Author qinwankang
 * @Date 2020/5/14 17:39
 * @Version V1.0
 **/
@RestController
@RequestMapping("/hopeSearchHistory")
@Slf4j
public class HopeSearchHistoryController {
    @Resource
    private HopeSearchHistoryService hopeSearchHistoryService;
/**
* 功能描述:搜索记录
 * @param request
 * @param hopeSearchHistory
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeSearchHistory>>
* @Author: qinwankang
* @Date: 2020/5/16 12:32
*/
    @RequestMapping(path = {"/querySearchRecord"},method = RequestMethod.POST)
    public BaseResponse<List<HopeSearchHistory>> querySearchRecord(HttpServletRequest request, @RequestBody HopeSearchHistory hopeSearchHistory){
        log.info("querySearchRecordStart hopeSearchHistory:"+JSON.toJSONString(hopeSearchHistory));
        BaseResponse<List<HopeSearchHistory>> searchHistoryResponse = hopeSearchHistoryService.querySearchRecord(hopeSearchHistory);
        log.info("querySearchRecordEnd Result:"+ JSON.toJSONString(searchHistoryResponse));
        return searchHistoryResponse;
    }


}
