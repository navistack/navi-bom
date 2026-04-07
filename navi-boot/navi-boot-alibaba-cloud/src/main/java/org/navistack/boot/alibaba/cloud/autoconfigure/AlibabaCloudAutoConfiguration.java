package org.navistack.boot.alibaba.cloud.autoconfigure;

import org.navistack.boot.alibaba.cloud.autoconfigure.captcha.AlibabaCloudCaptchaConfiguration;
import org.navistack.boot.alibaba.cloud.autoconfigure.sms.AlibabaCloudSmsConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        AlibabaCloudCaptchaConfiguration.class,
        AlibabaCloudSmsConfiguration.class
})
public class AlibabaCloudAutoConfiguration {
}
