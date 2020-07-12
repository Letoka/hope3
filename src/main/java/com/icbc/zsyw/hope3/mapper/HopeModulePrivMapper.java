package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeModulePriv;
import org.apache.ibatis.annotations.Mapper;


public interface HopeModulePrivMapper {
    int deleteByPrimaryKey(Integer moduleprivid);

    int insert(HopeModulePriv record);

    int insertSelective(HopeModulePriv record);

    HopeModulePriv selectByPrimaryKey(Integer moduleprivid);

    int updateByPrimaryKeySelective(HopeModulePriv record);

    int updateByPrimaryKey(HopeModulePriv record);
}