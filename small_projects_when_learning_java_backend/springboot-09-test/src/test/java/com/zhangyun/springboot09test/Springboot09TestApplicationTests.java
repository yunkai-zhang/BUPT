package com.zhangyun.springboot09test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.processor.BeanListProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class Springboot09TestApplicationTests {
    @Autowired
    JavaMailSenderImpl mailSender;

    @Test
    void contextLoads() {

        //一个简单的邮件
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("这是邮件的主题");
        mailMessage.setText("今天学习了吗");
        mailMessage.setTo("jie_shou_fang@qq.com");
        mailMessage.setFrom("fa_song_fang@qq.com");
        mailSender.send(mailMessage);
    }

    /*
    * 自己封装复杂邮件代码
    *
    * multipart:邮件是否支持multipart
    * mailSubject：邮件主题
    * 。。。
    * */
    public void sendMail(Boolean multipart,
                         String mailSubject,
                         String mailText,
                         Boolean mailTextHtml,
                         String attachmentName,
                         String attachmentFullUrl,
                         String sendTo,
                         String sendFrom) throws MessagingException {
        //一个复杂的邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        //组装
        //MimeMessageHelper帮助配置复杂邮件。MimeMessageHelper第二个参数为true时，表示允许多文件发送
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, multipart);
        helper.setSubject(mailSubject);
        //设置要发送的正文。第二个参数为true表示文本中的html标签会被解析。
        helper.setText(mailText,mailTextHtml);
        //附件
        //路径应为绝对路径
        helper.addAttachment(attachmentName,new File(attachmentFullUrl));
        helper.addAttachment("2.jpg",new File("D:\\2.jpg"));
        //配置邮件的发送方和接收方
        helper.setTo(sendTo);
        helper.setFrom(sendFrom);

    }

}
