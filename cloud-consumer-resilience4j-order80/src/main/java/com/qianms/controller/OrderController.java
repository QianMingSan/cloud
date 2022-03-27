package com.qianms.controller;

import com.qianms.service.PaymentOpenFeignService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Slf4j
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private PaymentOpenFeignService paymentOpenFeignService;

    /**
     * 测试超时降级
     * @return
     */
    @GetMapping("timeout")
    @TimeLimiter(name = "delay",fallbackMethod = "timeoutfallback")
    public CompletableFuture<String> timeout(){
        log.info("******** 进入方法**********");

        CompletableFuture<String> completableFuture = CompletableFuture
                   .supplyAsync((Supplier<String>) () -> (paymentOpenFeignService.timeout()));

        log.info("******** 离开方法**********");

        return completableFuture;
    }
    /**
     * 超时服务降级方法 * @param e
     * @return
     */
    public CompletableFuture<ResponseEntity> timeoutfallback(Exception e){
        e.printStackTrace();
        return CompletableFuture.completedFuture(ResponseEntity.ok("超时啦"));
    }
    @GetMapping("retry")
    @Retry(name = "backendA")
    public CompletableFuture<String> retry(){

        log.info("******** 进入方法**********");
        CompletableFuture<String> completableFuture = CompletableFuture
                .supplyAsync((Supplier<String>) () -> (paymentOpenFeignService.index()));
        log.info("******** 离开方法**********");
        return  completableFuture;
    }

    /**
     * 异常比例熔断降级
     * @return
     */
    @GetMapping("/citcuitBackend")
    @CircuitBreaker(name = "backendA",fallbackMethod = "fallback")
    public String citcuitBackend() {

        log.info("************ 进入方法 ***********");
        String index = paymentOpenFeignService.index();
        log.info("************ 离开方法 ***********");
        return index;
    }
    /**
    * 服务降级方法
    * @param e
     * * @return
     */
    public String fallback(Throwable e){
             e.printStackTrace();
             return "客官服务繁忙，稍等一会。。。。";
           }


    /**
          * 慢调用比例熔断降级
          * @return
          */
    @GetMapping("/slowcircuitbackend")
    @CircuitBreaker(name = "backendB",fallbackMethod = "slowfallback")
    public String slowcircuitbackend(){
            log.info("************ 进入方法 ***********");
             try {
                   TimeUnit.SECONDS.sleep(10);
                  } catch (InterruptedException e) {
                 e.printStackTrace();
                 }
               String index = paymentOpenFeignService.index();
               log.info("************ 离开方法 ***********");
               return index;
           }

    /**
     * 测试信号量隔离
     * @return
     */
    @Bulkhead(name = "backendA",type = Bulkhead.Type.SEMAPHORE)
    @GetMapping("bulkhead")
    public String bulkhead() throws InterruptedException {
        log.info("************** 进入方法 *******");
        TimeUnit.SECONDS.sleep(10);
        String index = paymentOpenFeignService.index();
        log.info("************** 离开方法 *******");
        return index;
    }

    /**
     * 测试线程池服务隔离 * @return
     */
    @Bulkhead(name = "backendA",type = Bulkhead.Type.THREADPOOL)
    @GetMapping("/futrue")
    public CompletableFuture  future() {
        log.info("********** 进入方法 *******");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("********** 离开方法 *******");
        return CompletableFuture.supplyAsync(() -> "线程池隔 离信息......");
    }

    /**
          * 限流
          * @return
          */
    @GetMapping("/limiter")
    @RateLimiter(name = "backendA")
   public CompletableFuture<String> RateLimiter() {
                log.info("********* 进入方法 ******");
                //异步操作
               CompletableFuture<String> completableFuture = CompletableFuture
                .supplyAsync((Supplier<String>) () -> (paymentOpenFeignService.index()));
                log.info("********* 离开方法 ******");
               return completableFuture;
    }
}
