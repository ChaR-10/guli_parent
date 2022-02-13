package com.charlie.eduservice.controller;


import com.charlie.commonutils.R;
import com.charlie.eduservice.entity.vo.CourseInfoForm;
import com.charlie.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author ChaR
 * @since 2022-02-13
 */
@RestController
@RequestMapping("/eduService/course")
@CrossOrigin //解决跨域问题
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    //添加课程基本信息方法
    @PostMapping("/addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoForm courseInfoForm){
        //返回添加之后课程id，为了后面添加大纲使用
        String id = eduCourseService.saveCourseInfo(courseInfoForm);
        return R.ok().data("courseId",id);
    }

}

