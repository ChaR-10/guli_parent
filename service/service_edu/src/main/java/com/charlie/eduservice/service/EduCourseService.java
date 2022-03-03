package com.charlie.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.charlie.commonutils.vo.CourseWebVo;
import com.charlie.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.charlie.eduservice.entity.frontVo.CourseFrontVo;
import com.charlie.eduservice.entity.vo.CourseInfoForm;
import com.charlie.eduservice.entity.vo.CoursePublishVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author ChaR
 * @since 2022-02-13
 */

public interface EduCourseService extends IService<EduCourse> {

    //添加课程基本信息方法
    String saveCourseInfo(CourseInfoForm courseInfoForm);

    //根据课程id查询课程基本信息
    CourseInfoForm getCourseInfo(String courseId);

    //修改课程信息
    void updateCourseInfo(CourseInfoForm courseInfoForm);

    CoursePublishVo getPublishCourseInfo(String courseId);

    void removeCourse(String courseId);

    Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);

    //前台根据课程id，查询课程基础信息
    CourseWebVo getBaseCourseInfo(String courseId);
}
