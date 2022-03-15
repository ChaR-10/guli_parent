package com.charlie.commonutils.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: ChaR
 * @Date: 2022/3/15 22:04
 */
@Data
@ApiModel(value = "用户信息-评论部分", description = "评论部分需要的用户信息")
public class UcenterMemberVo {
    private String id;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("手机")
    private String mobile;
}
