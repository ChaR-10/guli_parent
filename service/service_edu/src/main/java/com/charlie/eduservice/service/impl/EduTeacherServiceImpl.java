package com.charlie.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.charlie.eduservice.entity.EduTeacher;
import com.charlie.eduservice.entity.vo.TeacherQuery;
import com.charlie.eduservice.mapper.EduTeacherMapper;
import com.charlie.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author ChaR
 * @since 2021-11-21
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery) {
        //构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

        //取出值，判断他们是否有值
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        //判断条件值是否为空，如果不为空，拼接条件
        //判断是否有传入教师名
        if (!StringUtils.isEmpty(name)){
            //构建条件
            wrapper.like("name",name);//参数1：数据库字段名； 参数2：模糊查询的值
        }
        //判断是否传入教师头衔
        if (!StringUtils.isEmpty(level)){
            //构造条件
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)){
            //构造条件
            wrapper.ge("gmt_create", begin);//ge：大于等于
        }
        if (!StringUtils.isEmpty(begin)){
            //构造条件
            wrapper.le("gmt_modified", end);//le:小于等于
        }

        //排序
        wrapper.orderByDesc("gmt_create");


        //带上门判断后的条件进行分页查询
        baseMapper.selectPage(pageParam, wrapper);
    }

    // 自定义分页实现，需要返回分页的所有信息
    @Override
    public Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageParam) {
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        baseMapper.selectPage(pageParam, wrapper);

        List<EduTeacher> records = pageParam.getRecords();
        long current = pageParam.getCurrent();
        long pages = pageParam.getPages();
        long size = pageParam.getSize();
        long total = pageParam.getTotal();
        boolean hasNext = pageParam.hasNext();//下一页
        boolean hasPrevious = pageParam.hasPrevious();//上一页

        //把分页数据获取出来，放到map集合
        Map<String,Object> map = new HashMap<>();
        map.put("items",records);
        map.put("pages",pages);
        map.put("current",current);
        map.put("size",size);
        map.put("total",total);
        map.put("hasNext",hasNext);
        map.put("hasPrevious",hasPrevious);

        return map;
    }

}
