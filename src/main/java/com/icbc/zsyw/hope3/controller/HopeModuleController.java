package com.icbc.zsyw.hope3.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.*;
import com.icbc.zsyw.hope3.impl.HopeModuleServiceImpl;
import com.icbc.zsyw.hope3.service.HopeModuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @ClassName HopeModuleController
 * @Description
 * @Author qinwankang
 * @Date 2020/5/18 11:33
 * @Version V1.0
 **/
@RestController
@RequestMapping("/hopeModule")
@Slf4j
public class HopeModuleController {
    @Resource
    private HopeModuleService hopeModuleService;
    /**
    * 功能描述:我的关注模块全部视图
     * @param request
     * @param hopeviewModulePriv
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeModule>>
    * @Author: qinwankang
    * @Date: 2020/5/18 14:56
    */
    @RequestMapping(path = {"/queryHopeModule"},method = RequestMethod.POST)
        public BaseResponse<List<HopeModule>> queryHopeModule(HttpServletRequest request, @RequestBody HopeviewModulePriv hopeviewModulePriv){
        log.info("queryHopeModuleStart hopeviewModulePriv:"+ JSON.toJSONString(hopeviewModulePriv));
        BaseResponse<List<HopeModule>> response = hopeModuleService.queryHopeModule(hopeviewModulePriv.getAamid(),hopeviewModulePriv.getDeptid(),hopeviewModulePriv.getOdeptid());
       log.info("queryHopeModuleEnd Result:"+JSON.toJSONString(response));
        return response;
    }
    /**
    * 功能描述:首页增加我关注模块视图
     * @param request
     * @param hopeUserFavor
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeModule>>
    * @Author: qinwankang
    * @Date: 2020/5/18 15:05
    */
    @RequestMapping(path = {"/insertWatchHopeModule"},method = RequestMethod.POST)
    public BaseResponse<Integer> insertWatchHopeModule(HttpServletRequest request, @RequestBody HopeUserFavor hopeUserFavor){
        HttpSession session = request.getSession();
       log.info("insertWatchHopeModuleStart hopeUserFavor:"+JSON.toJSONString(hopeUserFavor));
        BaseResponse<Integer> response = hopeModuleService.insertWatchHopeModule(hopeUserFavor);
        log.info("insertWatchHopeModuleEnd Result:"+JSON.toJSONString(response));
        return response;
    }
    /**
    * 功能描述:首页删除我的关注模块视图
     * @param request
     * @param hopeUserFavor
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Integer>
    * @Author: qinwankang
    * @Date: 2020/5/18 21:17
    */
    @RequestMapping(path = {"/deleteWatchHopeModule"},method = RequestMethod.POST)
    public BaseResponse<Integer> deleteWatchHopeModule(HttpServletRequest request, @RequestBody HopeUserFavor hopeUserFavor){
        log.info("deleteWatchHopeModuleStart hopeUserFavor:"+JSON.toJSONString(hopeUserFavor));
        BaseResponse<Integer> response = hopeModuleService.deleteWatchHopeModule(hopeUserFavor);
       log.info("deleteWatchHopeModuleEnd Result:"+JSON.toJSONString(response));
        return response;
    }
    /**
    * 功能描述:视图二级页面移动已关注的视图图标的顺序，现在该方法被替代，没用；
     * @param request
     * @param jsonObject
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Integer>
    * @Author: qinwankang
    * @Date: 2020/5/18 21:26
    */
    @RequestMapping(path = {"/updateWatchHopeModuleSort"},method = RequestMethod.POST)
 //public BaseResponse<Integer> updateWatchHopeModuleSort(HttpServletRequest request, @RequestBody HopeUserFavor hopeUserFavor,@RequestBody HopeUserFavor hopeUserFavorAfter){
    public BaseResponse<Integer> updateWatchHopeModuleSort(HttpServletRequest request, @RequestBody JSONObject jsonObject){
        HopeUserFavor hopeUserFavor = jsonObject.getObject("hopeUserFavor",HopeUserFavor.class);
        HopeUserFavor hopeUserFavorAfter = jsonObject.getObject("hopeUserFavorAfter",HopeUserFavor.class);
        log.info("updateWatchHopeModuleSortStart jsonObject:"+JSON.toJSONString(jsonObject));
        BaseResponse<Integer> response = hopeModuleService.updateWatchHopeModuleSort(hopeUserFavor,hopeUserFavorAfter);
        log.info("updateWatchHopeModuleSortEnd Result:"+JSON.toJSONString(response));
        return response;
    }
    /**
    * 功能描述:首页一行图标区，默认登录后显示“我的关注”，是所有用户自己关注过的视图。
     * @param request
     * @param hopeUserFavor
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeUserFavor>>
    * @Author: qinwankang
    * @Date: 2020/5/19 16:59
    */

    @RequestMapping(path = {"/queryWatchHopeModule"},method = RequestMethod.POST)
    public BaseResponse<HopeModuleServiceImpl.ModuleFavor> queryWatchHopeModule(HttpServletRequest request, @RequestBody HopeUserFavor hopeUserFavor){
        log.info("queryWatchHopeModuleStart hopeUserFavor:"+JSON.toJSONString(hopeUserFavor));
        BaseResponse<HopeModuleServiceImpl.ModuleFavor> response = hopeModuleService.queryWatchHopeModule(hopeUserFavor);
       log.info("queryWatchHopeModuleEnd Result:"+JSON.toJSONString(response));
        return response;
    }
    /**
    * 功能描述:首页“我的足迹”视图显示（最多放7个），以及最后一个更多点击，（最多显示视图图标20个）。
     * @param request
     * @param hopeUserHistory
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeModule>>
    * @Author: qinwankang
    * @Date: 2020/5/19 17:42
    */
    @RequestMapping(path = {"/queryMyFoot"},method = RequestMethod.POST)
    public BaseResponse<List<HopeModuleServiceImpl.ModuleGroupSub>> queryMyFoot(HttpServletRequest request, @RequestBody HopeUserHistory hopeUserHistory){
        log.info("queryMyFootStart hopeUserHistory:"+JSON.toJSONString(hopeUserHistory));
        BaseResponse<List<HopeModuleServiceImpl.ModuleGroupSub>> response = hopeModuleService.queryMyFoot(hopeUserHistory);
        log.info("queryMyFootEnd Result:"+JSON.toJSONString(response));
        return response;
    }
    /**
    * 功能描述：首页增加视图足迹
     * @param request
     * @param hopeUserHistory
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeModule>>
    * @Author: qinwankang
    * @Date: 2020/5/27 15:55
    */
    @RequestMapping(path = {"/insertMyFoot"},method = RequestMethod.POST)
    public BaseResponse insertMyFoot(HttpServletRequest request, @RequestBody HopeUserHistory hopeUserHistory){
        log.info("insertMyFootStart hopeUserHistory:"+JSON.toJSONString(hopeUserHistory));
        BaseResponse response = hopeModuleService.insertMyFoot(hopeUserHistory);
        log.info("insertMyFootEnd Result:"+JSON.toJSONString(response));
        return response;
    }
    /**
    * 功能描述:首页（视图）搜索功能
     * @param request
     * @param hopeModule
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeModule>>
    * @Author: qinwankang
    * @Date: 2020/5/20 11:22
    */
    @RequestMapping(path = {"/searchMoudleByName"},method = RequestMethod.POST)
    public BaseResponse<List<HopeModule>> searchMoudleByName(HttpServletRequest request, @RequestBody HopeModule hopeModule){
       log.info("searchMoudleByNameStart hopeModule:"+JSON.toJSONString(hopeModule));
        BaseResponse<List<HopeModule>> response = hopeModuleService.searchMoudleByName(hopeModule);
        log.info("searchMoudleByNameEnd Result:"+JSON.toJSONString(response));
        return response;
    }
/**
* 功能描述:首页视图按照分组返回
 * @param request
 * @param hopeviewModulePriv
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeModule>>
* @Author: qinwankang
* @Date: 2020/5/22 9:28
*/
    @RequestMapping(path = {"/searchMoudleByGroup"},method = RequestMethod.POST)
    public BaseResponse<JSONObject> searchMoudleByGroup(HttpServletRequest request, @RequestBody HopeviewModulePriv hopeviewModulePriv){
        log.info("searchMoudleByGroupStart hopeviewModulePriv:"+JSON.toJSONString(hopeviewModulePriv));
        BaseResponse<JSONObject> response = hopeModuleService.searchMoudleByGroup(hopeviewModulePriv);
        log.info("searchMoudleByGroupEnd Result:"+JSON.toJSONString(response));
        return response;
    }
    /**
    * 功能描述:首页点击视图返回url
     * @param request
     * @param jsonObject
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.impl.HopeModuleServiceImpl.ModuleGroup>>
    * @Author: qinwankang
    * @Date: 2020/5/27 15:27
    */
    @RequestMapping(path = {"/queryUrlBymoduleid"},method = RequestMethod.POST)
    public BaseResponse<String> queryUrlBymoduleid(HttpServletRequest request, @RequestBody JSONObject jsonObject){
        log.info("queryUrlBymoduleidStart para:"+JSON.toJSONString(jsonObject));
        BaseResponse<String> response = hopeModuleService.queryUrlBymoduleid(jsonObject);
        log.info("queryUrlBymoduleidEnd Result:"+JSON.toJSONString(response));
        return response;
    }
/**
* 功能描述:（视图）关注二级页面，关注的视图展示，返回权限范围内所有视图，但分成已关注和
 * 未关注两类返回
 * @param request
 * @param hopePrivGroup
* @return: com.icbc.zsyw.hope3.common.BaseResponse<com.icbc.zsyw.hope3.dto.HopeModule>
* @Author: qinwankang
* @Date: 2020/5/29 11:16
*/
    @RequestMapping(path = {"/queryModuleSub"},method = RequestMethod.POST)
    public BaseResponse<List<HopeModule>> queryModuleSub(HttpServletRequest request, @RequestBody HopePrivGroup hopePrivGroup){
        log.info("queryModuleSubStart hopePrivGroup:"+JSON.toJSONString(hopePrivGroup));
        BaseResponse<List<HopeModule>> response = hopeModuleService.queryModuleSub(hopePrivGroup);
        log.info("queryModuleSubEnd Result:"+JSON.toJSONString(response));
        return response;
    }
    /**
    * 功能描述:（视图二级页面）添加视图，删除视图，改变视图顺序
     * @param request
     * @param jsonObject
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeModule>>
    * @Author: qinwankang
    * @Date: 2020/5/29 16:20
    */
    @RequestMapping(path = {"/editModuleSub"},method = RequestMethod.POST)
    public BaseResponse<Integer> editModuleSub(HttpServletRequest request, @RequestBody JSONObject jsonObject){
        log.info("editModuleSubStart jsonObject:"+JSON.toJSONString(jsonObject));
        BaseResponse<Integer> response = hopeModuleService.editModuleSub(jsonObject);
        log.info("editModuleSubEnd Result:"+JSON.toJSONString(response));
        return response;
    }
    /**
    * 功能描述:（视图二级页面）添加视图，删除视图，改变视图顺序V2
     * @param request
     * @param jsonObject
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Integer>
    * @Author: qinwankang
    * @Date: 2020/6/1 15:59
    */
    @RequestMapping(path = {"/editModuleSubV2"},method = RequestMethod.POST)
    public BaseResponse<Integer> editModuleSubV2(HttpServletRequest request, @RequestBody JSONObject jsonObject){
        log.info("editModuleSubV2Start jsonObject:"+JSON.toJSONString(jsonObject));
        BaseResponse<Integer> response = hopeModuleService.editModuleSubV2(jsonObject);
        log.info("editModuleSubV2End Result:"+JSON.toJSONString(response));
        return response;
    }
    /**
    * 功能描述:修改一行视图（或者分类视图）展开或者收起的状态
     * @param request
     * @param hopeModuleStatus
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Integer>
    * @Author: qinwankang
    * @Date: 2020/6/23 16:05
    */
    @RequestMapping(path = {"/editModuleStatus"},method = RequestMethod.POST)
    public BaseResponse<Object> editModuleStatus(HttpServletRequest request, @RequestBody HopeModuleStatus hopeModuleStatus){
        log.info("editModuleStatusStart hopeModuleStatus:"+JSON.toJSONString(hopeModuleStatus));
        BaseResponse<Object> response = hopeModuleService.editModuleStatus(hopeModuleStatus);
        log.info("editModuleStatusEnd Result:"+JSON.toJSONString(response));
        return response;
    }
    /**
    * 功能描述:修改一行视图（或者分类视图）展开或者收起的状态
     * @param request
     * @param hopeUserConf
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Object>
    * @Author: qinwankang
    * @Date: 2020/7/13 15:51
    */
   @RequestMapping(path = {"/editModuleConf"},method = RequestMethod.POST)
    public BaseResponse<Object> editModuleConf(HttpServletRequest request, @RequestBody HopeUserConf hopeUserConf){
         log.info("editModuleConfStart hopeModuleStatus:"+JSON.toJSONString(hopeUserConf));
        BaseResponse<Object> response = hopeModuleService.editModuleConf(hopeUserConf);
        log.info("editModuleConfEnd Result:"+JSON.toJSONString(response));
        return response;
    }
/**
* 功能描述:首页搜索，名称模糊匹配视图，文章两个维度，每个维度又分为模糊匹配视图和关键字
 * @param request
 * @param jsonObject
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Object>
* @Author: qinwankang
* @Date: 2020/6/29 14:24searchModuleByName
*/
    @RequestMapping(path = {"/searchModuleByName"},method = RequestMethod.POST)
    public BaseResponse<JSONObject> searchModuleByName(HttpServletRequest request, @RequestBody JSONObject jsonObject){
        log.info("searchModuleByNameStart hopeModule:"+JSON.toJSONString(jsonObject));
        BaseResponse<JSONObject> response = hopeModuleService.searchModuleByName(jsonObject);
        log.info("searchModuleByNameEnd Result:"+JSON.toJSONString(response));
        return response;
    }
    /**
    * 功能描述:跳转新搜索页，和首页搜索功能一样，名称模糊匹配视图，文章两个维度，每个维度又分为模糊匹配视图和关键字
     * @param request
     * @param jsonObject
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<com.alibaba.fastjson.JSONObject>
    * @Author: qinwankang
    * @Date: 2020/7/3 9:36
    */
    @RequestMapping(path = {"/searchModuleByNameSec"},method = RequestMethod.POST)
    public BaseResponse<JSONObject> searchModuleByNameSec(HttpServletRequest request, @RequestBody JSONObject jsonObject){
        log.info("searchModuleByNameSecStart hopeModule:"+JSON.toJSONString(jsonObject));
        BaseResponse<JSONObject> response = hopeModuleService.searchModuleByNameSec(jsonObject);
        log.info("searchModuleByNameSecEnd Result:"+JSON.toJSONString(response));
        return response;
    }
    /**
    * 功能描述:文章发现页搜索功能
     * @param request
     * @param jsonObject
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<com.alibaba.fastjson.JSONObject>
    * @Author: qinwankang
    * @Date: 2020/7/3 11:06
    */
    @RequestMapping(path = {"/findPageSearch"},method = RequestMethod.POST)
    public BaseResponse<HopeModuleServiceImpl.SearchDto> findPageSearch(HttpServletRequest request, @RequestBody JSONObject jsonObject){
        log.info("findPageSearchStart hopeModule:"+JSON.toJSONString(jsonObject));
        BaseResponse<HopeModuleServiceImpl.SearchDto> response = hopeModuleService.findPageSearch(jsonObject);
        log.info("findPageSearchEnd Result:"+JSON.toJSONString(response));
        return response;
    }
/**
* 功能描述:点击三大块跳转对应视图页面，页面渲染所需接口
 * @param request
 * @param jsonObject
* @return: com.icbc.zsyw.hope3.common.BaseResponse<com.icbc.zsyw.hope3.impl.HopeModuleServiceImpl.SearchDto>
* @Author: qinwankang
* @Date: 2020/7/10 17:16
*/
    @RequestMapping(path = {"/getModuleByShortCutBar"},method = RequestMethod.POST)
    public BaseResponse<List<HopeModuleServiceImpl.ModuleGroupSub>> getModuleByShortCutBar(HttpServletRequest request, @RequestBody JSONObject jsonObject){
        log.info("getModuleByShortCutBarStart hopeModule:"+JSON.toJSONString(jsonObject));
        BaseResponse<List<HopeModuleServiceImpl.ModuleGroupSub>> response = hopeModuleService.getModuleByShortCutBar(jsonObject);
        log.info("getModuleByShortCutBarEnd Result:"+JSON.toJSONString(response));
        return response;
    }
}
