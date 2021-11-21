package com.charlie.eduservice.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: ChaR
 * @Date: 2021/11/21 13:03
 */
@Configuration
@MapperScan("com.charlie.eduservice.mapper")
public class EduConfig {

    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }


}
