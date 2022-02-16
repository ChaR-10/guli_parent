package com.charlie.eduservice.controller;


import com.charlie.commonutils.R;
import com.charlie.eduservice.entity.vo.CourseInfoForm;
import com.charlie.eduservice.entity.vo.CoursePublishVo;
import com.charlie.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(tags="课程管理")
@RestController
@RequestMapping("/eduService/course")
@CrossOrigin //解决跨域问题
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduCourseService courseService;

    //添加课程基本信息方法
    @PostMapping("/addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoForm courseInfoForm){
        //返回添加之后课程id，为了后面添加大纲使用
        String id = eduCourseService.saveCourseInfo(courseInfoForm);
        return R.ok().data("courseId",id);
    }

    //根据课程id查询课程基本信息
    @GetMapping("/getCourseInfoById/{courseId}")
    public R getCourseInfoById(@PathVariable String courseId){
        CourseInfoForm courseInfoForm = eduCourseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoForm",courseInfoForm);
    }

    //修改课程信息
    @PostMapping("/updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoForm courseInfoForm){
        eduCourseService.updateCourseInfo(courseInfoForm);
        return R.ok();
    }


    @ApiOperation("获取课程发布信息")
    @GetMapping("getPublishCourseInfo/{courseId}")
    public R getPublishCourseInfo(
            @ApiParam(name = "courseId",value = "课程Id",required = true)
            @PathVariable String courseId
    ){
        CoursePublishVo publishVo = courseService.getPublishCourseInfo(courseId);
        return R.ok().data("publishInfo",publishVo);
    }

}


