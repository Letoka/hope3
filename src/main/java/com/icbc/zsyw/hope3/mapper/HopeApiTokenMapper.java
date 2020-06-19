package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeApiToken;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HopeApiTokenMapper {
    int deleteByPrimaryKey(String apicode);

    int insert(HopeApiToken record);

    int insertSelective(HopeApiToken record);

    HopeApiToken selectByPrimaryKey(String apicode);

    int updateByPrimaryKeySelective(HopeApiToken record);

    int updateByPrimaryKey(HopeApiToken record);
}