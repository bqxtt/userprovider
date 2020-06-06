package com.tcg.userprovider.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.tcg.userprovider.entity.ReturnData;
import com.tcg.userprovider.entity.User;
import com.tcg.userprovider.mapper.UserMapper;
import com.tcg.userprovider.service.UserService;

/**
 * @author 14861
 * @date 2020/5/21
 */
@Component
@Service(version = "1.0.0", timeout = 10000, interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public ReturnData login(String username, String password) {
        ReturnData returnData = new ReturnData();
        User user = userMapper.findUserByUsername(username);
        if (user != null && user.getUsername().equals(username) && user.getPassword().equals(password)) {
            returnData.setCode(0);
            returnData.setMessage("login success");
        } else {
            returnData.setCode(1);
            returnData.setMessage("login failed");
        }

        return returnData;
    }

    @Override
    public ReturnData register(String username, String password, String email) {
        User user = userMapper.findUserByUsername(username);
        ReturnData returnData = new ReturnData();
        if (user != null) {
            returnData.setCode(2);
            returnData.setMessage("user already exist");
        } else {
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

        return returnData;
    }
}
