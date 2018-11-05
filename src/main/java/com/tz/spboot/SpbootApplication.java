package com.tz.spboot;

import com.ecwid.consul.v1.ConsulClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.ComponentScan;
//import zipkin2.server.internal.EnableZipkinServer;

//@EnableZipkinServer
@EnableCircuitBreaker
@EnableHystrix
@EnableHystrixDashboard
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages= {"com.*"})
public class SpbootApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpbootApplication.class, args);
    }
}
