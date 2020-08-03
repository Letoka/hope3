package com.icbc.zsyw.hope3.service;

/**
 * @ClassName MailService
 * @Description
 * @Author qinwankang
 * @Date 2020/7/24 16:12
 * @Version V1.0
 **/
public interface MailService {
    //普通文字邮件
    public void sendMail(String title,String content);
    //html邮件
    public void sendMailHtml(String title,String mContent);
}
