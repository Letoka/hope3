package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeRecommend;

import java.util.List;

public interface HopeRecommendMapper {
    int insert(HopeRecommend record);

    int insertSelective(HopeRecommend record);

    List<HopeRecommend> selectAll();
}