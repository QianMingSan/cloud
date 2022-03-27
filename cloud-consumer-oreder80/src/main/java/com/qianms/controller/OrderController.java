package com.qianms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;
    /**
     * 服务发现
     * @return
     */
    @GetMapping("discovery")
    public Object testdiscoveryClient(){
        //获取所有服务列表清单
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            System.out.println(service);
        }
        return this.discoveryClient;
    }

    /**
     * 测试服务调用
     */
    @GetMapping("index")
    public String index(){
        //服务生产者名字
        String hostName ="PAYMENTMAIN8001";
        //远程调用方法具体url
        String url  = "/payment/index";
        //1.服务发现中获取服务生产者的实列
        List<ServiceInstance> instances = discoveryClient.getInstances(hostName);
        //2.获取到具体服务生产者实列
        ServiceInstance serviceInstance = instances.get(0);
        System.out.println(serviceInstance.getUri());
        System.out.println(serviceInstance.getPort());
        System.out.println(serviceInstance.getHost());
        System.out.println(serviceInstance.getServiceId());
        System.out.println("**************************");
        //3.发起远程调用
        String forObject = restTemplate.getForObject(serviceInstance.getUri() + url, String.class);
        return forObject;
    }
}
