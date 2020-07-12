package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeUserLog_h;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


public interface HopeUserLog_hMapper {
    int deleteByPrimaryKey(Integer userlogid);

    int insert(HopeUserLog_h record);

    int insertSelective(HopeUserLog_h record);

    HopeUserLog_h selectByPrimaryKey(Integer userlogid);

    int updateByPrimaryKeySelective(HopeUserLog_h record);

    int updateByPrimaryKey(HopeUserLog_h record);
   // @Select("SELECT COUNT(*) FROM hopeuserlog WHERE moduleid = #{moduleid}\n")
    Integer queryUserLog_h(Integer moduleid);
}