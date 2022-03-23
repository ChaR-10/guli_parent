package com.charlie.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.charlie.commonutils.JwtUtils;
import com.charlie.commonutils.R;
import com.charlie.commonutils.vo.CourseWebVo;
import com.charlie.eduservice.client.OrdersClient;
import com.charlie.eduservice.entity.EduCourse;
import com.charlie.eduservice.entity.frontVo.CourseFrontVo;
import com.charlie.eduservice.entity.vo.ChapterVo;
import com.charlie.eduservice.service.EduChapterService;
import com.charlie.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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

    @Autowired
    private EduChapterService eduChapterService;

    @Autowired
    private OrdersClient ordersClient;

    @ApiOperation(value = "根据条件获取课程分页列表")
    @PostMapping("getFrontCourseList/{page}/{limit}")
    public R getFrontCourseList(@PathVariable long page, @PathVariable long limit,
                                @RequestBody(required = false) CourseFrontVo courseFrontVo ){
        Page<EduCourse> pageCourse = new Page<>(page,limit);
        Map<String,Object> map = courseService.getCourseFrontList(pageCourse,courseFrontVo);
        return R.ok().data(map);
    }

    //课程详情的方法
    @GetMapping("/getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request){
        //根据课程id，编写sql语句查询课程信息
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);

        //根据课程id，查询章节和小节信息
        List<ChapterVo> chapterVideoList = eduChapterService.getChapterVideoByCourseId(courseId);

        if(!StringUtils.isEmpty(request.getHeader("token"))){
            String memberId = JwtUtils.getMemberIdByJwtToken(request);
            boolean isBuyCourse = ordersClient.isBuyCourse(courseId, memberId);
            return R.ok().data("courseInfo", courseWebVo).data("chapterList", chapterVideoList).data("isBuyCourse",isBuyCourse);
        }else {
            return R.ok().data("courseInfo", courseWebVo).data("chapterList", chapterVideoList);
        }

    }

    @ApiOperation(value = "根据课程id查询课程信息")
    @GetMapping("getCourseInfoClient/{id}")
    public CourseWebVo getCourseInfoClient(@PathVariable String id){
        CourseWebVo courseFrontInfo = courseService.getBaseCourseInfo(id);
        return courseFrontInfo;
    }


}
