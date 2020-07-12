package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeMedalInfo;
import org.apache.ibatis.annotations.Mapper;


public interface HopeMedalInfoMapper {
    int deleteByPrimaryKey(Integer medalid);

    int insert(HopeMedalInfo record);

    int insertSelective(HopeMedalInfo record);

    HopeMedalInfo selectByPrimaryKey(Integer medalid);

    int updateByPrimaryKeySelective(HopeMedalInfo record);

    int updateByPrimaryKey(HopeMedalInfo record);
}