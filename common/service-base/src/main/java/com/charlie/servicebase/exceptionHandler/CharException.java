package com.charlie.servicebase.exceptionHandler;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: ChaR
 * @Date: 2022/1/4 23:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharException extends RuntimeException{

    @ApiModelProperty(value = "状态码")
    private Integer code;
    private String msg;

}
