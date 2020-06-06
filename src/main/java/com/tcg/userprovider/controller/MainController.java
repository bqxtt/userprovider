package com.tcg.userprovider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.tcg.userprovider.configuration.EmailConfiguration;
import com.tcg.userprovider.entity.ReturnData;
import com.tcg.userprovider.service.MailService;
import com.tcg.userprovider.service.UserService;

/**
 * @author 14861
 * @date 2020/5/22
 */
@Controller
public class MainController {
    @Autowired
    MailService mailService;
    @Autowired
    UserService userService;

    @Autowired
    EmailConfiguration emailConfiguration;

    @ResponseBody
    @GetMapping("/test")
    public String mail() {
        mailService.sendSimpleMail("1486126243@qq.com", "验证码", "123456");
        return emailConfiguration.getMails().toString();
    }

    @ResponseBody
    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        ReturnData returnData = userService.login(username, password);
        return JSON.toJSONString(returnData);
    }

    @ResponseBody
    @PostMapping("/register")
    public String register(@RequestParam("username") String username, @RequestParam("password") String password,
        @RequestParam("email") String email) {
        ReturnData returnData = userService.register(username, password, email);
        return JSON.toJSONString(returnData);
    }
}