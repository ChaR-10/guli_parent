package com.charlie.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.charlie.oss.service.OssService;
import com.charlie.oss.utils.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Author: ChaR
 * @Date: 2022/2/9 22:39
 */

@Service
public class OssServiceImpl implements OssService {

    //上传头像到OSS - 使用文件流方式进行上传
    @Override
    public String uploadFileAvatar(MultipartFile file) {

        //工具类获取值
        String endpoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;


        InputStream inputStream = null;


        try {
            // 创建OSS实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 获取上传文件的输入流
            inputStream = file.getInputStream();

            //获取文件名称
            String fileName = file.getOriginalFilename();

            // 问题day06-4:
            //1 在文件名称里面添加随机唯一值，防止重复覆盖
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            fileName  = uuid + fileName;

            //2 把文件按日期进行分类
            // 2020/7/3/01.jpg
            // 获取当前日期
//            Date date = new Date();
//            String format = new SimpleDateFormat("yyyy/MM/dd").format(date);

            //System.out.println(format);
//            System.out.println(format + "/" + fileName);

            String datePath = new DateTime().toString("yyyy/MM/dd");
            fileName = datePath + "/" + fileName;

            //调用oss实例中的方法实现上传
            //参数1： Bucket名称
            //参数2： 上传到oss文件路径和文件名称 /aa/bb/1.jpg
            //参数3： 上传文件的输入流
            ossClient.putObject(bucketName, fileName, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();

            //把上传后文件路径返回
            //需要把上传到阿里云oss路径手动拼接出来
            //https://achang-edu.oss-cn-hangzhou.aliyuncs.com/default.gif
            String url = "https://"+bucketName+"."+endpoint+"/"+fileName ;

            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}

