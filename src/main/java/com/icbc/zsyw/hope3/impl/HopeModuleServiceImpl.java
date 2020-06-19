package com.icbc.zsyw.hope3.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.*;
import com.icbc.zsyw.hope3.enums.*;
import com.icbc.zsyw.hope3.mapper.*;
import com.icbc.zsyw.hope3.service.HopeModuleService;
import com.icbc.zsyw.hope3.util.FiltrateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Consumer;

/**
 * @ClassName HopeModuleServiceImpl
 * @Description
 * @Author qinwankang
 * @Date 2020/5/18 13:47
 * @Version V1.0
 **/
@Service
public class HopeModuleServiceImpl implements HopeModuleService {
    @Resource
    private HopeModuleMapper hopeModuleMapper;
    @Resource
    private HopeUserFavorMapper hopeUserFavorMapper;
    @Resource
    private HopeCommentsMapper hopeCommentsMapper;
    @Resource
    private HopeUserLog_hMapper hopeUserLog_hMapper;
    @Resource
    private HopeUserHistoryMapper hopeUserHistoryMapper;
    /**
     * 功能描述:我的关注模块(二级页面)全部视图，和queryModuleSub方法意义一样，现在被queryModuleSub替代
     * @param aamid
     * @param deptid
     * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeModule>>
     * @Author: qinwankang
     * @Date: 2020/5/18 14 :19
     */
    @Deprecated
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<List<HopeModule>> queryHopeModule(String aamid, String deptid,String odeptid) {
        //入参非空校验
        for (HopePrivRequestEnum headCheckEnum : HopePrivRequestEnum.values()) {
            if (headCheckEnum.isNotEmpty() && StringUtils.isEmpty(aamid) && headCheckEnum.name().toString().equals("aamid"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());
            if(headCheckEnum.isNotEmpty() && StringUtils.isEmpty(deptid) && headCheckEnum.name().toString().equals("deptid"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());
            if(headCheckEnum.isNotEmpty() && StringUtils.isEmpty(odeptid) && headCheckEnum.name().toString().equals("odeptid"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());
        }
      List<HopeModule>hopeModuleList =   hopeModuleMapper.queryHopeModule(aamid,deptid,odeptid);
      if(hopeModuleList==null || hopeModuleList.size()==0){
          return new BaseResponse<List<HopeModule>>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);
      }else{
          return new BaseResponse<List<HopeModule>>(BaseResponse.STATUS_HANDLE_SUCCESS,hopeModuleList,BaseResponse.STATUS_HANDLER_SUCCESS);
      }

    }
/**
* 功能描述:首页增加我关注模块视图
 * @param hopeUserFavor
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Integer>
* @Author: qinwankang
* @Date: 2020/5/18 17:43
*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<Integer> insertWatchHopeModule(HopeUserFavor hopeUserFavor) {
        //入参非空校验
        for (HopeUserFavorRequestEnum headCheckEnum : HopeUserFavorRequestEnum.values()) {
            if (headCheckEnum.isNotEmpty() && StringUtils.isEmpty(hopeUserFavor.getAamid()) && headCheckEnum.name().toString().equals("aamid"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());
            if(headCheckEnum.isNotEmpty() && StringUtils.isEmpty(hopeUserFavor.getModuleid()) && headCheckEnum.name().toString().equals("moduleid"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());

        }
        HopeUserFavor hopeUserFavor1= hopeUserFavorMapper.selectWatchModule(hopeUserFavor);
        if(hopeUserFavor1!=null)
            return new BaseResponse<Integer>(BaseResponse.DATA_STATUS_EXIST,BaseResponse.DATA_STATUS_EXISTER);
        Integer  maxSe =  hopeUserFavorMapper.selectModuleMaxSequence();
        if(maxSe==null){
            maxSe=0;
        }else {
            maxSe++;
        }
        hopeUserFavor.setModulesequence(maxSe);
        hopeUserFavor.setFavortype(UserFavorTypeEnum.shitu.getKey());
        hopeUserFavorMapper.insertSelective(hopeUserFavor);
        return new BaseResponse<Integer>(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
    /**
    * 功能描述:视图二级页面增加我关注模块视图（最终结果）V2
     * @param hopeUserFavor
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Integer>
    * @Author: qinwankang
    * @Date: 2020/6/1 16:06
    */
    private BaseResponse<Integer>  insertWatchHopeModuleV2(HopeUserFavor hopeUserFavor){
        //入参非空校验
        for (HopeUserFavorRequestEnum headCheckEnum : HopeUserFavorRequestEnum.values()) {
            if (headCheckEnum.isNotEmpty() && StringUtils.isEmpty(hopeUserFavor.getAamid()) && headCheckEnum.name().toString().equals("aamid"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());
            if(headCheckEnum.isNotEmpty() && StringUtils.isEmpty(hopeUserFavor.getModuleid()) && headCheckEnum.name().toString().equals("moduleid"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());
            if(headCheckEnum.isNotEmpty() && StringUtils.isEmpty(hopeUserFavor.getModulesequence()) && headCheckEnum.name().toString().equals("modulesequence"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());
        }
            hopeUserFavor.setFavortype(UserFavorTypeEnum.shitu.getKey());
            hopeUserFavorMapper.insert(hopeUserFavor);
        return new BaseResponse<Integer>(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);

    }
/**
* 功能描述:首页删除我的关注模块视图
 * @param hopeUserFavor
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Integer>
* @Author: qinwankang
* @Date: 2020/5/18 21:23
*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<Integer> deleteWatchHopeModule(HopeUserFavor hopeUserFavor) {
        //入参非空校验
        for (HopeUserFavorRequestEnum headCheckEnum : HopeUserFavorRequestEnum.values()) {
            if (headCheckEnum.isNotEmpty() && StringUtils.isEmpty(hopeUserFavor.getAamid()) && headCheckEnum.name().toString().equals("aamid"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());
            if(headCheckEnum.isNotEmpty() && StringUtils.isEmpty(hopeUserFavor.getModuleid()) && headCheckEnum.name().toString().equals("moduleid"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());
        }
        HopeUserFavor hopeUserFavor1= hopeUserFavorMapper.selectWatchModule(hopeUserFavor);
        if(hopeUserFavor1==null)
            return new BaseResponse<Integer>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);
        hopeUserFavorMapper.deleteByAamAndModule(hopeUserFavor.getAamid(),hopeUserFavor.getModuleid());
        return new BaseResponse<Integer>(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
/**
* 功能描述:视图二级页面移动已关注的视图图标的顺序，现在该方法被替代，没用；
 * @param hopeUserFavor
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Integer>
* @Author: qinwankang
* @Date: 2020/5/18 21:34
*/
    @Deprecated
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<Integer> updateWatchHopeModuleSort(HopeUserFavor hopeUserFavor,HopeUserFavor hopeUserFavorAfter) {
        //入参非空校验
        for (HopeUserFavorRequestEnum headCheckEnum : HopeUserFavorRequestEnum.values()) {
            if (headCheckEnum.isNotEmpty() && StringUtils.isEmpty(hopeUserFavor.getAamid()) && headCheckEnum.name().toString().equals("aamid"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());
            if(headCheckEnum.isNotEmpty() && StringUtils.isEmpty(hopeUserFavor.getModuleid()) && headCheckEnum.name().toString().equals("moduleid"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());
            if (headCheckEnum.isNotEmpty() && StringUtils.isEmpty(hopeUserFavorAfter.getAamid()) && headCheckEnum.name().toString().equals("aamid"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());
            if(headCheckEnum.isNotEmpty() && StringUtils.isEmpty(hopeUserFavorAfter.getModuleid()) && headCheckEnum.name().toString().equals("moduleid"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());
            if(!headCheckEnum.isNotEmpty() && StringUtils.isEmpty(hopeUserFavor.getModulesequence()) && headCheckEnum.name().toString().equals("sequence"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());
            if(!headCheckEnum.isNotEmpty() && StringUtils.isEmpty(hopeUserFavorAfter.getModulesequence()) && headCheckEnum.name().toString().equals("sequence"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());

        }
            //HopeUserFavor hopeUserFavor1 = hopeUserFavorMapper.selectByPrimaryKey(hopeUserFavor);
            //if(hopeUserFavor1==null)
             //   return new BaseResponse<>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);
     //       hopeUserFavorMapper.deleteByPrimaryKey(hopeUserFavor);
           // hopeUserFavorMapper.updateFromTo(hopeUserFavor.getAamid(),hopeUserFavor.getSequence(),hopeUserFavorAfter.getSequence());
           // hopeUserFavor.setSequence(hopeUserFavorAfter.getSequence());
      //  HopeUserFavor hopeUserFavor2 = hopeUserFavorMapper.selectByPrimaryKey(hopeUserFavor);
     //   if(hopeUserFavor2!=null)
     //          return new BaseResponse<>(BaseResponse.DATA_STATUS_EXIST,BaseResponse.DATA_STATUS_EXISTER);
            hopeUserFavorMapper.insertSelective(hopeUserFavor);
        return new BaseResponse<Integer>(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
   /**
   * 功能描述:首页一行图标区，默认登录后显示“我的关注”，是所有用户自己关注过的视图。
    * @param hopeUserFavor
   * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeUserFavor>>
   * @Author: qinwankang
   * @Date: 2020/5/19 17:01
   */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<List<HopeModule>> queryWatchHopeModule(HopeUserFavor hopeUserFavor) {
        //入参非空校验
        for (HopeUserFavorRequestEnum headCheckEnum : HopeUserFavorRequestEnum.values()) {
            if (headCheckEnum.isNotEmpty() && StringUtils.isEmpty(hopeUserFavor.getAamid()) && headCheckEnum.name().toString().equals("aamid"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());
        }
        hopeUserFavor.setFavortype(UserFavorTypeEnum.shitu.getKey());
        List<HopeModule> list = hopeUserFavorMapper.queryWatchHopeModule(hopeUserFavor);
        if(list!=null && list.size()!=0){
           return new BaseResponse<List<HopeModule>>(BaseResponse.STATUS_HANDLE_SUCCESS,list,BaseResponse.STATUS_HANDLER_SUCCESS);
        }
        return new BaseResponse<List<HopeModule>>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);
    }
    /**
    * 功能描述:首页“我的足迹”视图显示（最多放7个），以及最后一个更多点击，（最多显示视图图标20个）。
     * @param hopeUserHistory
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeModule>>
    * @Author: qinwankang
    * @Date: 2020/5/19 17:48
    */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<List<HopeModule>> queryMyFoot(HopeUserHistory hopeUserHistory) {
        //入参非空校验
        for (HopeUserFavorRequestEnum headCheckEnum : HopeUserFavorRequestEnum.values()) {
            if (headCheckEnum.isNotEmpty() && StringUtils.isEmpty(hopeUserHistory.getAamid()) && headCheckEnum.name().toString().equals("aamid"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());
        }
        if (HopeUserHistoryRequestEnum.status.isNotEmpty() && StringUtils.isEmpty(hopeUserHistory.getStatus()) )
            return new BaseResponse<>(HopeUserHistoryRequestEnum.status.getReturnCode(), null, HopeUserHistoryRequestEnum.status.getMsg());

        List<HopeModule>list =   hopeModuleMapper.queryMyFoot(hopeUserHistory);
        if(list==null || list.size()==0)
            return new BaseResponse<List<HopeModule>>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);
        BaseResponse<List<HopeModule>> response = checkFoot(list,hopeUserHistory);
        return response;
    }
/**
* 功能描述:首页（视图）搜索功能
 * @param hopeModule
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeModule>>
* @Author: qinwankang
* @Date: 2020/5/20 11:24
*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<List<HopeModule>> searchMoudleByName(HopeModule hopeModule) {
        //入参非空校验
        for (HopeModuleRequestEnum headCheckEnum : HopeModuleRequestEnum.values()) {
            if (headCheckEnum.isNotEmpty() && StringUtils.isEmpty(hopeModule.getModulename()) && headCheckEnum.name().toString().equals("modulename"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());
        }
        List<HopeModule>list = hopeModuleMapper.searchMoudleByName(hopeModule.getModulename());
        if(list==null || list.size()==0){
            return new BaseResponse<>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);
        }
        return new BaseResponse<List<HopeModule>>(BaseResponse.STATUS_HANDLE_SUCCESS,list,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
/**
* 功能描述:首页视图按照分组返回
 * @param hopeviewModulePriv
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeModule>>
* @Author: qinwankang
* @Date: 2020/5/22 9:37
*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<List<ModuleGroup>> searchMoudleByGroup(HopeviewModulePriv hopeviewModulePriv) {
        //入参非空校验
        String aamid = hopeviewModulePriv.getAamid();
        String deptid = hopeviewModulePriv.getDeptid();
        String odeptid = hopeviewModulePriv.getOdeptid();
        for (HopePrivRequestEnum headCheckEnum : HopePrivRequestEnum.values()) {
            if (headCheckEnum.isNotEmpty() && StringUtils.isEmpty(aamid) && headCheckEnum.name().toString().equals("aamid"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());
            if(headCheckEnum.isNotEmpty() && StringUtils.isEmpty(deptid) && headCheckEnum.name().toString().equals("deptid"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());
            if(headCheckEnum.isNotEmpty() && StringUtils.isEmpty(odeptid) && headCheckEnum.name().toString().equals("odeptid"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());
        }
        List<HopeModule>hopeModuleList =   hopeModuleMapper.queryHopeModule(aamid,deptid,odeptid);
        if(hopeModuleList==null || hopeModuleList.size()==0)
            return new BaseResponse<>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);
        List<ModuleGroup>relist = new ArrayList<ModuleGroup>();
        ModuleGroup moduleGroup1 = insertQuanbu(hopeModuleList,aamid);
        relist.add(moduleGroup1);
        List<ModuleGroup>relist1 = insertViewByGroup(relist,hopeModuleList,aamid);
        if(relist1!=null && relist1.size()!=0)
         return new BaseResponse<List<ModuleGroup>>(BaseResponse.STATUS_HANDLE_SUCCESS,relist,BaseResponse.STATUS_HANDLE_SUCCESS);
        return new BaseResponse<>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);
    }
/**
* 功能描述:首页点击视图返回url
 * @param jsonObject
 * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.impl.HopeModuleServiceImpl.ModuleGroup>>
* @Author: qinwankang
* @Date: 2020/5/27 15:29
*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<String> queryUrlBymoduleid(JSONObject jsonObject) {
        //入参非空校验

        JSONObject input = JSONObject.parseObject(JSON.toJSONString(jsonObject));
        if (input.size() == 0) {
            return new BaseResponse<>(BaseResponse.ALL_BLANK, null,
                    BaseResponse.ALL_BLANKER);
        } else {
            for (HopePrivRequestEnum applyCreditQuotaRequestEnum : HopePrivRequestEnum.values()) {
                if (applyCreditQuotaRequestEnum.isNotEmpty()
                        && StringUtils.isEmpty(input.getString(applyCreditQuotaRequestEnum.name()))
                        && null!=input.getString(applyCreditQuotaRequestEnum.name())) {
                    return new BaseResponse<>(applyCreditQuotaRequestEnum.getReturnCode(), null,
                            applyCreditQuotaRequestEnum.getMsg());
                }
            }
        }
        Integer moduleid = jsonObject.getInteger("moduleid");
        String aamid = jsonObject.getString("aamid");
        String deptid = jsonObject.getString("deptid");
        String urlRes = "";
        HopeModule hopeModule1 = hopeModuleMapper.queryUrlBymoduleid(moduleid);
        if(hopeModule1==null)
            return new BaseResponse<>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);
        urlRes=hopeModule1.getUrl();
        if(hopeModule1.getUseurltype()==1){
        urlRes= FiltrateUtil.getModuleUrl(hopeModule1.getUrl(),aamid,deptid);
        }
        return new BaseResponse<String>(BaseResponse.STATUS_HANDLE_SUCCESS,urlRes,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
/**
* 功能描述:首页增加视图足迹
 * @param hopeUserHistory
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeModule>>
* @Author: qinwankang
* @Date: 2020/5/27 15:56
*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse insertMyFoot(HopeUserHistory hopeUserHistory) {
        //入参非空校验
        for (HopePrivRequestEnum headCheckEnum : HopePrivRequestEnum.values()) {
            if (headCheckEnum.isNotEmpty() && StringUtils.isEmpty(hopeUserHistory.getAamid()) && headCheckEnum.name().toString().equals("aamid"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());
            if (headCheckEnum.isNotEmpty() && StringUtils.isEmpty(hopeUserHistory.getModuleid()) && headCheckEnum.name().toString().equals("moduleid"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());
            if (headCheckEnum.isNotEmpty() && StringUtils.isEmpty(hopeUserHistory.getModuleid()) && headCheckEnum.name().toString().equals("logtime"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());
        }
        hopeUserHistoryMapper.insert(hopeUserHistory);
        return new BaseResponse<>(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
    /**
    * 功能描述:（视图）关注二级页面，关注的视图展示，返回权限范围内所有视图，但分成已关注和
     *未关注两类返回，该方法替代queryHopeModule
     * @param hopePrivGroup
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<com.icbc.zsyw.hope3.dto.HopeModule>
    * @Author: qinwankang
    * @Date: 2020/5/29 11:20
    */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<List<HopeModule>> queryModuleSub(HopePrivGroup hopePrivGroup) {
        //入参校验
        BaseResponse paraResponse=   checkParameters(hopePrivGroup);
        if(!BaseResponse.STATUS_HANDLE_SUCCESS.equals(paraResponse.getStatus()) && !BaseResponse.STATUS_HANDLER_SUCCESS.equals(paraResponse.getMessage()))
            return paraResponse;
        List<HopeModule>list = hopeModuleMapper.queryHopeModule(hopePrivGroup.getAamid(),hopePrivGroup.getDeptid(),hopePrivGroup.getOdeptid());
        if(list==null || list.size()==0)
            return new BaseResponse<>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);
        List<HopeUserFavor> hopeUserFavors = hopeUserFavorMapper.queryModuleByAamid(hopePrivGroup.getAamid(),UserFavorTypeEnum.shitu.getKey());
        if(hopeUserFavors==null || hopeUserFavors.size()==0)
            return new BaseResponse<>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);
       if(hopeUserFavors!=null && hopeUserFavors.size()!=0){
            list.stream().forEach(new Consumer<HopeModule>() {
                @Override
                public void accept(HopeModule hopeModule) {
                    hopeUserFavors.stream().forEach(new Consumer<HopeUserFavor>() {
                        @Override
                        public void accept(HopeUserFavor hopeUserFavor) {
                            if(hopeUserFavor.getModuleid()==hopeModule.getModuleid()){
                                hopeModule.setStatus(ModuleStatusEnum.yiguanzhu.getKey());
                                hopeModule.setSequence(hopeUserFavor.getModulesequence());
                                return;
                            }

                        }

                    });
                }
            });
        }
        return new BaseResponse<List<HopeModule>>(BaseResponse.STATUS_HANDLE_SUCCESS,list,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
/**
* 功能描述:（视图二级页面）添加视图，删除视图，改变视图顺序，该方法被editModuleSubV2替代，暂时没用；
 * @param jsonObject
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeModule>>
* @Author: qinwankang
* @Date: 2020/5/29 16:23
*/
    @Deprecated
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<Integer> editModuleSub(JSONObject jsonObject) {
        String aamid = jsonObject.getString("aamid");
        JSONArray addModuleArr = jsonObject.getJSONArray("addModules");
        JSONArray cutModuleArr =jsonObject.getJSONArray("cutModules");
        JSONArray jsonArray = jsonObject.getJSONArray("editModuSort");
        if(StringUtils.isEmpty(aamid)){
            return new BaseResponse<>(HopePrivRequestEnum.aamid.getReturnCode(),HopePrivRequestEnum.aamid.getMsg());
        }
        if(addModuleArr!=null && addModuleArr.size()!=0){
             for(Object moduleid :addModuleArr){
                 HopeUserFavor hopeUserFavor = new HopeUserFavor();
                 hopeUserFavor.setAamid(aamid);
                 hopeUserFavor.setModuleid((Integer) moduleid);
                 BaseResponse<Integer>addresponse=insertWatchHopeModule(hopeUserFavor);
                 if(!BaseResponse.STATUS_HANDLE_SUCCESS.equals(addresponse.getStatus()) || !BaseResponse.STATUS_HANDLER_SUCCESS.equals(addresponse.getMessage())){
                     return addresponse;
                 }
             }
        }
        if(cutModuleArr!=null && cutModuleArr.size()!=0){
            for(Object moduleid :cutModuleArr){
                HopeUserFavor hopeUserFavor = new HopeUserFavor();
                hopeUserFavor.setAamid(aamid);
                hopeUserFavor.setModuleid((Integer) moduleid);
                BaseResponse<Integer>cutresponse=deleteWatchHopeModule(hopeUserFavor);
                if(!BaseResponse.STATUS_HANDLE_SUCCESS.equals(cutresponse.getStatus()) || !BaseResponse.STATUS_HANDLER_SUCCESS.equals(cutresponse.getMessage())){
                    return cutresponse;
                }
            }
        }
        if(jsonArray!=null && jsonArray.size()!=0){
           for(int i = 0;i<jsonArray.size();i++){
               JSONObject jsonObject1 = jsonArray.getJSONObject(i);
               Integer moduleid1 = jsonObject1.getInteger("moduleid");
               Integer sequence1 = jsonObject1.getInteger("sequence");
               HopeUserFavor hopeUserFavor1 = new HopeUserFavor();
               hopeUserFavor1.setModuleid(moduleid1);
               hopeUserFavor1.setAamid(aamid);
              // hopeUserFavor1.setSequence(sequence1);
               BaseResponse<Integer>editresponse =  updateModuleSort(hopeUserFavor1);
               if(!BaseResponse.STATUS_HANDLE_SUCCESS.equals(editresponse.getStatus()) || !BaseResponse.STATUS_HANDLER_SUCCESS.equals(editresponse.getMessage())){
                   return editresponse;
               }
           }
        }
        return new BaseResponse<>(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }

/**
* 功能描述:（视图二级页面）添加视图，删除视图，改变视图顺序V2
 * @param jsonObject
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Integer>
* @Author: qinwankang
* @Date: 2020/6/1 16:02
*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<Integer> editModuleSubV2(JSONObject jsonObject) {
        String aamid = jsonObject.getString("aamid");
        JSONArray addModuleArr = jsonObject.getJSONArray("addModules");
        if(StringUtils.isEmpty(aamid)){
            return new BaseResponse<>(HopePrivRequestEnum.aamid.getReturnCode(),HopePrivRequestEnum.aamid.getMsg());
        }
        if(addModuleArr!=null && addModuleArr.size()!=0){
            List<HopeUserFavor>hflist = hopeUserFavorMapper.queryModuleByAamid(aamid,UserFavorTypeEnum.shitu.getKey());
            if(hflist==null && hflist.size()==0){
                for(int i = 0;i<addModuleArr.size();i++){
                    HopeUserFavor hopeUserFavor = new HopeUserFavor();
                    hopeUserFavor.setAamid(aamid);
                    hopeUserFavor.setModuleid((Integer) addModuleArr.get(i));
                    hopeUserFavor.setModulesequence(i);
                    BaseResponse<Integer>addresponse=insertWatchHopeModuleV2(hopeUserFavor);
                    if(!BaseResponse.STATUS_HANDLE_SUCCESS.equals(addresponse.getStatus()) || !BaseResponse.STATUS_HANDLER_SUCCESS.equals(addresponse.getMessage())){
                        return addresponse;
                    }
                }
            }
            if(hflist!=null && hflist.size()!=0){
                for(HopeUserFavor hopeUserFavor: hflist){
                    int count = 0;
                    for(int i = 0;i<addModuleArr.size();i++){
                       if(hopeUserFavor.getModuleid()==(Integer) addModuleArr.get(i) && hopeUserFavor.getAamid().equals(aamid)){
                           if(hopeUserFavor.getModulesequence()!=i){
                               HopeUserFavor hopeUserFavor2 = new HopeUserFavor();
                               hopeUserFavor2.setAamid(aamid);
                               hopeUserFavor2.setModuleid((Integer) addModuleArr.get(i));
                               hopeUserFavor2.setModulesequence(i);
                               hopeUserFavor2.setFavortype(UserFavorTypeEnum.shitu.getKey());
                               hopeUserFavorMapper.updateHopeUserFavor(hopeUserFavor2);
                           }
                       }else{
                           count++;
                       }
                    }
                    if(addModuleArr.size()==count){
                       hopeUserFavorMapper.deleteByAamAndModule(hopeUserFavor.getAamid(),hopeUserFavor.getModuleid());
                    }
                }
            }

        }
        return new BaseResponse<>(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
    /**
    * 功能描述:该方法暂时作废
     * @param hopeUserFavor
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Integer>
    * @Author: qinwankang
    * @Date: 2020/6/4 10:37
    */
    private BaseResponse<Integer> updateModuleSort(HopeUserFavor hopeUserFavor){
        //body请求体校验
        JSONObject input = JSONObject.parseObject(JSON.toJSONString(hopeUserFavor));
        if (input.size() == 0) {
            return new BaseResponse<Integer>(BaseResponse.ALL_BLANK, null,
                    BaseResponse.ALL_BLANKER);
        } else {
            for (HopeUserFavorRequestEnum applyCreditQuotaRequestEnum : HopeUserFavorRequestEnum.values()) {
                if (applyCreditQuotaRequestEnum.isNotEmpty()
                        && StringUtils.isEmpty(input.getString(applyCreditQuotaRequestEnum.name()))
                        && null!=input.getString(applyCreditQuotaRequestEnum.name())) {
                    return new BaseResponse<Integer>(applyCreditQuotaRequestEnum.getReturnCode(), null,
                            applyCreditQuotaRequestEnum.getMsg());
                }
            }
        }
      // HopeUserFavor hopeUserFavor1 = hopeUserFavorMapper.selectByPrimaryKey(hopeUserFavor);
      //  HopeUserFavor hopeUserFavor2 = hopeUserFavorMapper.queryModuleBySequ(hopeUserFavor);
      //  if(hopeUserFavor2!=null){
      //      return new BaseResponse<>(BaseResponse.DATA_STATUS_EXIST,BaseResponse.DATA_STATUS_EXISTER);
       // }
       // if(hopeUserFavor1!=null && hopeUserFavor1.getSequence()!=hopeUserFavor.getSequence()){
         //   hopeUserFavorMapper.updateByPrimaryKey(hopeUserFavor);
        //    return new BaseResponse<>(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
       // }
    return new BaseResponse<>(BaseResponse.STATUS_SYSTEM_FAILURE,BaseResponse.STATUS_SYSTEM_FAILUREE);
    }

    /**
    * 功能描述:（视图）关注二级页面，关注的视图展示，返回权限范围内所有视图，但分成已关注和
     *      *  * 未关注两类返回参数非空校验
     * @param hopePrivGroup
    * @return: com.icbc.zsyw.hope3.common.BaseResponse
    * @Author: qinwankang
    * @Date: 2020/5/29 14:25
    */
    private BaseResponse checkParameters(HopePrivGroup hopePrivGroup){
       // JSONObject input = JSONObject.parseObject(JSON.toJSONString(hopePrivGroup));
        JSONObject input = (JSONObject) JSONObject.parse(JSON.toJSONString(hopePrivGroup));
       // JSONObject json = JSONObject.fromObject(hopePrivGroup);
        if (input.size() == 0) {
            return new BaseResponse<Object>(BaseResponse.ALL_BLANK, null,
                    BaseResponse.ALL_BLANKER);

        } else {
            for (HopePrivRequestEnum applyCreditQuotaRequestEnum : HopePrivRequestEnum.values()) {
                if (applyCreditQuotaRequestEnum.isNotEmpty()
                        && StringUtils.isEmpty(input.getString(applyCreditQuotaRequestEnum.name()))
                        && null!=input.getString(applyCreditQuotaRequestEnum.name())) {
                    return new BaseResponse<Object>(applyCreditQuotaRequestEnum.getReturnCode(), null,
                            applyCreditQuotaRequestEnum.getMsg());
                }
            }
        }
        return new BaseResponse(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }

    public static void main(String[] args) {
        List<HopeUserFavor> list = new ArrayList<HopeUserFavor>();
        HopeUserFavor hopeUserFavor = new HopeUserFavor();
        HopeUserFavor hopeUserFavor1 = new HopeUserFavor();
        HopeUserFavor hopeUserFavor2 = new HopeUserFavor();
        HopeUserFavor hopeUserFavor3 = new HopeUserFavor();
        HopeUserFavor hopeUserFavor4 = new HopeUserFavor();
        hopeUserFavor.setModuleid(1);
        hopeUserFavor1.setModuleid(2);
        hopeUserFavor2.setModuleid(3);  hopeUserFavor3.setModuleid(4);
        list.add(hopeUserFavor);list.add(hopeUserFavor1);list.add(hopeUserFavor2);
        list.add(hopeUserFavor3);
        list.stream().forEach(new Consumer<HopeUserFavor>() {
            @Override
            public void accept(HopeUserFavor hopeUserFavor) {
                System.out.println("第一次显示"+hopeUserFavor.getModuleid());
                if (hopeUserFavor.getModuleid()==3)
                    return;
                System.out.println("第二次显示"+hopeUserFavor.getModuleid());
            }
        });
    }
    /**
    * 功能描述:将数据库返回的视图按照视图分组名称分类然后组成集合返回
     * @param relist
     * @param hopeModuleList
     * @param aamid
    * @return: java.util.List<com.icbc.zsyw.hope3.impl.HopeModuleServiceImpl.ModuleGroup>
    * @Author: qinwankang
    * @Date: 2020/5/22 16:52
    */
    private List<ModuleGroup> insertViewByGroup(List<ModuleGroup>relist,List<HopeModule> hopeModuleList,String aamid){
        for (HopeModuleGroupEnum hopeModuleGroupEnum : HopeModuleGroupEnum.values()) {
            if(hopeModuleGroupEnum.getKey().equals(HopeModuleGroupEnum.quanbu.getKey()))
                continue;
            List<ModuleGroupSub>sublist = new ArrayList<ModuleGroupSub>();
            for(HopeModule hopeModule: hopeModuleList){
                if (hopeModuleGroupEnum.getValue().equals(hopeModule.getModulegroupname())){
                    ModuleGroupSub moduleGroupSub = new ModuleGroupSub();
                    moduleGroupSub.setModuleid(hopeModule.getModuleid());
                    moduleGroupSub.setIcon(hopeModule.getIcon());
                    moduleGroupSub.setImage(hopeModule.getImage());
                    moduleGroupSub.setSureCount(hopeCommentsMapper.sureCount(hopeModule));
                    IdentifySub identifySub = new IdentifySub();
                    Map<Integer,Boolean> map = checkAamSure(aamid,hopeModule.getModuleid());
                    identifySub.setShadeImg1(map.get(HopeCommentsEnum.dianzan.getKey()));
                    identifySub.setShadeImg2(map.get(HopeCommentsEnum.diancai.getKey()));
                    identifySub.setShadeImg(map.get(HopeCommentsEnum.guanzhu.getKey()));
                    moduleGroupSub.setIdentifySub(identifySub);
                    moduleGroupSub.setTel(hopeUserLog_hMapper.queryUserLog_h(hopeModule.getModuleid()));
                    moduleGroupSub.setText(hopeModule.getDescription());
                    moduleGroupSub.setTitle(hopeModule.getModulename());
                    sublist.add(moduleGroupSub);
                }
            }
            ModuleGroup moduleGroup = new ModuleGroup();
            moduleGroup.setModulegroupType(hopeModuleGroupEnum.getValue());
            moduleGroup.setList(sublist);
            relist.add(moduleGroup);
        }
        return relist;
    }
    /**
    * 功能描述:查出全部视图作为集合放到ModuleGroup实体类返回
     * @param hopeModuleList
     * @param aamid
    * @return: com.icbc.zsyw.hope3.impl.HopeModuleServiceImpl.ModuleGroup
    * @Author: qinwankang
    * @Date: 2020/5/22 16:43
    */
    private ModuleGroup insertQuanbu(List<HopeModule> hopeModuleList,String aamid){
        List<ModuleGroupSub>sublist = new ArrayList<ModuleGroupSub>();
        for(HopeModule hopeModule: hopeModuleList){
                ModuleGroupSub moduleGroupSub = new ModuleGroupSub();
                moduleGroupSub.setModuleid(hopeModule.getModuleid());
                moduleGroupSub.setIcon(hopeModule.getIcon());
                moduleGroupSub.setImage(hopeModule.getImage());
                moduleGroupSub.setSureCount(hopeCommentsMapper.sureCount(hopeModule));
                IdentifySub identifySub = new IdentifySub();
                Map<Integer,Boolean> map = checkAamSure(aamid,hopeModule.getModuleid());
                identifySub.setShadeImg1(map.get(HopeCommentsEnum.dianzan.getKey()));
                identifySub.setShadeImg2(map.get(HopeCommentsEnum.diancai.getKey()));
                identifySub.setShadeImg(map.get(HopeCommentsEnum.guanzhu.getKey()));
                moduleGroupSub.setIdentifySub(identifySub);
                moduleGroupSub.setTel(hopeUserLog_hMapper.queryUserLog_h(hopeModule.getModuleid()));
                moduleGroupSub.setText(hopeModule.getDescription());
                moduleGroupSub.setTitle(hopeModule.getModulename());
                sublist.add(moduleGroupSub);
        }

        ModuleGroup moduleGroup = new ModuleGroup();
        moduleGroup.setModulegroupType(HopeModuleGroupEnum.quanbu.getValue());
        moduleGroup.setList(sublist);
        return moduleGroup;
    }
    /**
    * 功能描述:判断用户当日是否对视图点赞，没点赞返回true,否则false
     * @param aamid
     * @param moduleid
    * @return: boolean
    * @Author: qinwankang
    * @Date: 2020/5/22 14:05
    */
    private Map<Integer,Boolean>  checkAamSure(String aamid,Integer moduleid){
      //不需要入参非空校验
      List<HopeComments> hopeCommentsList= hopeCommentsMapper.checkAamSure(aamid,moduleid);
      HopeUserFavor hopeUserFavor = new HopeUserFavor();
      hopeUserFavor.setAamid(aamid);
      hopeUserFavor.setModuleid(moduleid);
      hopeUserFavor.setFavortype(UserFavorTypeEnum.shitu.getKey());
      HopeUserFavor hopeUserFavor1 = hopeUserFavorMapper.selectWatchModule(hopeUserFavor);
      Map<Integer,Boolean> sureMap = new HashMap<Integer,Boolean>();
      sureMap.put(HopeCommentsEnum.dianzan.getKey(),false);
      sureMap.put(HopeCommentsEnum.diancai.getKey(),false);
      sureMap.put(HopeCommentsEnum.guanzhu.getKey(),false);
      if(hopeUserFavor1!=null){
          sureMap.put(HopeCommentsEnum.guanzhu.getKey(),true);
      }
      if(hopeCommentsList==null){
          //该用户没有点过赞
             return sureMap;
      }
        Calendar calendar = Calendar.getInstance();
      for(HopeComments hopeComments:hopeCommentsList){
          Date hdate = hopeComments.getLogtime();
          Calendar calendar1 = Calendar.getInstance();
          calendar1.setTime(hdate);
          if(calendar1.get(Calendar.YEAR)==calendar.get(Calendar.YEAR)){
              if(calendar1.get(Calendar.MONTH)+1==calendar.get(Calendar.MONTH)+1){
                  if(calendar1.get(Calendar.DAY_OF_MONTH)==calendar.get(Calendar.DAY_OF_MONTH)){
                     if(hopeComments.getComments()!=null && hopeComments.getComments()==HopeCommentsEnum.dianzan.getKey()){
                         sureMap.put(HopeCommentsEnum.dianzan.getKey(),true);
                     }
                      if(hopeComments.getComments()!=null && hopeComments.getComments()==HopeCommentsEnum.diancai.getKey()){
                          sureMap.put(HopeCommentsEnum.diancai.getKey(),true);
                      }
                  }
              }
          }
      }
        return sureMap;
    }
    /**
    * 功能描述:视图按组分类返回，其中每个分组所需的实体类
     * @param
    * @return:
    * @Author: qinwankang
    * @Date: 2020/5/22 10:09
    */
    public class ModuleGroup<List>{
        private String modulegroupType;
        private  List list;

        public String getModulegroupType() {
            return modulegroupType;
        }

        public List getList() {
            return list;
        }

        public void setModulegroupType(String modulegroupType) {
            this.modulegroupType = modulegroupType;
        }

        public void setList(List list) {
            this.list = list;
        }
    }
    /**
    * 功能描述:视图按组分类返回，其中组实体类当中list所包含元素对应的实体类
     * @param
    * @return:
    * @Author: qinwankang
    * @Date: 2020/5/22 13:53
    */
    public class ModuleGroupSub<IdentifySub>{
       private Integer moduleid;
       private String title;
       private String text;
       //访问量
       private Integer tel;
       //点赞量
        private Integer sureCount;

        private String icon;

        private String image;

        private IdentifySub identifySub;

        public Integer getModuleid() {
            return moduleid;
        }

        public void setModuleid(Integer moduleid) {
            this.moduleid = moduleid;
        }

        public IdentifySub getIdentifySub() {
            return identifySub;
        }

        public void setIdentifySub(IdentifySub identifySub) {
            this.identifySub = identifySub;
        }

        public String getTitle() {
            return title;
        }

        public String getText() {
            return text;
        }

        public Integer getTel() {
            return tel;
        }

        public Integer getSureCount() {
            return sureCount;
        }

        public String getIcon() {
            return icon;
        }

        public String getImage() {
            return image;
        }



        public void setTitle(String title) {
            this.title = title;
        }

        public void setText(String text) {
            this.text = text;
        }

        public void setTel(Integer tel) {
            this.tel = tel;
        }

        public void setSureCount(Integer sureCount) {
            this.sureCount = sureCount;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public void setImage(String image) {
            this.image = image;
        }


    }
    /**
    * 功能描述:视图按组返回，其中组实体类list里面元素又包含一个实体类，其具体信息如下
     * @param
    * @return:
    * @Author: qinwankang
    * @Date: 2020/5/22 13:55
    */
    public class IdentifySub{
        //点赞
        private boolean shadeImg1;
        //点踩
        private boolean shadeImg2;
        //关注，默认没关注
        private boolean shadeImg=false;
       //点赞，点踩，收藏页面默认不出现
        private boolean shadeShow=false;
        //点踩页面默认不出现
        private boolean trampleShow=false;


        public boolean isShadeImg() {
            return shadeImg;
        }


        public boolean isShadeImg1() {
            return shadeImg1;
        }

        public boolean isShadeImg2() {
            return shadeImg2;
        }

        public boolean isShadeShow() {
            return shadeShow;
        }

        public boolean isTrampleShow() {
            return trampleShow;
        }


        public void setShadeImg(boolean shadeImg) {
            this.shadeImg = shadeImg;
        }


        public void setShadeImg1(boolean shadeImg1) {
            this.shadeImg1 = shadeImg1;
        }

        public void setShadeImg2(boolean shadeImg2) {
            this.shadeImg2 = shadeImg2;
        }

        public void setShadeShow(boolean shadeShow) {
            this.shadeShow = shadeShow;
        }

        public void setTrampleShow(boolean trampleShow) {
            this.trampleShow = trampleShow;
        }


    }
    /**
    * 功能描述:判断我的足迹是显示最近，还是显示全部，然后“我的足迹”最多放7个，最后一个更多还是视图图标20个。
     * @param list
     * @param hopeUserHistory
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeModule>>
    * @Author: qinwankang
    * @Date: 2020/5/19 21:50
    */
    private BaseResponse<List<HopeModule>> checkFoot(List<HopeModule> list,HopeUserHistory hopeUserHistory){
        //显示全部足迹，最多显示20条
        if(hopeUserHistory.getStatus()==0){
            if(list.size()<=20){
                return new BaseResponse<List<HopeModule>>(BaseResponse.STATUS_HANDLE_SUCCESS,list,BaseResponse.STATUS_HANDLER_SUCCESS);
            }
            List<HopeModule>relist = new ArrayList<HopeModule>();
            for(int i = 0;i<20;i++){
                relist.add(list.get(i));
            }
            return new BaseResponse<List<HopeModule>>(BaseResponse.STATUS_HANDLE_SUCCESS,relist,BaseResponse.STATUS_HANDLER_SUCCESS);
        }else if(hopeUserHistory.getStatus()==1){
            //显示我的足迹，最多显示7条
            if(list.size()<=7){
                return new BaseResponse<List<HopeModule>>(BaseResponse.STATUS_HANDLE_SUCCESS,list,BaseResponse.STATUS_HANDLER_SUCCESS);
            }
            List<HopeModule>relist = new ArrayList<HopeModule>();
            for(int i = 0;i<7;i++){
                relist.add(list.get(i));
            }
            return new BaseResponse<List<HopeModule>>(BaseResponse.STATUS_HANDLE_SUCCESS,relist,BaseResponse.STATUS_HANDLER_SUCCESS);

        }
        return new BaseResponse<List<HopeModule>>(BaseResponse.STATUS_SYSTEM_FAILURE,BaseResponse.STATUS_SYSTEM_FAILUREE);
    }
}
