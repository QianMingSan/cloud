package com.qianms.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * 日志的网关过滤器
 */
@Slf4j
@Component
public class LogGatewayFilterFactory extends AbstractGatewayFilterFactory<LogGatewayFilterFactory.Config> {

    public LogGatewayFilterFactory(){
        super(Config.class);
    }

    /**
     * 表示我们配置填写的顺序
     * @return
     */
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("consoleLog","cacheLog");
    }

    /**
     * 执行过滤的逻辑
     * @param config
     * @return
     */
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (config.consoleLog){
                log.info("日志开启");
            }
            //执行下一个逻辑
            return chain.filter(exchange);
        };
    }

    /**
     * 过滤器使用的配置内容
     */
    @Data
    public static class Config{
        private boolean consoleLog;
    }


}
