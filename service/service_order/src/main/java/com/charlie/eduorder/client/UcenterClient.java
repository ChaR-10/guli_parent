package com.charlie.eduorder.client;

import com.charlie.commonutils.vo.UcenterMemberVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author: ChaR
 * @Date: 2022/3/22 15:57
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {

    // 根据用户id获取用户信息
//    @PostMapping("/educenter/member/getMemberInfoById/{memberId}")
//    public UcenterMemberVo getMemberInfo(@PathVariable String id);
    @PostMapping("/educenter/member/getMemberInfoById/{memberId}")
    public UcenterMemberVo getMemberInfoById(@PathVariable String memberId);

}

