package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopePrivGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HopePrivGroupMapper {
    int insert(HopePrivGroup record);

    int insertSelective(HopePrivGroup record);
@Select("SELECT privgroupid FROM hopeprivgroup WHERE aamid = #{aamid} OR deptid =#{deptid} OR odeptid = #{odeptid} GROUP BY privgroupid")
    List<String> queryPrivgroupid(String aamid, String deptid, String odeptid);
}