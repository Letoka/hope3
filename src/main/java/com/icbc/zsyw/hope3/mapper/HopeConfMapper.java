package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeConf;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


public interface HopeConfMapper {
    int insert(HopeConf record);

    int insertSelective(HopeConf record);
//@Select("SELECT value FROM hopeconf WHERE parameter = 'openimgurl'")
    String queryHopeConfUrl();
  //  @Select("SELECT value FROM hopeconf WHERE parameter = 'testuser'")
    String queryHopeConfUser();
}