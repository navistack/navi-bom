package org.navistack.boot.autoconfigure.sms;

import com.tencentcloudapi.sms.v20210111.SmsClient;
import org.navistack.boot.autoconfigure.cloudservice.tencentcloud.sms.TencentCloudSmsProperties;
import org.navistack.framework.sms.ShortMessageServiceProvider;
import org.navistack.framework.sms.tencentcloud.TencentCloudShortMessageServiceProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(SmsClient.class)
public class TencentCloudShortMessageConfiguration {
    @Bean
    @ConditionalOnBean({TencentCloudSmsProperties.class, SmsClient.class})
    @ConditionalOnMissingBean({ShortMessageServiceProvider.class, TencentCloudShortMessageServiceProvider.class})
    public TencentCloudShortMessageServiceProvider tencentCloudShortMessageServiceProvider(TencentCloudSmsProperties properties, SmsClient smsClient) {
        return new TencentCloudShortMessageServiceProvider(properties.getSdkAppId(), properties.getAppKey(), smsClient);
    }
}
