package com.charlie.educenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: ChaR
 * @Date: 2022/2/27 18:41
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.charlie"})
@MapperScan("com.charlie.educenter.mapper")
@EnableDiscoveryClient
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class, args);
    }
}

