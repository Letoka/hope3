package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeshortcutbarSeq;

public interface HopeshortcutbarSeqMapper {
    int deleteByPrimaryKey(Integer shortcutbarid);

    int insert(HopeshortcutbarSeq record);

    int insertSelective(HopeshortcutbarSeq record);

    HopeshortcutbarSeq selectByPrimaryKey(Integer shortcutbarid);

    int updateByPrimaryKeySelective(HopeshortcutbarSeq record);

    int updateByPrimaryKey(HopeshortcutbarSeq record);
}