package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeActicity;
import com.icbc.zsyw.hope3.dto.HopeModule;
import com.icbc.zsyw.hope3.dto.HopePrivGroup;
import com.icbc.zsyw.hope3.dto.HopeUserFavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


public interface HopeUserFavorMapper {
    int deleteByPrimaryKey(Integer favorid);

    int insert(HopeUserFavor record);

    int insertSelective(HopeUserFavor record);

    HopeUserFavor selectByPrimaryKey(Integer favorid);

    int updateByPrimaryKeySelective(HopeUserFavor record);

    int updateByPrimaryKey(HopeUserFavor record);
   // @Delete("DELETE  FROM hopeuserfavor WHERE aamid  = #{aamid} AND moduleid= #{moduleid}")
    int deleteByAamAndModule(String aamid,Integer moduleid);
   // @Select("SELECT hm.* from hopemodule hm INNER JOIN \n" +
          //  "(SELECT moduleid FROM hopeuserfavor WHERE aamid = #{aamid} AND favortype = #{favortype}  ORDER BY modulesequence DESC) b ON hm.moduleid = b.moduleid WHERE hm.enabled = 1\n" +
          //  "\n")
    List<HopeModule> queryWatchHopeModule(HopeUserFavor hopeUserFavor);
//@Select("SELECT hu.aamid,hu.moduleid,hu.modulesequence FROM hopeuserfavor hu WHERE aamid = #{aamid} AND favortype = #{key}\n")
    List<HopeUserFavor> queryModuleByAamid(String aamid, Integer key);
//@Update("UPDATE hopeuserfavor SET modulesequence = #{modulesequence} WHERE aamid = #{aamid} AND moduleid = #{moduleid} AND favortype = #{favortype}")
    int updateHopeUserFavor(HopeUserFavor hopeUserFavor2);
  // @Select("SELECT favorid FROM hopeuserfavor WHERE aamid = #{aamid} AND moduleid = #{moduleid} AND favortype = 0\n")
  Integer selectWatchModule(HopeUserFavor hopeUserFavor);
   // @Select("SELECT COUNT(*) FROM hopeuserfavor h WHERE aamid = #{aamid}  AND activityid = #{activityid} AND favortype = 1\n")
    Integer checkShoucang( String aamid,Integer activityid);
  // @Select("SELECT MAX(modulesequence) FROM hopeuserfavor WHERE favortype = 0\n")
    Integer selectModuleMaxSequence();
   // @Delete("DELETE FROM hopeuserfavor WHERE aamid = #{aamid} AND activityid=#{activityid} AND favortype=1")
    void deleteByAamidAndAcid(HopeUserFavor hopeUserFavor);
   // @Select("SELECT COUNT(*) FROM hopeuserfavor WHERE activityid=#{activityid} AND favortype = 1\n")
    Integer getshoucangliang(Integer activityid);

    void deleteMoudelByAamidAndType(String aamid, Integer favortype);
}