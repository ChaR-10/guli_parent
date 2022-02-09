package com.charlie.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: ChaR
 * @Date: 2022/2/9 22:38
 */
public interface OssService {
    //上传头像到OSS
    String uploadFileAvatar(MultipartFile file);

}

