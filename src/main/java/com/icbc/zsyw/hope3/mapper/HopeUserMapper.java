package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeUser;

public interface HopeUserMapper {
    int deleteByPrimaryKey(String aamid);

    int insert(HopeUser record);

    int insertSelective(HopeUser record);

    HopeUser selectByPrimaryKey(String aamid);

    int updateByPrimaryKeySelective(HopeUser record);

    int updateByPrimaryKey(HopeUser record);
}