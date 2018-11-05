package com.tz.spboot;


import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCacheAspect;
import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HystrixConfig {


    @Bean
    public HystrixCommandAspect hystrixAspect(){

        return new HystrixCommandAspect();
    }

    @Bean
    public ServletRegistrationBean hystrixMetricsStreamServlet(){

        ServletRegistrationBean registrationBean=new ServletRegistrationBean(new HystrixMetricsStreamServlet()) ;
        registrationBean.addUrlMappings("/hystrix.stream");
        return  registrationBean;

    }
}
