package com.tcg.userprovider.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.tcg.userprovider.entity.ReturnData;
import com.tcg.userprovider.service.MailService;
import com.tcg.userprovider.utils.MailUtil;

/**
 * @author 14861
 * @date 2020/5/22
 */
@Service(version = "1.0.0", timeout = 60000, interfaceClass = MailService.class)
@Component
public class MailServiceImpl implements MailService {
    @Autowired
    MailUtil mailUtil;

    @Override
    public ReturnData sendCode(String toMailAddress) {
        return mailUtil.sendCode(toMailAddress);
    }
}
