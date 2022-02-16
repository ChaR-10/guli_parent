package com.charlie.eduservice.controller;


import com.charlie.commonutils.R;
import com.charlie.eduservice.entity.EduVideo;
import com.charlie.eduservice.service.EduVideoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author ChaR
 * @since 2022-02-13
 */
@Api(tags="小节管理")
@RestController
@RequestMapping("/eduService/video")
@CrossOrigin //解决跨域问题
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    //添加小节
    @PostMapping("/addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.save(eduVideo);
        return R.ok();
    }


    //删除小节
    // TODO 后面这个方法需要完善，删除小节的时候，同时也要把视频删除
    @DeleteMapping("/deleteVideo/{id}")
    public R deleteVideo(@PathVariable String id){
        eduVideoService.removeById(id);
        return R.ok();
    }

    //修改小节
    @PostMapping("/updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.updateById(eduVideo);
        return R.ok();
    }

    //根据小节id查询
    @GetMapping("/getVideoById/{videoId}")
    public R getVideoById(@PathVariable String videoId){
        EduVideo eduVideo = eduVideoService.getById(videoId);
        return R.ok().data("video",eduVideo);
    }


}


