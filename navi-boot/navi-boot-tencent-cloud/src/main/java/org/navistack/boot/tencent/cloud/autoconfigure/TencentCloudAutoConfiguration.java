package org.navistack.boot.tencent.cloud.autoconfigure;

import org.navistack.boot.tencent.cloud.autoconfigure.captcha.TencentCloudCaptchaConfiguration;
import org.navistack.boot.tencent.cloud.autoconfigure.sms.TencentCloudSmsConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        TencentCloudCaptchaConfiguration.class,
        TencentCloudSmsConfiguration.class
})
public class TencentCloudAutoConfiguration {
}
