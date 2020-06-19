package com.icbc.zsyw.hope3.task;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName HopeImageBarTask
 * @Description
 * @Author qinwankang
 * @Date 2020/5/30 11:51
 * @Version V1.0
 **/
public class HopeImageBarTask extends BaseInputParamPackageTask {

    @Override
    protected Map<String, Object> packageSpecialInfo() {
        Map<String,Object> hopeImageBarmap = new HashMap<String,Object>();
        hopeImageBarmap.put("hopeimage",hopeImagebar.getImagebarid());
        return hopeImageBarmap;
    }
}
