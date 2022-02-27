package com.charlie.smsservice.service.impl;

import com.charlie.commonutils.R;
import com.charlie.commonutils.RandomUtil;
import com.charlie.servicebase.exceptionHandler.CharException;
import com.charlie.smsservice.service.TengXunSmsService;

import com.charlie.smsservice.utils.TengXunSmsProperties;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import com.tencentcloudapi.sms.v20190711.models.SendStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author: ChaR
 * @Date: 2022/2/27 14:48
 */

@Service
@Slf4j
public class TengXunSmsServiceImpl implements TengXunSmsService {
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public R send(String mobile) {
        //手机号码不能为空
//        Assert.notEmpty(mobile, ResponseEnum.MOBILE_NULL_ERROR);
        //是否是合法的手机号码
//        Assert.isTrue(RegexValidateUtils.checkCellphone(mobile), ResponseEnum.MOBILE_ERROR);
        String code = RandomUtil.getFourBitRandom();



        String jointMobile="+86"+mobile;
        String secretId = TengXunSmsProperties.SECRET_ID;
        String secretKey= TengXunSmsProperties.SECRET_KEY;
        //短信应用 ID
        String appid = TengXunSmsProperties.APP_ID;
        //短信签名内容
        String sign = TengXunSmsProperties.SIGN;
        //短信模板 ID
        String templateID = TengXunSmsProperties.TEMPLATE_ID;
        //+86为国家码，182********为手机号，最多不要超过200个手机号

        String[] phoneNumbers = { jointMobile};
        //模板参数: 若无模板参数，则设置为空
        String[] templateParams = { code };//对应模板中{1}
        try {
            //必要步骤： 实例化一个认证对象，入参需要传入腾讯云账户密钥对 secretId 和 secretKey
            Credential cred = new Credential(secretId , secretKey);

            ClientProfile clientProfile = new ClientProfile();
            //SDK 默认用 TC3-HMAC-SHA256 进行签名 非必要请不要修改该字段
            clientProfile.setSignMethod("HmacSHA256");
            // 实例化 SMS 的 client 对象 第二个参数是地域信息，可以直接填写字符串 ap-guangzhou，或者引用预设的常量
            SmsClient client = new SmsClient(cred, "", clientProfile);
            //实例化一个请求对象，根据调用的接口和实际情况，可以进一步设置请求参数 您可以直接查询 SDK 源码确定接口有哪些属性可以设置
            SendSmsRequest req = new SendSmsRequest();

            // 短信应用 ID: 在 [短信控制台] 添加应用后生成的实际 SDKAppID，例如1400006666
            req.setSmsSdkAppid(appid);

            // 短信签名内容: 使用 UTF-8 编码，必须填写已审核通过的签名，可登录 [短信控制台] 查看签名信息
            req.setSign(sign);

            //短信模板 ID: 必须填写已审核通过的模板 ID，可登录 [短信控制台] 查看模板 ID
            req.setTemplateID(templateID);

            //下发手机号码，采用 e.164 标准，+[国家或地区码][手机号] 例如+8613711112222
            req.setPhoneNumberSet(phoneNumbers);

            req.setTemplateParamSet(templateParams);

            // 通过 client 对象调用 SendSms 方法发起请求。注意请求方法名与请求对象是对应的 返回的 res 是一个SendSmsResponse 类的实例，与请求对象对应
            SendSmsResponse res = client.SendSms(req);
            //获取响应结果
            SendStatus[] sendStatusSet = res.getSendStatusSet();
            log.info("短信发送返回的响应："+ sendStatusSet);

            if (sendStatusSet[0].getCode() == "LimitExceeded.PhoneNumberThirtySecondLimit"){
//                throw new CharException(20001, "单个手机号30秒内下发短信条数超过设定的上限");
                return R.error().message("单个手机号30秒内下发短信条数超过设定的上限");
            }

//            Assert.notEquals("LimitExceeded.PhoneNumberThirtySecondLimit", sendStatusSet[0].getCode(), ResponseEnum.TENGXUN_SMS_LIMIT_CONTROL_ERROR);
//            Assert.equals("Ok", sendStatusSet[0].getCode() ,ResponseEnum.TENGXUN_SMS_ERROR);

            if ("Ok".equals(sendStatusSet[0].getCode())){

                //发送成功 将验证码存入redis中
//                redisTemplate.opsForValue().set("charlie:sms:code:" + mobile, code,5, TimeUnit.MINUTES); //设置redis分组

                //发送成功，把发送成功的验证码放到redis里面
                //设置有效时间
                redisTemplate.opsForValue().set(mobile, code, 5, TimeUnit.MINUTES);

                return R.ok();
            }

            return R.error().message("短信发送失败");

        } catch (TencentCloudSDKException e) {
            log.error("腾讯云短信发送sdk调用失败："+e.getErrorCode()+","+e.getMessage());
//            throw new CharException(ResponseEnum.TENGXUN_SMS_ERROR,e);
            return R.error().message("短信发送失败");
        }
    }
}
