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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
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
@Slf4j
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
    @Resource
    private HopeModuleStatusMapper hopeModuleStatusMapper;
    @Resource
    private HopeSearchkeyMapper hopeSearchkeyMapper;
    @Resource
    private HopeActicityMapper hopeActicityMapper;
    @Resource
    private HopeActivityLogMapper hopeActivityLogMapper;
    @Resource
    private HopeUserLogMapper hopeUserLogMapper;
    @Resource
    private HopeUserConfMapper hopeUserConfMapper;
    @Resource
    private HopeViewTimesMapper hopeViewTimesMapper;
    @Value("${img.local.path}")
    private String imgLoaclPath;

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
      //  for (HopeUserFavorRequestEnum headCheckEnum : HopeUserFavorRequestEnum.values()) {
            if (HopeUserFavorRequestEnum.aamid.isNotEmpty() && StringUtils.isEmpty(hopeUserFavor.getAamid()) )
                return new BaseResponse<>(HopeUserFavorRequestEnum.aamid.getReturnCode(), null, HopeUserFavorRequestEnum.aamid.getMsg());
            if(HopeUserFavorRequestEnum.moduleid.isNotEmpty() && StringUtils.isEmpty(hopeUserFavor.getModuleid()) )
                return new BaseResponse<>(HopeUserFavorRequestEnum.moduleid.getReturnCode(), null, HopeUserFavorRequestEnum.moduleid.getMsg());

       // }
        Integer hopeUserFavor1= hopeUserFavorMapper.selectWatchModule(hopeUserFavor);
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
    private BaseResponse<Integer>  insertWatchHopeModuleV2(List<HopeUserFavor> hopeUserFavor){
        //入参非空校验
      //  for (HopeUserFavorRequestEnum headCheckEnum : HopeUserFavorRequestEnum.values()) {
           /* if (StringUtils.isEmpty(hopeUserFavor.getAamid()) )
                return new BaseResponse<>(HopeUserFavorRequestEnum.aamid.getReturnCode(), null, HopeUserFavorRequestEnum.aamid.getMsg());
            if(StringUtils.isEmpty(hopeUserFavor.getModuleid()) )
                return new BaseResponse<>(HopeUserFavorRequestEnum.moduleid.getReturnCode(), null, HopeUserFavorRequestEnum.moduleid.getMsg());
            if( StringUtils.isEmpty(hopeUserFavor.getModulesequence()))*/
                //return new BaseResponse<>(HopeUserFavorRequestEnum.modulesequence.getReturnCode(), null, HopeUserFavorRequestEnum.modulesequence.getMsg());
        //}
            //hopeUserFavor.setFavortype(UserFavorTypeEnum.shitu.getKey());
          //  hopeUserFavorMapper.insert(hopeUserFavor);
        try {
            hopeUserFavorMapper.insertFavorList(hopeUserFavor);
        }catch (Exception e){
            return new BaseResponse<>(BaseResponse.STATUS_SYSTEM_FAILURE,BaseResponse.STATUS_SYSTEM_FAILUREE);
        }
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
        //for (HopeUserFavorRequestEnum headCheckEnum : HopeUserFavorRequestEnum.values()) {
            if (StringUtils.isEmpty(hopeUserFavor.getAamid()) )
                return new BaseResponse<>(HopeUserFavorRequestEnum.aamid.getReturnCode(), null, HopeUserFavorRequestEnum.aamid.getMsg());
            if(StringUtils.isEmpty(hopeUserFavor.getModuleid()))
                return new BaseResponse<>(HopeUserFavorRequestEnum.moduleid.getReturnCode(), null, HopeUserFavorRequestEnum.moduleid.getMsg());
      //  }
        Integer hopeUserFavor1= hopeUserFavorMapper.selectWatchModule(hopeUserFavor);
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
                return new BaseResponse<>(HopeUserFavorRequestEnum.aamid.getReturnCode(), null, HopeUserFavorRequestEnum.aamid.getMsg());
            if(headCheckEnum.isNotEmpty() && StringUtils.isEmpty(hopeUserFavor.getModuleid()) && headCheckEnum.name().toString().equals("moduleid"))
                return new BaseResponse<>(HopeUserFavorRequestEnum.moduleid.getReturnCode(), null, HopeUserFavorRequestEnum.moduleid.getMsg());
            if (headCheckEnum.isNotEmpty() && StringUtils.isEmpty(hopeUserFavorAfter.getAamid()) && headCheckEnum.name().toString().equals("aamid"))
                return new BaseResponse<>(HopeUserFavorRequestEnum.aamid.getReturnCode(), null, HopeUserFavorRequestEnum.aamid.getMsg());
            if(headCheckEnum.isNotEmpty() && StringUtils.isEmpty(hopeUserFavorAfter.getModuleid()) && headCheckEnum.name().toString().equals("moduleid"))
                return new BaseResponse<>(HopeUserFavorRequestEnum.moduleid.getReturnCode(), null, HopeUserFavorRequestEnum.moduleid.getMsg());
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
    public BaseResponse<ModuleFavor> queryWatchHopeModule(HopeUserFavor hopeUserFavor) {

        //入参非空校验
       // for (HopeUserFavorRequestEnum headCheckEnum : HopeUserFavorRequestEnum.values()) {
            if (StringUtils.isEmpty(hopeUserFavor.getAamid()))
                return new BaseResponse<>(HopeUserFavorRequestEnum.aamid.getReturnCode(), null, HopeUserFavorRequestEnum.aamid.getMsg());
        //}
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String webUrlq =imgLoaclPath;
        hopeUserFavor.setFavortype(UserFavorTypeEnum.shitu.getKey());
        List<HopeModule> list = hopeUserFavorMapper.queryWatchHopeModule(hopeUserFavor);
        if(list!=null && list.size()!=0){
            for(HopeModule hopeModule:list){
                String icon = hopeModule.getIcon();
                try {
                    icon= java.net.URLDecoder.decode(icon,"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                hopeModule.setIcon(webUrlq+icon);
            }
        }
        //String moduleStatus=   hopeModuleStatusMapper.selectByaamidAndtype(hopeUserFavor.getAamid(),HopeModuleTypeEnum.yihang.getKey());
        Integer mouleStautus= hopeUserConfMapper.selectByaamidAndName(hopeUserFavor.getAamid(),HopeModuleTypeEnum.yihang.getValue());
        BaseResponse<ModuleFavor> moduleFavorBaseResponse = checkModuelStauts(list,mouleStautus);
        return moduleFavorBaseResponse;
    }
    /**
    * 功能描述:查询视图（展开或者收起）状态
     * @param list
     * @param moduleStatus
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<com.icbc.zsyw.hope3.impl.HopeModuleServiceImpl.ModuleFavor>
    * @Author: qinwankang
    * @Date: 2020/6/23 16:33
    */
    private BaseResponse<ModuleFavor> checkModuelStauts(List list,Integer moduleStatus){
        if(StringUtils.isEmpty(moduleStatus)|| null==moduleStatus){
            if(list!=null && list.size()!=0){
                ModuleFavor moduleFavor = new ModuleFavor();
                moduleFavor.setMstatus(true);
                moduleFavor.setList(list);
                return new BaseResponse<ModuleFavor>(BaseResponse.STATUS_HANDLE_SUCCESS,moduleFavor,BaseResponse.STATUS_HANDLER_SUCCESS);
            }else{
                ModuleFavor moduleFavor = new ModuleFavor();
                moduleFavor.setMstatus(true);
                moduleFavor.setList(new ArrayList());
                return new BaseResponse<ModuleFavor>(BaseResponse.STATUS_HANDLE_SUCCESS,moduleFavor,BaseResponse.STATUS_HANDLER_SUCCESS);

            }
        }else{
            if(list!=null && list.size()!=0){
                ModuleFavor moduleFavor = new ModuleFavor();
                moduleFavor.setMstatus(moduleStatus==1?false:true);
                moduleFavor.setList(list);
                return new BaseResponse<ModuleFavor>(BaseResponse.STATUS_HANDLE_SUCCESS,moduleFavor,BaseResponse.STATUS_HANDLER_SUCCESS);
            }else{
                ModuleFavor moduleFavor = new ModuleFavor();
                moduleFavor.setMstatus(moduleStatus==1?false:true);
                moduleFavor.setList(new ArrayList());
                return new BaseResponse<ModuleFavor>(BaseResponse.STATUS_HANDLE_SUCCESS,moduleFavor,BaseResponse.STATUS_HANDLER_SUCCESS);
            }
        }
    }
    /**
    * 功能描述:一行视图区返回结果的实体类
     * @param
    * @return:
    * @Author: qinwankang
    * @Date: 2020/6/23 15:10
    */
    public class ModuleFavor{
        private boolean mstatus;
        private List list;

        public boolean isMstatus() {
            return mstatus;
        }

        public List getList() {
            return list;
        }

        public void setMstatus(boolean mstatus) {
            this.mstatus = mstatus;
        }

        public void setList(List list) {
            this.list = list;
        }
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
    public BaseResponse<List<ModuleGroupSub>> queryMyFoot(HopeUserHistory hopeUserHistory) {
        //入参非空校验
        /*for (HopeUserFavorRequestEnum headCheckEnum : HopeUserFavorRequestEnum.values()) {*/
            if ( StringUtils.isEmpty(hopeUserHistory.getAamid()) )
                return new BaseResponse<>(HopeUserFavorRequestEnum.aamid.getReturnCode(), null, HopeUserFavorRequestEnum.aamid.getMsg());
      /*  }*/
        if (HopeUserHistoryRequestEnum.status.isNotEmpty() && StringUtils.isEmpty(hopeUserHistory.getStatus()) )
            return new BaseResponse<>(HopeUserHistoryRequestEnum.status.getReturnCode(), null, HopeUserHistoryRequestEnum.status.getMsg());

        List<HopeModule>list =   hopeModuleMapper.queryMyFoot(hopeUserHistory);

        if(list==null || list.size()==0)
            return new BaseResponse<List<ModuleGroupSub>>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String webUrlq =imgLoaclPath;
     //   Set<HopeModule>moduleSet = new HashSet<>();
        //list去重
       // list =FiltrateUtil.quChongXianglin(list);
        /*for(HopeModule hm:list){
            hm.setIcon(webUrlq+hm.getIcon());
      //      moduleSet.add(hm);
        }*/
      //  BaseResponse<List<HopeModule>> response = checkFoot(list,hopeUserHistory);
       // String mStatus = hopeModuleStatusMapper.selectByaamidAndtype(hopeUserHistory.getAamid(),HopeModuleTypeEnum.yihang.getKey());
        /*List<HopeModule>hlist= new ArrayList<HopeModule>();

        for(HopeModule hopeModule:moduleSet){
            hlist.add(hopeModule);
        }*/
      //  BaseResponse<List<HopeModule>> response = checkFoot(list,hopeUserHistory);
        List<ModuleGroupSub>footlist=getFootType(list,hopeUserHistory.getAamid());

        //   BaseResponse<ModuleFavor>moduleFavorBaseResponse=checkModuelStauts(footlist,mStatus);
        return new BaseResponse<List<ModuleGroupSub>>(BaseResponse.STATUS_HANDLE_SUCCESS,footlist,BaseResponse.STATUS_HANDLER_SUCCESS);
    }

    private List<ModuleGroupSub> getFootType(List<HopeModule> data, String aamid) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String webUrlq =imgLoaclPath;
        List<ModuleGroupSub>sublist = new ArrayList<ModuleGroupSub>();
        for(HopeModule hopeModule: data){
                    ModuleGroupSub moduleGroupSub = new ModuleGroupSub();
                    moduleGroupSub.setModuleid(hopeModule.getModuleid());
                    String icon = hopeModule.getIcon();
            try {
                icon=java.net.URLDecoder.decode(icon,"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            moduleGroupSub.setIcon(webUrlq+icon);
            String imageStr=hopeModule.getImage();
            try {
                imageStr=java.net.URLDecoder.decode(imageStr,"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            moduleGroupSub.setImage(webUrlq+FiltrateUtil.getModuleSmallImage(imageStr));
            String mUrl=hopeModule.getUrl();
            try {
                mUrl=java.net.URLDecoder.decode(mUrl,"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            moduleGroupSub.setUrl(mUrl);
                    moduleGroupSub.setModulename(hopeModule.getModulename());
                    moduleGroupSub.setShortname(hopeModule.getShortname());
                    moduleGroupSub.setSureCount(hopeCommentsMapper.sureCount(hopeModule.getModuleid(),HopeCommentsEnum.dianzan.getKey()));
                    IdentifySub identifySub = new IdentifySub();
                    Map<Integer,Boolean> map = checkAamSure(aamid,hopeModule.getModuleid());
                    identifySub.setShadeImg1(map.get(HopeCommentsEnum.dianzan.getKey()));
                    identifySub.setShadeImg2(map.get(HopeCommentsEnum.diancai.getKey()));
                    identifySub.setShadeImg(map.get(HopeCommentsEnum.guanzhu.getKey()));
                    moduleGroupSub.setIdentifySub(identifySub);
                    moduleGroupSub.setTel(hopeUserLogMapper.queryUserLog_h(hopeModule.getModuleid()));
                    moduleGroupSub.setText(hopeModule.getDescription());
                    moduleGroupSub.setTitle(hopeModule.getModulename());
                    moduleGroupSub.setFootTime(hopeModule.getFootTime().getTime());
                    sublist.add(moduleGroupSub);
        }
        return sublist;
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
      //  for (HopeModuleRequestEnum headCheckEnum : HopeModuleRequestEnum.values()) {
            if (StringUtils.isEmpty(hopeModule.getModulename()) )
                return new BaseResponse<>(HopeModuleRequestEnum.modulename.getReturnCode(), null, HopeModuleRequestEnum.modulename.getMsg());
        //}
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
    public BaseResponse<JSONObject> searchMoudleByGroup(HopeviewModulePriv hopeviewModulePriv) {
        //入参非空校验
        String aamid = hopeviewModulePriv.getAamid();
        String deptid = hopeviewModulePriv.getDeptid();
        String odeptid = hopeviewModulePriv.getOdeptid();
        //for (HopePrivRequestEnum headCheckEnum : HopePrivRequestEnum.values()) {
            if (StringUtils.isEmpty(aamid))
                return new BaseResponse<>(HopePrivRequestEnum.aamid.getReturnCode(), null, HopePrivRequestEnum.aamid.getMsg());
            if(StringUtils.isEmpty(deptid) )
                return new BaseResponse<>(HopePrivRequestEnum.deptid.getReturnCode(), null, HopePrivRequestEnum.deptid.getMsg());
            if(StringUtils.isEmpty(odeptid))
                return new BaseResponse<>(HopePrivRequestEnum.odeptid.getReturnCode(), null, HopePrivRequestEnum.odeptid.getMsg());
       // }
        List<HopeModule>hopeModuleList =   hopeModuleMapper.queryHopeModule(aamid,deptid,odeptid);
        if(hopeModuleList==null || hopeModuleList.size()==0)
            return new BaseResponse<>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);
        //视图排序
        hopeModuleList=FiltrateUtil.sortModule(hopeModuleList);
      /* Set<String>modulegroupSet=new HashSet<String>();
       for(HopeModule m:hopeModuleList){
           modulegroupSet.add(m.getModulegroupname());
        }
       if(modulegroupSet==null || modulegroupSet.size()==0){
           return new BaseResponse<>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);
       }*/
       //打乱视图顺序
       // Collections.shuffle(hopeModuleList);
     //   List<ModuleGroup>relist = new ArrayList<ModuleGroup>();
      //  String mStatus = hopeModuleStatusMapper.selectByaamidAndtype(aamid,HopeModuleTypeEnum.fenzu.getKey());
        //String moduleStatus=   hopeModuleStatusMapper.selectByaamidAndtype(hopeUserFavor.getAamid(),HopeModuleTypeEnum.yihang.getKey());
        Integer mouleStautus= hopeUserConfMapper.selectByaamidAndName(aamid,HopeModuleTypeEnum.fenzu.getValue());

        JSONObject moduleGroup1 = insertQuanbu(hopeModuleList,aamid,mouleStautus);
        Set<String>modulegroupSet = (Set<String>) moduleGroup1.get("moduleGroupSet");
        List<ModuleGroupSub>moduleGroupList = (List<ModuleGroupSub>) moduleGroup1.get("sublist");
        //relist.add(moduleGroup1);
       // List<ModuleGroup>relist1 = insertViewByGroup(relist,hopeModuleList,aamid,mouleStautus);
       // String mStatus = hopeModuleStatusMapper.selectByaamidAndtype(aamid,HopeModuleTypeEnum.fenzu.getKey());
        //List<ModuleGroup>relist1 = checkModuleImage(relist1,mStatus);
      //  BaseResponse<ModuleFavor>moduleFavorBaseResponse = checkModuelStauts(relist1,mouleStautus);
        List<HopeViewTimes>reVlist=getHopeViewTimes();
        List<HopeComments>dianzanList= getMoudledianZan(HopeCommentsEnum.dianzan.getKey());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("allModules",moduleGroupList);
        jsonObject.put("modulegroupSet",modulegroupSet);
        jsonObject.put("reVlist",reVlist);
        jsonObject.put("dianzanList",dianzanList);
        if(!StringUtils.isEmpty(mouleStautus)){
            jsonObject.put("mstatus",mouleStautus==1?false:true);
        }else {
            jsonObject.put("mstatus",true);
        }
        if(jsonObject.size()==0 || jsonObject==null){
            return new BaseResponse<>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);
        }
        return new BaseResponse<JSONObject>(BaseResponse.STATUS_HANDLE_SUCCESS,jsonObject,BaseResponse.STATUS_HANDLER_SUCCESS);

    }
    //获取每个视图对应的访问量
    private List<HopeViewTimes> getHopeViewTimes(){
        List<HopeViewTimes>vList = hopeViewTimesMapper.queryViewTimesByGroup();
        if(vList!=null&&vList.size()!=0){
            List<HopeViewTimes>revList=new ArrayList<HopeViewTimes>();
            Set<Integer>moduleSet = new HashSet<Integer>();
            for(HopeViewTimes viewTimes:vList){
                moduleSet.add(viewTimes.getModuleid());
            }

            if(moduleSet!=null&&moduleSet.size()!=0){
                for(Integer mSet:moduleSet){
                    Integer count=0;
                    for(HopeViewTimes viewTimes:vList){
                       if(mSet==viewTimes.getModuleid()){
                           count+=viewTimes.getViewtimes();
                       }
                    }
                    HopeViewTimes rVtime = new HopeViewTimes();
                    rVtime.setModuleid(mSet);
                    rVtime.setViewtimes(count);
                    revList.add(rVtime);
                }
            }
           return revList;
        }
        return null;
    }
    //获取每个视图对应的点赞量
    private List<HopeComments> getMoudledianZan(Integer dianzan){
        List<HopeComments>cList = hopeCommentsMapper.getDianzan(dianzan);
        if(cList!=null&&cList.size()!=0){
            return cList;
        }
        return null;
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
       /// for (HopePrivRequestEnum headCheckEnum : HopePrivRequestEnum.values()) {
            if (StringUtils.isEmpty(hopeUserHistory.getAamid()) )
                return new BaseResponse<>(HopePrivRequestEnum.aamid.getReturnCode(), null, HopePrivRequestEnum.aamid.getMsg());
            if (StringUtils.isEmpty(hopeUserHistory.getModuleid()) )
                return new BaseResponse<>(HopePrivRequestEnum.moduleid.getReturnCode(), null, HopePrivRequestEnum.moduleid.getMsg());
            if (StringUtils.isEmpty(hopeUserHistory.getLogtime()) )
                return new BaseResponse<>(HopePrivRequestEnum.logtime.getReturnCode(), null, HopePrivRequestEnum.logtime.getMsg());
       // }
        try {
            hopeUserHistoryMapper.insert(hopeUserHistory);
        }catch (Exception e){
            return new BaseResponse(BaseResponse.STATUS_SYSTEM_FAILURE,BaseResponse.STATUS_SYSTEM_FAILUREE);
        }
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
        /*if(hopeUserFavors==null || hopeUserFavors.size()==0)
            return new BaseResponse<>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);*/
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String webUrlq =imgLoaclPath;
        if(hopeUserFavors!=null && hopeUserFavors.size()!=0){
            for(HopeModule hopeModule:list){
                String mIcon = hopeModule.getIcon();
                try {
                    mIcon= java.net.URLDecoder.decode(mIcon,"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                hopeModule.setIcon(webUrlq+mIcon);
                for(HopeUserFavor hopeUserFavor:hopeUserFavors){
                    if(hopeUserFavor.getModuleid()==hopeModule.getModuleid()){
                        hopeModule.setWstatus(ModuleStatusEnum.yiguanzhu.getKey());
                        hopeModule.setSequence(hopeUserFavor.getModulesequence());
                       break;
                    }
                }
            }
           /* list.stream().forEach(new Consumer<HopeModule>() {
                @Override
                public void accept(HopeModule hopeModule) {
                    hopeModule.setIcon(webUrlq+hopeModule.getIcon());
                    hopeUserFavors.stream().forEach(new Consumer<HopeUserFavor>() {
                        @Override
                        public void accept(HopeUserFavor hopeUserFavor) {
                            if(hopeUserFavor.getModuleid()==hopeModule.getModuleid()){
                                hopeModule.setWstatus(ModuleStatusEnum.yiguanzhu.getKey());
                                hopeModule.setSequence(hopeUserFavor.getModulesequence());
                                return;
                            }

                        }

                    });
                }
            });*/
        }else {
            for(HopeModule hopeModule:list){
                String mIcon = hopeModule.getIcon();
                try {
                    mIcon= java.net.URLDecoder.decode(mIcon,"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                hopeModule.setIcon(webUrlq+mIcon);
            }
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
        if(addModuleArr==null || addModuleArr.size()==0){
            hopeUserFavorMapper.deleteMoudelByAamidAndType(aamid,UserFavorTypeEnum.shitu.getKey());
        }
        if(addModuleArr!=null && addModuleArr.size()!=0){
            List<HopeUserFavor>hflist = hopeUserFavorMapper.queryModuleByAamid(aamid,UserFavorTypeEnum.shitu.getKey());
            if(hflist==null || hflist.size()==0){
                List<HopeUserFavor>userfavorList = new ArrayList<HopeUserFavor>();
                for(int i = 0;i<addModuleArr.size();i++){
                    HopeUserFavor hopeUserFavor = new HopeUserFavor();
                    hopeUserFavor.setAamid(aamid);
                    hopeUserFavor.setModuleid((Integer) addModuleArr.get(i));
                    hopeUserFavor.setModulesequence(i);
                    hopeUserFavor.setFavortype(UserFavorTypeEnum.shitu.getKey());
                    userfavorList.add(hopeUserFavor);
                }
                BaseResponse<Integer>addresponse=insertWatchHopeModuleV2(userfavorList);
                if(!BaseResponse.STATUS_HANDLE_SUCCESS.equals(addresponse.getStatus()) || !BaseResponse.STATUS_HANDLER_SUCCESS.equals(addresponse.getMessage())){
                    return addresponse;
                }
            }
            if(hflist!=null && hflist.size()!=0){
                //====
                hopeUserFavorMapper.deleteMoudelByAamidAndType(aamid,UserFavorTypeEnum.shitu.getKey());
                List<HopeUserFavor>userfavorList = new ArrayList<HopeUserFavor>();
                for(int i = 0;i<addModuleArr.size();i++){
                    HopeUserFavor hopeUserFavor = new HopeUserFavor();
                    hopeUserFavor.setAamid(aamid);
                    hopeUserFavor.setModuleid((Integer) addModuleArr.get(i));
                    hopeUserFavor.setModulesequence(i);
                    hopeUserFavor.setFavortype(UserFavorTypeEnum.shitu.getKey());
                    userfavorList.add(hopeUserFavor);
                }
                BaseResponse<Integer>addresponse=insertWatchHopeModuleV2(userfavorList);
                if(!BaseResponse.STATUS_HANDLE_SUCCESS.equals(addresponse.getStatus()) || !BaseResponse.STATUS_HANDLER_SUCCESS.equals(addresponse.getMessage())){
                    return addresponse;
                }
                //==
            /*    for(HopeUserFavor hopeUserFavor: hflist){
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
                }*/
            }

        }
        return new BaseResponse<>(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
/**
* 功能描述:修改一行视图（或者分类视图）展开或者收起的状态
 * @param hopeModuleStatus
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Integer>
* @Author: qinwankang
* @Date: 2020/6/23 16:07
*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<Object> editModuleStatus(HopeModuleStatus hopeModuleStatus) {
        //入参校验
        BaseResponse<Object> response=checkStatusP(hopeModuleStatus);
        if(!response.getStatus().equals(BaseResponse.STATUS_HANDLE_SUCCESS)||!response.getMessage().equals(BaseResponse.STATUS_HANDLER_SUCCESS)){
            return response;
        }
        HopeModuleStatus hopeModuleStatus1 = hopeModuleStatusMapper.selectByaamidAndtype1(hopeModuleStatus.getAamid(),hopeModuleStatus.getModuletype());
        if(hopeModuleStatus1!=null){
            if(!hopeModuleStatus1.getModulestatus().equals(hopeModuleStatus.getModulestatus())){
                try {
                    hopeModuleStatusMapper.updateMstatus(hopeModuleStatus);
                }catch (Exception e){
                    return new BaseResponse<Object>(BaseResponse.STATUS_SYSTEM_FAILUREE,BaseResponse.STATUS_SYSTEM_FAILUREE);
                }
            }
        }else if(hopeModuleStatus1==null){
            try {
                hopeModuleStatusMapper.insert(hopeModuleStatus);
            }catch (Exception e){
                return new BaseResponse<Object>(BaseResponse.STATUS_SYSTEM_FAILUREE,BaseResponse.STATUS_SYSTEM_FAILUREE);
            }
        }
        return new BaseResponse<Object>(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
/**
* 功能描述:首页搜索，名称模糊匹配视图，文章两个维度，每个维度又分为模糊匹配视图和关键字
 * @param jsonObject
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Object>
* @Author: qinwankang
* @Date: 2020/6/29 14:28
*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<JSONObject> searchModuleByName(JSONObject jsonObject) {
        //入参校验
        BaseResponse response =  checkModuleKey(jsonObject);
        if(!response.getStatus().equals(BaseResponse.STATUS_HANDLE_SUCCESS)||!response.getMessage().equals(BaseResponse.STATUS_HANDLER_SUCCESS)){
            return response;
        }
        String name = jsonObject.getString("name");
        String aamid = jsonObject.getString("aamid");
        String deptid = jsonObject.getString("deptid");
        String odeptid = jsonObject.getString("odeptid");
        //视图维度结果
        SearchDto searchDtoModule = getModuleByDim(name,aamid,deptid,odeptid);
        //文章维度结果
        SearchDto searchDtoacti = getActivByDim(name,aamid,deptid,odeptid);
        JSONObject rejson = new JSONObject();
        rejson.put(HopeSearchEnum.module.getValue(),searchDtoModule);
        rejson.put(HopeSearchEnum.article.getValue(),searchDtoacti);
        return new BaseResponse<JSONObject>(BaseResponse.STATUS_HANDLE_SUCCESS,rejson,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
/**
* 功能描述:跳转新搜索页，和首页搜索功能一样，名称模糊匹配视图，文章两个维度，每个维度又分为模糊匹配视图和关键字
 * @param jsonObject
* @return: com.icbc.zsyw.hope3.common.BaseResponse<com.alibaba.fastjson.JSONObject>
* @Author: qinwankang
* @Date: 2020/7/3 9:39
*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<JSONObject> searchModuleByNameSec(JSONObject jsonObject) {
        //入参校验
        BaseResponse response =  checkModuleKey(jsonObject);
        if(!response.getStatus().equals(BaseResponse.STATUS_HANDLE_SUCCESS)||!response.getMessage().equals(BaseResponse.STATUS_HANDLER_SUCCESS)){
            return response;
        }
        String name = jsonObject.getString("name");
        String aamid = jsonObject.getString("aamid");
        String deptid = jsonObject.getString("deptid");
        String odeptid = jsonObject.getString("odeptid");
        //视图维度结果
        SearchDto searchDtoModule = getModuleByDimSec(name,aamid,deptid,odeptid);
        //文章维度结果
        SearchDto searchDtoacti = getActivByDimSec(name,aamid,deptid,odeptid);
        JSONObject rejson = new JSONObject();
        rejson.put(HopeSearchEnum.module.getValue(),searchDtoModule);
        rejson.put(HopeSearchEnum.article.getValue(),searchDtoacti);
        return new BaseResponse<JSONObject>(BaseResponse.STATUS_HANDLE_SUCCESS,rejson,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
/**
* 功能描述:发现页搜索功能
 * @param jsonObject
* @return: com.icbc.zsyw.hope3.common.BaseResponse<com.icbc.zsyw.hope3.impl.HopeModuleServiceImpl.SearchDto>
* @Author: qinwankang
* @Date: 2020/7/3 15:40
*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<SearchDto> findPageSearch(JSONObject jsonObject) {
        //入参校验
        BaseResponse response =  checFindsearch(jsonObject);
        if(!response.getStatus().equals(BaseResponse.STATUS_HANDLE_SUCCESS)||!response.getMessage().equals(BaseResponse.STATUS_HANDLER_SUCCESS)){
            return response;
        }
        Integer textclass = jsonObject.getInteger("textclass");
        String name = jsonObject.getString("name");
        String aamid = jsonObject.getString("aamid");
        String deptid = jsonObject.getString("deptid");
        String odeptid = jsonObject.getString("odeptid");
        //文章维度结果
        SearchDto searchDtoacti = getActivByDimFind(textclass,name,aamid,deptid,odeptid);
        return new BaseResponse<SearchDto>(BaseResponse.STATUS_HANDLE_SUCCESS,searchDtoacti,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
/**
* 功能描述:点击三大块跳转对应视图页面，页面渲染所需接口
 * @param jsonObject
* @return: com.icbc.zsyw.hope3.common.BaseResponse<com.icbc.zsyw.hope3.impl.HopeModuleServiceImpl.SearchDto>
* @Author: qinwankang
* @Date: 2020/7/10 17:22
*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<List<ModuleGroupSub>> getModuleByShortCutBar(JSONObject jsonObject) {
        String aamid = jsonObject.getString("aamid");
        String deptid = jsonObject.getString("deptid");
        String odeptid = jsonObject.getString("odeptid");
        String shortname = jsonObject.getString("shortname");
        JSONArray jsonArray = jsonObject.getJSONArray("moduleidArr");
        //参数校验
        BaseResponse response=  checkJsonParam(jsonObject);
        if(!response.getMessage().equals(BaseResponse.STATUS_HANDLER_SUCCESS)||!response.getStatus().equals(BaseResponse.STATUS_HANDLE_SUCCESS)){
            return response;
        }
         List<HopeModule>mlist= hopeModuleMapper.selectbyIdAndPri(aamid,deptid,odeptid,jsonArray);
       // List<HopeModule>mlist=new ArrayList<>();
        if(mlist==null||mlist.size()==0){
            return new BaseResponse<>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String webUrlq =imgLoaclPath;
        List<ModuleGroupSub>sublist = new ArrayList<ModuleGroupSub>();
        for(HopeModule hopeModule:mlist){
            ModuleGroupSub moduleGroupSub = new ModuleGroupSub();
            moduleGroupSub.setModuleid(hopeModule.getModuleid());
            String mIcon = hopeModule.getIcon();
            try {
                mIcon=java.net.URLDecoder.decode(mIcon,"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            moduleGroupSub.setIcon(webUrlq+mIcon);
            String mImage=hopeModule.getImage();
            try {
                mImage=java.net.URLDecoder.decode(mImage,"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            moduleGroupSub.setImage(webUrlq+FiltrateUtil.getModuleSmallImage(mImage));
            String mUrl= hopeModule.getUrl();
            try {
                mUrl=java.net.URLDecoder.decode(mUrl,"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            moduleGroupSub.setUrl(mUrl);
            moduleGroupSub.setModulename(hopeModule.getModulename());
            moduleGroupSub.setShortname(hopeModule.getShortname());
            moduleGroupSub.setSureCount(hopeCommentsMapper.sureCount(hopeModule.getModuleid(),HopeCommentsEnum.dianzan.getKey()));
            IdentifySub identifySub = new IdentifySub();
            Map<Integer,Boolean> map = checkAamSure(aamid,hopeModule.getModuleid());
            identifySub.setShadeImg1(map.get(HopeCommentsEnum.dianzan.getKey()));
            identifySub.setShadeImg2(map.get(HopeCommentsEnum.diancai.getKey()));
            identifySub.setShadeImg(map.get(HopeCommentsEnum.guanzhu.getKey()));
            moduleGroupSub.setIdentifySub(identifySub);
            moduleGroupSub.setTel(hopeUserLogMapper.queryUserLog_h(hopeModule.getModuleid()));
            moduleGroupSub.setText(hopeModule.getDescription());
            moduleGroupSub.setTitle(hopeModule.getModulename());
            sublist.add(moduleGroupSub);
        }
        return new BaseResponse<List<ModuleGroupSub>>(BaseResponse.STATUS_HANDLE_SUCCESS,sublist,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
/**
* 功能描述:修改一行视图（或者分类视图）展开或者收起的状态
 * @param hopeUserConf
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.Object>
* @Author: qinwankang
* @Date: 2020/7/13 16:41
*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<Object> editModuleConf(HopeUserConf hopeUserConf) {
        BaseResponse response = checkUserConfP(hopeUserConf);
        if(!response.getStatus().equals(BaseResponse.STATUS_HANDLE_SUCCESS)||!response.getMessage().equals(BaseResponse.STATUS_HANDLER_SUCCESS)){
            return response;
        }
        HopeUserConf hopeUserConf1 =hopeUserConfMapper.queryByAamidAndName(hopeUserConf.getAamid(),hopeUserConf.getSetname());
        if(hopeUserConf1!=null){
            if(hopeUserConf1.getConfvalue()!=hopeUserConf.getConfvalue()){
                hopeUserConfMapper.updateConfvalue(hopeUserConf.getAamid(),hopeUserConf.getConfvalue(),hopeUserConf.getSetname());
            }
        }
        if(hopeUserConf1==null){
            try {
                hopeUserConfMapper.insert(hopeUserConf);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return new BaseResponse<>(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }

    private BaseResponse<Object> checkUserConfP(HopeUserConf hopeUserConf) {
        String aamid = hopeUserConf.getAamid();
        String setname = hopeUserConf.getSetname();
        Integer confvalue = hopeUserConf.getConfvalue();
        if(StringUtils.isEmpty(aamid)){
            return new BaseResponse<>(HopePrivRequestEnum.aamid.getReturnCode(),HopePrivRequestEnum.aamid.getMsg());
        }
        if(StringUtils.isEmpty(setname)){
            return new BaseResponse<>(HopeUserConfReqEnum.setname.getReturnCode(),HopeUserConfReqEnum.setname.getMsg());
        }
        if(StringUtils.isEmpty(confvalue)||confvalue==null){
            return new BaseResponse<>(HopeUserConfReqEnum.confvalue.getReturnCode(),HopeUserConfReqEnum.confvalue.getMsg());
        }
      return new BaseResponse<>(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }

    private BaseResponse checkJsonParam(JSONObject jsonObject) {
        if(StringUtils.isEmpty(jsonObject.getString("aamid"))){
            return new BaseResponse(HopePrivRequestEnum.aamid.getReturnCode(),HopePrivRequestEnum.aamid.getMsg());
        }
        if(StringUtils.isEmpty(jsonObject.getString("deptid"))){
            return new BaseResponse(HopePrivRequestEnum.deptid.getReturnCode(),HopePrivRequestEnum.deptid.getMsg());
        }
        if(StringUtils.isEmpty(jsonObject.getString("odeptid"))){
            return new BaseResponse(HopePrivRequestEnum.odeptid.getReturnCode(),HopePrivRequestEnum.odeptid.getMsg());
        }
        if(StringUtils.isEmpty(jsonObject.getString("odeptid"))){
            return new BaseResponse(HopePrivRequestEnum.odeptid.getReturnCode(),HopePrivRequestEnum.odeptid.getMsg());
        }
        if(StringUtils.isEmpty(jsonObject.getString("shortname"))){
            return new BaseResponse(HopeShortcutbarReqEnum.shortname.getReturnCode(),HopeShortcutbarReqEnum.shortname.getMsg());
        }
        if(StringUtils.isEmpty(jsonObject.getString("moduleidArr"))||jsonObject.getString("moduleidArr")==null){
            return new BaseResponse(HopeShortcutbarReqEnum.jsonArray.getReturnCode(),HopeShortcutbarReqEnum.jsonArray.getMsg());
        }
        return new BaseResponse(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }

    private SearchDto getActivByDimFind(Integer textclass,String name, String aamid, String deptid, String odeptid) {
        //文章表维度
        //有权限
        List<HopeActicity>acticities= hopeActicityMapper.searchActiciByNameAndClass(aamid,deptid,odeptid,textclass,name);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String webUrlq =imgLoaclPath;
        //上墙/下墙时间判断
        List<HopeActicity>reacticitiesNo = new ArrayList<HopeActicity>();
        if(acticities!=null && acticities.size()!=0){
            Date now =new Date();
            for(HopeActicity hopeActicity:acticities){
                if(null==hopeActicity.getStarttime()&& null==hopeActicity.getEndtime()){
                    //点赞量
                    Integer dianzangliang= hopeActivityLogMapper.getdianzanliang(hopeActicity.getActivityid());
                    hopeActicity.setDianzanliang(dianzangliang);
                    //是否收藏
                    Integer hopeUserFavors=  hopeUserFavorMapper.checkShoucang(aamid,hopeActicity.getActivityid());
                    if(hopeUserFavors!=0){
                        hopeActicity.setShoucang(true);
                    }
                    //收藏量
                    Integer shoucangliang =  hopeUserFavorMapper.getshoucangliang(hopeActicity.getActivityid());
                    hopeActicity.setShoucangliang(shoucangliang);
                    //图片路径
                    String mImage = hopeActicity.getImagename();
                    try {
                        mImage = java.net.URLDecoder.decode(mImage,"utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    String webUrlend = webUrlq+mImage;
                    hopeActicity.setImagename(webUrlend);
                    //文章路径
                    String tName = hopeActicity.getTextname();
                    try {
                        tName = java.net.URLDecoder.decode(tName,"utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                 //   String textpath=webUrlq+tName+".pdf";
                    hopeActicity.setTextpath(hopeActicity.getTextpath().split("/")[hopeActicity.getTextpath().split("/").length-1]);
                    reacticitiesNo.add(hopeActicity);
                    continue;
                }
                if(null==hopeActicity.getStarttime()&&null!=hopeActicity.getEndtime()){
                    if(hopeActicity.getEndtime().after(now)){
                        //点赞量
                        Integer dianzangliang= hopeActivityLogMapper.getdianzanliang(hopeActicity.getActivityid());
                        hopeActicity.setDianzanliang(dianzangliang);
                        //是否收藏
                        Integer hopeUserFavors=  hopeUserFavorMapper.checkShoucang(aamid,hopeActicity.getActivityid());
                        if(hopeUserFavors!=0){
                            hopeActicity.setShoucang(true);
                        }
                        //收藏量
                        Integer shoucangliang =  hopeUserFavorMapper.getshoucangliang(hopeActicity.getActivityid());
                        hopeActicity.setShoucangliang(shoucangliang);
                        //图片路径
                        String mImage = hopeActicity.getImagename();
                        try {
                            mImage = java.net.URLDecoder.decode(mImage,"utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        String webUrlend = webUrlq+mImage;
                        hopeActicity.setImagename(webUrlend);
                        //文章路径
                        String tName = hopeActicity.getTextname();
                        try {
                            tName=java.net.URLDecoder.decode(tName,"utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                      //  String textpath=webUrlq+tName+".pdf";
                        hopeActicity.setTextpath(hopeActicity.getTextpath().split("/")[hopeActicity.getTextpath().split("/").length-1]);
                        reacticitiesNo.add(hopeActicity);
                        continue;
                    }
                }
                if(null!=hopeActicity.getStarttime()&&null==hopeActicity.getEndtime()){
                    if(hopeActicity.getStarttime().before(now)){
                        //点赞量
                        Integer dianzangliang= hopeActivityLogMapper.getdianzanliang(hopeActicity.getActivityid());
                        hopeActicity.setDianzanliang(dianzangliang);
                        //是否收藏
                        Integer hopeUserFavors=  hopeUserFavorMapper.checkShoucang(aamid,hopeActicity.getActivityid());
                        if(hopeUserFavors!=0){
                            hopeActicity.setShoucang(true);
                        }
                        //收藏量
                        Integer shoucangliang =  hopeUserFavorMapper.getshoucangliang(hopeActicity.getActivityid());
                        hopeActicity.setShoucangliang(shoucangliang);
                        //图片路径
                        String mImage = hopeActicity.getImagename();
                        try {
                            mImage=java.net.URLDecoder.decode(mImage,"utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        String webUrlend = webUrlq+mImage;
                        hopeActicity.setImagename(webUrlend);
                        //文章路径
                        String tName = hopeActicity.getTextname();
                        try {
                            tName =java.net.URLDecoder.decode(tName,"utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                     //   String textpath=webUrlq+tName+".pdf";
                        hopeActicity.setTextpath(hopeActicity.getTextpath().split("/")[hopeActicity.getTextpath().split("/").length-1]);
                        reacticitiesNo.add(hopeActicity);
                        continue;
                    }
                }
                if(hopeActicity.getStarttime().before(now) && hopeActicity.getEndtime().after(now)){
                    //点赞量
                    Integer dianzangliang= hopeActivityLogMapper.getdianzanliang(hopeActicity.getActivityid());
                    hopeActicity.setDianzanliang(dianzangliang);
                    //是否收藏
                    Integer hopeUserFavors=  hopeUserFavorMapper.checkShoucang(aamid,hopeActicity.getActivityid());
                    if(hopeUserFavors!=0){
                        hopeActicity.setShoucang(true);
                    }
                    //收藏量
                    Integer shoucangliang =  hopeUserFavorMapper.getshoucangliang(hopeActicity.getActivityid());
                    hopeActicity.setShoucangliang(shoucangliang);
                    //图片路径
                    String mImage = hopeActicity.getImagename();
                    try {
                        mImage= java.net.URLDecoder.decode(mImage,"utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    String webUrlend = webUrlq+mImage;
                    hopeActicity.setImagename(webUrlend);
                    //文章路径
                    String tName = hopeActicity.getTextname();
                    try {
                        tName= java.net.URLDecoder.decode(tName,"utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                   // String textpath=webUrlq+hopeActicity.getTextname()+".pdf";
                    hopeActicity.setTextpath(hopeActicity.getTextpath().split("/")[hopeActicity.getTextpath().split("/").length-1]);
                    reacticitiesNo.add(hopeActicity);
                    continue;
                }
            }
        }

        //关键字表维度
        //有权限
        List<HopeActicity>acticities1 = hopeActicityMapper.searchActiciByKeyandClass(name,aamid,deptid,odeptid,textclass);
        //上墙/下墙时间判断
        List<HopeActicity>reacticities1No = new ArrayList<HopeActicity>();
        if(acticities1!=null && acticities1.size()!=0){
            for(HopeActicity hopeActicity:acticities1){
                Date now = new Date();
                if(null==hopeActicity.getStarttime()&& null==hopeActicity.getEndtime()){
                    //点赞量
                    Integer dianzangliang= hopeActivityLogMapper.getdianzanliang(hopeActicity.getActivityid());
                    hopeActicity.setDianzanliang(dianzangliang);
                    //是否收藏
                    Integer hopeUserFavors=  hopeUserFavorMapper.checkShoucang(aamid,hopeActicity.getActivityid());
                    if(hopeUserFavors!=0){
                        hopeActicity.setShoucang(true);
                    }
                    //收藏量
                    Integer shoucangliang =  hopeUserFavorMapper.getshoucangliang(hopeActicity.getActivityid());
                    hopeActicity.setShoucangliang(shoucangliang);
                    //图片路径
                    String mImage = hopeActicity.getImagename();
                    try {
                        mImage=java.net.URLDecoder.decode(mImage,"utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    String webUrlend = webUrlq+mImage;
                    hopeActicity.setImagename(webUrlend);
                    //文章路径
                    String tName = hopeActicity.getTextname();
                    try {
                        tName=java.net.URLDecoder.decode(tName,"utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                   // String textpath=webUrlq+tName+".pdf";
                    hopeActicity.setTextpath(hopeActicity.getTextpath().split("/")[hopeActicity.getTextpath().split("/").length-1]);
                    reacticities1No.add(hopeActicity);
                    continue;
                }
                if(null==hopeActicity.getStarttime()&&null!=hopeActicity.getEndtime()){
                    if(hopeActicity.getEndtime().after(now)){
                        //点赞量
                        Integer dianzangliang= hopeActivityLogMapper.getdianzanliang(hopeActicity.getActivityid());
                        hopeActicity.setDianzanliang(dianzangliang);
                        //是否收藏
                        Integer hopeUserFavors=  hopeUserFavorMapper.checkShoucang(aamid,hopeActicity.getActivityid());
                        if(hopeUserFavors!=0){
                            hopeActicity.setShoucang(true);
                        }
                        //收藏量
                        Integer shoucangliang =  hopeUserFavorMapper.getshoucangliang(hopeActicity.getActivityid());
                        hopeActicity.setShoucangliang(shoucangliang);
                        //图片路径
                        String mImage = hopeActicity.getImagename();
                        try {
                            mImage=java.net.URLDecoder.decode(mImage,"utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        String webUrlend = webUrlq+mImage;
                        hopeActicity.setImagename(webUrlend);
                        //文章路径
                        String tName = hopeActicity.getTextname();
                        try {
                            tName=java.net.URLDecoder.decode(tName,"utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    //    String textpath=webUrlq+tName+".pdf";
                        hopeActicity.setTextpath(hopeActicity.getTextpath().split("/")[hopeActicity.getTextpath().split("/").length-1]);
                        reacticities1No.add(hopeActicity);
                        continue;
                    }
                }
                if(null!=hopeActicity.getStarttime()&&null==hopeActicity.getEndtime()){
                    if(hopeActicity.getStarttime().before(now)){
                        //点赞量
                        Integer dianzangliang= hopeActivityLogMapper.getdianzanliang(hopeActicity.getActivityid());
                        hopeActicity.setDianzanliang(dianzangliang);
                        //是否收藏
                        Integer hopeUserFavors=  hopeUserFavorMapper.checkShoucang(aamid,hopeActicity.getActivityid());
                        if(hopeUserFavors!=0){
                            hopeActicity.setShoucang(true);
                        }
                        //收藏量
                        Integer shoucangliang =  hopeUserFavorMapper.getshoucangliang(hopeActicity.getActivityid());
                        hopeActicity.setShoucangliang(shoucangliang);
                        //图片路径
                        String mImage = hopeActicity.getImagename();
                        try {
                            mImage=java.net.URLDecoder.decode(mImage,"utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        String webUrlend = webUrlq+mImage;
                        hopeActicity.setImagename(webUrlend);
                        //文章路径
                        String tName = hopeActicity.getTextname();
                        try {
                            tName=java.net.URLDecoder.decode(tName,"utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                       // String textpath=webUrlq+tName+".pdf";
                        hopeActicity.setTextpath(hopeActicity.getTextpath().split("/")[hopeActicity.getTextpath().split("/").length-1]);
                        reacticities1No.add(hopeActicity);
                        continue;
                    }
                }
                if(hopeActicity.getStarttime().before(now) && hopeActicity.getEndtime().after(now)){
                    //点赞量
                    Integer dianzangliang= hopeActivityLogMapper.getdianzanliang(hopeActicity.getActivityid());
                    hopeActicity.setDianzanliang(dianzangliang);
                    //是否收藏
                    Integer hopeUserFavors=  hopeUserFavorMapper.checkShoucang(aamid,hopeActicity.getActivityid());
                    if(hopeUserFavors!=0){
                        hopeActicity.setShoucang(true);
                    }
                    //收藏量
                    Integer shoucangliang =  hopeUserFavorMapper.getshoucangliang(hopeActicity.getActivityid());
                    hopeActicity.setShoucangliang(shoucangliang);
                    //图片路径
                    String mImage = hopeActicity.getImagename();
                    try {
                        mImage=java.net.URLDecoder.decode(mImage,"utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    String webUrlend = webUrlq+mImage;
                    hopeActicity.setImagename(webUrlend);
                    //文章路径
                    String tName = hopeActicity.getTextname();
                    try {
                        tName=java.net.URLDecoder.decode(tName,"utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                   // String textpath=webUrlq+tName+".pdf";
                    hopeActicity.setTextpath(hopeActicity.getTextpath().split("/")[hopeActicity.getTextpath().split("/").length-1]);
                    reacticities1No.add(hopeActicity);
                    continue;
                }
            }
        }
        //对模糊匹配表得到记录和关键字表匹配去重
        List<HopeActicity>ackeylist = new ArrayList<HopeActicity>();
        if(reacticitiesNo!=null && reacticitiesNo.size()!=0 && reacticities1No!=null && reacticities1No.size()!=0){
            for(HopeActicity hopeActicity:reacticities1No){
                int count =0;
                for(HopeActicity hopeActicity1: reacticitiesNo){
                    if(!hopeActicity1.getTextname().equals(hopeActicity.getTextname())){
                        count++;
                    }
                }
                if(count==reacticitiesNo.size()){
                    ackeylist.add(hopeActicity);
                }
            }
        }else{
            ackeylist=reacticities1No;
        }
        //acticitiesNo+ackeylist
        SearchDtoSub searchDtoSub2 = new SearchDtoSub();
        searchDtoSub2.setSearchStyle(HopeSearchEnum.chabiao.getValue());
        if(reacticitiesNo==null || reacticitiesNo.size()==0){
            searchDtoSub2.setStyleList(new ArrayList());
        }else{
            searchDtoSub2.setStyleList(reacticitiesNo);
        }
        SearchDtoSub searchDtoSub3 = new SearchDtoSub();
        searchDtoSub3.setSearchStyle(HopeSearchEnum.keyname.getValue());
        if(ackeylist==null || ackeylist.size()==0){
            searchDtoSub3.setStyleList(new ArrayList());
        }else{
            searchDtoSub3.setStyleList(ackeylist);
        }
        List activlist = new ArrayList();
        activlist.add(searchDtoSub2);
        activlist.add(searchDtoSub3);
        SearchDto searchDto =new SearchDto();
        searchDto.setSearchType(HopeSearchEnum.article.getValue());
        searchDto.setList(activlist);
        return searchDto;
    }

    private BaseResponse checFindsearch(JSONObject jsonObject) {
        String textclass = jsonObject.getString("textclass");
        String name = jsonObject.getString("name");
        String aamid = jsonObject.getString("aamid");
        String deptid = jsonObject.getString("deptid");
        String odeptid = jsonObject.getString("odeptid");
        if(StringUtils.isEmpty(name)){
            return new BaseResponse(HopeModuleRequestEnum.name.getReturnCode(),HopeModuleRequestEnum.name.getMsg());
        }
        if(StringUtils.isEmpty(aamid)){
            return new BaseResponse(HopePrivRequestEnum.aamid.getReturnCode(),HopePrivRequestEnum.aamid.getMsg());
        }
        if(StringUtils.isEmpty(deptid)){
            return new BaseResponse(HopePrivRequestEnum.deptid.getReturnCode(),HopePrivRequestEnum.deptid.getMsg());
        }
        if(StringUtils.isEmpty(odeptid)){
            return new BaseResponse(HopePrivRequestEnum.odeptid.getReturnCode(),HopePrivRequestEnum.odeptid.getMsg());
        }
        if(StringUtils.isEmpty(textclass)){
            return new BaseResponse(HopeActivityReqEnum.textclass.getReturnCode(),HopeActivityReqEnum.textclass.getMsg());
        }
        return new BaseResponse(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }

    /**
* 功能描述:新搜索页文章维度结果
 * @param name
 * @param aamid
 * @param deptid
 * @param odeptid
* @return: com.icbc.zsyw.hope3.impl.HopeModuleServiceImpl.SearchDto
* @Author: qinwankang
* @Date: 2020/7/3 10:32
*/
    private SearchDto getActivByDimSec(String name, String aamid, String deptid, String odeptid) {
        //文章表维度
        //有权限
        List<HopeActicity>acticities= hopeActicityMapper.searchActiciByName(aamid,deptid,odeptid,name);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String webUrlq =imgLoaclPath;
        //上墙/下墙时间判断
        List<HopeActicity>reacticitiesNo = new ArrayList<HopeActicity>();
        if(acticities!=null && acticities.size()!=0){
            Date now =new Date();
            for(HopeActicity hopeActicity:acticities){
                if(null==hopeActicity.getStarttime()&& null==hopeActicity.getEndtime()){
                    //点赞量
                    Integer dianzangliang= hopeActivityLogMapper.getdianzanliang(hopeActicity.getActivityid());
                    hopeActicity.setDianzanliang(dianzangliang);
                    //是否收藏
                    Integer hopeUserFavors=  hopeUserFavorMapper.checkShoucang(aamid,hopeActicity.getActivityid());
                    if(hopeUserFavors!=0){
                        hopeActicity.setShoucang(true);
                    }
                    //收藏量
                    Integer shoucangliang =  hopeUserFavorMapper.getshoucangliang(hopeActicity.getActivityid());
                    hopeActicity.setShoucangliang(shoucangliang);
                    //图片路径
                    String mImage = hopeActicity.getImagename();
                    try {
                        mImage=java.net.URLDecoder.decode(mImage,"utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    String webUrlend = webUrlq+mImage;
                    hopeActicity.setImagename(webUrlend);
                    //文章路径
                    String tName = hopeActicity.getTextname();
                    try {
                        tName=java.net.URLDecoder.decode(tName,"utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                   // String textpath=webUrlq+tName+".pdf";
                    hopeActicity.setTextpath(hopeActicity.getTextpath().split("/")[hopeActicity.getTextpath().split("/").length-1]);
                    hopeActicity.setQuanxianC(1);
                    reacticitiesNo.add(hopeActicity);
                    continue;
                }
                if(null==hopeActicity.getStarttime()&&null!=hopeActicity.getEndtime()){
                    if(hopeActicity.getEndtime().after(now)){
                        //点赞量
                        Integer dianzangliang= hopeActivityLogMapper.getdianzanliang(hopeActicity.getActivityid());
                        hopeActicity.setDianzanliang(dianzangliang);
                        //是否收藏
                        Integer hopeUserFavors=  hopeUserFavorMapper.checkShoucang(aamid,hopeActicity.getActivityid());
                        if(hopeUserFavors!=0){
                            hopeActicity.setShoucang(true);
                        }
                        //收藏量
                        Integer shoucangliang =  hopeUserFavorMapper.getshoucangliang(hopeActicity.getActivityid());
                        hopeActicity.setShoucangliang(shoucangliang);
                        //图片路径
                        String mImage = hopeActicity.getImagename();
                        try {
                            mImage=java.net.URLDecoder.decode(mImage,"utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        String webUrlend = webUrlq+mImage;
                        hopeActicity.setImagename(webUrlend);
                        //文章路径
                        String tName = hopeActicity.getTextname();
                        try {
                            tName=java.net.URLDecoder.decode(tName,"utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                      //  String textpath=webUrlq+tName+".pdf";
                        hopeActicity.setTextpath(hopeActicity.getTextpath().split("/")[hopeActicity.getTextpath().split("/").length-1]);
                        hopeActicity.setQuanxianC(1);
                        reacticitiesNo.add(hopeActicity);
                        continue;
                    }
                }
                if(null!=hopeActicity.getStarttime()&&null==hopeActicity.getEndtime()){
                    if(hopeActicity.getStarttime().before(now)){
                        //点赞量
                        Integer dianzangliang= hopeActivityLogMapper.getdianzanliang(hopeActicity.getActivityid());
                        hopeActicity.setDianzanliang(dianzangliang);
                        //是否收藏
                        Integer hopeUserFavors=  hopeUserFavorMapper.checkShoucang(aamid,hopeActicity.getActivityid());
                        if(hopeUserFavors!=0){
                            hopeActicity.setShoucang(true);
                        }
                        //收藏量
                        Integer shoucangliang =  hopeUserFavorMapper.getshoucangliang(hopeActicity.getActivityid());
                        hopeActicity.setShoucangliang(shoucangliang);
                        //图片路径
                        String mImage = hopeActicity.getImagename();
                        try {
                            mImage=java.net.URLDecoder.decode(mImage,"utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        String webUrlend = webUrlq+mImage;
                        hopeActicity.setImagename(webUrlend);
                        //文章路径
                        String tName = hopeActicity.getTextname();
                        try {
                            tName=java.net.URLDecoder.decode(tName,"utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                       // String textpath=webUrlq+tName+".pdf";
                        hopeActicity.setTextpath(hopeActicity.getTextpath().split("/")[hopeActicity.getTextpath().split("/").length-1]);
                        hopeActicity.setQuanxianC(1);
                        reacticitiesNo.add(hopeActicity);
                        continue;
                    }
                }
                if(hopeActicity.getStarttime().before(now) && hopeActicity.getEndtime().after(now)){
                    //点赞量
                    Integer dianzangliang= hopeActivityLogMapper.getdianzanliang(hopeActicity.getActivityid());
                    hopeActicity.setDianzanliang(dianzangliang);
                    //是否收藏
                    Integer hopeUserFavors=  hopeUserFavorMapper.checkShoucang(aamid,hopeActicity.getActivityid());
                    if(hopeUserFavors!=0){
                        hopeActicity.setShoucang(true);
                    }
                    //收藏量
                    Integer shoucangliang =  hopeUserFavorMapper.getshoucangliang(hopeActicity.getActivityid());
                    hopeActicity.setShoucangliang(shoucangliang);
                    //图片路径
                    String mImage = hopeActicity.getImagename();
                    try {
                        mImage=java.net.URLDecoder.decode(mImage,"utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    String webUrlend = webUrlq+mImage;
                    hopeActicity.setImagename(webUrlend);
                    //文章路径
                    String tName = hopeActicity.getTextname();
                    try {
                        tName=java.net.URLDecoder.decode(tName,"utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    //String textpath=webUrlq+tName+".pdf";
                    hopeActicity.setTextpath(hopeActicity.getTextpath().split("/")[hopeActicity.getTextpath().split("/").length-1]);
                    hopeActicity.setQuanxianC(1);
                    reacticitiesNo.add(hopeActicity);
                    continue;
                }
            }
        }

        //关键字表维度
        //有权限
        List<HopeActicity>acticities1 = hopeActicityMapper.searchActiciByKey(name,aamid,deptid,odeptid);
        //上墙/下墙时间判断
        List<HopeActicity>reacticities1No = new ArrayList<HopeActicity>();
        if(acticities1!=null && acticities1.size()!=0){
            for(HopeActicity hopeActicity:acticities1){
                Date now = new Date();
                if(null==hopeActicity.getStarttime()&& null==hopeActicity.getEndtime()){
                    //点赞量
                    Integer dianzangliang= hopeActivityLogMapper.getdianzanliang(hopeActicity.getActivityid());
                    hopeActicity.setDianzanliang(dianzangliang);
                    //是否收藏
                    Integer hopeUserFavors=  hopeUserFavorMapper.checkShoucang(aamid,hopeActicity.getActivityid());
                    if(hopeUserFavors!=0){
                        hopeActicity.setShoucang(true);
                    }
                    //收藏量
                    Integer shoucangliang =  hopeUserFavorMapper.getshoucangliang(hopeActicity.getActivityid());
                    hopeActicity.setShoucangliang(shoucangliang);
                    //图片路径
                    String mImage = hopeActicity.getImagename();
                    try {
                        mImage=java.net.URLDecoder.decode(mImage,"utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    String webUrlend = webUrlq+mImage;
                    hopeActicity.setImagename(webUrlend);
                    //文章路径
                    String tName = hopeActicity.getTextname();
                    try {
                        tName=java.net.URLDecoder.decode(tName,"utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    //String textpath=webUrlq+tName+".pdf";
                    hopeActicity.setTextpath(hopeActicity.getTextpath().split("/")[hopeActicity.getTextpath().split("/").length-1]);
                    hopeActicity.setQuanxianC(1);
                    reacticities1No.add(hopeActicity);
                    continue;
                }
                if(null==hopeActicity.getStarttime()&&null!=hopeActicity.getEndtime()){
                    if(hopeActicity.getEndtime().after(now)){
                        //点赞量
                        Integer dianzangliang= hopeActivityLogMapper.getdianzanliang(hopeActicity.getActivityid());
                        hopeActicity.setDianzanliang(dianzangliang);
                        //是否收藏
                        Integer hopeUserFavors=  hopeUserFavorMapper.checkShoucang(aamid,hopeActicity.getActivityid());
                        if(hopeUserFavors!=0){
                            hopeActicity.setShoucang(true);
                        }
                        //收藏量
                        Integer shoucangliang =  hopeUserFavorMapper.getshoucangliang(hopeActicity.getActivityid());
                        hopeActicity.setShoucangliang(shoucangliang);
                        //图片路径
                        String mImage = hopeActicity.getImagename();
                        try {
                            mImage=java.net.URLDecoder.decode(mImage,"utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        String webUrlend = webUrlq+mImage;
                        hopeActicity.setImagename(webUrlend);
                        //文章路径
                        String tName = hopeActicity.getTextname();
                        try {
                            tName=java.net.URLDecoder.decode(tName,"utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                      //  String textpath=webUrlq+tName+".pdf";
                        hopeActicity.setTextpath(hopeActicity.getTextpath().split("/")[hopeActicity.getTextpath().split("/").length-1]);
                        hopeActicity.setQuanxianC(1);
                        reacticities1No.add(hopeActicity);
                        continue;
                    }
                }
                if(null!=hopeActicity.getStarttime()&&null==hopeActicity.getEndtime()){
                    if(hopeActicity.getStarttime().before(now)){
                        //点赞量
                        Integer dianzangliang= hopeActivityLogMapper.getdianzanliang(hopeActicity.getActivityid());
                        hopeActicity.setDianzanliang(dianzangliang);
                        //是否收藏
                        Integer hopeUserFavors=  hopeUserFavorMapper.checkShoucang(aamid,hopeActicity.getActivityid());
                        if(hopeUserFavors!=0){
                            hopeActicity.setShoucang(true);
                        }
                        //收藏量
                        Integer shoucangliang =  hopeUserFavorMapper.getshoucangliang(hopeActicity.getActivityid());
                        hopeActicity.setShoucangliang(shoucangliang);
                        //图片路径
                        String mImage = hopeActicity.getImagename();
                        try {
                            mImage=java.net.URLDecoder.decode(mImage,"utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        String webUrlend = webUrlq+mImage;
                        hopeActicity.setImagename(webUrlend);
                        //文章路径
                        String tName = hopeActicity.getTextname();
                        try {
                            tName=java.net.URLDecoder.decode(tName,"utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                     //   String textpath=webUrlq+tName+".pdf";
                        hopeActicity.setTextpath(hopeActicity.getTextpath().split("/")[hopeActicity.getTextpath().split("/").length-1]);
                        hopeActicity.setQuanxianC(1);
                        reacticities1No.add(hopeActicity);
                        continue;
                    }
                }
                if(hopeActicity.getStarttime().before(now) && hopeActicity.getEndtime().after(now)){
                    //点赞量
                    Integer dianzangliang= hopeActivityLogMapper.getdianzanliang(hopeActicity.getActivityid());
                    hopeActicity.setDianzanliang(dianzangliang);
                    //是否收藏
                    Integer hopeUserFavors=  hopeUserFavorMapper.checkShoucang(aamid,hopeActicity.getActivityid());
                    if(hopeUserFavors!=0){
                        hopeActicity.setShoucang(true);
                    }
                    //收藏量
                    Integer shoucangliang =  hopeUserFavorMapper.getshoucangliang(hopeActicity.getActivityid());
                    hopeActicity.setShoucangliang(shoucangliang);
                    //图片路径
                    String mImage = hopeActicity.getImagename();
                    try {
                        mImage=java.net.URLDecoder.decode(mImage,"utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    String webUrlend = webUrlq+mImage;
                    hopeActicity.setImagename(webUrlend);
                    //文章路径
                    String tName = hopeActicity.getTextname();
                    try {
                        tName=java.net.URLDecoder.decode(tName,"utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    //String textpath=webUrlq+tName+".pdf";
                    hopeActicity.setTextpath(hopeActicity.getTextpath().split("/")[hopeActicity.getTextpath().split("/").length-1]);
                    hopeActicity.setQuanxianC(1);
                    reacticities1No.add(hopeActicity);
                    continue;
                }
            }
        }
        //对模糊匹配表得到记录和关键字表匹配去重
        List<HopeActicity>ackeylist = new ArrayList<HopeActicity>();
        if(reacticitiesNo!=null && reacticitiesNo.size()!=0 && reacticities1No!=null && reacticities1No.size()!=0){
            for(HopeActicity hopeActicity:reacticities1No){
                int count =0;
                for(HopeActicity hopeActicity1: reacticitiesNo){
                    if(!hopeActicity1.getTextname().equals(hopeActicity.getTextname())){
                        count++;
                    }
                }
                if(count==reacticitiesNo.size()){
                    ackeylist.add(hopeActicity);
                }
            }
        }else{
            ackeylist=reacticities1No;
        }
        //acticitiesNo+ackeylist
        SearchDtoSub searchDtoSub2 = new SearchDtoSub();
        searchDtoSub2.setSearchStyle(HopeSearchEnum.chabiao.getValue());
        if(reacticitiesNo==null || reacticitiesNo.size()==0){
            searchDtoSub2.setStyleList(null);
        }else{
            searchDtoSub2.setStyleList(reacticitiesNo);
        }
        SearchDtoSub searchDtoSub3 = new SearchDtoSub();
        searchDtoSub3.setSearchStyle(HopeSearchEnum.keyname.getValue());
        if(ackeylist==null || ackeylist.size()==0){
            searchDtoSub3.setStyleList(null);
        }else{
            searchDtoSub3.setStyleList(ackeylist);
        }
        List activlist = new ArrayList();
        activlist.add(searchDtoSub2);
        activlist.add(searchDtoSub3);
        SearchDto searchDto =new SearchDto();
        searchDto.setSearchType(HopeSearchEnum.article.getValue());
        searchDto.setList(activlist);
        return searchDto;
    }

    /**
* 功能描述:新搜索页面视图维度结果
 * @param name
 * @param aamid
 * @param deptid
 * @param odeptid
* @return: com.icbc.zsyw.hope3.impl.HopeModuleServiceImpl.SearchDto
* @Author: qinwankang
* @Date: 2020/7/3 10:14
*/
    private SearchDto getModuleByDimSec(String name, String aamid, String deptid, String odeptid) {
        //视图表维度
        //有权限
        List<HopeModule>moduleList=hopeModuleMapper.searchMoudleByName1(aamid,deptid,odeptid,name);
        //无需权限
        List<HopeModule>moduleListNo=hopeModuleMapper.searchMoudleByName1No(name);
        //权限判断,url判断
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String webUrlq =imgLoaclPath;
        if(moduleList!=null && moduleList.size()!=0 && moduleListNo!=null && moduleListNo.size()!=0){
            for(HopeModule hopeModule:moduleListNo){
                String mIcon = hopeModule.getIcon();
                try {
                    mIcon=java.net.URLDecoder.decode(mIcon,"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                hopeModule.setIcon(webUrlq+mIcon);
                String mImage = hopeModule.getImage();
                try {
                    mImage=java.net.URLDecoder.decode(mImage,"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                hopeModule.setImage(webUrlq+FiltrateUtil.getModuleSmallImage(mImage));
                hopeModule.setFangwenTel(hopeUserLogMapper.queryUserLog_h(hopeModule.getModuleid()));
                if(null!=hopeModule.getUseurltype()&&hopeModule.getUseurltype()==1){
                    String mUrl = hopeModule.getUrl();
                    try {
                        mUrl=java.net.URLDecoder.decode(mUrl,"utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    hopeModule.setUrl(FiltrateUtil.getModuleUrl(mUrl,aamid,deptid));
                }
                for(HopeModule hopeModule1:moduleList){
                    if(hopeModule.getModuleid()==hopeModule1.getModuleid()){
                        hopeModule.setQuanxianC(1);
                    }
                }
            }
        }
        //
        //关键字表维度
        //有权限
        List<HopeModule>moduleList1=hopeSearchkeyMapper.searchModuleByKey(name,aamid,deptid,odeptid);

        //无需权限
        List<HopeModule>moduleList1No=hopeSearchkeyMapper.searchModuleByKeyNo(name);
        //权限判断
        if(moduleList1!=null && moduleList1.size()!=0 && moduleList1No!=null && moduleList1No.size()!=0){
            for(HopeModule hopeModule:moduleList1No){
                String mIcon = hopeModule.getIcon();
                try {
                    mIcon=java.net.URLDecoder.decode(mIcon,"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                hopeModule.setIcon(webUrlq+mIcon);
                String mImage = hopeModule.getImage();
                try {
                    mImage=java.net.URLDecoder.decode(mImage,"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                hopeModule.setImage(webUrlq+FiltrateUtil.getModuleSmallImage(mImage));
                hopeModule.setFangwenTel(hopeUserLogMapper.queryUserLog_h(hopeModule.getModuleid()));
                if(null!=hopeModule.getUseurltype()&&hopeModule.getUseurltype()==1){
                    String mUrl = hopeModule.getUrl();
                    try {
                        mUrl=java.net.URLDecoder.decode(mUrl,"utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    hopeModule.setUrl(FiltrateUtil.getModuleUrl(mUrl,aamid,deptid));
                }
                for(HopeModule hopeModule1:moduleList1){
                    if(hopeModule.getModuleid()==hopeModule1.getModuleid()){
                        hopeModule.setQuanxianC(1);
                    }
                }
            }
        }
        //对模糊匹配表得到记录和关键字表匹配去重
        List<HopeModule>keylist = new ArrayList<HopeModule>();
        if(moduleListNo!=null && moduleListNo.size()!=0 && moduleList1No!=null && moduleList1No.size()!=0){
            for(HopeModule hopeModule:moduleList1No){
                int count =0;
                for(HopeModule hopeModule1: moduleListNo){
                    if(!hopeModule1.getModulename().equals(hopeModule.getModulename())){
                        count++;
                    }
                }
                if(count==moduleListNo.size()){
                    keylist.add(hopeModule);
                }
            }
        }else{
            keylist=moduleList1No;
        }
        //moduleListNo+keylist
        SearchDtoSub searchDtoSub = new SearchDtoSub();
        searchDtoSub.setSearchStyle(HopeSearchEnum.chabiao.getValue());
        if(moduleListNo==null || moduleListNo.size()==0){
            searchDtoSub.setStyleList(null);
        }else{
            searchDtoSub.setStyleList(moduleListNo);
        }
        SearchDtoSub searchDtoSub1 = new SearchDtoSub();
        searchDtoSub1.setSearchStyle(HopeSearchEnum.keyname.getValue());
        if(keylist==null || keylist.size()==0){
            searchDtoSub1.setStyleList(null);
        }else{
            searchDtoSub1.setStyleList(keylist);
        }
        List moulelist = new ArrayList();
        moulelist.add(searchDtoSub);
        moulelist.add(searchDtoSub1);
        SearchDto searchDto =new SearchDto();
        searchDto.setSearchType(HopeSearchEnum.module.getValue());
        searchDto.setList(moulelist);
        return searchDto;
    }

    /**
* 功能描述:搜索文章维度结果
 * @param name
 * @param aamid
 * @param deptid
 * @param odeptid
* @return: com.icbc.zsyw.hope3.impl.HopeModuleServiceImpl.SearchDto
* @Author: qinwankang
* @Date: 2020/6/30 10:25
*/
    private SearchDto getActivByDim(String name, String aamid, String deptid, String odeptid) {
        //文章表维度
        //有权限
        List<HopeActicity>acticities= hopeActicityMapper.searchActiciByName(aamid,deptid,odeptid,name);
        /*//无需权限
        List<HopeActicity>acticitiesNo= hopeActicityMapper.searchActiciByNameNo(name);
        //权限判断
        if(acticities!=null && acticities.size()!=0 && acticitiesNo!=null && acticitiesNo.size()!=0){
            for(HopeActicity hopeActicity:acticitiesNo){
                for(HopeActicity hopeActicity1:acticities){
                    if(hopeActicity.getActivityid()==hopeActicity1.getActivityid()){
                        hopeActicity.setQuanxianC(1);
                    }
                }
            }
        }*/
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String webUrlq =imgLoaclPath;

        //上墙/下墙时间判断
        List<HopeActicity>reacticitiesNo = new ArrayList<HopeActicity>();
        if(acticities!=null && acticities.size()!=0){
            for(HopeActicity hopeActicity:acticities){
                Date now = new Date();
                if(null==hopeActicity.getStarttime()&& null==hopeActicity.getEndtime()){
                    String tName = hopeActicity.getTextname();
                    try {
                        tName=java.net.URLDecoder.decode(tName,"utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                  //  String textpath=webUrlq+tName+".pdf";
                    hopeActicity.setTextpath(hopeActicity.getTextpath().split("/")[hopeActicity.getTextpath().split("/").length-1]);
                    hopeActicity.setQuanxianC(1);
                    reacticitiesNo.add(hopeActicity);
                    continue;
                }
                if(null==hopeActicity.getStarttime()&&null!=hopeActicity.getEndtime()){
                    if(hopeActicity.getEndtime().after(now)){
                        String tName = hopeActicity.getTextname();
                        try {
                            tName=java.net.URLDecoder.decode(tName,"utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    //    String textpath=webUrlq+tName+".pdf";
                        hopeActicity.setTextpath(hopeActicity.getTextpath().split("/")[hopeActicity.getTextpath().split("/").length-1]);
                        hopeActicity.setQuanxianC(1);
                        reacticitiesNo.add(hopeActicity);
                        continue;
                    }
                }
                if(null!=hopeActicity.getStarttime()&&null==hopeActicity.getEndtime()){
                    if(hopeActicity.getStarttime().before(now)){
                        String tName = hopeActicity.getTextname();
                        try {
                            tName=java.net.URLDecoder.decode(tName,"utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                      //  String textpath=webUrlq+tName+".pdf";
                        hopeActicity.setTextpath(hopeActicity.getTextpath().split("/")[hopeActicity.getTextpath().split("/").length-1]);
                        hopeActicity.setQuanxianC(1);
                        reacticitiesNo.add(hopeActicity);
                        continue;
                    }
                }
                if(hopeActicity.getStarttime().before(now) && hopeActicity.getEndtime().after(now)){
                    String tName = hopeActicity.getTextname();
                    try {
                        tName=java.net.URLDecoder.decode(tName,"utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                   // String textpath=webUrlq+tName+".pdf";
                    hopeActicity.setTextpath(hopeActicity.getTextpath().split("/")[hopeActicity.getTextpath().split("/").length-1]);
                    hopeActicity.setQuanxianC(1);
                    reacticitiesNo.add(hopeActicity);
                    continue;
                }
            }
        }

        //关键字表维度
        //有权限
        List<HopeActicity>acticities1 = hopeActicityMapper.searchActiciByKey(name,aamid,deptid,odeptid);
        //无权限
      /*  List<HopeActicity>acticities1No = hopeActicityMapper.searchActiciByKeyNo(name);
        //权限判断
        if(acticities1!=null && acticities1.size()!=0 && acticities1No!=null && acticities1No.size()!=0){
            for(HopeActicity hopeActicity:acticities1No){
                for(HopeActicity hopeActicity1:acticities1){
                    if(hopeActicity.getActivityid()==hopeActicity1.getActivityid()){
                        hopeActicity.setQuanxianC(1);
                    }
                }
            }
        }*/
        //上墙/下墙时间判断
        List<HopeActicity>reacticities1No = new ArrayList<HopeActicity>();
        if(acticities1!=null && acticities1.size()!=0){
            for(HopeActicity hopeActicity:acticities1){
                Date now = new Date();
                if(null==hopeActicity.getStarttime()&& null==hopeActicity.getEndtime()){
                    String tName = hopeActicity.getTextname();
                    try {
                        tName=java.net.URLDecoder.decode(tName,"utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                  //  String textpath=webUrlq+tName+".pdf";
                    hopeActicity.setTextpath(hopeActicity.getTextpath().split("/")[hopeActicity.getTextpath().split("/").length-1]);
                    hopeActicity.setQuanxianC(1);
                    reacticities1No.add(hopeActicity);
                    continue;
                }
                if(null==hopeActicity.getStarttime()&&null!=hopeActicity.getEndtime()){
                    if(hopeActicity.getEndtime().after(now)){
                        String tName = hopeActicity.getTextname();
                        try {
                            tName=java.net.URLDecoder.decode(tName,"utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                      //  String textpath=webUrlq+tName+".pdf";
                        hopeActicity.setTextpath(hopeActicity.getTextpath().split("/")[hopeActicity.getTextpath().split("/").length-1]);
                        hopeActicity.setQuanxianC(1);
                        reacticities1No.add(hopeActicity);
                        continue;
                    }
                }
                if(null!=hopeActicity.getStarttime()&&null==hopeActicity.getEndtime()){
                    if(hopeActicity.getStarttime().before(now)){
                        String tName = hopeActicity.getTextname();
                        try {
                            tName=java.net.URLDecoder.decode(tName,"utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                     //   String textpath=webUrlq+tName+".pdf";
                        hopeActicity.setTextpath(hopeActicity.getTextpath().split("/")[hopeActicity.getTextpath().split("/").length-1]);
                        hopeActicity.setQuanxianC(1);
                        reacticities1No.add(hopeActicity);
                        continue;
                    }
                }
                if(hopeActicity.getStarttime().before(now) && hopeActicity.getEndtime().after(now)){
                    String tName = hopeActicity.getTextname();
                    try {
                        tName=java.net.URLDecoder.decode(tName,"utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                  //  String textpath=webUrlq+tName+".pdf";
                    hopeActicity.setTextpath(hopeActicity.getTextpath().split("/")[hopeActicity.getTextpath().split("/").length-1]);
                    hopeActicity.setQuanxianC(1);
                    reacticities1No.add(hopeActicity);
                    continue;
                }
            }
        }
        //对模糊匹配表得到记录和关键字表匹配去重
        List<HopeActicity>ackeylist = new ArrayList<HopeActicity>();
        if(reacticitiesNo!=null && reacticitiesNo.size()!=0 && reacticities1No!=null && reacticities1No.size()!=0){
            for(HopeActicity hopeActicity:reacticities1No){
                int count =0;
                for(HopeActicity hopeActicity1: reacticitiesNo){
                    if(!hopeActicity1.getTextname().equals(hopeActicity.getTextname())){
                        count++;
                    }
                }
                if(count==reacticitiesNo.size()){
                    ackeylist.add(hopeActicity);
                }
            }
        }else{
            ackeylist=reacticities1No;
        }
        //acticitiesNo+ackeylist
        SearchDtoSub searchDtoSub2 = new SearchDtoSub();
        searchDtoSub2.setSearchStyle(HopeSearchEnum.chabiao.getValue());
        if(reacticitiesNo==null || reacticitiesNo.size()==0){
            searchDtoSub2.setStyleList(null);
        }else{
            searchDtoSub2.setStyleList(reacticitiesNo);
        }
        SearchDtoSub searchDtoSub3 = new SearchDtoSub();
        searchDtoSub3.setSearchStyle(HopeSearchEnum.keyname.getValue());
        if(ackeylist==null || ackeylist.size()==0){
            searchDtoSub3.setStyleList(null);
        }else{
            searchDtoSub3.setStyleList(ackeylist);
        }
        List activlist = new ArrayList();
        activlist.add(searchDtoSub2);
        activlist.add(searchDtoSub3);
        SearchDto searchDto =new SearchDto();
        searchDto.setSearchType(HopeSearchEnum.article.getValue());
        searchDto.setList(activlist);
        return searchDto;
    }
/**
* 功能描述:搜索视图维度结果
 * @param name
 * @param aamid
 * @param deptid
 * @param odeptid
* @return: com.icbc.zsyw.hope3.impl.HopeModuleServiceImpl.SearchDto
* @Author: qinwankang
* @Date: 2020/6/30 10:25
*/
    private SearchDto getModuleByDim(String name, String aamid, String deptid, String odeptid) {
        //视图表维度
        //有权限
        List<HopeModule>moduleList=hopeModuleMapper.searchMoudleByName1(aamid,deptid,odeptid,name);
        //无需权限
        List<HopeModule>moduleListNo=hopeModuleMapper.searchMoudleByName1No(name);
        //权限判断,url判断
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String webUrlq =imgLoaclPath;
        if(moduleList!=null && moduleList.size()!=0 && moduleListNo!=null && moduleListNo.size()!=0){
            for(HopeModule hopeModule:moduleListNo){
                String mIcon = hopeModule.getIcon();
                try {
                    mIcon=java.net.URLDecoder.decode(mIcon,"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                hopeModule.setIcon(webUrlq+mIcon);
                if(null!=hopeModule.getUseurltype()&&hopeModule.getUseurltype()==1){
                    String mUrl = hopeModule.getUrl();
                    try {
                        mUrl=java.net.URLDecoder.decode(mUrl,"utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    hopeModule.setUrl(FiltrateUtil.getModuleUrl(mUrl,aamid,deptid));
                }
                for(HopeModule hopeModule1:moduleList){
                    if(hopeModule.getModuleid()==hopeModule1.getModuleid()){
                        hopeModule.setQuanxianC(1);
                    }
                }
            }
        }
        //
        //关键字表维度
        //有权限
        List<HopeModule>moduleList1=hopeSearchkeyMapper.searchModuleByKey(name,aamid,deptid,odeptid);
        //无需权限
        List<HopeModule>moduleList1No=hopeSearchkeyMapper.searchModuleByKeyNo(name);
        //权限判断
        if(moduleList1!=null && moduleList1.size()!=0 && moduleList1No!=null && moduleList1No.size()!=0){
            for(HopeModule hopeModule:moduleList1No){
                String mIcon = hopeModule.getIcon();
                try {
                    mIcon=java.net.URLDecoder.decode(mIcon,"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                hopeModule.setIcon(webUrlq+mIcon);
                if(null!=hopeModule.getUseurltype()&&hopeModule.getUseurltype()==1){
                    String mUrl = hopeModule.getUrl();
                    try {
                        mUrl=java.net.URLDecoder.decode(mUrl,"utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    hopeModule.setUrl(FiltrateUtil.getModuleUrl(mUrl,aamid,deptid));
                }
                for(HopeModule hopeModule1:moduleList1){
                    if(hopeModule.getModuleid()==hopeModule1.getModuleid()){
                        hopeModule.setQuanxianC(1);
                    }
                }
            }
        }
        //对模糊匹配表得到记录和关键字表匹配去重
        List<HopeModule>keylist = new ArrayList<HopeModule>();
        if(moduleListNo!=null && moduleListNo.size()!=0 && moduleList1No!=null && moduleList1No.size()!=0){
            for(HopeModule hopeModule:moduleList1No){
                int count =0;
                for(HopeModule hopeModule1: moduleListNo){
                    if(!hopeModule1.getModulename().equals(hopeModule.getModulename())){
                        count++;
                    }
                }
                if(count==moduleListNo.size()){
                    keylist.add(hopeModule);
                }
            }
        }else{
            keylist=moduleList1No;
        }
        //moduleListNo+keylist
        SearchDtoSub searchDtoSub = new SearchDtoSub();
        searchDtoSub.setSearchStyle(HopeSearchEnum.chabiao.getValue());
        if(moduleListNo==null || moduleListNo.size()==0){
            searchDtoSub.setStyleList(null);
        }else{
            searchDtoSub.setStyleList(moduleListNo);
        }
        SearchDtoSub searchDtoSub1 = new SearchDtoSub();
        searchDtoSub1.setSearchStyle(HopeSearchEnum.keyname.getValue());
        if(keylist==null || keylist.size()==0){
            searchDtoSub1.setStyleList(null);
        }else{
            searchDtoSub1.setStyleList(keylist);
        }
        List moulelist = new ArrayList();
        moulelist.add(searchDtoSub);
        moulelist.add(searchDtoSub1);
        SearchDto searchDto =new SearchDto();
        searchDto.setSearchType(HopeSearchEnum.module.getValue());
        searchDto.setList(moulelist);
        return searchDto;
    }

    //搜索结果按照模糊匹配和关键字返回的实体类
    public class SearchDtoSub{
        private String searchStyle;
        private List styleList;

        public String getSearchStyle() {
            return searchStyle;
        }

        public List getStyleList() {
            return styleList;
        }

        public void setSearchStyle(String searchStyle) {
            this.searchStyle = searchStyle;
        }

        public void setStyleList(List styleList) {
            this.styleList = styleList;
        }
    }
//搜索模糊匹配返回结果实体类
    public class SearchDto{
        private String searchType;
        private List list;

    public String getSearchType() {
        return searchType;
    }
    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
    private BaseResponse checkModuleKey(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String aamid = jsonObject.getString("aamid");
        String deptid = jsonObject.getString("deptid");
        String odeptid = jsonObject.getString("odeptid");
        if(StringUtils.isEmpty(name)){
            return new BaseResponse(HopeModuleRequestEnum.name.getReturnCode(),HopeModuleRequestEnum.name.getMsg());
        }
        if(StringUtils.isEmpty(aamid)){
            return new BaseResponse(HopePrivRequestEnum.aamid.getReturnCode(),HopePrivRequestEnum.aamid.getMsg());
        }
        if(StringUtils.isEmpty(deptid)){
            return new BaseResponse(HopePrivRequestEnum.deptid.getReturnCode(),HopePrivRequestEnum.deptid.getMsg());
        }if(StringUtils.isEmpty(odeptid)){
            return new BaseResponse(HopePrivRequestEnum.odeptid.getReturnCode(),HopePrivRequestEnum.odeptid.getMsg());
        }
        return new BaseResponse(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
    }

    private BaseResponse<Object> checkStatusP(HopeModuleStatus hopeModuleStatus) {
        JSONObject input = (JSONObject) JSONObject.parse(JSON.toJSONString(hopeModuleStatus));
        if (input.size() == 0) {
            return new BaseResponse<Object>(BaseResponse.ALL_BLANK, null,
                    BaseResponse.ALL_BLANKER);

        } else {
            for (ModuleStatusRequestEnum applyCreditQuotaRequestEnum : ModuleStatusRequestEnum.values()) {
                if (applyCreditQuotaRequestEnum.isNotEmpty()
                        && (StringUtils.isEmpty(input.getString(applyCreditQuotaRequestEnum.name()))
                        || null==input.getString(applyCreditQuotaRequestEnum.name()))) {
                    return new BaseResponse<Object>(applyCreditQuotaRequestEnum.getReturnCode(), null,
                            applyCreditQuotaRequestEnum.getMsg());
                }
            }
        }
        return new BaseResponse(BaseResponse.STATUS_HANDLE_SUCCESS,BaseResponse.STATUS_HANDLER_SUCCESS);
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

    /**
    * 功能描述:将数据库返回的视图按照视图分组名称分类然后组成集合返回
     * @param relist
     * @param hopeModuleList
     * @param aamid
    * @return: java.util.List<com.icbc.zsyw.hope3.impl.HopeModuleServiceImpl.ModuleGroup>
    * @Author: qinwankang
    * @Date: 2020/5/22 16:52
    */
    private List<ModuleGroup> insertViewByGroup(List<ModuleGroup>relist,List<HopeModule> hopeModuleList,String aamid,Integer mStatus){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String webUrlq =imgLoaclPath;
        for (HopeModuleGroupEnum hopeModuleGroupEnum : HopeModuleGroupEnum.values()) {
            if(hopeModuleGroupEnum.getKey().equals(HopeModuleGroupEnum.quanbu.getKey()))
                continue;
            List<ModuleGroupSub>sublist = new ArrayList<ModuleGroupSub>();
            for(HopeModule hopeModule: hopeModuleList){
                if (hopeModuleGroupEnum.getValue().equals(hopeModule.getModulegroupname())){
                    if(StringUtils.isEmpty(mStatus)||null==mStatus){
                        ModuleGroupSub moduleGroupSub = new ModuleGroupSub();
                        moduleGroupSub.setModuleid(hopeModule.getModuleid());
                        String mIcon = hopeModule.getIcon();
                        try {
                            mIcon=java.net.URLDecoder.decode(mIcon,"utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        moduleGroupSub.setIcon(webUrlq+mIcon);
                        String mImage = hopeModule.getImage();
                        try {
                            mImage=java.net.URLDecoder.decode(mImage,"utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        moduleGroupSub.setImage(webUrlq+mImage);
                        String mUrl = hopeModule.getUrl();
                        try {
                            mUrl=java.net.URLDecoder.decode(mUrl,"utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        moduleGroupSub.setUrl(mUrl);
                        moduleGroupSub.setSureCount(hopeCommentsMapper.sureCount(hopeModule.getModuleid(),HopeCommentsEnum.dianzan.getKey()));
                        IdentifySub identifySub = new IdentifySub();
                        Map<Integer,Boolean> map = checkAamSure(aamid,hopeModule.getModuleid());
                        identifySub.setShadeImg1(map.get(HopeCommentsEnum.dianzan.getKey()));
                        identifySub.setShadeImg2(map.get(HopeCommentsEnum.diancai.getKey()));
                        identifySub.setShadeImg(map.get(HopeCommentsEnum.guanzhu.getKey()));
                        moduleGroupSub.setIdentifySub(identifySub);
                        moduleGroupSub.setTel(hopeUserLogMapper.queryUserLog_h(hopeModule.getModuleid()));
                        moduleGroupSub.setText(hopeModule.getDescription());
                        moduleGroupSub.setTitle(hopeModule.getModulename());
                        sublist.add(moduleGroupSub);
                    }else{
                        if(mStatus==1){
                            ModuleGroupSub moduleGroupSub = new ModuleGroupSub();
                            moduleGroupSub.setModuleid(hopeModule.getModuleid());
                            String mIcon = hopeModule.getIcon();
                            try {
                                mIcon=java.net.URLDecoder.decode(mIcon,"utf-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            moduleGroupSub.setIcon(webUrlq+mIcon);
                            String mImage = hopeModule.getImage();
                            try {
                                mImage=java.net.URLDecoder.decode(mImage,"utf-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            moduleGroupSub.setImage(webUrlq+mImage);
                            String mUrl = hopeModule.getUrl();
                            try {
                                mUrl=java.net.URLDecoder.decode(mUrl,"utf-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            moduleGroupSub.setUrl(mUrl);
                            moduleGroupSub.setSureCount(hopeCommentsMapper.sureCount(hopeModule.getModuleid(),HopeCommentsEnum.dianzan.getKey()));
                            IdentifySub identifySub = new IdentifySub();
                            Map<Integer,Boolean> map = checkAamSure(aamid,hopeModule.getModuleid());
                            identifySub.setShadeImg1(map.get(HopeCommentsEnum.dianzan.getKey()));
                            identifySub.setShadeImg2(map.get(HopeCommentsEnum.diancai.getKey()));
                            identifySub.setShadeImg(map.get(HopeCommentsEnum.guanzhu.getKey()));
                            moduleGroupSub.setIdentifySub(identifySub);
                            moduleGroupSub.setTel(hopeUserLogMapper.queryUserLog_h(hopeModule.getModuleid()));
                            moduleGroupSub.setText(hopeModule.getDescription());
                            moduleGroupSub.setTitle(hopeModule.getModulename());
                            sublist.add(moduleGroupSub);
                        }else if(mStatus==0){
                            ModuleGroupSub moduleGroupSub = new ModuleGroupSub();
                            moduleGroupSub.setModuleid(hopeModule.getModuleid());
                            String mIcon = hopeModule.getIcon();
                            try {
                                mIcon=java.net.URLDecoder.decode(mIcon,"utf-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            moduleGroupSub.setIcon(webUrlq+mIcon);
                            String mImage = hopeModule.getImage();
                            try {
                                mImage=java.net.URLDecoder.decode(mImage,"utf-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            moduleGroupSub.setImage(webUrlq+FiltrateUtil.getModuleSmallImage(mImage));
                            String mUrl = hopeModule.getUrl();
                            try {
                                mUrl=java.net.URLDecoder.decode(mUrl,"utf-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            moduleGroupSub.setUrl(mUrl);

                            moduleGroupSub.setSureCount(hopeCommentsMapper.sureCount(hopeModule.getModuleid(),HopeCommentsEnum.dianzan.getKey()));
                            IdentifySub identifySub = new IdentifySub();
                            Map<Integer,Boolean> map = checkAamSure(aamid,hopeModule.getModuleid());
                            identifySub.setShadeImg1(map.get(HopeCommentsEnum.dianzan.getKey()));
                            identifySub.setShadeImg2(map.get(HopeCommentsEnum.diancai.getKey()));
                            identifySub.setShadeImg(map.get(HopeCommentsEnum.guanzhu.getKey()));
                            moduleGroupSub.setIdentifySub(identifySub);
                            moduleGroupSub.setTel(hopeUserLogMapper.queryUserLog_h(hopeModule.getModuleid()));
                            moduleGroupSub.setText(hopeModule.getDescription());
                            moduleGroupSub.setTitle(hopeModule.getModulename());
                            sublist.add(moduleGroupSub);
                        }
                    }

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
    * @Author: qinwankang    ---------------20200730sql语句tel逻辑重写
    * @Date: 2020/5/22 16:43
    */
    private JSONObject insertQuanbu(List<HopeModule> hopeModuleList,String aamid,Integer mStatus){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String webUrlq =imgLoaclPath;
        log.info("requestServerName {} requestServerPort {}", request.getServerName(),request.getServerPort());
       // List<HopeComments>hopeCommentsList=getZanOrCai(aamid,new Date());
        List<ModuleGroupSub>sublist = new ArrayList<ModuleGroupSub>();
        Set<String>moduleGroupSet = new HashSet<String>();
      //  Set<Integer>moduleidSet = new HashSet<Integer>();
        for(HopeModule hopeModule: hopeModuleList){
            moduleGroupSet.add(hopeModule.getModulegroupname());
         //   moduleidSet.add(hopeModule.getModuleid());
            if(StringUtils.isEmpty(mStatus)|| null==mStatus){
                ModuleGroupSub moduleGroupSub = new ModuleGroupSub();
                moduleGroupSub.setModuleid(hopeModule.getModuleid());
                String mIcon = hopeModule.getIcon();
                try {
                    mIcon=java.net.URLDecoder.decode(mIcon,"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                moduleGroupSub.setIcon(webUrlq+mIcon);
                String mImage = hopeModule.getImage();
                try {
                    mImage=java.net.URLDecoder.decode(mImage,"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                moduleGroupSub.setImage(webUrlq+mImage);
                String mUrl = hopeModule.getUrl();
                try {
                    mUrl=java.net.URLDecoder.decode(mUrl,"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
              //  moduleGroupSub.setMstatus(true);
                moduleGroupSub.setUrl(mUrl);
                moduleGroupSub.setModulegroupname(hopeModule.getModulegroupname());
               // moduleGroupSub.setSureCount(hopeCommentsMapper.sureCount(hopeModule.getModuleid(),HopeCommentsEnum.dianzan.getKey()));
               // IdentifySub identifySub = new IdentifySub();
               /* Map<Integer,Boolean> map = checkAamSure(aamid,hopeModule.getModuleid());

                identifySub.setShadeImg1(map.get(HopeCommentsEnum.dianzan.getKey()));
                identifySub.setShadeImg2(map.get(HopeCommentsEnum.diancai.getKey()));
                identifySub.setShadeImg(map.get(HopeCommentsEnum.guanzhu.getKey()));*/
               // moduleGroupSub.setIdentifySub(identifySub);
                //访问量
              //  moduleGroupSub.setTel(hopeUserLogMapper.queryUserLog_h(hopeModule.getModuleid()));
                //问题修改；；；；；
           //   moduleGroupSub.setTel(hopeViewTimesMapper.queryViewTimes(hopeModule.getModuleid()));
                moduleGroupSub.setText(hopeModule.getDescription());
                moduleGroupSub.setTitle(hopeModule.getModulename());

                //update by 开艳20200805 添加返回的modulestatus
                moduleGroupSub.setModulestatus(hopeModule.getModulestatus());


                sublist.add(moduleGroupSub);
            }else{
                if(mStatus==0){
                    ModuleGroupSub moduleGroupSub = new ModuleGroupSub();
                    moduleGroupSub.setModuleid(hopeModule.getModuleid());
                    String mIcon = hopeModule.getIcon();
                    try {
                        mIcon=java.net.URLDecoder.decode(mIcon,"utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    moduleGroupSub.setIcon(webUrlq+mIcon);
                    String mImage = hopeModule.getImage();
                    try {
                        mImage=java.net.URLDecoder.decode(mImage,"utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    moduleGroupSub.setImage(webUrlq+mImage);
                    String mUrl = hopeModule.getUrl();
                    try {
                        mUrl=java.net.URLDecoder.decode(mUrl,"utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                  //  moduleGroupSub.setMstatus(true);
                    moduleGroupSub.setUrl(mUrl);
                    moduleGroupSub.setModulegroupname(hopeModule.getModulegroupname());
                   // moduleGroupSub.setSureCount(hopeCommentsMapper.sureCount(hopeModule.getModuleid(),HopeCommentsEnum.dianzan.getKey()));
                   /* IdentifySub identifySub = new IdentifySub();
                    Map<Integer,Boolean> map = checkAamSure(aamid,hopeModule.getModuleid());
                    identifySub.setShadeImg1(map.get(HopeCommentsEnum.dianzan.getKey()));
                    identifySub.setShadeImg2(map.get(HopeCommentsEnum.diancai.getKey()));
                    identifySub.setShadeImg(map.get(HopeCommentsEnum.guanzhu.getKey()));*/
                   // moduleGroupSub.setIdentifySub(identifySub);
                  //  moduleGroupSub.setTel(hopeUserLogMapper.queryUserLog_h(hopeModule.getModuleid()));
                //    moduleGroupSub.setTel(hopeViewTimesMapper.queryViewTimes(hopeModule.getModuleid()));
                    moduleGroupSub.setText(hopeModule.getDescription());
                    moduleGroupSub.setTitle(hopeModule.getModulename());

                    //update by 开艳20200805 添加返回的modulestatus
                    moduleGroupSub.setModulestatus(hopeModule.getModulestatus());

                    sublist.add(moduleGroupSub);
                }else if(mStatus==1){
                    ModuleGroupSub moduleGroupSub = new ModuleGroupSub();
                    moduleGroupSub.setModuleid(hopeModule.getModuleid());
                    String mIcon = hopeModule.getIcon();
                    try {
                        mIcon=java.net.URLDecoder.decode(mIcon,"utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    moduleGroupSub.setIcon(webUrlq+mIcon);
                    String mImage = hopeModule.getImage();
                    try {
                        mImage=java.net.URLDecoder.decode(mImage,"utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    moduleGroupSub.setImage(webUrlq+FiltrateUtil.getModuleSmallImage(mImage));
                    String mUrl = hopeModule.getUrl();
                    try {
                        mUrl=java.net.URLDecoder.decode(mUrl,"utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                //    moduleGroupSub.setMstatus(false);
                    moduleGroupSub.setUrl(mUrl);
                    moduleGroupSub.setModulegroupname(hopeModule.getModulegroupname());
                   // moduleGroupSub.setSureCount(hopeCommentsMapper.sureCount(hopeModule.getModuleid(),HopeCommentsEnum.dianzan.getKey()));
                   /* IdentifySub identifySub = new IdentifySub();
                    Map<Integer,Boolean> map = checkAamSure(aamid,hopeModule.getModuleid());
                    identifySub.setShadeImg1(map.get(HopeCommentsEnum.dianzan.getKey()));
                    identifySub.setShadeImg2(map.get(HopeCommentsEnum.diancai.getKey()));
                    identifySub.setShadeImg(map.get(HopeCommentsEnum.guanzhu.getKey()));
                    moduleGroupSub.setIdentifySub(identifySub);*/
                  //  moduleGroupSub.setTel(hopeUserLogMapper.queryUserLog_h(hopeModule.getModuleid()));
                   // moduleGroupSub.setTel(hopeViewTimesMapper.queryViewTimes(hopeModule.getModuleid()));
                    moduleGroupSub.setText(hopeModule.getDescription());
                    moduleGroupSub.setTitle(hopeModule.getModulename());

                    //update by 开艳20200805 添加返回的modulestatus
                    moduleGroupSub.setModulestatus(hopeModule.getModulestatus());

                    sublist.add(moduleGroupSub);
                }
            }

        }

      //  ModuleGroup moduleGroup = new ModuleGroup();
       // moduleGroup.setModulegroupType(HopeModuleGroupEnum.quanbu.getValue());
       // moduleGroup.setList(sublist);
       // List<HopeViewTimes>reVlist= new ArrayList<HopeViewTimes>();
        //if(moduleidSet!=null&&moduleidSet.size()!=0){
        //    reVlist = hopeViewTimesMapper.queryViewTimesByModuleid(moduleidSet);
       // }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sublist",sublist);
        jsonObject.put("moduleGroupSet",moduleGroupSet);
       // jsonObject.put("reVlist",reVlist);
        return jsonObject;
    }
    //某用户点赞，点踩数据
    private List<HopeComments> getZanOrCai(String aamid,Date date){
        List<HopeComments> hopeCommentsList=hopeCommentsMapper.getZanOrCai(aamid,date);
       return hopeCommentsList;
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
     //List<HopeComments> hopeCommentsList= hopeCommentsMapper.checkAamSure(aamid,moduleid);
      HopeUserFavor hopeUserFavor = new HopeUserFavor();
      hopeUserFavor.setAamid(aamid);
      hopeUserFavor.setModuleid(moduleid);
      hopeUserFavor.setFavortype(UserFavorTypeEnum.shitu.getKey());
     // Integer hopeUserFavor1 = hopeUserFavorMapper.selectWatchModule(hopeUserFavor);
      Map<Integer,Boolean> sureMap = new HashMap<Integer,Boolean>();
      sureMap.put(HopeCommentsEnum.dianzan.getKey(),false);
      sureMap.put(HopeCommentsEnum.diancai.getKey(),false);
      sureMap.put(HopeCommentsEnum.guanzhu.getKey(),false);
     /* if(hopeUserFavor1!=null){
          sureMap.put(HopeCommentsEnum.guanzhu.getKey(),true);
      }*/
      /*if(hopeCommentsList==null||hopeCommentsList.size()==0){
          //该用户没有点过赞或者点过踩
             return sureMap;
      }*/
        Calendar calendar = Calendar.getInstance();
     /* for(HopeComments hopeComments:hopeCommentsList){
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
      }*/
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

        private String url;

        private String modulename;

        private String shortname;

        private String modulegroupname;

        private boolean mstatus;

        private long footTime;

        private String modulestatus;

        public String getModulestatus() {
            return modulestatus;
        }

        public void setModulestatus(String modulestatus) {
            this.modulestatus = modulestatus;
        }

        public long getFootTime() {
            return footTime;
        }

        public void setFootTime(long footTime) {
            this.footTime = footTime;
        }

        public boolean isMstatus() {
            return mstatus;
        }

        public void setMstatus(boolean mstatus) {
            this.mstatus = mstatus;
        }

        public String getModulegroupname() {
            return modulegroupname;
        }

        public void setModulegroupname(String modulegroupname) {
            this.modulegroupname = modulegroupname;
        }

        public String getModulename() {
            return modulename;
        }

        public String getShortname() {
            return shortname;
        }

        public void setModulename(String modulename) {
            this.modulename = modulename;
        }

        public void setShortname(String shortname) {
            this.shortname = shortname;
        }

        private IdentifySub identifySub;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

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
