package com.charlie.educms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.charlie.educms.entity.CrmBanner;
import com.charlie.educms.mapper.CrmBannerMapper;
import com.charlie.educms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author ChaR
 * @since 2022-02-23
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Override
    public List<CrmBanner> selectBanner() {
        // 根据id进行降序排序，显示排序之后前两条记录
        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last(" limit 2");
        List<CrmBanner> crmBanners = baseMapper.selectList(null);
        return crmBanners;
    }
}
