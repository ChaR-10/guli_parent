package com.charlie.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ChaR
 * @Date: 2022/2/16 19:41
 */
@Data
public class CourseQuery implements Serializable {
    private static final long serialVersionUID = 5082983974744446441L;

    @ApiModelProperty(value = "课程名称")
    private String title;

    @ApiModelProperty(value = "课程状态")
    private String status;

    @ApiModelProperty(value = "一级分类Id")
    private String subjectParentId;

    @ApiModelProperty(value = "二级分类Id")
    private String subjectId;

    @ApiModelProperty(value = "教师Id")
    private String teacherId;


}
