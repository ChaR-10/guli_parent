package com.charlie.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.charlie.commonutils.R;
import com.charlie.eduservice.entity.EduTeacher;
import com.charlie.eduservice.entity.vo.TeacherQuery;
import com.charlie.eduservice.service.EduTeacherService;
import com.charlie.servicebase.exceptionHandler.CharException;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author ChaR
 * @since 2021-11-21
 */
@RestController
@RequestMapping("/eduService/teacher")
//@Api(description="讲师管理")
@Api(tags="讲师管理")
@CrossOrigin
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

        try{
            int i = 10/0;
        }catch (Exception e){
            // 执行自定义异常
            throw new CharException(20000,"执行了自定义CharException异常");
        }


        //分页查询，查完后，会将数据封装在pageParam中
        eduTeacherService.page(pageParam,null);
        //获取查询到的数据
        List<EduTeacher> records = pageParam.getRecords();
        //获取总记录数
        long total = pageParam.getTotal();
        return R.ok().data("total",total).data("rows",records);
    }

    //多条件查询讲师带分页
    @ApiOperation(value = "多条件查询讲师带分页")
    @PostMapping("/pageTeacherCondition/{page}/{limit}")
    public R pageTeacherCondition(@ApiParam(name = "page", value = "当前页码", required = true)@PathVariable Long page,
                                  @ApiParam(name = "limit", value = "每页记录数", required = true)@PathVariable Long limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery){//通过封装TeacherQuery对象来直接传递查询条件
        //创建分页page对象
        Page<EduTeacher> pageParam = new Page<>(page, limit);

        //调用方法实现多条件分页查询
        eduTeacherService.pageQuery(pageParam, teacherQuery);

        //获取查询到的数据
        List<EduTeacher> records = pageParam.getRecords();

        //获取总记录数
        long total = pageParam.getTotal();
        return R.ok().data("total",total).data("rows",records);

    }

    //新增讲师
    @ApiModelProperty(value = "新增讲师")
    @PostMapping("/save")
    public R save(@RequestBody EduTeacher eduTeacher){
        boolean flag = eduTeacherService.save(eduTeacher);
//        if (flag){
//            return R.ok();
//        }else {
//            return R.error();
//        }
        return flag ? R.ok() : R.error();
    }

    //根据id查询,用于信息回显
    @ApiModelProperty(value = "根据id查询")
    @GetMapping("/getById/{id}")
    public R getById(@PathVariable String id){
        EduTeacher teacher = eduTeacherService.getById(id);
        return R.ok().data("item",teacher);
    }

    //修改讲师
    @ApiModelProperty(value = "修改讲师")
    @PostMapping("/updateById")
    public R updateById(@RequestBody EduTeacher teacher){
        boolean flag = eduTeacherService.updateById(teacher);
        if (flag){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation(value = "controller其他写法", produces = "application/json")
    @ApiModelProperty(value = "修改讲师")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userUid", value = "用户uid"),
            @ApiImplicitParam(name = "uidArr[]", value = "uid数组"),

    })
    @RequestMapping(value = "/getListNewDemo", method = RequestMethod.POST) //get 不支持RestBody
    @Transactional
    public R getListNewDemo(@RequestParam(required = false) UUID userUid,
                            @RequestParam(required = false, value = "uidArr[]") UUID[] uidArr,
                            @RequestBody EduTeacher teacher){

        try{
            List<EduTeacher> list = eduTeacherService.list(null);
            return R.ok().data("items",list);
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 事务回滚
            return R.error();
        }

    }

}

