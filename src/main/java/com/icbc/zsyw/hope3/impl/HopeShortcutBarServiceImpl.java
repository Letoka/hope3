package com.icbc.zsyw.hope3.impl;

import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopeActicity;
import com.icbc.zsyw.hope3.dto.HopeModule;
import com.icbc.zsyw.hope3.dto.HopeShortcutBar;
import com.icbc.zsyw.hope3.dto.HopeShortcutBarPriv;
import com.icbc.zsyw.hope3.enums.HopePrivRequestEnum;
import com.icbc.zsyw.hope3.mapper.HopeActicityMapper;
import com.icbc.zsyw.hope3.mapper.HopePrivGroupMapper;
import com.icbc.zsyw.hope3.mapper.HopeShortcutBarMapper;
import com.icbc.zsyw.hope3.mapper.HopeUserLog_hMapper;
import com.icbc.zsyw.hope3.service.HopeShortcutBarService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
        for (HopePrivRequestEnum headCheckEnum : HopePrivRequestEnum.values()) {
            if (headCheckEnum.isNotEmpty() && StringUtils.isEmpty(aamid) && headCheckEnum.name().toString().equals("aamid"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());
            if(headCheckEnum.isNotEmpty() && StringUtils.isEmpty(deptid) && headCheckEnum.name().toString().equals("deptid"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());
            if(headCheckEnum.isNotEmpty() && StringUtils.isEmpty(odeptid) && headCheckEnum.name().toString().equals("odeptid"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());
        }
        List<HopeShortcutBar> list = hopeShortcutBarMapper.queryShortcutBar(aamid,deptid,odeptid);
        if(list==null || list.size()==0)
            return new BaseResponse<>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);
        HopeActicity hopeActicity =  hopeActicityMapper.queryLatestActivity();
        if(hopeActicity==null || hopeActicity.getShowed()==0){
            ShortcutBarSub shortcutBarSub = new ShortcutBarSub();
            shortcutBarSub.setList(list);
            return new BaseResponse<ShortcutBarSub>(BaseResponse.STATUS_HANDLE_SUCCESS,shortcutBarSub,BaseResponse.STATUS_HANDLER_SUCCESS);
        }
        if(hopeActicity.getActivitytype()==0){
            ShortcutBarSub shortcutBarSub = new ShortcutBarSub();
            shortcutBarSub.setList(list);
            Date now = new Date();
            if(hopeActicity.getStarttime().before(now) && hopeActicity.getEndtime().after(now)){
                shortcutBarSub.setHopeActicity(hopeActicity);
            }
            return new BaseResponse<ShortcutBarSub>(BaseResponse.STATUS_HANDLE_SUCCESS,shortcutBarSub,BaseResponse.STATUS_HANDLER_SUCCESS);
        }else if(hopeActicity.getActivitytype()==1){
            List<String>prilist=hopePrivGroupMapper.queryPrivgroupid(aamid,deptid,odeptid);
            if(prilist==null || prilist.size()==0){
                ShortcutBarSub shortcutBarSub = new ShortcutBarSub();
                shortcutBarSub.setList(list);
                return new BaseResponse<>(BaseResponse.STATUS_HANDLE_SUCCESS,shortcutBarSub,BaseResponse.STATUS_HANDLER_SUCCESS);
          }else if(prilist!=null && prilist.size()!=0){
                ShortcutBarSub shortcutBarSub = new ShortcutBarSub();
                shortcutBarSub.setList(list);
                for(String pr:prilist){
                    if(hopeActicity.getPrivgroupid().equals(pr)){
                        Date now = new Date();
                        if(hopeActicity.getStarttime().before(now) && hopeActicity.getEndtime().after(now)){
                            shortcutBarSub.setHopeActicity(hopeActicity);
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
        for (HopePrivRequestEnum headCheckEnum : HopePrivRequestEnum.values()) {
            if (headCheckEnum.isNotEmpty() && StringUtils.isEmpty(aamid) && headCheckEnum.name().toString().equals("aamid"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());
            if(headCheckEnum.isNotEmpty() && StringUtils.isEmpty(deptid) && headCheckEnum.name().toString().equals("deptid"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());
            if(headCheckEnum.isNotEmpty() && StringUtils.isEmpty(odeptid) && headCheckEnum.name().toString().equals("odeptid"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());
            if(headCheckEnum.isNotEmpty() && StringUtils.isEmpty(odeptid) && headCheckEnum.name().toString().equals("odeptid"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());
            if(headCheckEnum.isNotEmpty() && StringUtils.isEmpty(shortcutbarname) && headCheckEnum.name().toString().equals("shortcutbarname"))
                return new BaseResponse<>(headCheckEnum.getReturnCode(), null, headCheckEnum.getMsg());
        }
        List<HopeModule> list = hopeShortcutBarMapper.queryModuleByShortcutbar(aamid,deptid,odeptid,shortcutbarname);
        if(list==null || list.size()==0)
            return new BaseResponse<>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);
        return new BaseResponse<List<HopeModule>>(BaseResponse.STATUS_HANDLE_SUCCESS,list,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
}
