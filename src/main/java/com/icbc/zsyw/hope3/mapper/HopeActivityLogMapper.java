package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeActicity;
import com.icbc.zsyw.hope3.dto.HopeActivityLog;
import com.icbc.zsyw.hope3.dto.HopeActivityLogKey;
import com.icbc.zsyw.hope3.dto.HopePrivGroup;
import org.apache.ibatis.annotations.Select;

public interface HopeActivityLogMapper {
    int deleteByPrimaryKey(HopeActivityLogKey key);

    int insert(HopeActivityLog record);

    int insertSelective(HopeActivityLog record);

    HopeActivityLog selectByPrimaryKey(HopeActivityLogKey key);

    int updateByPrimaryKeySelective(HopeActivityLog record);

    int updateByPrimaryKey(HopeActivityLog record);//activityid activityid
    @Select("SELECT * FROM hopeactivitylog WHERE activityid = #{activityid} AND aamid = #{aamid} AND liked = 1\n")
    HopeActivityLog checkDianzan(Integer activityid, String aamid);
    @Select("SELECT COUNT(*) FROM hopeactivitylog WHERE aamid =  #{aamid}  AND activityid = #{activityid} AND liked = 0\n")
    Integer queryActiviCount(Integer activityid, String aamid);
}