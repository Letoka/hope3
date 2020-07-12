package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeModuleStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


public interface HopeModuleStatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HopeModuleStatus record);

    int insertSelective(HopeModuleStatus record);

    HopeModuleStatus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HopeModuleStatus record);

    int updateByPrimaryKey(HopeModuleStatus record);
    //@Select("SELECT modulestatus FROM hopemodulestatus WHERE aamid = #{aamid} AND moduletype = #{key}")
    String selectByaamidAndtype(String aamid, String key);
    //@Select("SELECT * FROM hopemodulestatus WHERE aamid = #{aamid} AND moduletype = #{moduletype}")
    HopeModuleStatus selectByaamidAndtype1(String aamid, String moduletype);
   /// @Update("UPDATE hopemodulestatus SET modulestatus =  #{modulestatus} WHERE aamid =#{aamid} AND moduletype = #{moduletype}")
    void updateMstatus(HopeModuleStatus hopeModuleStatus);
}