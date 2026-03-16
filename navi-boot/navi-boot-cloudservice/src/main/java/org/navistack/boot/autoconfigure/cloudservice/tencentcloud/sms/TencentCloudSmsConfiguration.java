package org.navistack.boot.autoconfigure.cloudservice.tencentcloud.sms;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import org.navistack.boot.autoconfigure.cloudservice.tencentcloud.TencentCloudClientProperties;
import org.navistack.boot.autoconfigure.cloudservice.tencentcloud.TencentCloudConfigurationSupport;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(SmsClient.class)
@ConditionalOnProperty(prefix = TencentCloudSmsProperties.PROPERTY_PREFIX, name = "enabled", havingValue = "true",
        matchIfMissing = true)
@EnableConfigurationProperties(TencentCloudSmsProperties.class)
public class TencentCloudSmsConfiguration extends TencentCloudConfigurationSupport {
    @Bean
    @ConditionalOnMissingBean(SmsClient.class)
    public SmsClient smsClient(TencentCloudSmsProperties properties) {
        return bindSmsClient(properties.getClient());
    }

    protected static SmsClient bindSmsClient(TencentCloudClientProperties properties) {
        String region = properties.getRegion();
        Credential credential = bindCredential(properties.getCredential());
        ClientProfile clientProfile = bindClientProfile(properties.getClientProfile());
        return new SmsClient(credential, region, clientProfile);
    }
}
