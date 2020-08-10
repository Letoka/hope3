package com.icbc.zsyw.hope3.impl;

import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopeImagebar;
import com.icbc.zsyw.hope3.dto.HopeModule;
import com.icbc.zsyw.hope3.enums.HopePrivRequestEnum;
import com.icbc.zsyw.hope3.enums.HopeviewImagebarPrivRequestEnum;
import com.icbc.zsyw.hope3.mapper.HopeImagebarMapper;
import com.icbc.zsyw.hope3.mapper.HopeModuleMapper;
import com.icbc.zsyw.hope3.service.HopeImagebarService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName HopeImagebarServiceImpl
 * @Description
 * @Author qinwankang
 * @Date 2020/5/18 15:27
 * @Version V1.0
 **/
@Service
@Slf4j
public class HopeImagebarServiceImpl implements HopeImagebarService {
    @Resource
    private HopeImagebarMapper hopeImagebarMapper;
    @Resource
    private HopeModuleMapper hopeModuleMapper;
    @Value("${img.local.path}")
    private String imgLoaclPath;
    /**
    * 功能描述:查询头图
     * @param aamid
     * @param deptid
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeImagebar>>
    * @Author: qinwankang
    * @Date: 2020/5/20 11:06
    */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<List<HopeImagebar>> queryHopeImagebar(String aamid, String deptid,String odeptid) {
        //入参非空校验
       // for (HopePrivRequestEnum headCheckEnum : HopePrivRequestEnum.values()) {
            if (HopePrivRequestEnum.aamid.isNotEmpty() && StringUtils.isEmpty(aamid) )
                return new BaseResponse<>(HopePrivRequestEnum.aamid.getReturnCode(), null, HopePrivRequestEnum.aamid.getMsg());
            if(HopePrivRequestEnum.deptid.isNotEmpty() && StringUtils.isEmpty(deptid))
                return new BaseResponse<>(HopePrivRequestEnum.deptid.getReturnCode(), null, HopePrivRequestEnum.deptid.getMsg());
            if(HopePrivRequestEnum.odeptid.isNotEmpty() && StringUtils.isEmpty(odeptid))
                return new BaseResponse<>(HopePrivRequestEnum.odeptid.getReturnCode(), null, HopePrivRequestEnum.odeptid.getMsg());
       // }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String webUrlq =imgLoaclPath;
        log.info("requestServerName {} requestServerPort {}", request.getServerName(),request.getServerPort());
        List<HopeImagebar> list= hopeImagebarMapper.queryHopeImagebar(aamid,deptid,odeptid);
        //查询权限下视图
        List<HopeModule>hopeModuleList =   hopeModuleMapper.queryHopeModule(aamid,deptid,odeptid);
        if(list!=null && list.size()!=0){
            List<HopeImagebar>relist = new ArrayList<HopeImagebar>();
            Date date = new Date();
            for(HopeImagebar hopeImagebar:list){
                if(hopeImagebar.getStarttime()==null){
                    if(hopeImagebar.getEndtime().after(date)){
                        if(null!=hopeImagebar.getModuleid()){
                           // String iUrl= hopeModuleMapper.queryUrlBymoduleids(aamid,deptid,odeptid,hopeImagebar.getModuleid());
                            String iUrl="";
                            if(hopeModuleList!=null&&hopeModuleList.size()!=0){
                                for(HopeModule hopeModule:hopeModuleList){
                                    if(hopeModule.getModuleid()==hopeImagebar.getModuleid()){
                                        iUrl=hopeModule.getUrl();
                                    }
                                }
                            }
                         if(!StringUtils.isEmpty(iUrl)){
                                try {
                                    iUrl=  java.net.URLDecoder.decode(iUrl,"utf-8");
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                                hopeImagebar.setImagebarurl(iUrl);
                                String icon = hopeImagebar.getIcon();
                                try {
                                    icon=java.net.URLDecoder.decode(icon,"utf-8");
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                                hopeImagebar.setIcon(webUrlq+icon);
                                relist.add(hopeImagebar);
                            }
                        }else if(null==hopeImagebar.getModuleid()){
                            String icon = hopeImagebar.getIcon();
                            try {
                                icon=java.net.URLDecoder.decode(icon,"utf-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            hopeImagebar.setIcon(webUrlq+icon);
                            relist.add(hopeImagebar);
                        }

                    }
                }else{
                   if((hopeImagebar.getStarttime().before(date) || hopeImagebar.getStarttime().compareTo(date)==0) && hopeImagebar.getEndtime().after(date)){
                       if(null!=hopeImagebar.getModuleid()){
                           // String iUrl= hopeModuleMapper.queryUrlBymoduleids(aamid,deptid,odeptid,hopeImagebar.getModuleid());
                           String iUrl="";
                           if(hopeModuleList!=null&&hopeModuleList.size()!=0){
                               for(HopeModule hopeModule:hopeModuleList){
                                   if(hopeModule.getModuleid()==hopeImagebar.getModuleid()){
                                       iUrl=hopeModule.getUrl();
                                   }
                               }
                           }
                           try {
                               iUrl=  java.net.URLDecoder.decode(iUrl,"utf-8");
                           } catch (UnsupportedEncodingException e) {
                               e.printStackTrace();
                           }
                           if(!StringUtils.isEmpty(iUrl)){
                               hopeImagebar.setImagebarurl(iUrl);
                               String icon = hopeImagebar.getIcon();
                               try {
                                   icon=java.net.URLDecoder.decode(icon,"utf-8");
                               } catch (UnsupportedEncodingException e) {
                                   e.printStackTrace();
                               }
                               hopeImagebar.setIcon(webUrlq+icon);
                               relist.add(hopeImagebar);
                           }

                       }else if(null==hopeImagebar.getModuleid()){
                           String icon = hopeImagebar.getIcon();
                           try {
                               icon=java.net.URLDecoder.decode(icon,"utf-8");
                           } catch (UnsupportedEncodingException e) {
                               e.printStackTrace();
                           }
                           hopeImagebar.setIcon(webUrlq+icon);
                           relist.add(hopeImagebar);
                       }

                   }
                }
            }
         if(relist!=null && relist.size()!=0){
             return new BaseResponse<>(BaseResponse.STATUS_HANDLE_SUCCESS,relist,BaseResponse.STATUS_HANDLER_SUCCESS);
         }
            return new BaseResponse<>(BaseResponse.DATA_STATUS_NULL,null,BaseResponse.DATA_STATUS_NULLR);
        }
        return new BaseResponse<>(BaseResponse.DATA_STATUS_NULL,null,BaseResponse.DATA_STATUS_NULLR);
    }
   /**
   * 功能描述:点击头图返回视图
    * @param imagebarid
   * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.String>
   * @Author: qinwankang
   * @Date: 2020/5/19 15:23
   */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<String> queryModuleUrl(Integer imagebarid) {
        //入参非空校验
        /*for (HopeviewImagebarPrivRequestEnum headCheckEnum : HopeviewImagebarPrivRequestEnum.values()) {
            if (headCheckEnum.isNotEmpty() && StringUtils.isEmpty(imagebarid) && headCheckEnum.name().toString().equals("imagebarid"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());

        }
        HopeImagebar hopeImagebar =    hopeImagebarMapper.selectByPrimaryKey(imagebarid);
        if(!"".equals(hopeImagebar.getModuleurl())){
            String moduleUrl = hopeImagebar.getModuleurl();
            return new BaseResponse<String>(BaseResponse.STATUS_HANDLE_SUCCESS,moduleUrl,BaseResponse.STATUS_HANDLER_SUCCESS);
        }
        return new BaseResponse<String>(BaseResponse.DATA_STATUS_NULL,null,BaseResponse.DATA_STATUS_NULLR);
    */
    return null;
    }


}
