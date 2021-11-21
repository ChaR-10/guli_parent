package com.charlie.eduservice.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: ChaR
 * @Date: 2021/11/21 13:03
 */
@Configuration
@MapperScan("com.charlie.eduservice.mapper")
public class EduConfig {
}
