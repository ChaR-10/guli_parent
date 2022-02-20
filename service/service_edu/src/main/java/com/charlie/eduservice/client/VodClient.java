package com.charlie.eduservice.client;

import com.charlie.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: ChaR
 * @Date: 2022/2/20 14:23
 */
@Component
@FeignClient(value = "service-vod", fallback = VodFileDegradeFeignClient.class)//指定调用的服务名，前提要注册到nacos注册中心中
public interface VodClient {

    //根据视频id删除阿里云视频
    @DeleteMapping("/eduVod/video/removeAliyunVideoById/{id}")
    public R removeAliyunVideoById(@PathVariable("id") String id);

    //删除多个阿里云视频
    @DeleteMapping("/eduVod/video/deleteBatch")
    public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);


}

