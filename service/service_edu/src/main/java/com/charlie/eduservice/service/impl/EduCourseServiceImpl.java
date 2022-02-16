package com.charlie.eduservice.service.impl;

import com.charlie.eduservice.entity.EduCourse;
import com.charlie.eduservice.entity.EduCourseDescription;
import com.charlie.eduservice.entity.vo.CourseInfoForm;
import com.charlie.eduservice.entity.vo.CoursePublishVo;
import com.charlie.eduservice.mapper.EduCourseMapper;
import com.charlie.eduservice.service.EduCourseDescriptionService;
import com.charlie.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.charlie.servicebase.exceptionHandler.CharException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author ChaR
 * @since 2022-02-13
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    //课程描述注入
    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Override
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {
        //向课程表里面添加课程基本信息
        //CourseInfoForm对象 转换成 EduCourse对象
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm, eduCourse);
        int result = baseMapper.insert(eduCourse);

        if (result<=0){//表示添加失败
            throw new CharException(20001,"添加课程信息失败");
        }

        //获取添加之后课程信息的id
        String cid = eduCourse.getId();

        //想课程简介表里面添加课程简介
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoForm.getDescription());

        //手动设置描述课程表的id，与上面的课程信息表id关联
        eduCourseDescription.setId(cid);


        eduCourseDescriptionService.save(eduCourseDescription);

        return cid;

    }

    @Override
    public CourseInfoForm getCourseInfo(String courseId) {
        //查询课程表
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(eduCourse,courseInfoForm);

        //查询简介表
        EduCourseDescription courseDescription = eduCourseDescriptionService.getById(courseId);
        courseInfoForm.setDescription(courseDescription.getDescription());

        return courseInfoForm;
    }

    @Override
    public void updateCourseInfo(CourseInfoForm courseInfoForm) {
        //1、修改课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm,eduCourse);
        int update = baseMapper.updateById(eduCourse);
        if (update <= 0){
            throw new CharException(20001,"修改课程信息失败");
        }

        //2、修改描述信息
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoForm.getDescription());
        eduCourseDescription.setId(courseInfoForm.getId());
        eduCourseDescriptionService.updateById(eduCourseDescription);
    }

    @Override
    public CoursePublishVo getPublishCourseInfo(String courseId) {
        // 调用mapper
        CoursePublishVo vo = baseMapper.getPublishCourseInfo(courseId);
        return vo;
    }
}
