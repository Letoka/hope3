package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeActicity;
import com.icbc.zsyw.hope3.dto.HopePrivGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface HopeActicityMapper {
    int deleteByPrimaryKey(Integer activityid);

    int insert(HopeActicity record);

    int insertSelective(HopeActicity record);

    HopeActicity selectByPrimaryKey(Integer activityid);

    int updateByPrimaryKeySelective(HopeActicity record);

    int updateByPrimaryKey(HopeActicity record);
    @Select("SELECT hc.* FROM hopeacticity hc  INNER JOIN\n" +
        "(SELECT privgroupid FROM hopeprivgroup WHERE aamid = #{aamid} OR deptid = #{deptid} OR odeptid = #{odeptid} GROUP BY privgroupid) b ON hc.privgroupid = b.privgroupid \n" +
        "WHERE hc.activitytype = 1 AND hc.showed =1\n" +
        "UNION\n" +
        "SELECT * FROM hopeacticity WHERE activitytype = 0 AND showed =1 ORDER BY starttime DESC")
    List<HopeActicity> queryWatchActivity(HopePrivGroup hopePrivGroup);
    @Select("SELECT hc.* FROM hopeacticity hc INNER JOIN\n" +
            "(SELECT MAX(timeori) AS timeori FROM hopeacticity ) b ON hc.timeori = b.timeori")
    HopeActicity queryLatestActivity();
    @Select("SELECT * FROM hopeacticity ORDER BY timeori DESC")
    List<HopeActicity> getHopeActivitys();
}