package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeviewModulePriv;
import org.apache.ibatis.annotations.Mapper;


public interface HopeviewModulePrivMapper {
    int insert(HopeviewModulePriv record);

    int insertSelective(HopeviewModulePriv record);
}