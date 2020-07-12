package com.icbc.zsyw.hope3.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * @ClassName ScheduledConfiguration
 * @Description
 * @Author qinwankang
 * @Date 2020/5/29 8:35
 * @Version V1.0
 **/
@Configuration
public class ScheduledConfiguration {
    //@Scheduled(cron = "0 0/1 * * * ?")
    public void runfirst(){
        System.out.println("每分钟执行一次，当下时间："+new Date());
       // System.out.println("********定时任务开始执行（每秒执行一次,我在ScheduledConfiguration中）******");
    }

}
