package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeUserLogdetail;

public interface HopeUserLogdetailMapper {
    int deleteByPrimaryKey(Integer userlogdetailid);

    int insert(HopeUserLogdetail record);

    int insertSelective(HopeUserLogdetail record);

    HopeUserLogdetail selectByPrimaryKey(Integer userlogdetailid);

    int updateByPrimaryKeySelective(HopeUserLogdetail record);

    int updateByPrimaryKey(HopeUserLogdetail record);
}