package com.charlie.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
 * @author ChaR
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

    //分页查询
    //page：当前页
    //limit：每页显示记录数
    @ApiOperation(value = "分页讲师列表")
    @GetMapping("/pageList/{page}/{limit}")
    public R pageList(@ApiParam(name = "page", value = "当前页码", required = true)@PathVariable Long page,
                      @ApiParam(name = "limit", value = "每页记录数", required = true)@PathVariable Long limit
    ){
        Page<EduTeacher> pageParam = new Page<>(page, limit);
        //分页查询，查完后，会将数据封装在pageParam中
        eduTeacherService.page(pageParam,null);
        //获取查询到的数据
        List<EduTeacher> records = pageParam.getRecords();
        //获取总记录数
        long total = pageParam.getTotal();
        return R.ok().data("total",total).data("rows",records);
    }

}

