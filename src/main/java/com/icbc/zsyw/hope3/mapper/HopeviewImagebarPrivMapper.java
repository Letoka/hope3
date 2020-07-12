package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeviewImagebarPriv;
import org.apache.ibatis.annotations.Mapper;


public interface HopeviewImagebarPrivMapper {
    int insert(HopeviewImagebarPriv record);

    int insertSelective(HopeviewImagebarPriv record);
}