package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeModule;
import com.icbc.zsyw.hope3.dto.HopeUserHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HopeModuleMapper {
    int deleteByPrimaryKey(Integer moduleid);

    int insert(HopeModule record);

    int insertSelective(HopeModule record);

    HopeModule selectByPrimaryKey(Integer moduleid);

    int updateByPrimaryKeySelective(HopeModule record);

    int updateByPrimaryKey(HopeModule record);
   @Select("SELECT hm.moduleid,hm.modulename,hm.shortname,hm.description,hm.icon,hm.image,hm.modulegroupname FROM hopemodule hm INNER JOIN\n" +
           "(SELECT moduleid FROM hopeviewmodulepriv WHERE aamid = #{aamid} OR deptid = #{deptid} OR odeptid = #{odeptid}) b\n" +
           "ON hm.moduleid = b.moduleid  AND hm.enabled = 1 GROUP BY hm.moduleid\n")
   List<HopeModule> queryHopeModule(String aamid, String deptid,String odeptid);
   @Select("SELECT hm.moduleid,hm.modulename,hm.shortname,hm.description,hm.icon,hm.image,hm.modulegroupname FROM hopemodule hm INNER JOIN \n" +
           "(SELECT moduleid FROM hopeuserhistory WHERE aamid = #{aamid} GROUP BY moduleid ORDER BY logtime DESC) b ON b.moduleid = hm.moduleid AND hm.enabled = 1\n")
   List<HopeModule> queryMyFoot(HopeUserHistory hopeUserHistory);
   @Select({"SELECT hm.moduleid,hm.modulename,hm.shortname,hm.description,hm.icon,hm.image,hm.modulegroupname,hm.url FROM hopemodule hm WHERE hm.modulename LIKE CONCAT('%',#{modulename},'%')"})
   List<HopeModule> searchMoudleByName(String modulename);
    @Select("SELECT url,useurltype FROM hopemodule WHERE moduleid = #{moduleid} ")
    HopeModule queryUrlBymoduleid(Integer moduleid);
}