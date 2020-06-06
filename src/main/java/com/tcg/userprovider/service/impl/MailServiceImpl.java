package com.tcg.userprovider.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.tcg.userprovider.configuration.EmailConfiguration;
import com.tcg.userprovider.entity.ReturnData;
import com.tcg.userprovider.service.MailService;

/**
 * @author 14861
 * @date 2020/5/22
 */
@Service(version = "1.0.0", timeout = 60000, interfaceClass = MailService.class)
@Component
public class MailServiceImpl implements MailService {

    @Autowired
    private EmailConfiguration emailConfiguration;

    private int emailId = 0;
    private final int MAX_EMAIL = 3;

    private synchronized int setEmailId() {
        emailId = (emailId + 1) % MAX_EMAIL;
        return emailId;
    }

    @Override
    public ReturnData sendSimpleMail(String toMailAddress, String title, String content) {
        ReturnData returnData = new ReturnData();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toMailAddress);
        message.setSubject(title);
        message.setText(content);
        int newEmailId = setEmailId();
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
