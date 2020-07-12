package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeUserLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

public interface HopeUserLogMapper {
    int deleteByPrimaryKey(Integer userlogid);

    int insert(HopeUserLog record);

    int insertSelective(HopeUserLog record);

    HopeUserLog selectByPrimaryKey(Integer userlogid);

    int updateByPrimaryKeySelective(HopeUserLog record);

    int updateByPrimaryKey(HopeUserLog record);
   // @Select("SELECT COUNT(*) FROM hopeuserlog WHERE moduleid = #{moduleid}\n")
   // @Select("SELECT COUNT(*) FROM hopeuserlog WHERE moduleid = #{moduleid}\n")
    Integer queryUserLog_h(Integer moduleid);
}