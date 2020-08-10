package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeViewTimes;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface HopeViewTimesMapper {
    int insert(HopeViewTimes record);

    int insertSelective(HopeViewTimes record);

    Integer queryViewTimes(Integer moduleid);

    Integer queryTimeByaaAndmoid(String aamid, Integer moduleid);

    void updateViewTimes(Integer reVtimes, Date logtime, String aamid, Integer moduleid);

    List<HopeViewTimes> queryViewTimesByGroup();

   // List<HopeViewTimes> queryViewTimesByModuleid(Set<Integer> moduleidSet);
}