package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeUserStatus;

public interface HopeUserStatusMapper {
    int deleteByPrimaryKey(String aamid);

    int insert(HopeUserStatus record);

    int insertSelective(HopeUserStatus record);

    HopeUserStatus selectByPrimaryKey(String aamid);

    int updateByPrimaryKeySelective(HopeUserStatus record);

    int updateByPrimaryKey(HopeUserStatus record);



    Integer queryStatusByAamid(String aamid);
}