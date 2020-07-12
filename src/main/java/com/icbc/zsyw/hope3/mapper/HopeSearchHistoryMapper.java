package com.icbc.zsyw.hope3.mapper;

import com.alibaba.fastjson.JSONArray;
import com.icbc.zsyw.hope3.dto.HopeSearchHistory;
import com.icbc.zsyw.hope3.dto.HopeSearchHistory_h;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface HopeSearchHistoryMapper {
    int deleteByPrimaryKey(Integer searchhistoryid);

    int insert(HopeSearchHistory record);

    int insertSelective(HopeSearchHistory record);

    HopeSearchHistory selectByPrimaryKey(Integer searchhistoryid);

    int updateByPrimaryKeySelective(HopeSearchHistory record);

    int updateByPrimaryKey(HopeSearchHistory record);
   // @Select("SELECT searchtext FROM hopesearchhistory WHERE aamid = #{aamid} ORDER BY logtime DESC")
    List<HopeSearchHistory> querySearchRecord(HopeSearchHistory hopeSearchHistory);

    List<HopeSearchHistory> queryHotSearch(HopeSearchHistory hopeSearchHistory);

    void delSearchRecord(JSONArray jsonArray,String aamid);
}