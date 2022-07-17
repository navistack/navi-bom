package org.navistack.boot.autoconfigure.cloudservice.tencentcloud;

import com.tencentcloudapi.captcha.v20190722.CaptchaClient;
import com.tencentcloudapi.common.Credential;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty({TencentCloudClientProperties.PROPERTY_PREFIX, TencentCloudCaptchaProperties.PROPERTY_PREFIX})
@EnableConfigurationProperties({TencentCloudClientProperties.class, TencentCloudCaptchaProperties.class})
public class TencentCloudAutoConfiguration {
    @Bean
    @ConditionalOnClass(CaptchaClient.class)
    @ConditionalOnMissingBean(CaptchaClient.class)
    public CaptchaClient captchaClient(TencentCloudClientProperties properties) {
        String region = properties.getRegion();
        TencentCloudClientProperties.Credential credential = properties.getCredential();
        return new CaptchaClient(
                new Credential(credential.getSecretId(), credential.getSecretKey()),
                region
        );
    }
}
