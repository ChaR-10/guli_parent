package com.charlie.eduservice.client;

import com.charlie.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: ChaR
 * @Date: 2022/2/20 22:52
 */
@Component
public class VodFileDegradeFeignClient implements VodClient {
    //出错之后执行的方法

    @Override
    public R deleteBatch(List<String> videoIdList) {
        return R.error().message("删除视频出错了");
    }

    @Override
    public R removeAliyunVideoById(String id) {
        return R.error().message("删除视频出错了");
    }


}

