package com.tcg.userprovider.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.tcg.userprovider.entity.ReturnData;
import com.tcg.userprovider.service.MailService;
import com.tcg.userprovider.service.UserService;
import com.tcg.userprovider.utils.JwtUtil;

import io.jsonwebtoken.Claims;

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
    JwtUtil jwtUtil;

    @ResponseBody
    @GetMapping("/mail")
    public String mail(@RequestParam("email") String email) {
        ReturnData returnData = mailService.sendCode(email);
        return JSON.toJSONString(returnData);
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
        ReturnData returnData = userService.register(username, password, email, "1234");
        return JSON.toJSONString(returnData);
    }

    @ResponseBody
    @PostMapping("/parse")
    public String parse(@RequestParam("token") String token) {
        byte[] data = Base64.getDecoder().decode(token);
        String info = new String(data, StandardCharsets.UTF_8);
        Claims claims = jwtUtil.parseToken(info);
        return JSON.toJSONString(claims);
    }
}
