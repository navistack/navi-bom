package org.navistack.boot.autoconfigure.cloudservice.tencentcloud.captcha;

import com.tencentcloudapi.captcha.v20190722.CaptchaClient;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import org.navistack.boot.autoconfigure.cloudservice.tencentcloud.TencentCloudClientProperties;
import org.navistack.boot.autoconfigure.cloudservice.tencentcloud.TencentCloudConfigurationSupport;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(CaptchaClient.class)
@ConditionalOnProperty(prefix = TencentCloudCaptchaProperties.PROPERTY_PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(TencentCloudCaptchaProperties.class)
public class TencentCloudCaptchaConfiguration extends TencentCloudConfigurationSupport {
    @Bean
    @ConditionalOnMissingBean(CaptchaClient.class)
    public CaptchaClient captchaClient(TencentCloudCaptchaProperties properties) {
        return bindCaptchaClient(properties.getClient());
    }

    protected static CaptchaClient bindCaptchaClient(TencentCloudClientProperties properties) {
        String region = properties.getRegion();
        Credential credential = bindCredential(properties.getCredential());
        ClientProfile clientProfile = bindClientProfile(properties.getClientProfile());
        return new CaptchaClient(credential, region, clientProfile);
    }
}
