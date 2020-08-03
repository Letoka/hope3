package com.icbc.zsyw.hope3.impl;

import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopeBroadcast;
import com.icbc.zsyw.hope3.dto.TcenterLog;
import com.icbc.zsyw.hope3.enums.BroadcastTypeEnum;
import com.icbc.zsyw.hope3.enums.HopePrivRequestEnum;
import com.icbc.zsyw.hope3.mapper.HopeBroadcastMapper;
import com.icbc.zsyw.hope3.service.HopeBroadcastService;
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
 * @ClassName HopeBroadcastServiceImpl
 * @Description
 * @Author qinwankang
 * @Date 2020/5/14 16:31
 * @Version V1.0
 **/
@Service
public class HopeBroadcastServiceImpl implements HopeBroadcastService {
    @Resource
    private HopeBroadcastMapper hopeBroadcastMapper;
    /**
    * 功能描述:项目测试使用，和业务需求无关（可留可不留）
     * @param tcenterLog
    * @return: com.icbc.zsyw.hope3.dto.HopeBroadcast
    * @Author: qinwankang
    * @Date: 2020/5/21 15:07
    */
    @Override
    public HopeBroadcast checkUserSex(TcenterLog tcenterLog) {
        HopeBroadcast h =    hopeBroadcastMapper.selectByPrimaryKey(1);
        return  h;
    }
/**
* 功能描述:查询“掌上播报”
 * @param aamid
 * @param deptid
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeBroadcast>>
* @Author: qinwankang
* @Date: 2020/5/20 22:30
*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<List<HopeBroadcast>> queryHopeBroadcast(String aamid, String deptid,String odeptid) {
        //入参非空校验
        //for (HopePrivRequestEnum headCheckEnum : HopePrivRequestEnum.values()) {
            if (HopePrivRequestEnum.aamid.isNotEmpty() && StringUtils.isEmpty(aamid) )
                return new BaseResponse<>(HopePrivRequestEnum.aamid.getReturnCode(), null, HopePrivRequestEnum.aamid.getMsg());
            if(HopePrivRequestEnum.deptid.isNotEmpty() && StringUtils.isEmpty(deptid) )
                return new BaseResponse<>(HopePrivRequestEnum.deptid.getReturnCode(), null, HopePrivRequestEnum.deptid.getMsg());
            if(HopePrivRequestEnum.odeptid.isNotEmpty() && StringUtils.isEmpty(odeptid) )
                return new BaseResponse<>(HopePrivRequestEnum.odeptid.getReturnCode(), null, HopePrivRequestEnum.odeptid.getMsg());
       // }
        List<HopeBroadcast>list =   hopeBroadcastMapper.queryHopeBroadcast(aamid,deptid,odeptid);
        BaseResponse<List<HopeBroadcast>> response = checkHopeBroadcast(list);
        return response;
    }
/**
* 功能描述:查询“掌上播报”详情
 * @param aamid
 * @param deptid
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeBroadcast>>
* @Author: qinwankang
* @Date: 2020/5/21 10:12
*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<List<BroadcastNameResult>> queryHopeBroadcastDetail(String aamid, String deptid,String odeptid) {
        //入参非空校验
      //  for (HopePrivRequestEnum headCheckEnum : HopePrivRequestEnum.values()) {
            if (HopePrivRequestEnum.aamid.isNotEmpty() && StringUtils.isEmpty(aamid) )
                return new BaseResponse<>(HopePrivRequestEnum.aamid.getReturnCode(), null, HopePrivRequestEnum.aamid.getMsg());
            if(HopePrivRequestEnum.deptid.isNotEmpty() && StringUtils.isEmpty(deptid) )
                return new BaseResponse<>(HopePrivRequestEnum.deptid.getReturnCode(), null, HopePrivRequestEnum.deptid.getMsg());
            if(HopePrivRequestEnum.odeptid.isNotEmpty() && StringUtils.isEmpty(odeptid) )
                return new BaseResponse<>(HopePrivRequestEnum.odeptid.getReturnCode(), null, HopePrivRequestEnum.odeptid.getMsg());
        //}
        List<HopeBroadcast>list =   hopeBroadcastMapper.queryHopeBroadcast(aamid,deptid,odeptid);
        BaseResponse<List<BroadcastNameResult>> response = handleHopeBroadcast(list);
        return response;
    }
    /**
    * 功能描述:把掌上播报获取的结果通过通过类别（公告/优化/上新/推广）分类返回
     * @param list
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.impl.HopeBroadcastServiceImpl.BroadcastNameResult>>
    * @Author: qinwankang
    * @Date: 2020/5/21 14:52
    */
    private  BaseResponse<List<BroadcastNameResult>> handleHopeBroadcast(List<HopeBroadcast> list){
        if(list==null)
            return new BaseResponse<>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);
        List<BroadcastNameResult>relist = new ArrayList<BroadcastNameResult>();
        for (BroadcastTypeEnum broadcastTypeEnum : BroadcastTypeEnum.values()) {
            List<BroadcastName>sublist = new ArrayList<BroadcastName>();
            for(HopeBroadcast hopeBroadcast: list){
                if (broadcastTypeEnum.getValue().equals(hopeBroadcast.getBroadcasttype())){
                    BroadcastName broadcastName = new BroadcastName();
                    broadcastName.setBroadcastname(hopeBroadcast.getBroadcastname());
                    broadcastName.setStarttime(hopeBroadcast.getStarttime());
                    sublist.add(broadcastName);
                }
            }
            BroadcastNameResult broadcastNameResult = new BroadcastNameResult();
            broadcastNameResult.setBroadcasttype(broadcastTypeEnum.getValue());
            broadcastNameResult.setList(sublist);
            relist.add(broadcastNameResult);
        }
        if(relist==null)
            return new BaseResponse<>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);
        return new BaseResponse<List<BroadcastNameResult>>(BaseResponse.STATUS_HANDLE_SUCCESS,relist,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
    /**
    * 功能描述:返回开始时间小于当下，结束时间大于当下的掌上播报
     * @param list
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeBroadcast>>
    * @Author: qinwankang
    * @Date: 2020/5/21 14:57
    */
    private BaseResponse<List<HopeBroadcast>> checkHopeBroadcast(List<HopeBroadcast> list){
        if(list!=null && list.size()!=0){
            List<HopeBroadcast>relist = new ArrayList<HopeBroadcast>();
            Date date = new Date();
            for (HopeBroadcast hopeBroadcast:list){
                if((hopeBroadcast.getStarttime().before(date) || hopeBroadcast.getStarttime().compareTo(date)==0) && hopeBroadcast.getEndtime().after(date)){
                    relist.add(hopeBroadcast);
                }
            }
            if(relist!=null&&relist.size()!=0){
               return new BaseResponse<>(BaseResponse.STATUS_HANDLE_SUCCESS,relist,BaseResponse.STATUS_HANDLER_SUCCESS);
            }
            return new BaseResponse<>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);
        }
        return new BaseResponse<>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);
    }
    /**
    * 功能描述:掌上播报获取的结果通过通过类别（公告/优化/上新/推广）分类所需要类的子类
     * @param
    * @return:
    * @Author: qinwankang
    * @Date: 2020/5/21 15:01
    */
    public  class BroadcastName {
        private String broadcastname;
        private Date starttime;
        private boolean broadcastnamedef=false;

        public String getBroadcastname() {
            return broadcastname;
        }

        public Date getStarttime() {
            return starttime;
        }

        public void setBroadcastname(String broadcastname) {
            this.broadcastname = broadcastname;
        }

        public void setStarttime(Date starttime) {
            this.starttime = starttime;
        }

        public boolean isBroadcastnamedef() {
            return broadcastnamedef;
        }

        public void setBroadcastnamedef(boolean broadcastnamedef) {
            this.broadcastnamedef = broadcastnamedef;
        }
    }
    /**
    * 功能描述:掌上播报获取的结果通过通过类别（公告/优化/上新/推广）分类所需类
     * @param
    * @return:
    * @Author: qinwankang
    * @Date: 2020/5/21 15:01
    */
     public class BroadcastNameResult<List> {
        private String broadcasttype;
        private List list;

        public String getBroadcasttype() {
            return broadcasttype;
        }

        public List getList() {
            return list;
        }

        public void setBroadcasttype(String broadcasttype) {
            this.broadcasttype = broadcasttype;
        }

        public void setList(List list) {
            this.list = list;
        }
    }

}
