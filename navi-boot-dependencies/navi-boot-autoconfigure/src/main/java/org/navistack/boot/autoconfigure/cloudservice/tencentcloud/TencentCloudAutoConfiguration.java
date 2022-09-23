package org.navistack.boot.autoconfigure.cloudservice.tencentcloud;

import org.navistack.boot.autoconfigure.cloudservice.tencentcloud.captcha.TencentCloudCaptchaConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(TencentCloudCaptchaConfiguration.class)
public class TencentCloudAutoConfiguration {
}
