package com.qianms.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("PaymentMain8001")
public interface PaymentOpenFeignService {
    @GetMapping("/payment/timeout")
    String timeout();

    @GetMapping("/payment/index")
    String index();
}
