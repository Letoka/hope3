package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeBroadcast;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HopeBroadcastMapper {
    int deleteByPrimaryKey(Integer broadcastid);

    int insert(HopeBroadcast record);

    int insertSelective(HopeBroadcast record);

    HopeBroadcast selectByPrimaryKey(Integer broadcastid);

    int updateByPrimaryKeySelective(HopeBroadcast record);

    int updateByPrimaryKey(HopeBroadcast record);
    @Select("SELECT hb.* FROM hopebroadcast hb INNER JOIN\n" +
            "                (SELECT broadcastid FROM hopeviewbroadcastpriv WHERE aamid = #{aamid} OR deptid = #{deptid} GROUP BY broadcastid ) b\n" +
            "                              ON (hb.broadcastid = b.broadcastid AND hb.enabled = 1) OR (hb.enabled = 1 AND hb.published = 1) GROUP BY hb.broadcastid ORDER BY hb.starttime")
    List<HopeBroadcast> queryHopeBroadcast(String aamid, String deptid);

}