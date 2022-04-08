package com.charlie.staservice.entity.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: ChaR
 * @Date: 2022/4/8 22:48
 */
@Data
@Builder
public class PieVo {
    private String name;
    private Integer value;
}
