package org.navistack.boot.autoconfigure.sms;

import com.tencentcloudapi.sms.v20210111.SmsClient;
import org.navistack.boot.autoconfigure.cloudservice.tencentcloud.captcha.TencentCloudCaptchaProperties;
import org.navistack.boot.autoconfigure.cloudservice.tencentcloud.sms.TencentCloudSmsProperties;
import org.navistack.framework.sms.tencentcloud.TencentCloudShortMessageService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AutoConfiguration
public class ShortMessageAutoConfiguration {
    @Configuration
    @ConditionalOnClass(SmsClient.class)
    public static class TencentCloudShortMessageConfiguration {
        @Bean
        @ConditionalOnBean({TencentCloudCaptchaProperties.class, SmsClient.class})
        public TencentCloudShortMessageService tencentCloudShortMessageService(TencentCloudSmsProperties properties, SmsClient smsClient) {
            return new TencentCloudShortMessageService(properties.getSdkAppId(), properties.getAppKey(), smsClient);
        }
    }
}
