package com.icbc.zsyw.hope3.mapper;

import com.alibaba.fastjson.JSONObject;
import com.icbc.zsyw.hope3.dto.HopeActicity;
import com.icbc.zsyw.hope3.dto.HopePrivGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface HopeActicityMapper {
    int deleteByPrimaryKey(Integer activityid);

    int insert(HopeActicity record);

    int insertSelective(HopeActicity record);

    HopeActicity selectByPrimaryKey(Integer activityid);

    int updateByPrimaryKeySelective(HopeActicity record);

    int updateByPrimaryKey(HopeActicity record);
   /* @Select("SELECT hc.* FROM hopeacticity hc  INNER JOIN\n" +
        "(SELECT privgroupid FROM hopeprivgroup WHERE aamid = #{aamid} OR deptid = #{deptid} OR odeptid = #{odeptid} GROUP BY privgroupid) b ON hc.privgroupid = b.privgroupid \n" +
        "WHERE hc.activitytype = 1 AND hc.showed =1 AND hc.textclass=#{textclass}\n" +
        "UNION\n" +
        "SELECT * FROM hopeacticity WHERE activitytype = 0 AND showed =1 AND textclass=#{textclass} ORDER BY timeori DESC")*/
    List<HopeActicity> queryWatchActivity(JSONObject jsonObject);
    /*@Select("SELECT hc.* FROM hopeacticity hc INNER JOIN\n" +
            "(SELECT MAX(timeori) AS timeori FROM hopeacticity ) b ON hc.timeori = b.timeori")*/
    List<HopeActicity> queryLatestActivity();
    /*@Select("SELECT * FROM hopeacticity ORDER BY timeori DESC")*/
    List<HopeActicity> getHopeActivitys();
    /*@Select("SELECT hc.* FROM hopeacticity hc  INNER JOIN\n" +
            "        (SELECT privgroupid FROM hopeprivgroup WHERE aamid =#{aamid} OR deptid = #{deptid} OR odeptid = #{odeptid} GROUP BY privgroupid) b ON hc.privgroupid = b.privgroupid \n" +
            "        WHERE hc.activitytype = 1 AND hc.showed =1 AND hc.textname LIKE CONCAT('%',#{name},'%')\n" +
            "        UNION\n" +
            "        SELECT * FROM hopeacticity WHERE activitytype = 0 AND showed =1 AND textname LIKE CONCAT('%',#{name},'%') ORDER BY timeori DESC")*/
    List<HopeActicity>  searchActiciByName( String aamid, String deptid, String odeptid,String name);
/*@Select("SELECT c.* FROM\n" +
        " ( SELECT ha.activityid,ha.textname,ha.textpath,ha.privgroupid,ha.activitytype,ha.showed,ha.starttime,ha.endtime,b.keyname,b.weight FROM hopeacticity ha INNER JOIN\n" +
        "(SELECT keyname,contentclass,contentid,weight FROM hopesearchkey WHERE keyname LIKE CONCAT('%',#{name},'%') AND contentclass=1 ) b ON b.contentid=ha.activityid) c INNER JOIN \n" +
        "(SELECT privgroupid FROM hopeprivgroup WHERE aamid =#{aamid} OR deptid = #{deptid} OR odeptid = #{odeptid} GROUP BY privgroupid) d ON d.privgroupid = c.privgroupid WHERE c.activitytype=1 \n" +
        "AND c.showed=1 \n" +
        "UNION\n" +
        " SELECT ha.activityid,ha.textname,ha.textpath,ha.privgroupid,ha.activitytype,ha.showed,ha.starttime,ha.endtime,b.keyname,b.weight FROM hopeacticity ha INNER JOIN\n" +
        "(SELECT keyname,contentclass,contentid,weight FROM hopesearchkey WHERE keyname LIKE CONCAT('%',#{name},'%') AND contentclass=1 ) b ON b.contentid=ha.activityid\n" +
        "WHERE ha.activitytype=0 AND ha.showed=1 ORDER BY weight DESC\n")*/
    List<HopeActicity> searchActiciByKey(String name,String aamid, String deptid, String odeptid);
/*@Select("SELECT * FROM hopeacticity WHERE showed =1 AND textname LIKE CONCAT('%',#{name},'%') ORDER BY timeori DESC")*/
    List<HopeActicity> searchActiciByNameNo(String name);
    /*@Select("SELECT ha.activityid,ha.textname,ha.textpath,ha.privgroupid,ha.activitytype,ha.showed,ha.starttime,ha.endtime,b.keyname,b.weight FROM hopeacticity ha INNER JOIN\n" +
            "(SELECT keyname,contentclass,contentid,weight FROM hopesearchkey WHERE keyname LIKE CONCAT('%',#{name},'%') AND contentclass=1) b ON b.contentid=ha.activityid\n" +
            "WHERE  ha.showed=1 ORDER BY b.weight DESC")*/
    List<HopeActicity> searchActiciByKeyNo(String name);
/*@Select("SELECT hc.* FROM hopeacticity hc  INNER JOIN\n" +
        "(SELECT privgroupid FROM hopeprivgroup WHERE aamid =#{aamid} OR deptid = #{deptid} OR odeptid = #{odeptid} GROUP BY privgroupid) b ON hc.privgroupid = b.privgroupid \n" +
        "WHERE hc.activitytype = 1 AND hc.showed =1 AND hc.textclass = #{textclass} AND hc.textname LIKE CONCAT('%',#{name},'%')\n" +
        "UNION\n" +
        "SELECT * FROM hopeacticity WHERE activitytype = 0 AND showed =1 AND textclass = #{textclass} AND textname LIKE CONCAT('%',#{name},'%') ORDER BY timeori DESC")*/
    List<HopeActicity> searchActiciByNameAndClass(String aamid, String deptid, String odeptid,Integer textclass,String name);
/*@Select("SELECT c.* FROM\n" +
        "( SELECT ha.activityid,ha.textname,ha.textpath,ha.privgroupid,ha.activitytype,ha.showed,ha.starttime,ha.endtime,ha.textclass,b.keyname,b.weight FROM hopeacticity ha INNER JOIN\n" +
        "(SELECT keyname,contentclass,contentid,weight FROM hopesearchkey WHERE keyname LIKE CONCAT('%',#{name},'%') AND contentclass=1 ) b ON b.contentid=ha.activityid) c INNER JOIN \n" +
        "(SELECT privgroupid FROM hopeprivgroup WHERE aamid =#{aamid} OR deptid = #{deptid} OR odeptid = #{odeptid} GROUP BY privgroupid) d ON d.privgroupid = c.privgroupid WHERE c.activitytype=1 \n" +
        "AND c.showed=1 AND c.textclass = #{textclass}\n" +
        "UNION\n" +
        " SELECT ha.activityid,ha.textname,ha.textpath,ha.privgroupid,ha.activitytype,ha.showed,ha.starttime,ha.endtime,ha.textclass,b.keyname,b.weight FROM hopeacticity ha INNER JOIN\n" +
        "(SELECT keyname,contentclass,contentid,weight FROM hopesearchkey WHERE keyname LIKE CONCAT('%',#{name},'%') AND contentclass=1 ) b ON b.contentid=ha.activityid\n" +
        "WHERE ha.activitytype=0 AND ha.showed=1 AND ha.textclass =#{textclass} ORDER BY weight DESC")*/
    List<HopeActicity> searchActiciByKeyandClass(String name, String aamid, String deptid, String odeptid,Integer textclass);

    List<Integer> queryActivityClass();

    List<String> queryActiviByAamid(String aamid);
}