package com.charlie.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author: ChaR
 * @Date: 2022/2/19 19:56
 */
public interface VodService {
    String uploadVideoAliyun(MultipartFile file);

    void removeAliyunVideoById(String id);

    void removeMoreAlyVideo(List videoIdList);

    //根据视频id获取视频凭证
    String getPlayAuth(String id);
}

