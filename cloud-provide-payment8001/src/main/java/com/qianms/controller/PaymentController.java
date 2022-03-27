package com.qianms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("payment")
public class PaymentController {

    @GetMapping("index")
    public String index(){
        return "success";
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
