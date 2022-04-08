package com.charlie.staservice.service;

import com.charlie.staservice.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author ChaR
 * @since 2022-03-27
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    void registerCount(String day);
}
