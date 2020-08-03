package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeUserInfo;

public interface HopeUserInfoMapper {
    int deleteByPrimaryKey(String aamid);

    int insert(HopeUserInfo record);

    int insertSelective(HopeUserInfo record);

    HopeUserInfo selectByPrimaryKey(String aamid);

    int updateByPrimaryKeySelective(HopeUserInfo record);

    int updateByPrimaryKey(HopeUserInfo record);

    String queryHopeUserInfo(String aamid);
}