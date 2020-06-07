package com.tcg.userprovider.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 14861
 * @date 2020/6/7
 */
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtConfiguration {
    private int expire;
    private String secret;

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
