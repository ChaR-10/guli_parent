package com.charlie.eduservice.service;

import com.charlie.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.charlie.eduservice.entity.vo.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author ChaR
 * @since 2022-02-10
 */
public interface EduSubjectService extends IService<EduSubject> {

    /**
     * 添加课程分类
     * @param file
     * @param eduSubjectService
     */
    void saveSubject(MultipartFile file, EduSubjectService eduSubjectService);

    List<OneSubject> getAllOneTwoSubject();
}
