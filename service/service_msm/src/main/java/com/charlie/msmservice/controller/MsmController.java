package com.charlie.msmservice.controller;

import com.charlie.commonutils.R;
import com.charlie.commonutils.RandomUtil;
import com.charlie.msmservice.service.MsmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: ChaR
 * @Date: 2022/2/27 12:06
 */
@Api(tags = "短信服务")
@RestController
@RequestMapping("/edumsm/msm")
@CrossOrigin
public class MsmController {

    @Autowired
    private MsmService msmService;

    @ApiOperation(value = "发送短信的方法")
    @GetMapping("send/{phone}")
    public R sendMsm(@PathVariable String phone) {

        //调用service发送短信的方法
        return msmService.send(phone);

    }

}

