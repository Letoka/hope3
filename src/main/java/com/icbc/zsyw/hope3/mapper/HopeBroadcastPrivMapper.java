package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeBroadcastPriv;
import org.apache.ibatis.annotations.Mapper;


public interface HopeBroadcastPrivMapper {
    int deleteByPrimaryKey(Integer broadcastprivid);

    int insert(HopeBroadcastPriv record);

    int insertSelective(HopeBroadcastPriv record);

    HopeBroadcastPriv selectByPrimaryKey(Integer broadcastprivid);

    int updateByPrimaryKeySelective(HopeBroadcastPriv record);

    int updateByPrimaryKey(HopeBroadcastPriv record);
}