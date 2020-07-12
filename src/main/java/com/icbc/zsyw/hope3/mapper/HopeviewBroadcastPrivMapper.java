package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeviewBroadcastPriv;
import org.apache.ibatis.annotations.Mapper;


public interface HopeviewBroadcastPrivMapper {
    int insert(HopeviewBroadcastPriv record);

    int insertSelective(HopeviewBroadcastPriv record);
}