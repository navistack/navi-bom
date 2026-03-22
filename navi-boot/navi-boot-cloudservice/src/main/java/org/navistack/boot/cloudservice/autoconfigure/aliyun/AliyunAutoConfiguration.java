package org.navistack.boot.cloudservice.autoconfigure.aliyun;

import org.navistack.boot.cloudservice.autoconfigure.aliyun.captcha.AliyunCaptchaConfiguration;
import org.navistack.boot.cloudservice.autoconfigure.aliyun.sms.AliyunSmsConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        AliyunCaptchaConfiguration.class,
        AliyunSmsConfiguration.class
})
public class AliyunAutoConfiguration {
}
