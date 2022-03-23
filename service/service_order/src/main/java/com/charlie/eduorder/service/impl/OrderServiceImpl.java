package com.charlie.eduorder.service.impl;

import com.charlie.commonutils.vo.CourseWebVo;
import com.charlie.commonutils.vo.UcenterMemberVo;
import com.charlie.eduorder.client.EduClient;
import com.charlie.eduorder.client.UcenterClient;
import com.charlie.eduorder.entity.Order;
import com.charlie.eduorder.mapper.OrderMapper;
import com.charlie.eduorder.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.charlie.eduorder.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author ChaR
 * @since 2022-03-22
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private EduClient eduClient;

    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public String createOrders(String courseId, String memberId) {
        // 通过远程调用获取课程信息
        CourseWebVo courseInfo = eduClient.getCourseInfoClient(courseId);
        // 通过远程调用获取用户信息
        UcenterMemberVo memberInfo = ucenterClient.getMemberInfoById(memberId);

        Order order = new Order();
        order.setCourseId(courseInfo.getId());
        order.setCourseCover(courseInfo.getCover());
        order.setCourseTitle(courseInfo.getTitle());
        order.setTeacherName(courseInfo.getTeacherName());
        order.setMemberId(memberInfo.getId());
        order.setMobile(memberInfo.getMobile());
        order.setNickname(memberInfo.getNickname());
        order.setTotalFee(courseInfo.getPrice());
        order.setStatus(0);// 未支付0,已支付1
        order.setPayType(1);//目前默认微信支付1,支付宝2
        String orderNo = OrderNoUtil.getOrderNo();
        order.setOrderNo(orderNo); // 订单号

        // 保存订单数据
        baseMapper.insert(order);

        // 返回订单号
        return order.getOrderNo();
    }
}
