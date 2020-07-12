package com.icbc.zsyw.hope3.controller;

import com.alibaba.fastjson.JSON;
import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopeImagebar;
import com.icbc.zsyw.hope3.dto.HopeviewImagebarPriv;
import com.icbc.zsyw.hope3.service.HopeImagebarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName HopeImagebarController
 * @Description
 * @Author qinwankang
 * @Date 2020/5/18 15:22
 * @Version V1.0
 **/
@RestController
@RequestMapping("/hopeImagebar")
@Slf4j
public class HopeImagebarController {
    @Resource
    private HopeImagebarService hopeImagebarService;
/**
* 功能描述:头图展示
 * @param request
 * @param hopeviewImagebarPriv
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeImagebar>>
* @Author: qinwankang
* @Date: 2020/5/18 15:38
*/
    @RequestMapping(path = {"/queryHopeImagebar"},method = RequestMethod.POST)
    public BaseResponse<List<HopeImagebar>> queryHopeImagebar(HttpServletRequest request, @RequestBody HopeviewImagebarPriv hopeviewImagebarPriv){
      //  log.info("queryHopeImagebarStart hopeviewImagebarPriv:"+JSON.toJSONString(hopeviewImagebarPriv));
        BaseResponse<List<HopeImagebar>> response = hopeImagebarService.queryHopeImagebar(hopeviewImagebarPriv.getAamid(),hopeviewImagebarPriv.getDeptid(),hopeviewImagebarPriv.getOdeptid());
       // log.info("queryHopeImagebarEnd Result:"+JSON.toJSONString(response));
        return response;
    }
    /**
    * 功能描述:点击头图返回对应视图
     * @param request
     * @param hopeImagebar
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeImagebar>>
    * @Author: qinwankang
    * @Date: 2020/5/19 15:17
    */
    @RequestMapping(path = {"/queryModuleUrl"},method = RequestMethod.POST)
    public BaseResponse<String> queryModuleUrl(HttpServletRequest request, @RequestBody HopeImagebar hopeImagebar){
        Integer imagebarid = hopeImagebar.getImagebarid();
       // log.info("queryModuleUrlStart hopeImagebar:"+JSON.toJSONString(hopeImagebar));
        BaseResponse<String> response = hopeImagebarService.queryModuleUrl(imagebarid);
      //  log.info("queryModuleUrlEnd Result:"+ JSON.toJSONString(response));
        return response;
    }
    
}
