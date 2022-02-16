package com.charlie.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.charlie.commonutils.R;
import com.charlie.eduservice.entity.EduCourse;
import com.charlie.eduservice.entity.vo.CourseInfoForm;
import com.charlie.eduservice.entity.vo.CoursePublishVo;
import com.charlie.eduservice.entity.vo.CourseQuery;
import com.charlie.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ApiOperation("课程最终发布")
    @PostMapping("publishCourse/{id}")
    public R publishCourse(
            @ApiParam(name = "id",value = "课程Id",required = true)
            @PathVariable() String id
    ){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");
        boolean success = courseService.updateById(eduCourse);
        if(success){
            return R.ok();
        }else {
            return R.error().message("课程发布失败");
        }
    }

    @ApiOperation(value = "课程列表")
    @GetMapping("getCourseList")
    public R getCourseList(){
        List<EduCourse> list = courseService.list(null);
        return R.ok().data("list",list);
    }

    @ApiOperation(value = "分页查询课程列表")
    @PostMapping("pageListCourse/{current}/{limit}")
    public R pageListCourse(
            @ApiParam(name = "current", value = "当前页码", required = true)
            @PathVariable Long current,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit){
        //创建page对象
        Page<EduCourse> pageCourse =new Page<>(current,limit);

        //调用方法查询
        courseService.page(pageCourse,null);

        long total = pageCourse.getTotal();
        List<EduCourse> courseList = pageCourse.getRecords();

        return R.ok().data("total",total).data("rows",courseList);
    }

    //TODO 带条件的分页查询
    @ApiOperation(value = "条件查询带分页课程列表")
    @PostMapping("pageCourseCondition/{current}/{limit}")
    public R pageCourseCondition(
            @ApiParam(name = "current", value = "当前页码", required = true)
            @PathVariable Long current,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @RequestBody(required = false) CourseQuery courseQuery){
        //创建page对象
        Page<EduCourse> pageCourse =new Page<>(current,limit);

        //构建条件
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();

        //组合条件查询
        String title = courseQuery.getTitle();
        String status = courseQuery.getStatus();

        //判断是否为空，不为空就拼接查询
        if (!StringUtils.isEmpty(title)) {
            wrapper.like("title",title);
        }
        if (!StringUtils.isEmpty(status)) {
            wrapper.eq("status",status);
        }

        //调用方法查询
        courseService.page(pageCourse,wrapper);

        long total = pageCourse.getTotal();
        List<EduCourse> courseList = pageCourse.getRecords();

        return R.ok().data("total",total).data("rows",courseList);
    }

    @ApiOperation(value = "删除课程")
    @DeleteMapping("deleteCourse/{courseId}")
    public R deleteCourse(@PathVariable String courseId){
        courseService.removeCourse(courseId);
        return R.ok();
    }



}


