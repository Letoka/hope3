package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeComments;
import com.icbc.zsyw.hope3.dto.HopeCommentsExample;
import com.icbc.zsyw.hope3.dto.HopeCommentsKey;

import java.util.Date;
import java.util.List;

import com.icbc.zsyw.hope3.dto.HopeModule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface HopeCommentsMapper {
    int countByExample(HopeCommentsExample example);

    int deleteByExample(HopeCommentsExample example);

    int deleteByPrimaryKey(HopeCommentsKey key);

    int insert(HopeComments record);

    int insertSelective(HopeComments record);

    List<HopeComments> selectByExample(HopeCommentsExample example);

    HopeComments selectByPrimaryKey(HopeCommentsKey key);

    int updateByExampleSelective(@Param("record") HopeComments record, @Param("example") HopeCommentsExample example);

    int updateByExample(@Param("record") HopeComments record, @Param("example") HopeCommentsExample example);

    int updateByPrimaryKeySelective(HopeComments record);

    int updateByPrimaryKey(HopeComments record);
   // @Select("SELECT COUNT(*) FROM hopecomments WHERE moduleid = #{moduleid} AND comments = #{key}\n")
    Integer sureCount(Integer moduleid,Integer comments);
   // @Select("SELECT logtime,comments FROM hopecomments WHERE aamid = #{aamid} AND moduleid = #{moduleid}\n")
    List<HopeComments> checkAamSure(String aamid, Integer moduleid);

    List<HopeComments> getDianzan(Integer dianzan);

    List<HopeComments> getZanOrCai(String aamid, Date date);
}