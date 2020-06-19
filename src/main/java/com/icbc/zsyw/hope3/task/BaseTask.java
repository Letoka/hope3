package com.icbc.zsyw.hope3.task;

import com.icbc.zsyw.hope3.dto.HopeBroadcast;
import com.icbc.zsyw.hope3.dto.HopeComments;
import com.icbc.zsyw.hope3.dto.HopeImagebar;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @ClassName BaseTask
 * @Description
 * @Author qinwankang
 * @Date 2020/5/30 11:40
 * @Version V1.0
 **/
public abstract class BaseTask implements Callable<Map<String,Object>> {
    protected HopeBroadcast hopeBroadcast;
    protected HopeComments hopeComments;
    protected HopeImagebar hopeImagebar;

    public HopeBroadcast getHopeBroadcast() {
        return hopeBroadcast;
    }

    public HopeComments getHopeComments() {
        return hopeComments;
    }

    public HopeImagebar getHopeImagebar() {
        return hopeImagebar;
    }

    public void setHopeBroadcast(HopeBroadcast hopeBroadcast) {
        this.hopeBroadcast = hopeBroadcast;
    }

    public void setHopeComments(HopeComments hopeComments) {
        this.hopeComments = hopeComments;
    }

    public void setHopeImagebar(HopeImagebar hopeImagebar) {
        this.hopeImagebar = hopeImagebar;
    }
}
