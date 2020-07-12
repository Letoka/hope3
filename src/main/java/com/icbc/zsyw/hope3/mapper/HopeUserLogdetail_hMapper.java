package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeUserLogdetail_h;
import org.apache.ibatis.annotations.Mapper;


public interface HopeUserLogdetail_hMapper {
    int insert(HopeUserLogdetail_h record);

    int insertSelective(HopeUserLogdetail_h record);
}