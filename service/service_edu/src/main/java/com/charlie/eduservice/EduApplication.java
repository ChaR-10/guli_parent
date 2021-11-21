package com.charlie.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: ChaR
 * @Date: 2021/11/21 13:08
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.charlie"})
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}