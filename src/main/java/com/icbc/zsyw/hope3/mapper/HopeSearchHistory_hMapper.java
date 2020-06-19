package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeSearchHistory;
import com.icbc.zsyw.hope3.dto.HopeSearchHistory_h;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface HopeSearchHistory_hMapper {
    int insert(HopeSearchHistory_h record);

    int insertSelective(HopeSearchHistory_h record);
    @Select("SELECT searchtext,COUNT(*) as hotSearchcount FROM hopesearchhistory_h GROUP BY searchtext ORDER BY hotSearchcount DESC")
    List<HopeSearchHistory_h> queryHotSearch(HopeSearchHistory_h hopeSearchHistory_h);
}