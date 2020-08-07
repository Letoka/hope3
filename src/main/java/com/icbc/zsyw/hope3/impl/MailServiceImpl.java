package com.icbc.zsyw.hope3.impl;

import com.icbc.zsyw.hope3.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @ClassName MailServiceImpl
 * @Description
 * @Author qinwankang
 * @Date 2020/7/24 16:12
 * @Version V1.0
 **/
@Service
public class MailServiceImpl implements MailService {
    @Resource
   // private JavaMailSender mailSender;


    //JavaMailSender Config第一种
    @Value("${spring.mail.username}")
    private String from;
    @Value("${spring.mail.toadmin}")
    private String toTmp;
    @Value("${qwk.name}")
    private String qwk;
    /*spring:
    mail:
    fName: 秦万康
    toName: 尊敬的xxx先生，xxx女士*/
    @Value("${spring.mail.fName}")
    private String fName;
    @Value("${spring.mail.toName}")
    private String toName;

   //SSL Config第二种
   // private String from="273509825@qq.com";
   // private String toTmp="2154609837@qq.com;qinwankang123@163.com";
    //普通文字邮件

    /*@Async //设置异步，加快发送速度
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)*/
    /*public void sendMail( String title, String content) {
        String[] to = toTmp.split(";"); //目标必须字符串或数组，多接收人时必须为数组，用字府串会报异常
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from); //腾讯的限制，发送人必须与发送邮箱相同，不同会报异常
        message.setTo(to);
        message.setSubject(title);
        message.setText(content);
        mailSender.send(message);
        //System.out.println("send mail to: "+ Arrays.toString(to) +"and content: "+ content);
    }*/
//html文件
    /*@Async //设置异步，加快发送速度
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public void sendMailHtml(String title,String mContent) {
        MimeMessage message = null;
        String[] to = toTmp.split(";");
        try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = null;
            try {
                helper = new MimeMessageHelper(message, true);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(title);

            StringBuffer sb = new StringBuffer();
            sb.append("<h3>&nbsp;&nbsp;&nbsp;&nbsp;"+toName+"</h3>")
                    .append("<p style='color:#F00'>&nbsp;&nbsp;&nbsp;&nbsp;"+mContent+"</p>")
                    .append("<p style='text-align:right'>"+fName+"</p>");
            helper.setText(sb.toString(), true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(message);
    }*/
}
