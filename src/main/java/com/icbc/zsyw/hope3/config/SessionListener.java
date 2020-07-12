package com.icbc.zsyw.hope3.config;

import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @ClassName SessionListener
 * @Description
 * @Author qinwankang
 * @Date 2020/6/27 1:14
 * @Version V1.0
 **/
@WebListener
@Component
public class SessionListener implements HttpSessionListener {
  /*  @Override
    public void sessionCreated(HttpSessionEvent event) {
                     System.out.println("新建一个session"+event.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        System.out.println("销毁一个session"+event.getSession().getId());
    }*/
}
