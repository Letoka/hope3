package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeMedalKey;
import org.apache.ibatis.annotations.Mapper;


public interface HopeMedalMapper {
    int deleteByPrimaryKey(HopeMedalKey key);

    int insert(HopeMedalKey record);

    int insertSelective(HopeMedalKey record);
}