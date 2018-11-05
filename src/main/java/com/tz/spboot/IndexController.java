package com.tz.spboot;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.zipkin2.ZipkinLoadBalancer;
import org.springframework.cloud.sleuth.zipkin2.ZipkinRestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class IndexController {

    protected  final Logger logger=LoggerFactory.getLogger(IndexController.class);
    @GetMapping("/first")
    public String invoke(){

        return "string is word";
    }

    @PostMapping("/second")
    public String  postInvoke(){


        return "post param";
    }

    @GetMapping(value = "/getOrderPageList")
    @HystrixCommand(
            fallbackMethod = "getOrderPageListFallback",
            threadPoolProperties = {  //10个核心线程池,超过20个的队列外的请求被拒绝; 当一切都是正常的时候，线程池一般仅会有1到2个线程激活来提供服务
                    @HystrixProperty(name = "coreSize", value = "10"),
                    @HystrixProperty(name = "maxQueueSize", value = "100"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "20")},
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000"), //命令执行超时时间
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2"), //若干10s一个窗口内失败三次, 则达到触发熔断的最少请求量
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "30000") //断路30s后尝试执行, 默认为5s
            })
    public String  getOrderPagelist(){

        return "真的号码";
    }


    public String getOrderPageListFallback(){
        logger.error("===================== 执行降级策略");
        return "real false ";
    }

    @GetMapping("/error")
    public  String error(){
        return " get error page";
    }

    @PostMapping("/error")
    public  String erro(){
        return " post error page";
    }

    @PostMapping("/third")
    public String postinvokeparam(@RequestParam("name")String name){

        return " post  param is "+name;
    }

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/forth") public String callBackend() {
        return restTemplate.getForObject("http://localhost:9000/api", String.class);
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
