package com.tcg.userprovider.entity;

/**
 * @author 14861
 * @date 2020/5/23
 */
public class User implements java.io.Serializable {
    String username;
    String password;
    String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
