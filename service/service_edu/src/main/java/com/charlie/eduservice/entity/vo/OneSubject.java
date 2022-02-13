package com.charlie.eduservice.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ChaR
 * @Date: 2022/2/13 17:32
 */
//一级分类
@Data
public class OneSubject {
    private String id;
    private String title;

    //一个一级分类有多个二级分类
    private List<TwoSubject> children = new ArrayList<>();

}
