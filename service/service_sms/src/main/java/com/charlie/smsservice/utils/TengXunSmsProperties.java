package com.charlie.smsservice.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: ChaR
 * @Date: 2022/2/27 14:45
 */
@Getter  //idea2020.2.3版配置文件自动提示需要这个
@Setter
@Component
//注意prefix要写到最后一个 "." 符号之前
//调用setter为成员赋值
@ConfigurationProperties(prefix = "tengxun.sms")
public class TengXunSmsProperties implements InitializingBean {
    private String secretId;
    private String secretKey;
    private String appid;
    private String sign;
    private String templateID;
    public static String SECRET_ID;
    public static String SECRET_KEY;
    public static String APP_ID;
    public static String SIGN;
    public static String TEMPLATE_ID;
    //当私有成员被赋值后，此方法自动被调用，从而初始化常量

    @Override
    public void afterPropertiesSet() throws Exception {
        SECRET_ID=secretId;
        SECRET_KEY=secretKey;
        APP_ID=appid;
        SIGN=sign;
        TEMPLATE_ID=templateID;
    }

}
