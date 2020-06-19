package com.icbc.zsyw.hope3.mapper;

import com.icbc.zsyw.hope3.dto.HopeModule;
import com.icbc.zsyw.hope3.dto.HopeShortcutBar;
import com.icbc.zsyw.hope3.dto.HopeShortcutBarKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface HopeShortcutBarMapper {
    int deleteByPrimaryKey(HopeShortcutBarKey key);

    int insert(HopeShortcutBar record);

    int insertSelective(HopeShortcutBar record);

    HopeShortcutBar selectByPrimaryKey(HopeShortcutBarKey key);

    int updateByPrimaryKeySelective(HopeShortcutBar record);

    int updateByPrimaryKey(HopeShortcutBar record);
    @Select("SELECT hs.shortcutbarname,hs.shortcut_image FROM hopeshortcutbar hs INNER JOIN \n" +
        "(SELECT shortcutbarname FROM hopeviewshortcutbarpriv WHERE aamid = #{aamid} OR deptid = #{deptid} OR odeptid = #{odeptid} GROUP BY shortcutbarname) b\n" +
        "ON hs.shortcutbarname = b.shortcutbarname GROUP BY hs.shortcutbarname")
    List<HopeShortcutBar> queryShortcutBar(String aamid, String deptid, String odeptid);
    @Select("SELECT hm.moduleid,hm.modulename,hm.shortname,hm.description,hm.icon,hm.image,hm.modulegroupname,hm.url FROM hopemodule hm INNER JOIN\n" +
        "(SELECT hs.moduleid FROM hopeshortcutbar hs INNER JOIN \n" +
        "(SELECT moduleid FROM hopeviewmodulepriv WHERE aamid = #{aamid} OR deptid=#{deptid} OR odeptid = #{odeptid} GROUP BY moduleid) b ON hs.moduleid = b.moduleid\n" +
        "WHERE hs.shortcutbarname = #{shortcutbarname} GROUP BY hs.moduleid) c ON c.moduleid = hm.moduleid WHERE hm.enabled = 1 ")
    List<HopeModule> queryModuleByShortcutbar(String aamid, String deptid, String odeptid, String shortcutbarname);
}