package com.icbc.zsyw.hope3.mapper;

import com.alibaba.fastjson.JSONArray;
import com.icbc.zsyw.hope3.dto.HopeModule;
import com.icbc.zsyw.hope3.dto.HopeUserHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface HopeModuleMapper {
    int deleteByPrimaryKey(Integer moduleid);

    int insert(HopeModule record);

    int insertSelective(HopeModule record);

    HopeModule selectByPrimaryKey(Integer moduleid);

    int updateByPrimaryKeySelective(HopeModule record);

    int updateByPrimaryKey(HopeModule record);
  // @Select("SELECT hm.moduleid,hm.modulename,hm.shortname,hm.description,hm.icon,hm.image,hm.modulegroupname,hm.url,hm.modulestatus FROM hopemodule hm INNER JOIN\n" +
   //        "(SELECT moduleid FROM hopeviewmodulepriv WHERE aamid = #{aamid} OR deptid = #{deptid} OR odeptid = #{odeptid} GROUP BY moduleid) b\n" +
    //       "ON hm.moduleid = b.moduleid  WHERE hm.enabled = 1 GROUP BY hm.moduleid\n")
   List<HopeModule> queryHopeModule(String aamid, String deptid,String odeptid);
  // @Select("SELECT hm.moduleid,hm.modulename,hm.shortname,hm.description,hm.icon,hm.image,hm.modulegroupname,hm.url FROM hopemodule hm INNER JOIN \n" +
     //      " (SELECT * FROM(SELECT moduleid FROM hopeuserhistory WHERE aamid =#{aamid}  ORDER BY logtime DESC) c ) b ON b.moduleid = hm.moduleid WHERE hm.enabled = 1")
   List<HopeModule> queryMyFoot(HopeUserHistory hopeUserHistory);
  // @Select({"SELECT hm.moduleid,hm.modulename,hm.url FROM hopemodule hm WHERE hm.modulename LIKE CONCAT('%',#{modulename},'%')"})
   List<HopeModule> searchMoudleByName(String modulename);
   // @Select("SELECT url,useurltype FROM hopemodule WHERE moduleid = #{moduleid} ")
    HopeModule queryUrlBymoduleid(Integer moduleid);
    /*@Select({"SELECT hm.moduleid,hm.modulename,hm.url,hm.icon,hm.useurltype,hm.image,hm.description FROM hopemodule hm INNER JOIN\n" +
            "(SELECT moduleid FROM hopeviewmodulepriv WHERE aamid = #{aamid} OR deptid =  #{deptid} OR odeptid =  #{odeptid} GROUP BY moduleid) b \n" +
            "ON hm.moduleid = b.moduleid  WHERE hm.enabled = 1 AND hm.modulename LIKE  CONCAT('%',#{name},'%') GROUP BY hm.moduleid ORDER BY weight DESC"})*/
    List<HopeModule> searchMoudleByName1( String aamid, String deptid, String odeptid,String name);
//@Select("SELECT hm.moduleid,hm.modulename,hm.url,hm.icon,hm.image,hm.description  FROM hopemodule hm WHERE hm.enabled = 1 AND hm.modulename LIKE  CONCAT('%',#{name},'%') GROUP BY hm.moduleid ORDER BY hm.weight DESC\n")
    List<HopeModule> searchMoudleByName1No(String name);
/*@Select("SELECT url FROM hopemodule hm INNER JOIN \n" +
        "(SELECT moduleid FROM hopeviewmodulepriv WHERE aamid = #{aamid} OR deptid =  #{deptid} OR odeptid =  #{odeptid}) b ON b.moduleid = hm.moduleid WHERE hm.moduleid = #{moduleid}")
    */
String queryUrlBymoduleids( String aamid, String deptid, String odeptid,Integer moduleid);

   // List<HopeModule> selectbyIdAndPri(String aamid, String deptid, String odeptid, JSONArray jsonArray);
}