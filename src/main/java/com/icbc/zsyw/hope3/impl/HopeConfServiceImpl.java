package com.icbc.zsyw.hope3.impl;

import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.mapper.HopeConfMapper;
import com.icbc.zsyw.hope3.service.HopeConfService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName HopeConfServiceImpl
 * @Description
 * @Author qinwankang
 * @Date 2020/7/3 17:01
 * @Version V1.0
 **/
@Service
public class HopeConfServiceImpl implements HopeConfService {
    @Resource
    private HopeConfMapper hopeConfMapper;
    /**
    * 功能描述:掌上运维首页加载获取url
     * @param
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.lang.String>
    * @Author: qinwankang
    * @Date: 2020/7/4 8:45
    */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<String> queryHopeConfUrl() {
        String confUrl = hopeConfMapper.queryHopeConfUrl();
        if(StringUtils.isEmpty(confUrl)){
            return new BaseResponse<>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);
        }
        return new BaseResponse<String>(BaseResponse.STATUS_HANDLE_SUCCESS,confUrl,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
/**
* 功能描述:掌上运维首页加载获取对应用户
 * @param
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<java.lang.String>>
* @Author: qinwankang
* @Date: 2020/7/4 8:48
*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<String[]> queryHopeConfUser() {
        String userStr = hopeConfMapper.queryHopeConfUser();
        if(StringUtils.isEmpty(userStr)){
            return new BaseResponse<>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);
        }
        String[] userArr=userStr.split(",");
        return new BaseResponse<String[]>(BaseResponse.STATUS_HANDLE_SUCCESS,userArr,BaseResponse.STATUS_HANDLER_SUCCESS);
    }
}
