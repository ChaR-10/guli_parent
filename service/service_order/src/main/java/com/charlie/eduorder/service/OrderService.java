package com.charlie.eduorder.service;

import com.charlie.eduorder.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author ChaR
 * @since 2022-03-22
 */
public interface OrderService extends IService<Order> {

    String createOrders(String courseId, String memberId);
}
