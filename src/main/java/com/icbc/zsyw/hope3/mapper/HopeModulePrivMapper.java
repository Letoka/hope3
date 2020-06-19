package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeModulePriv;

public interface HopeModulePrivMapper {
    int deleteByPrimaryKey(Integer moduleprivid);

    int insert(HopeModulePriv record);

    int insertSelective(HopeModulePriv record);

    HopeModulePriv selectByPrimaryKey(Integer moduleprivid);

    int updateByPrimaryKeySelective(HopeModulePriv record);

    int updateByPrimaryKey(HopeModulePriv record);
}