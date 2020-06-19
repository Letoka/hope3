package com.icbc.zsyw.hope3.service;

import com.icbc.zsyw.hope3.common.BaseResponse;
import com.icbc.zsyw.hope3.dto.HopeModule;
import com.icbc.zsyw.hope3.dto.HopeShortcutBar;
import com.icbc.zsyw.hope3.dto.HopeShortcutBarPriv;
import com.icbc.zsyw.hope3.impl.HopeShortcutBarServiceImpl;

import java.util.List;

/**
 * @ClassName HopeShortcutBarService
 * @Description
 * @Author qinwankang
 * @Date 2020/5/28 8:52
 * @Version V1.0
 **/
public interface HopeShortcutBarService {
    /**
     * 功能描述:查询三大块放的视图类别（如：分行，重点业务等等），其中第三块放置最新文章更新的广告，
     * 如果最新文章上下墙时间过期，则第三块仍显示原有的视图类别，
     *如果文章设置权限，只有对应权限用户可以看到，如果文章没有设置权限则所有用户都可以看到
     * 其次，权限这块，还有文章是否展示，如果不展示，即使拥有权限也看不到
     * @param hopeShortcutBarPriv
    * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeShortcutBar>>
    * @Author: qinwankang
    * @Date: 2020/5/28 9:15
    */
    BaseResponse<HopeShortcutBarServiceImpl.ShortcutBarSub> queryShortcutBar(HopeShortcutBarPriv hopeShortcutBarPriv);
   /**
   * 功能描述:三大块当中，通过每个块当中名称获取对应视图（获取视图时注意用户对应权限）
    * @param hopeShortcutBarPriv
   * @return: com.icbc.zsyw.hope3.common.BaseResponse<java.util.List<com.icbc.zsyw.hope3.dto.HopeShortcutBar>>
   * @Author: qinwankang
   * @Date: 2020/5/28 11:16
   */
    BaseResponse<List<HopeModule>> queryModuleByShortcutbar(HopeShortcutBarPriv hopeShortcutBarPriv);
}
