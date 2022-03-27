package com.qianms.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    /**
     * 自定义全局过滤器的需求
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1.从请求中获取token令牌
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        //2.判断token是否存在
        if (StringUtils.isEmpty(token)){
            System.out.println("鉴权失败，参数缺失");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);//未授权
            return exchange.getResponse().setComplete();
        }
        //判断是否有效
        if ("itbaizhan".equals(token)){
            System.out.println("token无效，参数缺失");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);//未授权
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    /**
     * 数值越小 优先级越高
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
