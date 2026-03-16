package org.navistack.boot.autoconfigure.cloudservice.tencentcloud;

import org.navistack.boot.autoconfigure.cloudservice.tencentcloud.captcha.TencentCloudCaptchaConfiguration;
import org.navistack.boot.autoconfigure.cloudservice.tencentcloud.sms.TencentCloudSmsConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        TencentCloudCaptchaConfiguration.class,
        TencentCloudSmsConfiguration.class
})
public class TencentCloudAutoConfiguration {
}
