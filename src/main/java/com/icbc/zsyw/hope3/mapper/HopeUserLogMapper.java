package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeUserLog;

public interface HopeUserLogMapper {
    int deleteByPrimaryKey(Integer userlogid);

    int insert(HopeUserLog record);

    int insertSelective(HopeUserLog record);

    HopeUserLog selectByPrimaryKey(Integer userlogid);

    int updateByPrimaryKeySelective(HopeUserLog record);

    int updateByPrimaryKey(HopeUserLog record);
}