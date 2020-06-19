package com.icbc.zsyw.hope3.impl;

import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopeImagebar;
import com.icbc.zsyw.hope3.enums.HopePrivRequestEnum;
import com.icbc.zsyw.hope3.enums.HopeviewImagebarPrivRequestEnum;
import com.icbc.zsyw.hope3.mapper.HopeImagebarMapper;
import com.icbc.zsyw.hope3.service.HopeImagebarService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
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
public class HopeImagebarServiceImpl<main> implements HopeImagebarService {
    @Resource
    private HopeImagebarMapper hopeImagebarMapper;
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
    public BaseResponse<List<HopeImagebar>> queryHopeImagebar(String aamid, String deptid) {
        //入参非空校验
        for (HopePrivRequestEnum headCheckEnum : HopePrivRequestEnum.values()) {
            if (headCheckEnum.isNotEmpty() && StringUtils.isEmpty(aamid) && headCheckEnum.name().toString().equals("aamid"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());
            if(headCheckEnum.isNotEmpty() && StringUtils.isEmpty(deptid) && headCheckEnum.name().toString().equals("deptid"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());
        }
        List<HopeImagebar> list= hopeImagebarMapper.queryHopeImagebar(aamid,deptid);
        if(list!=null && list.size()!=0){
            List<HopeImagebar>relist = new ArrayList<HopeImagebar>();
            Date date = new Date();
            for(HopeImagebar hopeImagebar:list){
                if(hopeImagebar.getStarttime()==null){
                    if(hopeImagebar.getEndtime().after(date)){
                        relist.add(hopeImagebar);
                    }
                }else{
                   if(hopeImagebar.getStarttime().before(date) || hopeImagebar.getStarttime().compareTo(date)==0 && hopeImagebar.getEndtime().after(date)){
                       relist.add(hopeImagebar);
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
        for (HopeviewImagebarPrivRequestEnum headCheckEnum : HopeviewImagebarPrivRequestEnum.values()) {
            if (headCheckEnum.isNotEmpty() && StringUtils.isEmpty(imagebarid) && headCheckEnum.name().toString().equals("imagebarid"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());

        }
        HopeImagebar hopeImagebar =    hopeImagebarMapper.selectByPrimaryKey(imagebarid);
        if(!"".equals(hopeImagebar.getModuleurl())){
            String moduleUrl = hopeImagebar.getModuleurl();
            return new BaseResponse<String>(BaseResponse.STATUS_HANDLE_SUCCESS,moduleUrl,BaseResponse.STATUS_HANDLER_SUCCESS);
        }
        return new BaseResponse<String>(BaseResponse.DATA_STATUS_NULL,null,BaseResponse.DATA_STATUS_NULLR);
    }


}
