package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeShortcutBarPriv;

public interface HopeShortcutBarPrivMapper {
    int deleteByPrimaryKey(Integer shortcutbarprivid);

    int insert(HopeShortcutBarPriv record);

    int insertSelective(HopeShortcutBarPriv record);

    HopeShortcutBarPriv selectByPrimaryKey(Integer shortcutbarprivid);

    int updateByPrimaryKeySelective(HopeShortcutBarPriv record);

    int updateByPrimaryKey(HopeShortcutBarPriv record);
}