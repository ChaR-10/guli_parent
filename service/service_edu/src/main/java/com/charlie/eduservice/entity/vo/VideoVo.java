package com.charlie.eduservice.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ChaR
 * @Date: 2022/2/15 9:21
 */
@Data
public class VideoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String title;

    private Boolean free;

    private String videoSourceId;

}

