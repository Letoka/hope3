package com.icbc.zsyw.hope3;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @ClassName MemcacheTests
 * @Description
 * @Author qinwankang
 * @Date 2020/5/27 22:26
 * @Version V1.0
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MemcacheTests {
    //private static final Logger logger = LoggerFactory.getLogger(MemcacheTests.class);
   /* @Resource
    private MemCachedClient memCachedClient;

    @Test
    public void testSetGet()  {
       Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean flag = memCachedClient.set("mem", "qwk");
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean flag = memCachedClient.set("mem", "lcw");
            }
        });
        t1.start();
        t2.start();
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
     //   boolean flag = memCachedClient.set("mem", "name");
        // 取出缓存
        log.info("qwk2241");
        Object value = memCachedClient.get("mem");
        System.out.println(value);

    }*/
}
