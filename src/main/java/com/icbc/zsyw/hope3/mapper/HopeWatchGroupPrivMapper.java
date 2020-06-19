package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeWatchGroupPriv;

public interface HopeWatchGroupPrivMapper {
    int deleteByPrimaryKey(String watchgroupprivid);

    int insert(HopeWatchGroupPriv record);

    int insertSelective(HopeWatchGroupPriv record);

    HopeWatchGroupPriv selectByPrimaryKey(String watchgroupprivid);

    int updateByPrimaryKeySelective(HopeWatchGroupPriv record);

    int updateByPrimaryKey(HopeWatchGroupPriv record);
}