package com.charlie.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: ChaR
 * @Date: 2022/2/19 19:56
 */
public interface VodService {
    String uploadVideoAliyun(MultipartFile file);

    void removeAliyunVideoById(String id);
}

