package com.charlie.oss.controller;

import com.charlie.commonutils.R;
import com.charlie.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: ChaR
 * @Date: 2022/2/9 22:47
 */
//@Api(description="阿里云文件管理") 已过时
@Api(tags="阿里云文件管理")
//@CrossOrigin //跨域
@RestController
@RequestMapping("/edu_oss/fileoss")
public class OssController {

    @Autowired
    private OssService ossService;

    //上传头像
    @ApiOperation(value = "文件上传")
    @PostMapping("/upload")
    public R uploadOssFile(@RequestParam("file") MultipartFile file){
        //获取上传的文件

        //返回上传到oss的路径
        String url = ossService.uploadFileAvatar(file);

        //返回r对象
        return R.ok().data("url",url).message("文件上传成功");
    }

}

