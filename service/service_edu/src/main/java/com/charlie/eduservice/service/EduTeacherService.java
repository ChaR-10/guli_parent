package com.charlie.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.charlie.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.charlie.eduservice.entity.vo.TeacherQuery;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author ChaR
 * @since 2021-11-21
 */
public interface EduTeacherService extends IService<EduTeacher> {

    //多条件查询讲师带分页
    void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);

    Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageTeacher);
}
