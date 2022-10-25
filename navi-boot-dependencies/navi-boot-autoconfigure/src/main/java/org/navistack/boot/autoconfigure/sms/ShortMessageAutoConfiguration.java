package org.navistack.boot.autoconfigure.sms;

import com.aliyuncs.IAcsClient;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import org.navistack.boot.autoconfigure.cloudservice.tencentcloud.captcha.TencentCloudCaptchaProperties;
import org.navistack.boot.autoconfigure.cloudservice.tencentcloud.sms.TencentCloudSmsProperties;
import org.navistack.framework.sms.*;
import org.navistack.framework.sms.aliyun.AliyunShortMessageServiceProvider;
import org.navistack.framework.sms.tencentcloud.TencentCloudShortMessageServiceProvider;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AutoConfiguration
@ConditionalOnClass(ShortMessageService.class)
@EnableConfigurationProperties(ShortMessageProperties.class)
public class ShortMessageAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public ShortMessageTemplateRegistration shortMessageTemplateRegistration(ShortMessageProperties properties) {
        ConfigurableShortMessageTemplateRegistration registration = new ConfigurableShortMessageTemplateRegistration();
        registration.addTemplates(properties.getTemplates());
        return registration;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean({ShortMessageTemplateRegistration.class, ShortMessageServiceProvider.class})
    public ShortMessageService shortMessageService(
            ShortMessageTemplateRegistration templateRegistration,
            ShortMessageServiceProvider serviceProvider
    ) {
        return new DefaultShortMessageService(templateRegistration, serviceProvider);
    }

    @Configuration
    @ConditionalOnClass(SmsClient.class)
    public static class TencentCloudShortMessageConfiguration {
        @Bean
        @ConditionalOnBean({TencentCloudCaptchaProperties.class, SmsClient.class})
        public TencentCloudShortMessageServiceProvider tencentCloudShortMessageServiceProvider(TencentCloudSmsProperties properties, SmsClient smsClient) {
            return new TencentCloudShortMessageServiceProvider(properties.getSdkAppId(), properties.getAppKey(), smsClient);
        }
    }

    @Configuration
    @ConditionalOnClass(IAcsClient.class)
    public static class AliyunShortMessageConfiguration {
        @Bean
        @ConditionalOnBean({IAcsClient.class})
        public AliyunShortMessageServiceProvider aliyunShortMessageServiceProvider(IAcsClient acsClient) {
            return new AliyunShortMessageServiceProvider(acsClient);
        }
    }
}
