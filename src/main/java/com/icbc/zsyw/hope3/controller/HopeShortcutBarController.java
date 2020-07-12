package com.icbc.zsyw.hope3.controller;

import com.alibaba.fastjson.JSON;
import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopeModule;
import com.icbc.zsyw.hope3.dto.HopeShortcutBar;
import com.icbc.zsyw.hope3.dto.HopeShortcutBarPriv;
import com.icbc.zsyw.hope3.impl.HopeShortcutBarServiceImpl;
import com.icbc.zsyw.hope3.service.HopeShortcutBarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName HopeShortcutBarController
 * @Description
 * @Author qinwankang
 * @Date 2020/5/28 8:51
 * @Version V1.0
 **/
@RestController
@RequestMapping("/hopeShortcutBar")
@Slf4j
public class HopeShortcutBarController {
    @Resource
    private HopeShortcutBarService hopeShortcutBarService;
    /**
     * 功能描述:查询三大块放的视图类别（如：分行，重点业务等等），其中第三块放置最新文章更新的广告，
     * 如果最新文章上下墙时间过期，则第三块仍显示原有的视图类别，
     *如果文章设置权限，只有对应权限用户可以看到，如果文章没有设置权限则所有用户都可以看到
     * 其次，权限这块，还有文章是否展示，如果不展示，即使拥有权限也看不到
     * @param request
     * @param hopeShortcutBarPriv
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Integer>
    * @Author: qinwankang
    * @Date: 2020/5/28 9:11
    */
    @RequestMapping(path = {"/queryShortcutBar"},method = RequestMethod.POST)
    public BaseResponse<HopeShortcutBarServiceImpl.ShortcutBarSub> queryShortcutBar(HttpServletRequest request, @RequestBody HopeShortcutBarPriv hopeShortcutBarPriv){
      //  log.info("queryShortcutBarStart hopeShortcutBarPriv:"+JSON.toJSONString(hopeShortcutBarPriv));
        BaseResponse<HopeShortcutBarServiceImpl.ShortcutBarSub> response = hopeShortcutBarService.queryShortcutBar(hopeShortcutBarPriv);
      //  log.info("queryShortcutBarEnd Result:"+ JSON.toJSONString(response));
        return response;

    }
    /**
    * 功能描述:三大块当中，通过每个块当中名称获取对应视图（获取视图时注意用户对应权限）
     * @param request
     * @param hopeShortcutBarPriv
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeShortcutBar>>
    * @Author: qinwankang
    * @Date: 2020/5/28 11:11
    */
    @RequestMapping(path = {"/queryModuleByShortcutbar"},method = RequestMethod.POST)
    public BaseResponse<List<HopeModule>> queryModuleByShortcutbar(HttpServletRequest request, @RequestBody HopeShortcutBarPriv hopeShortcutBarPriv){
       // log.info("queryModuleByShortcutbarStart hopeShortcutBarPriv:"+JSON.toJSONString(hopeShortcutBarPriv));
        BaseResponse<List<HopeModule>> response = hopeShortcutBarService.queryModuleByShortcutbar(hopeShortcutBarPriv);
      //  log.info("queryModuleByShortcutbarEnd Result:"+JSON.toJSONString(response));
        return response;

    }
}
