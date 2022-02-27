package com.charlie.msmservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.charlie.commonutils.R;
import com.charlie.commonutils.RandomUtil;
import com.charlie.msmservice.service.MsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: ChaR
 * @Date: 2022/2/27 12:06
 */
@Service
public class MsmServiceImpl implements MsmService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public R send(String phone) {
        //从redis中获取验证码，如果获取到就直接返回
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)) {
            return R.ok();
        }
        //如果redis获取不到，就进行阿里云发送
        //生成随机值，传递阿里云进行发送
        code = RandomUtil.getFourBitRandom();
        Map<String, Object> param = new HashMap<>();
        param.put("code", code);

        if(StringUtils.isEmpty(phone)) return R.error().message("短信发送失败");

        // ============================= 跳过阿里云短信直接写入redis ===============================
        if(!StringUtils.isEmpty(phone)) {
            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
            System.out.println("当前验证码为：" + code + ", 5分钟后失效");
            return R.ok();
        }

        // ============================= 跳过阿里云短信直接写入redis ===============================

        // ========================================== 阿里云短信调用 ==================================================================

        DefaultProfile profile =
                DefaultProfile.getProfile("default", "accessKeyId", "secret");
        IAcsClient client = new DefaultAcsClient(profile);


        //设置相关固定的参数
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");

        //设置发送相关的参数
        request.putQueryParameter("PhoneNumbers",phone); //手机号
        request.putQueryParameter("SignName","我的xmall购物商城"); //申请阿里云 签名名称
        request.putQueryParameter("TemplateCode",""); //申请阿里云 模板code
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param)); //验证码数据，转换json数据传递

        try {
            //最终发送
            CommonResponse response = client.getCommonResponse(request);
            boolean success = response.getHttpResponse().isSuccess();

            if (success){
                //发送成功，把发送成功的验证码放到redis里面
                //设置有效时间
                redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
                return R.ok();
            }else {
                return R.error().message("短信发送失败");
            }

        }catch(Exception e) {
            e.printStackTrace();
            return R.error().message("短信发送失败");
        }

        // ========================================== 阿里云短信调用 ==================================================================

    }
}
