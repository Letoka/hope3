package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeUserHistory;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;


public interface HopeUserHistoryMapper {
    int deleteByPrimaryKey(Integer userhistoryid);

    int insert(HopeUserHistory record);

    int insertSelective(HopeUserHistory record);

    HopeUserHistory selectByPrimaryKey(Integer userhistoryid);

    int updateByPrimaryKeySelective(HopeUserHistory record);

    int updateByPrimaryKey(HopeUserHistory record);

    List<HopeUserHistory> queryByaamidAndModuleid(String aamid, Integer moduleid);

    void updateUserHistory(String aamid, Integer moduleid, Date logtime);

    List<HopeUserHistory> queryFootByAamid(String aamid);


    void deleteMinFoot(String aamid, Date logtime);
}