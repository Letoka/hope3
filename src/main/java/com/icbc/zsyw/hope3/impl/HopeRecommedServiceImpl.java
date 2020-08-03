package com.icbc.zsyw.hope3.impl;

import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopeRecommend;
import com.icbc.zsyw.hope3.mapper.HopeRecommendMapper;
import com.icbc.zsyw.hope3.mapper.HopeSearchHistory_hMapper;
import com.icbc.zsyw.hope3.service.HopeRecommedService;
import com.icbc.zsyw.hope3.util.FiltrateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName HopeRecommedServiceImpl
 * @Description
 * @Author qinwankang
 * @Date 2020/7/22 21:28
 * @Version V1.0
 **/
@Service
public class HopeRecommedServiceImpl implements HopeRecommedService {
    @Resource
    private HopeRecommendMapper hopeRecommendMapper;
/**
* 功能描述:查询视图推荐
* @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeRecommend>>
* @Author: qinwankang
* @Date: 2020/7/23 8:40
*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BaseResponse<List<HopeRecommend>> queryRecommedModule() {
        List<HopeRecommend> recList=hopeRecommendMapper.selectAll();
        if(recList==null||recList.size()==0){
            return new BaseResponse<>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);
        }
        List<HopeRecommend>midList= FiltrateUtil.getRecommedMoudle(recList.get(0).getWeight(),recList);
        if(midList!=null&&midList.size()!=0){
            return new BaseResponse<List<HopeRecommend>>(BaseResponse.STATUS_HANDLE_SUCCESS,midList,BaseResponse.STATUS_HANDLER_SUCCESS);
        }
        return new BaseResponse<List<HopeRecommend>>(BaseResponse.DATA_STATUS_NULL,BaseResponse.DATA_STATUS_NULLR);
    }
}
