package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeBroadcast;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface HopeBroadcastMapper {
    int deleteByPrimaryKey(Integer broadcastid);

    int insert(HopeBroadcast record);

    int insertSelective(HopeBroadcast record);

    HopeBroadcast selectByPrimaryKey(Integer broadcastid);

    int updateByPrimaryKeySelective(HopeBroadcast record);

    int updateByPrimaryKey(HopeBroadcast record);
   /* @Select("SELECT c.* FROM \n" +
            "(SELECT hb.* FROM hopebroadcast hb INNER JOIN\n" +
            "                            (SELECT broadcastid FROM hopeviewbroadcastpriv WHERE aamid = #{aamid} OR deptid = #{deptid} OR odeptid = #{odeptid} GROUP BY broadcastid ) b\n" +
            "                                          ON hb.broadcastid = b.broadcastid ) c WHERE c.enabled=1 AND c.published=0\n" +
            "UNION\n" +
            "SELECT * FROM hopebroadcast WHERE enabled=1 AND published=1 GROUP BY broadcastid ORDER BY starttime DESC")*/
    List<HopeBroadcast> queryHopeBroadcast(String aamid, String deptid,String odeptid);

}