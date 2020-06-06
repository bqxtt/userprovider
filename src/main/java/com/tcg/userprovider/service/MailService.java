package com.tcg.userprovider.service;

import com.tcg.userprovider.entity.ReturnData;

/**
 * @author 14861
 * @date 2020/5/22
 */
public interface MailService {
    /**
     * 发送注册验证码至邮箱
     * 
     * @param to
     *            接收验证码邮箱
     * @param title
     *            邮件标题
     * @param content
     *            邮件内容
     * @return 自定义消息类
     */
    public ReturnData sendSimpleMail(String to, String title, String content);
}
