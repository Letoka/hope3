package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeModule;
import com.icbc.zsyw.hope3.dto.HopeSearchkey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface HopeSearchkeyMapper {
    int deleteByPrimaryKey(Integer searchid);

    int insert(HopeSearchkey record);

    int insertSelective(HopeSearchkey record);

    HopeSearchkey selectByPrimaryKey(Integer searchid);

    int updateByPrimaryKeySelective(HopeSearchkey record);

    int updateByPrimaryKey(HopeSearchkey record);
   // @Select("SELECT hx.* FROM\n" +
      //      "(SELECT hm.moduleid,hm.modulename,hm.url,hm.useurltype,hm.icon,hm.image,hm.description,hl.contentclass,hl.weight,hl.keyname FROM hopemodule hm INNER JOIN \n" +
       //     "(SELECT hs.keyname,hs.contentclass,hs.contentid,hs.weight FROM hopesearchkey hs WHERE hs.keyname LIKE  CONCAT('%',#{name},'%') AND hs.contentclass=0) hl ON hl.contentid = hm.moduleid WHERE hm.enabled=1) hx INNER JOIN\n" +
       //     "(SELECT moduleid FROM hopeviewmodulepriv WHERE aamid = #{aamid} OR deptid = #{deptid} OR odeptid = #{odeptid} GROUP BY moduleid) b ON hx.moduleid = b.moduleid  GROUP BY hx.moduleid ORDER BY hx.weight DESC\n")
    List<HopeModule> searchModuleByKey(String name, String aamid, String deptid, String odeptid);
  //  @Select("SELECT hm.moduleid,hm.modulename,hm.url,hm.useurltype,hm.icon,hm.image,hm.description,hl.contentclass,hl.weight,hl.keyname FROM hopemodule hm INNER JOIN\n" +
      //      "        (SELECT hs.keyname,hs.contentclass,hs.contentid,hs.weight FROM hopesearchkey hs WHERE hs.keyname LIKE  CONCAT('%',#{name},'%')) hl ON hl.contentid = hm.moduleid WHERE hm.enabled=1 GROUP BY hm.moduleid ORDER BY hl.weight DESC\n")
    List<HopeModule> searchModuleByKeyNo(String name);
}