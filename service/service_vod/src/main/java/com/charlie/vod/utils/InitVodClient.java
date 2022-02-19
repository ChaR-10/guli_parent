package com.charlie.vod.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @Author: ChaR
 * @Date: 2022/2/19 20:51
 */
public class InitVodClient {
    public static DefaultAcsClient init(String accessKeyId, String accessKeySecret){
        String regionId = "cn-shanghai";  // 点播服务接入区域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }
}
