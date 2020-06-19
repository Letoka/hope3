package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeUserConf;
import com.icbc.zsyw.hope3.dto.HopeUserConfKey;

public interface HopeUserConfMapper {
    int deleteByPrimaryKey(HopeUserConfKey key);

    int insert(HopeUserConf record);

    int insertSelective(HopeUserConf record);

    HopeUserConf selectByPrimaryKey(HopeUserConfKey key);

    int updateByPrimaryKeySelective(HopeUserConf record);

    int updateByPrimaryKey(HopeUserConf record);
}