package com.qianms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 启动类
 */
@SpringBootApplication
@EnableFeignClients
@Slf4j
public class OrderResilience4jMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderResilience4jMain80.class,args);
        log.info("******OrderResilience4jMain80启动成功********");
    }
}
