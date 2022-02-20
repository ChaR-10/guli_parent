package com.charlie.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.charlie.commonutils.R;
import com.charlie.servicebase.exceptionHandler.CharException;
import com.charlie.vod.service.VodService;
import com.charlie.vod.utils.ConstantVodUtils;
import com.charlie.vod.utils.InitVodClient;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Author: ChaR
 * @Date: 2022/2/19 19:53
 */
@RestController
@CrossOrigin
@RequestMapping("/eduVod/video")
public class VodController {

    @Autowired
    private VodService vodService;

    //上传视频到阿里云
    @PostMapping("/uploadAliyunVideo")
    public R uploadVideo(MultipartFile file){
        //返回上传视频的id
        String videoId = vodService.uploadVideoAliyun(file);
        return R.ok().data("videoId",videoId);
    }

    //根据视频id删除阿里云视频
    @DeleteMapping("/removeAliyunVideoById/{id}")
    public R removeAliyunVideoById(@PathVariable String id){
        vodService.removeAliyunVideoById(id);
        return R.ok();
    }

    @ApiOperation(value = "删除多个阿里云视频")
    @DeleteMapping("deleteBatch")
    public R deleteBatch(@RequestParam("videoIdList") List videoIdList){
        vodService.removeMoreAlyVideo(videoIdList);
        return R.ok();
    }


}

