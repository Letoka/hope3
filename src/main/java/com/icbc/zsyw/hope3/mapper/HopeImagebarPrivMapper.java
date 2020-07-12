package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeImagebarPriv;
import org.apache.ibatis.annotations.Mapper;


public interface HopeImagebarPrivMapper {
    int deleteByPrimaryKey(Integer imagebarprivid);

    int insert(HopeImagebarPriv record);

    int insertSelective(HopeImagebarPriv record);

    HopeImagebarPriv selectByPrimaryKey(Integer imagebarprivid);

    int updateByPrimaryKeySelective(HopeImagebarPriv record);

    int updateByPrimaryKey(HopeImagebarPriv record);
}