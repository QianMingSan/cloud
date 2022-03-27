package com.qianms.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("payment")
public class PaymentController {
    @Value("${server.port}")
    private String port;
    @GetMapping("index")
    public String index(){
        return "success";
    }

    @GetMapping("lb")
    public String lb(){
        return "success"+port;
    }
    /**
     * 测试超时
     * @return
     */
    @GetMapping("timeout")
    public String timeout() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "success timeout";
    }
}
