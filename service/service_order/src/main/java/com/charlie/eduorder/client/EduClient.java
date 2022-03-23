package com.charlie.eduorder.client;

import com.charlie.commonutils.vo.CourseWebVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author: ChaR
 * @Date: 2022/3/22 15:40
 */
@Component
@FeignClient("service-edu")
public interface EduClient {

    // 根据课程id获取课程详情信息
    @GetMapping("/eduservice/courseFront/getCourseInfoClient/{id}")
    public CourseWebVo getCourseInfoClient(@PathVariable String id);


}

