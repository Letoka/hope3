package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeImagebar;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface HopeImagebarMapper {
    int deleteByPrimaryKey(Integer imagebarid);

    int insert(HopeImagebar record);

    int insertSelective(HopeImagebar record);

    HopeImagebar selectByPrimaryKey(Integer imagebarid);

    int updateByPrimaryKeySelective(HopeImagebar record);

    int updateByPrimaryKey(HopeImagebar record);
    @Select("SELECT hi.* FROM hopeimagebar hi INNER JOIN\n" +
            "               (SELECT imagebarid FROM hopeviewimagebarpriv WHERE aamid = #{aamid} OR deptid = #{deptid} GROUP BY imagebarid) b\n" +
            "             ON (hi.imagebarid = b.imagebarid  AND hi.enabled = 1) OR (hi.enabled = 1 AND hi.published = 1)  GROUP BY hi.imagebarid ORDER BY hi.weight DESC")
    List<HopeImagebar> queryHopeImagebar(String aamid, String deptid);
}