package com.charlie.eduservice.controller;

import com.charlie.commonutils.R;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: ChaR
 * @Date: 2022/2/7 22:16
 */
@RestController
@CrossOrigin //解决跨域
@RequestMapping("/eduService/user")
public class EduLoginController {

    //login
    @PostMapping("/login")
    public R login(){
        return R.ok().data("token","admin");
    }

    //info
    @GetMapping("/info")
    public R info(){
        return R.ok().data("roles","admin").data("name","阿蔡").data("avatar","http://www.weixintouxiang.cn/weixin/20140607090832328.gif");
    }

}

