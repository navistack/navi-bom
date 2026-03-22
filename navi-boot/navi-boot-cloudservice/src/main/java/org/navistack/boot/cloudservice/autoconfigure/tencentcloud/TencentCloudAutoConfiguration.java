package org.navistack.boot.cloudservice.autoconfigure.tencentcloud;

import org.navistack.boot.cloudservice.autoconfigure.tencentcloud.captcha.TencentCloudCaptchaConfiguration;
import org.navistack.boot.cloudservice.autoconfigure.tencentcloud.sms.TencentCloudSmsConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        TencentCloudCaptchaConfiguration.class,
        TencentCloudSmsConfiguration.class
})
public class TencentCloudAutoConfiguration {
}
