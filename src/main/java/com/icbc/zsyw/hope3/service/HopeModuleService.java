package com.icbc.zsyw.hope3.service;

import com.alibaba.fastjson.JSONObject;
import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.*;
import com.icbc.zsyw.hope3.impl.HopeModuleServiceImpl;

import java.util.List;

/**
 * @ClassName HopeModuleService
 * @Description
 * @Author qinwankang
 * @Date 2020/5/18 13:45
 * @Version V1.0
 **/
public interface HopeModuleService {
   /**
   * 功能描述:我的关注模块全部视图
    * @param aamid
    * @param deptid
   * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeModule>>
   * @Author: qinwankang
   * @Date: 2020/5/18 18:00
   */
    BaseResponse<List<HopeModule>> queryHopeModule(String aamid, String deptid,String odeptid);
/**
* 功能描述:首页增加我关注模块视图
 * @param hopeUserFavor
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Integer>
* @Author: qinwankang
* @Date: 2020/5/18 18:00
*/
    BaseResponse<Integer> insertWatchHopeModule(HopeUserFavor hopeUserFavor);
/**
* 功能描述:首页删除我的关注模块视图
 * @param hopeUserFavor
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Integer>
* @Author: qinwankang
* @Date: 2020/5/18 21:18
*/
    BaseResponse<Integer> deleteWatchHopeModule(HopeUserFavor hopeUserFavor);
/**
* 功能描述:视图二级页面移动已关注的视图图标的顺序，现在该方法被替代，没用；
 * @param hopeUserFavor
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Integer>
* @Author: qinwankang
* @Date: 2020/5/18 21:27
*/
    BaseResponse<Integer> updateWatchHopeModuleSort(HopeUserFavor hopeUserFavor,HopeUserFavor hopeUserFavorAfter);
/**
* 功能描述:首页一行图标区，默认登录后显示“我的关注”，是所有用户自己关注过的视图。
 * @param hopeUserFavor
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeUserFavor>>
* @Author: qinwankang
* @Date: 2020/5/19 17:01
*/
    BaseResponse<HopeModuleServiceImpl.ModuleFavor> queryWatchHopeModule(HopeUserFavor hopeUserFavor);
/**
* 功能描述:首页“我的足迹”视图显示（最多放7个），以及最后一个更多点击，（最多显示视图图标20个）。
 * @param hopeUserHistory
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeModule>>
* @Author: qinwankang
* @Date: 2020/5/19 17:47
*/
    BaseResponse<List<HopeModuleServiceImpl.ModuleGroupSub>> queryMyFoot(HopeUserHistory hopeUserHistory);
/**
* 功能描述:首页（视图）搜索功能
 * @param hopeModule
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeModule>>
* @Author: qinwankang
* @Date: 2020/5/20 11:24
*/
    BaseResponse<List<HopeModule>> searchMoudleByName(HopeModule hopeModule);
/**
* 功能描述:首页视图按照分组返回
 * @param hopeviewModulePriv
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeModule>>
* @Author: qinwankang
* @Date: 2020/5/22 9:35
*/
    BaseResponse<HopeModuleServiceImpl.ModuleFavor> searchMoudleByGroup(HopeviewModulePriv hopeviewModulePriv);
/**
* 功能描述:首页点击视图返回url
 * @param jsonObject
 * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.impl.HopeModuleServiceImpl.ModuleGroup>>
* @Author: qinwankang
* @Date: 2020/5/27 15:26
*/
BaseResponse<String> queryUrlBymoduleid(JSONObject jsonObject);
/**
* 功能描述:首页增加视图足迹
 * @param hopeUserHistory
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeModule>>
* @Author: qinwankang
* @Date: 2020/5/27 15:56
*/
    BaseResponse insertMyFoot(HopeUserHistory hopeUserHistory);
/**
* 功能描述:视图）关注二级页面，关注的视图展示，返回权限范围内所有视图，但分成已关注和
 *  * 未关注两类返回
 * @param hopePrivGroup
* @return: com.icbc.zsyw.hope3.common.BaseResponse<com.icbc.zsyw.hope3.dto.HopeModule>
* @Author: qinwankang
* @Date: 2020/5/29 11:19
*/
    BaseResponse<List<HopeModule>> queryModuleSub(HopePrivGroup hopePrivGroup);
/**
* 功能描述:（视图二级页面）添加视图，删除视图，改变视图顺序
 * @param jsonObject
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeModule>>
* @Author: qinwankang
* @Date: 2020/5/29 16:23
*/
    BaseResponse<Integer> editModuleSub(JSONObject jsonObject);
/**
* 功能描述:（视图二级页面）添加视图，删除视图，改变视图顺序V2
 * @param jsonObject
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Integer>
* @Author: qinwankang
* @Date: 2020/6/1 16:00
*/
    BaseResponse<Integer> editModuleSubV2(JSONObject jsonObject);
/**
* 功能描述:修改一行视图（或者分类视图）展开或者收起的状态
 * @param hopeModuleStatus
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Integer>
* @Author: qinwankang
* @Date: 2020/6/23 16:06
*/
    BaseResponse<Object> editModuleStatus(HopeModuleStatus hopeModuleStatus);
/**
* 功能描述:首页搜索，名称模糊匹配视图，文章两个维度，每个维度又分为模糊匹配视图和关键字
 * @param jsonObject
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Object>
* @Author: qinwankang
* @Date: 2020/6/29 14:27
*/
    BaseResponse<JSONObject> searchModuleByName(JSONObject jsonObject);
    /**
    * 功能描述:跳转新搜索页，和首页搜索功能一样，名称模糊匹配视图，文章两个维度，每个维度又分为模糊匹配视图和关键字
     * @param jsonObject
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<com.alibaba.fastjson.JSONObject>
    * @Author: qinwankang
    * @Date: 2020/7/3 9:38
    */
    BaseResponse<JSONObject> searchModuleByNameSec(JSONObject jsonObject);
/**
* 功能描述:文章发现页搜索功能
 * @param jsonObject
* @return: com.icbc.zsyw.hope3.common.BaseResponse<com.alibaba.fastjson.JSONObject>
* @Author: qinwankang
* @Date: 2020/7/3 11:09
*/
    BaseResponse<HopeModuleServiceImpl.SearchDto> findPageSearch(JSONObject jsonObject);
/**
* 功能描述:点击三大块跳转对应视图页面，页面渲染所需接口
 * @param jsonObject
* @return: com.icbc.zsyw.hope3.common.BaseResponse<com.icbc.zsyw.hope3.impl.HopeModuleServiceImpl.SearchDto>
* @Author: qinwankang
* @Date: 2020/7/10 17:18
*/
    BaseResponse<List<HopeModuleServiceImpl.ModuleGroupSub>> getModuleByShortCutBar(JSONObject jsonObject);
}
