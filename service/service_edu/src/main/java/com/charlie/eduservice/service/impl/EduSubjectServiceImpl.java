package com.charlie.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.charlie.eduservice.entity.EduSubject;
import com.charlie.eduservice.entity.excel.SubjectData;
import com.charlie.eduservice.listener.SubjectExcelListener;
import com.charlie.eduservice.mapper.EduSubjectMapper;
import com.charlie.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.charlie.servicebase.exceptionHandler.CharException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author ChaR
 * @since 2022-02-10
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file, EduSubjectService eduSubjectService) {
        try {
            //文件输入流
            InputStream is = file.getInputStream();

            //调用方法进行读取
            EasyExcel.read(is, SubjectData.class, new SubjectExcelListener(eduSubjectService))
                    .sheet().doRead();

        }catch (Exception e){
            e.printStackTrace();
            throw new CharException(20002,"添加课程分类失败");
        }
    }
}
