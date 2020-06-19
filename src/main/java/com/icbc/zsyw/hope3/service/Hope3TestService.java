package com.icbc.zsyw.hope3.service;

import com.icbc.zsyw.hope3.dto.TcenterLog;

import java.util.List;

/**
 * @ClassName Hope3TestService
 * @Description
 * @Author qinwankang
 * @Date 2020/5/14 10:35
 * @Version V1.0
 **/
public interface Hope3TestService {

    public List<TcenterLog> checkUserSex(TcenterLog tcenterLog);

    public int add(TcenterLog tcenterLog);

}
