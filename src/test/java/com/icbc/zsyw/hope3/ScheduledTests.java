package com.icbc.zsyw.hope3;

import com.icbc.zsyw.hope3.config.ScheduledConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @ClassName ScheduledTests
 * @Description
 * @Author qinwankang
 * @Date 2020/5/28 17:25
 * @Version V1.0
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableScheduling//可以在启动类上注解也可以在当前文件
@Configuration
public class ScheduledTests {
    @Resource
    private ScheduledConfiguration scheduledConfiguration;
    @Test
    public void runfirst(){
        scheduledConfiguration.runfirst();
        System.out.println("********测试定时任务******");
    }

}
