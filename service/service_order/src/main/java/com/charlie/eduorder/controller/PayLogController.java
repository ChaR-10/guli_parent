package com.charlie.eduorder.controller;


import com.charlie.commonutils.R;
import com.charlie.eduorder.service.PayLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author ChaR
 * @since 2022-03-22
 */
@Api(tags = "微信支付管理")
@RestController
@RequestMapping("/eduorder/paylog")
@CrossOrigin
public class PayLogController {

    @Autowired
    private PayLogService payLogService;

    @ApiOperation(value = "生成微信支付二维码接口")
    @GetMapping("createNative/{orderNo}")
    public R createNative(@PathVariable String orderNo) {
        //返回信息，包含二维码地址，还有其他需要的信息
        Map map = payLogService.createNative(orderNo);
        System.out.println("****返回二维码map集合"+map);
        return R.ok().data(map);
    }

    @ApiOperation(value = "查询订单支付状态")
    @GetMapping("queryPayStatus/{orderNo}")
    public R queryPayStatus(@PathVariable String orderNo) {
        Map<String,String> map = payLogService.queryPayStatus(orderNo);
        System.out.println("****查询订单状态："+map);
        if (map == null) {
            return R.error().message("支付出错了");
        }
        //如果返回map不为空，通过map获取订单状态
        if (map.get("trade_state").equals("SUCCESS")) {//支付成功
            //添加记录到支付表，更新订单表订单状态
            payLogService.updateOrdersStatus(map);
            return R.ok().message("支付成功");
        }
        return R.ok().code(25000).message("订单支付中");
    }

}


