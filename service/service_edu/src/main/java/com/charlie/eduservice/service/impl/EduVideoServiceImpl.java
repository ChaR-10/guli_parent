package com.charlie.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.charlie.eduservice.client.VodClient;
import com.charlie.eduservice.entity.EduVideo;
import com.charlie.eduservice.mapper.EduVideoMapper;
import com.charlie.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author ChaR
 * @since 2022-02-13
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    //注入VodClient
    @Autowired
    private VodClient vodClient;

    //根据课程id删除小节
    // TODO 删除小节，要删除对应的视频文件
    @Override
    public void removeVideoByCourseId(String id) {
        //根据课程id查询所有课程所有的视频id
        QueryWrapper<EduVideo> wrapperVideo  = new QueryWrapper<>();
        wrapperVideo .eq("course_id",id);
        wrapperVideo .select("video_source_id");//只查询video_source_id单独一列数据
        List<EduVideo> eduVideoList = baseMapper.selectList(wrapperVideo );

        //List<EduVideo> 变成 List<String>
        List<String> videoIds = new ArrayList<>();
        for (int i = 0; i < eduVideoList.size(); i++) {
            EduVideo eduVideo = eduVideoList.get(i);
            if (eduVideo != null) {
                String videoSourceId = eduVideo.getVideoSourceId();
                if (!StringUtils.isEmpty(videoSourceId)) {
                    //放到videoIds集合中
                    videoIds.add(videoSourceId);
                }
            }
        }

        if (videoIds.size() >0){
            //根据多个视频id删除多个视频
            vodClient.deleteBatch(videoIds);
        }

        //删除小节
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", id);
        baseMapper.delete(wrapper);
    }
}
