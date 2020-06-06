package com.tcg.userprovider.service;

import com.tcg.userprovider.entity.ReturnData;

/**
 * @author 14861
 * @date 2020/5/21
 */
public interface UserService {

    /**
     * 登录app
     * 
     * @param username
     *            用户名
     * @param password
     *            密码
     * @return 结果信息
     */
    ReturnData login(String username, String password);

    /**
     * 注册账号
     * 
     * @param username
     *            用户名
     * @param password
     *            密码
     * @param mail
     *            邮箱
     * @return 结果信息
     */
    ReturnData register(String username, String password, String mail);
}
