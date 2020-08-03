package com.icbc.zsyw.hope3;

import com.icbc.zsyw.hope3.service.MailService;
import com.whalin.MemCached.MemCachedClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @ClassName MailTests
 * @Description
 * @Author qinwankang
 * @Date 2020/7/24 16:16
 * @Version V1.0
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTests {
    @Resource
    private MailService mailService;
    @Test
    public void mailSendTest()  {
        mailService.sendMail("欢迎来到北京！","这是你的居住证");
    }
    @Test
    public void mailSendHtmlTest()  {
        mailService.sendMailHtml("欢迎来到北京！","现附上你的永久居住证，祝您生活愉快！");
    }
}
