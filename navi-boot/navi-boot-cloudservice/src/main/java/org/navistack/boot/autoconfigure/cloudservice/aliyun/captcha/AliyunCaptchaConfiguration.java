package org.navistack.boot.autoconfigure.cloudservice.aliyun.captcha;

import com.aliyun.captcha20230305.Client;
import com.aliyun.teaopenapi.models.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(Client.class)
@ConditionalOnProperty(prefix = AliyunCaptchaProperties.PROPERTY_PREFIX, name = "enabled", havingValue = "true",
        matchIfMissing = true)
@EnableConfigurationProperties(AliyunCaptchaProperties.class)
public class AliyunCaptchaConfiguration {
    @Bean
    @ConditionalOnClass(Client.class)
    @ConditionalOnMissingBean(Client.class)
    public Client client(AliyunCaptchaProperties clientProperties) throws Exception {
        Config config = new Config();
        config.setAccessKeyId(clientProperties.getAccessKeyId());
        config.setAccessKeySecret(clientProperties.getAccessKeySecret());
        config.setEndpoint(clientProperties.getEndpoint());
        return new Client(config);
    }
}
