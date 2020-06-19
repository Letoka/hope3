package com.icbc.zsyw.hope3.task;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName BaseInputParamPackageTask
 * @Description
 * @Author qinwankang
 * @Date 2020/5/30 11:42
 * @Version V1.0
 **/
public abstract class BaseInputParamPackageTask extends BaseTask {
    @Override
    public Map<String, Object> call() throws Exception {
        Map<String,Object> resBaseMap = new HashMap<String,Object>();
        Map<String,Object> map1 =packageBaseInfo();
        Map<String,Object> map2 =packageSpecialInfo();
        resBaseMap.putAll(map1);
        resBaseMap.putAll(map2);
        return resBaseMap;
    }
    private Map<String,Object> packageBaseInfo(){
        Map<String,Object> packBaseMap = new HashMap<String,Object>();
        packBaseMap.put("11",hopeImagebar.getModuleurl());
        return packBaseMap;
    }
    protected abstract Map<String,Object> packageSpecialInfo();
}
