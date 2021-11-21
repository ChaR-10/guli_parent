package com.charlie.eduservice.controller;


import com.charlie.eduservice.entity.EduTeacher;
import com.charlie.eduservice.service.EduTeacherService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-11-21
 */
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    //测试地址：http:localhost:8001/eduservice/teacher/findAll

    // service注入
    @Autowired
    private EduTeacherService eduTeacherService;

    // 1 查询讲师表中所有数据
    // rest风格
    @GetMapping("findAll")
    @ApiOperation(value = "教师列表")
    public List<EduTeacher> findAllTeacher() throws Exception {

        List<EduTeacher> list = eduTeacherService.list(null);
        return list;
    }
}

