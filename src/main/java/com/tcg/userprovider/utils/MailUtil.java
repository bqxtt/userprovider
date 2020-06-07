package com.tcg.userprovider.utils;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import com.tcg.userprovider.configuration.EmailConfiguration;
import com.tcg.userprovider.entity.ReturnData;

/**
 * @author 14861
 * @date 2020/6/7
 */
@Component
public class MailUtil {
    @Autowired
    EmailConfiguration emailConfiguration;

    @Autowired
    RedisUtil redisUtil;

    private final AtomicInteger emailId = new AtomicInteger(0);

    public ReturnData sendCode(String toMailAddress) {
        ReturnData returnData = new ReturnData();
        SimpleMailMessage message = new SimpleMailMessage();
        String verifyCode = String.format("%04d", new Random().nextInt(9999));
        redisUtil.set(toMailAddress, verifyCode, emailConfiguration.getExpire());
        message.setTo(toMailAddress);
        message.setSubject("欢迎注册魔镜应用");
        message.setText("您的验证码为：" + verifyCode + "，有效时间为5分钟，请尽快完成注册！");
        int newEmailId = emailId.getAndIncrement() % 3;
        try {
            Map<String, String> emailProperties = emailConfiguration.getMails().get("mail-" + newEmailId);
            JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
            javaMailSender.setHost(emailProperties.get("host"));
            javaMailSender.setProtocol(emailProperties.get("protocol"));
            javaMailSender.setUsername(emailProperties.get("username"));
            javaMailSender.setPassword(emailProperties.get("password"));
            message.setFrom(emailProperties.get("username"));
            javaMailSender.send(message);
            returnData.setCode(0);
            returnData.setMessage("send success");
        } catch (Exception e) {
            e.printStackTrace();
            returnData.setCode(1);
            returnData.setMessage("send failed");
        }
        return returnData;
    }
}
