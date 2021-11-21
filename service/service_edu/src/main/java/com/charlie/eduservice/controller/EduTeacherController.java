package com.charlie.eduservice.controller;


import com.charlie.commonutils.R;
import com.charlie.eduservice.entity.EduTeacher;
import com.charlie.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-11-21
 */
@RestController
@RequestMapping("/eduservice/teacher")
@Api(description="讲师管理")
public class EduTeacherController {

    //测试地址：http:localhost:8001/eduservice/teacher/findAll

    // service注入
    @Autowired
    private EduTeacherService eduTeacherService;

    // 1 查询讲师表中所有数据
    // rest风格
    @GetMapping("findAll")
    @ApiOperation(value = "所有讲师列表")
    public R findAllTeacher() throws Exception {

        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items",list);
    }

    //逻辑删除讲师
    @DeleteMapping("{id}")
    @ApiOperation(value = "根据ID删除讲师")
    public R removeById(
            @ApiParam(name = "id",value = "讲师ID",required = true)
            @PathVariable String id
    ){
        boolean flag = eduTeacherService.removeById(id);
//        if(flag){
//            return R.ok();
//        }else {
//            return R.error();
//        }
        return flag == true ? R.ok() : R.error();
    }

}

