package com.charlie.educms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: ChaR
 * @Date: 2022/2/23 23:18
 */
@SpringBootApplication
@ComponentScan({"com.charlie"})//指定扫描位置
@MapperScan("com.charlie.educms.mapper")
@EnableDiscoveryClient
public class CmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }
}

