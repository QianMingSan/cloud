package com.qianms.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 支付远程调用接口
 */
@FeignClient("PaymentMain8001")
public interface PaymentFeignService {

    @GetMapping("/payment/index")
    String index();

    @GetMapping("/payment/timeout")
    String timeout();
}
