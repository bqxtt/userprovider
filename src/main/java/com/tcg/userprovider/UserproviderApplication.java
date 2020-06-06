package com.tcg.userprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;

/**
 * @author 14861
 * @date 2020/05/21
 */
@EnableDubboConfiguration
@SpringBootApplication
public class UserproviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserproviderApplication.class, args);
    }

}
