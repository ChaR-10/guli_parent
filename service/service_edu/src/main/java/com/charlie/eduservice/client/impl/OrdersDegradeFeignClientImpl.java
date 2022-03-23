package com.charlie.eduservice.client.impl;

import com.charlie.eduservice.client.OrdersClient;
import com.charlie.servicebase.exceptionHandler.CharException;
import org.springframework.stereotype.Component;

/**
 * @Author: ChaR
 * @Date: 2022/3/22 17:50
 */

@Component
public class OrdersDegradeFeignClientImpl implements OrdersClient {
    @Override
    public boolean isBuyCourse(String courseId, String memberId) {
        throw new CharException(20001, "获取订单状态失败啦");
    }
}
