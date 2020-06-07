package com.tcg.userprovider.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tcg.userprovider.configuration.JwtConfiguration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author 14861
 * @date 2020/6/7
 */
@Component
public class JwtUtil {
    @Autowired
    JwtConfiguration jwtConfiguration;

    public String createToken(Map<String, Object> claims) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.SECOND, jwtConfiguration.getExpire());
        Date d = c.getTime();
        return Jwts.builder().setClaims(claims).setExpiration(d)
            .signWith(SignatureAlgorithm.HS512, jwtConfiguration.getSecret()).compact();
    }

    public Claims parseToken(String jwt) {
        return Jwts.parser().setSigningKey(jwtConfiguration.getSecret()).parseClaimsJws(jwt).getBody();
    }
}
