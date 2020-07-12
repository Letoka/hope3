package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeModuleGroup;
import org.apache.ibatis.annotations.Mapper;


public interface HopeModuleGroupMapper {
    int deleteByPrimaryKey(String modulegroupname);

    int insert(HopeModuleGroup record);

    int insertSelective(HopeModuleGroup record);

    HopeModuleGroup selectByPrimaryKey(String modulegroupname);

    int updateByPrimaryKeySelective(HopeModuleGroup record);

    int updateByPrimaryKey(HopeModuleGroup record);
}