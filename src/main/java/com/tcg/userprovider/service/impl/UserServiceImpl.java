package com.tcg.userprovider.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.tcg.userprovider.entity.ReturnData;
import com.tcg.userprovider.entity.User;
import com.tcg.userprovider.mapper.UserMapper;
import com.tcg.userprovider.service.UserService;
import com.tcg.userprovider.utils.JwtUtil;
import com.tcg.userprovider.utils.RedisUtil;

/**
 * @author 14861
 * @date 2020/5/21
 */
@Component
@Service(version = "1.0.0", timeout = 10000, interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public ReturnData login(String username, String password) {
        ReturnData returnData = new ReturnData();
        User user = userMapper.findUserByUsername(username);
        if (user != null && username.equals(user.getUsername()) && password.equals(user.getPassword())) {
            returnData.setCode(0);
            returnData.setMessage("login success");
            Map<String, Object> claims = new HashMap<>();
            claims.put("username", username);
            claims.put("password", password);
            byte[] data = jwtUtil.createToken(claims).getBytes(StandardCharsets.UTF_8);
            returnData.setData(data);
        } else {
            returnData.setCode(1);
            returnData.setMessage("login failed");
        }

        return returnData;
    }

    @Override
    public ReturnData register(String username, String password, String email, String verifyCode) {
        User user = userMapper.findUserByUsername(username);
        ReturnData returnData = new ReturnData();
        if (user != null) {
            returnData.setCode(2);
            returnData.setMessage("user already exist");
        } else {
            if (!redisUtil.hasKey(email) || !verifyCode.equals(redisUtil.get(email))) {
                returnData.setCode(1);
                returnData.setMessage("verifyCode Error");
            } else {
                redisUtil.del(email);
                User newUser = new User();
                newUser.setEmail(email);
                newUser.setPassword(password);
                newUser.setUsername(username);
                int result = userMapper.addUser(newUser);
                if (result == 1) {
                    returnData.setCode(0);
                    returnData.setMessage("register success");
                } else {
                    returnData.setCode(1);
                    returnData.setMessage("register failed");
                }
            }
        }

        return returnData;
    }
}
