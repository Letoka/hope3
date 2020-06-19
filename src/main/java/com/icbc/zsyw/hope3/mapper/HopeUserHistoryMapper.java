package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeUserHistory;

public interface HopeUserHistoryMapper {
    int deleteByPrimaryKey(Integer userhistoryid);

    int insert(HopeUserHistory record);

    int insertSelective(HopeUserHistory record);

    HopeUserHistory selectByPrimaryKey(Integer userhistoryid);

    int updateByPrimaryKeySelective(HopeUserHistory record);

    int updateByPrimaryKey(HopeUserHistory record);
}