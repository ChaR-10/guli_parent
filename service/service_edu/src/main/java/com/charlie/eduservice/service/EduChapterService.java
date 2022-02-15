package com.charlie.eduservice.service;

import com.charlie.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.charlie.eduservice.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author ChaR
 * @since 2022-02-13
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);
}
