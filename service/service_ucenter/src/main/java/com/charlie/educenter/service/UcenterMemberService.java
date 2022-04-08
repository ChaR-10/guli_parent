package com.charlie.educenter.service;

import com.charlie.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.charlie.educenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author ChaR
 * @since 2022-02-27
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(UcenterMember member);

    void register(RegisterVo registerVo);

    UcenterMember getOpenIdMember(String openid);

    Integer countRegisterDay(String day);
}
