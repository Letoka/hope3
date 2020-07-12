package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeWatchGroup;
import org.apache.ibatis.annotations.Mapper;


public interface HopeWatchGroupMapper {
    int insert(HopeWatchGroup record);

    int insertSelective(HopeWatchGroup record);
}