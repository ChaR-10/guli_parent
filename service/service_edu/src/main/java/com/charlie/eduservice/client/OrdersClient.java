package com.charlie.eduservice.client;

import com.charlie.eduservice.client.impl.OrdersDegradeFeignClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: ChaR
 * @Date: 2022/3/22 17:49
 */
@FeignClient(value = "service-order", fallback = OrdersDegradeFeignClientImpl.class)
@Component
public interface OrdersClient {
    @GetMapping("/eduorder/order/isBuyCourse/{courseId}/{memberId}")
    public boolean isBuyCourse(@PathVariable("courseId") String courseId, @PathVariable("memberId") String memberId);
}
