package com.icbc.zsyw.hope3.task;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName HopeBroadcastTask
 * @Description
 * @Author qinwankang
 * @Date 2020/5/30 12:14
 * @Version V1.0
 **/
public class HopeBroadcastTask  extends BaseInputParamPackageTask {
    @Override
    protected Map<String, Object> packageSpecialInfo() {
        Map<String,Object> hopeBroadcastmap = new HashMap<String,Object>();
        hopeBroadcastmap.put("hopebroadid",hopeBroadcast.getBroadcastid());
        return hopeBroadcastmap;
    }
}
