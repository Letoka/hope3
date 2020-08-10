package com.icbc.zsyw.hope3.impl;

import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopeActicity;
import com.icbc.zsyw.hope3.dto.HopeModule;
import com.icbc.zsyw.hope3.dto.HopeShortcutBar;
import com.icbc.zsyw.hope3.dto.HopeShortcutBarPriv;
import com.icbc.zsyw.hope3.enums.ActivityClassEnum;
import com.icbc.zsyw.hope3.enums.HopePrivRequestEnum;
import com.icbc.zsyw.hope3.mapper.*;
import com.icbc.zsyw.hope3.service.HopeShortcutBarService;
import com.icbc.zsyw.hope3.util.FiltrateUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.SessionTrackingMode;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @ClassName HopeShortcutBarServiceImpl
 * @Description
 * @Author qinwankang
 * @Date 2020/5/28 8:53
 * @Version V1.0
 **/
@Service
public class HopeShortcutBarServiceImpl implements HopeShortcutBarService {
    @Resource
    private HopeShortcutBarMapper hopeShortcutBarMapper;
    @Resource
    private HopeActicityMapper hopeActicityMapper;
    @Resource
    private HopePrivGroupMapper hopePrivGroupMapper;
    @Resource
    private HopeModuleMapper hopeModuleMapper;
    @Value("${img.local.path}")
    private String imgLoaclPath;
/**
* 功能描述:查询三大块放的视图类别（如：分行，重点业务等等），其中第三块放置最新文章更新的广告，
 * 如果最新文章上下墙时间过期，则第三块仍显示原有的视图类别，
 *如果文章设置权限，只有对应权限用户可以看到，如果文章没有设置权限则所有用户都可以看到
 * 其次，权限这块，还有文章是否展示，如果不展示，即使拥有权限也看不到
 * @param hopeShortcutBarPriv
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeShortcutBar>>
* @Author: qinwankang
* @Date: 2020/5/28 10:08
*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<ShortcutBarSub> queryShortcutBar(HopeShortcutBarPriv hopeShortcutBarPriv) {
        String aamid = hopeShortcutBarPriv.getAamid();
        String deptid = hopeShortcutBarPriv.getDeptid();
        String odeptid = hopeShortcutBarPriv.getOdeptid();
        //入参非空校验
       // for (HopePrivRequestEnum headCheckEnum : HopePrivRequestEnum.values()) {
            if (HopePrivRequestEnum.aamid.isNotEmpty() && StringUtils.isEmpty(aamid))
                return new BaseResponse<>(HopePrivRequestEnum.aamid.getReturnCode(), null, HopePrivRequestEnum.aamid.getMsg());
            if(HopePrivRequestEnum.deptid.isNotEmpty() && StringUtils.isEmpty(deptid) )
                return new BaseResponse<>(HopePrivRequestEnum.deptid.getReturnCode(), null, HopePrivRequestEnum.deptid.getMsg());
            if(HopePrivRequestEnum.odeptid.isNotEmpty() && StringUtils.isEmpty(odeptid) )
                return new BaseResponse<>(HopePrivRequestEnum.odeptid.getReturnCode(), null, HopePrivRequestEnum.odeptid.getMsg());
     //   }
      //  List<HopeShortcutBar> list = hopeShortcutBarMapper.queryShortcutBar(aamid,deptid,odeptid);
        List<HopeShortcutBar> list = hopeShortcutBarMapper.queryShortcutBarByAamid(aamid);
        if(list==null||list.size()==0){
            list=hopeShortcutBarMapper.queryShortcutBarByDeptid(deptid);
            if(list==null||list.size()==0){
                list=hopeShortcutBarMapper.queryShortcutBarByOdeptid(odeptid);
                if(list==null||list.size()==0){
                    list=hopeShortcutBarMapper.queryByadoid(aamid,deptid,odeptid);
                    if(list==null||list.size()==0){
                        list=hopeShortcutBarMapper.queryShortcutBarByPrivtype();
                    }
                }
            }

        }
        if(list==null || list.size()==0)
            return new BaseResponse<>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String webUrlq =imgLoaclPath;

       List<HopeShortcutBar>shortList=new ArrayList<HopeShortcutBar>();
        for(HopeShortcutBar shortcutBar:list){
            String sImage = shortcutBar.getShortcutImage();
            try {
                sImage=java.net.URLDecoder.decode(sImage,"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            shortcutBar.setShortcutImage(webUrlq+sImage);
            //去重
            if(shortList==null || shortList.size()==0){
                HopeShortcutBar hopeShortcutBar = new HopeShortcutBar();
                hopeShortcutBar.setShortcutbarname(shortcutBar.getShortcutbarname());
                hopeShortcutBar.setWeight(shortcutBar.getWeight());
                shortList.add(hopeShortcutBar);
            }else {
                int count =0;
                for(HopeShortcutBar shortcutBar1:shortList){
                        if(shortcutBar1.getShortcutbarname().equals(shortcutBar.getShortcutbarname())){
                            break;
                        }else {
                            count++;
                        }
                }
                if(count==shortList.size()){
                    HopeShortcutBar hopeShortcutBar = new HopeShortcutBar();
                    hopeShortcutBar.setShortcutbarname(shortcutBar.getShortcutbarname());
                    hopeShortcutBar.setWeight(shortcutBar.getWeight());
                    shortList.add(hopeShortcutBar);
                }
            }

        }
        //排序
        List<HopeShortcutBar>sortList=FiltrateUtil.sortShortcutbarSet(shortList);
        List<HopeShortcutBar>shortlist=new ArrayList<HopeShortcutBar>();
        if(sortList!=null&&sortList.size()!=0){
            for(HopeShortcutBar shortcutBarS:sortList){
                String sname = shortcutBarS.getShortcutbarname();
                HopeShortcutBar hopeShortcutBar = new HopeShortcutBar();
                List<Integer>list1=new ArrayList<Integer>();
                Integer count = 0;

                for(HopeShortcutBar shortcutBar:list){
                     if(shortcutBar.getShortcutbarname().equals(sname)){
                         count++;
                         list1.add(shortcutBar.getModuleid());
                         if(StringUtils.isEmpty(hopeShortcutBar.getShortcutbarname())){
                             hopeShortcutBar.setShortcutbarname(shortcutBar.getShortcutbarname());
                         }
                         if(StringUtils.isEmpty(hopeShortcutBar.getShortcutImage())){
                             String sImage = shortcutBar.getShortcutImage();
                             try {
                                 sImage=java.net.URLDecoder.decode(sImage,"utf-8");
                             } catch (UnsupportedEncodingException e) {
                                 e.printStackTrace();
                             }
                             hopeShortcutBar.setShortcutImage(sImage);
                         }
                         if(StringUtils.isEmpty(hopeShortcutBar.getShortcutbardescript())){
                             hopeShortcutBar.setShortcutbardescript(shortcutBar.getShortcutbardescript());
                         }
                         if(hopeShortcutBar.getShortcutbarid()==null){
                             hopeShortcutBar.setShortcutbarid(shortcutBar.getShortcutbarid());
                         }
                         if(hopeShortcutBar.getModuleid()==null){
                             hopeShortcutBar.setModuleid(shortcutBar.getModuleid());
                         }
                     }
                }
                if(count==1){
                    if(!StringUtils.isEmpty(hopeShortcutBar.getModuleid())){
                        HopeModule module=hopeModuleMapper.queryUrlBymoduleid(hopeShortcutBar.getModuleid());
                        String sUrl="";
                        if(!StringUtils.isEmpty(module.getUseurltype())&&module.getUseurltype()==1){
                            sUrl= FiltrateUtil.getModuleUrl(module.getUrl(),aamid,deptid);
                        }else{
                            sUrl = module.getUrl();
                        }
                        try {
                            sUrl=java.net.URLDecoder.decode(sUrl,"utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        hopeShortcutBar.setShortModuleUrl(sUrl);

                    }

                }
                hopeShortcutBar.setList(list1);
                shortlist.add(hopeShortcutBar);
            }
        }

        /*ShortcutBarSub shortcutBarSub = new ShortcutBarSub();
        shortcutBarSub.setList(shortlist);
        return new BaseResponse<ShortcutBarSub>(BaseResponse.STATUS_HANDLE_SUCCESS,shortcutBarSub,BaseResponse.STATUS_HANDLER_SUCCESS);*/
        List<HopeActicity> hopeActicityS =  hopeActicityMapper.queryLatestActivity();
        HopeActicity hopeActicity=null;
        if(hopeActicityS!=null && hopeActicityS.size()!=0){
            hopeActicity=hopeActicityS.get(hopeActicityS.size()-1);
        }
        if(hopeActicity==null || hopeActicity.getShowed()==0){
            ShortcutBarSub shortcutBarSub = new ShortcutBarSub();
            shortcutBarSub.setList(shortlist);
            return new BaseResponse<ShortcutBarSub>(BaseResponse.STATUS_HANDLE_SUCCESS,shortcutBarSub,BaseResponse.STATUS_HANDLER_SUCCESS);
        }

      if(hopeActicity.getActivitytype()==0){
            ShortcutBarSub shortcutBarSub = new ShortcutBarSub();
            shortcutBarSub.setList(shortlist);
            Date now = new Date();
            if(null==hopeActicity.getStarttime()&& null==hopeActicity.getEndtime()){
                String sImage = hopeActicity.getImagename();
                try {
                    sImage=java.net.URLDecoder.decode(sImage,"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                hopeActicity.setImagename(webUrlq+sImage);
                shortcutBarSub.setHopeActicity(hopeActicity);
            }
            if(null==hopeActicity.getStarttime()&&null!=hopeActicity.getEndtime()){
                if(hopeActicity.getEndtime().after(now)){
                    String sImage = hopeActicity.getImagename();
                    try {
                        sImage=java.net.URLDecoder.decode(sImage,"utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    hopeActicity.setImagename(webUrlq+sImage);
                    shortcutBarSub.setHopeActicity(hopeActicity);
                }
            }
            if(null!=hopeActicity.getStarttime()&&null==hopeActicity.getEndtime()){
                if(hopeActicity.getStarttime().before(now)){
                    String sImage = hopeActicity.getImagename();
                    try {
                        sImage=java.net.URLDecoder.decode(sImage,"utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    hopeActicity.setImagename(webUrlq+sImage);
                    shortcutBarSub.setHopeActicity(hopeActicity);
                }
            }
            if(null!=hopeActicity.getStarttime()&&null!=hopeActicity.getEndtime()){
                if(hopeActicity.getStarttime().before(now) && hopeActicity.getEndtime().after(now)){
                    String sImage = hopeActicity.getImagename();
                    try {
                        sImage=java.net.URLDecoder.decode(sImage,"utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    hopeActicity.setImagename(webUrlq+sImage);
                    shortcutBarSub.setHopeActicity(hopeActicity);
                }
            }


           return new BaseResponse<ShortcutBarSub>(BaseResponse.STATUS_HANDLE_SUCCESS,shortcutBarSub,BaseResponse.STATUS_HANDLER_SUCCESS);
        }else if(hopeActicity.getActivitytype()==1){
            List<String>prilist=hopePrivGroupMapper.queryPrivgroupid(aamid,deptid,odeptid);
            if(prilist==null || prilist.size()==0){
                ShortcutBarSub shortcutBarSub = new ShortcutBarSub();
                shortcutBarSub.setList(shortlist);
                return new BaseResponse<>(BaseResponse.STATUS_HANDLE_SUCCESS,shortcutBarSub,BaseResponse.STATUS_HANDLER_SUCCESS);
          }else if(prilist!=null && prilist.size()!=0){
                ShortcutBarSub shortcutBarSub = new ShortcutBarSub();
                shortcutBarSub.setList(shortlist);
                for(String pr:prilist){
                    if(!StringUtils.isEmpty(hopeActicity.getPrivgroupid())){
                        if(hopeActicity.getPrivgroupid().equals(pr)){
                            Date now = new Date();
                            if(null==hopeActicity.getStarttime()&& null==hopeActicity.getEndtime()){
                                String sImage = hopeActicity.getImagename();
                                try {
                                    sImage=java.net.URLDecoder.decode(sImage,"utf-8");
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                                hopeActicity.setImagename(webUrlq+sImage);
                                shortcutBarSub.setHopeActicity(hopeActicity);
                            }
                            if(null==hopeActicity.getStarttime()&&null!=hopeActicity.getEndtime()){
                                if(hopeActicity.getEndtime().after(now)){
                                    String sImage = hopeActicity.getImagename();
                                    try {
                                        sImage=java.net.URLDecoder.decode(sImage,"utf-8");
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    }
                                    hopeActicity.setImagename(webUrlq+sImage);
                                    shortcutBarSub.setHopeActicity(hopeActicity);
                                }
                            }
                            if(null!=hopeActicity.getStarttime()&&null==hopeActicity.getEndtime()){
                                if(hopeActicity.getStarttime().before(now)){
                                    String sImage = hopeActicity.getImagename();
                                    try {
                                        sImage=java.net.URLDecoder.decode(sImage,"utf-8");
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    }
                                    hopeActicity.setImagename(webUrlq+sImage);
                                    shortcutBarSub.setHopeActicity(hopeActicity);
                                }
                            }
                            if(null!=hopeActicity.getStarttime()&&null!=hopeActicity.getEndtime()){
                                if(hopeActicity.getStarttime().before(now) && hopeActicity.getEndtime().after(now)){
                                    String sImage = hopeActicity.getImagename();
                                    try {
                                        sImage=java.net.URLDecoder.decode(sImage,"utf-8");
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    }
                                    hopeActicity.setImagename(webUrlq+sImage);
                                    shortcutBarSub.setHopeActicity(hopeActicity);
                                }
                            }

                        }
                    }

                }
                return new BaseResponse<>(BaseResponse.STATUS_HANDLE_SUCCESS,shortcutBarSub,BaseResponse.STATUS_HANDLER_SUCCESS);
            }
        }
        return new BaseResponse<ShortcutBarSub>(BaseResponse.STATUS_SYSTEM_FAILURE,BaseResponse.STATUS_SYSTEM_FAILUREE);
    }


    /**
    * 功能描述:三大块视图返回所需要的封装类
     * @param
    * @return:
    * @Author: qinwankang
    * @Date: 2020/6/4 10:29
    */
    public class ShortcutBarSub{
        private HopeActicity hopeActicity;
        private List list;

        public HopeActicity getHopeActicity() {
            return hopeActicity;
        }

        public List getList() {
            return list;
        }

        public void setHopeActicity(HopeActicity hopeActicity) {
            this.hopeActicity = hopeActicity;
        }

        public void setList(List list) {
            this.list = list;
        }
    }
/**
* 功能描述:三大块当中，通过每个块当中名称获取对应视图（获取视图时注意用户对应权限）
 * @param hopeShortcutBarPriv
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeShortcutBar>>
* @Author: qinwankang
* @Date: 2020/5/28 11:18
*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<List<HopeModule>> queryModuleByShortcutbar(HopeShortcutBarPriv hopeShortcutBarPriv) {
        String aamid = hopeShortcutBarPriv.getAamid();
        String deptid = hopeShortcutBarPriv.getDeptid();
        String odeptid = hopeShortcutBarPriv.getOdeptid();
        String shortcutbarname = hopeShortcutBarPriv.getShortcutbarname();
        //入参非空校验
        //for (HopePrivRequestEnum headCheckEnum : HopePrivRequestEnum.values()) {
            if (HopePrivRequestEnum.aamid.isNotEmpty() && StringUtils.isEmpty(aamid))
                return new BaseResponse<>(HopePrivRequestEnum.aamid.getReturnCode(), null, HopePrivRequestEnum.aamid.getMsg());
           if(HopePrivRequestEnum.deptid.isNotEmpty() && StringUtils.isEmpty(deptid) )
                return new BaseResponse<>(HopePrivRequestEnum.deptid.getReturnCode(), null, HopePrivRequestEnum.deptid.getMsg());
            if(HopePrivRequestEnum.odeptid.isNotEmpty() && StringUtils.isEmpty(odeptid) )
                return new BaseResponse<>(HopePrivRequestEnum.odeptid.getReturnCode(), null, HopePrivRequestEnum.odeptid.getMsg());
            if(HopePrivRequestEnum.shortcutbarname.isNotEmpty() && StringUtils.isEmpty(shortcutbarname) )
                return new BaseResponse<>(HopePrivRequestEnum.shortcutbarname.getReturnCode(), null, HopePrivRequestEnum.shortcutbarname.getMsg());
       // }
        List<HopeModule> list = hopeShortcutBarMapper.queryModuleByShortcutbar(aamid,deptid,odeptid,shortcutbarname);
        if(list==null || list.size()==0)
            return new BaseResponse<>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);
        return new BaseResponse<List<HopeModule>>(BaseResponse.STATUS_HANDLE_SUCCESS,list,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
}
