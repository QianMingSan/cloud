package com.qianms.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {
    /**
     *通过java api方式构建路由规则
     * @return
     */
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder){
        //获取路由规则
        RouteLocatorBuilder.Builder routes = builder.routes();
        //设置路由
        routes.route("path_rote",r ->r.path("/QianMingSan/cloud").uri("https://github.com/QianMingSan/cloud")).build();
        return routes.build();
    }
}
