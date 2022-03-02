package com.charlie.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.charlie.commonutils.R;
import com.charlie.eduservice.entity.EduCourse;
import com.charlie.eduservice.entity.frontVo.CourseFrontVo;
import com.charlie.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: ChaR
 * @Date: 2022/3/2 21:08
 */

@RestController
@RequestMapping("/eduservice/courseFront")
@CrossOrigin
@Api(tags = "【前台】课程管理")
public class CourseFrontController {


    @Autowired
    private EduCourseService courseService;

    @ApiOperation(value = "根据条件获取课程分页列表")
    @PostMapping("getFrontCourseList/{page}/{limit}")
    public R getFrontCourseList(@PathVariable long page, @PathVariable long limit,
                                @RequestBody(required = false) CourseFrontVo courseFrontVo ){
        Page<EduCourse> pageCourse = new Page<>(page,limit);
        Map<String,Object> map = courseService.getCourseFrontList(pageCourse,courseFrontVo);
        return R.ok().data(map);
    }

}
