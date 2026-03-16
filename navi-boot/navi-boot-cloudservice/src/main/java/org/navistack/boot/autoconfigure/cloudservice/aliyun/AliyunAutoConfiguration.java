package org.navistack.boot.autoconfigure.cloudservice.aliyun;

import org.navistack.boot.autoconfigure.cloudservice.aliyun.captcha.AliyunCaptchaConfiguration;
import org.navistack.boot.autoconfigure.cloudservice.aliyun.sms.AliyunSmsConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        AliyunCaptchaConfiguration.class,
        AliyunSmsConfiguration.class
})
public class AliyunAutoConfiguration {
}
