package com.charlie.educms.controller;

import com.charlie.commonutils.R;
import com.charlie.educms.entity.CrmBanner;
import com.charlie.educms.service.CrmBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: ChaR
 * @Date: 2022/2/23 23:22
 */
@Api(tags = "前台轮播图接口")
@RestController
@RequestMapping("/educms/bannerfront")
//@CrossOrigin
public class BannerFrontController {

    @Autowired
    private CrmBannerService bannerService;

    @Cacheable(value = "banner",key = "'selectIndexList'")
    @ApiOperation(value = "查询所有banner")
    @GetMapping("getAllBanner")
    public R getAllBanner() {
        List<CrmBanner> list = bannerService.selectBanner();
        return R.ok().data("list",list);
    }
}

