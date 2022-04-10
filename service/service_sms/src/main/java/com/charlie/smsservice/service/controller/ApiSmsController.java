package com.charlie.smsservice.service.controller;

import com.charlie.commonutils.R;
import com.charlie.commonutils.RandomUtil;
//import com.charlie.commonutils.util.RegexValidateUtils;
import com.charlie.smsservice.service.TengXunSmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author: ChaR
 * @Date: 2022/2/27 14:56
 */
//@CrossOrigin //跨域
@RestController
@RequestMapping("/api/sms")
@Api(tags = "短信管理")
@Slf4j
public class ApiSmsController {

    @Resource
    private TengXunSmsService tengXunSmsService;


    @ApiOperation("获取验证码")
    @GetMapping("/send/{mobile}")
    public R send(@PathVariable String mobile){

        return tengXunSmsService.send(mobile);

    }
}