package com.qianms.controller;

import com.qianms.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
     private PaymentFeignService paymentFeignService;


    @GetMapping("index")
    public String index(){
      return   paymentFeignService.index();
    }
    /**
     * 测试超时
     * @return
     */
    @GetMapping("timeout")
    public String timeout() {
        return paymentFeignService.timeout();
    }
}
