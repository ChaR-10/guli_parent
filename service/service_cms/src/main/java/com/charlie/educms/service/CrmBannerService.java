package com.charlie.educms.service;

import com.charlie.educms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author ChaR
 * @since 2022-02-23
 */
public interface CrmBannerService extends IService<CrmBanner> {

    List<CrmBanner> selectBanner();
}
