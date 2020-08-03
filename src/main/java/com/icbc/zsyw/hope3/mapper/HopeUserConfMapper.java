package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeUserConf;

public interface HopeUserConfMapper {
    int deleteByPrimaryKey(Integer confid);

    int insert(HopeUserConf record);

    int insertSelective(HopeUserConf record);

    HopeUserConf selectByPrimaryKey(Integer confid);

    int updateByPrimaryKeySelective(HopeUserConf record);

    int updateByPrimaryKey(HopeUserConf record);

    HopeUserConf queryByAamidAndName(String aamid, String setname);

    void updateConfvalue(String aamid, Integer confvalue, String setname);

    Integer selectByaamidAndName(String aamid, String setname);
}