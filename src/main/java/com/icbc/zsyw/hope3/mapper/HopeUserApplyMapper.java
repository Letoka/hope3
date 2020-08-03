package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeUserApply;
import com.icbc.zsyw.hope3.dto.HopeUserApplyKey;

public interface HopeUserApplyMapper {
    int deleteByPrimaryKey(HopeUserApplyKey key);

    int insert(HopeUserApply record);

    int insertSelective(HopeUserApply record);

    HopeUserApply selectByPrimaryKey(HopeUserApplyKey key);

    int updateByPrimaryKeySelective(HopeUserApply record);

    int updateByPrimaryKey(HopeUserApply record);
}