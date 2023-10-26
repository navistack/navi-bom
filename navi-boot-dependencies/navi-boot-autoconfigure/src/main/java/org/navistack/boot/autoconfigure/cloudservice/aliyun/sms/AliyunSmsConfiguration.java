package org.navistack.boot.autoconfigure.cloudservice.aliyun.sms;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.teaopenapi.models.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(Client.class)
@ConditionalOnProperty(prefix = AliyunSmsProperties.PROPERTY_PREFIX, name = "enabled", havingValue = "true",
        matchIfMissing = true)
@EnableConfigurationProperties(AliyunSmsProperties.class)
public class AliyunSmsConfiguration {
    @Bean
    @ConditionalOnClass(Client.class)
    @ConditionalOnMissingBean(Client.class)
    public Client client(AliyunSmsProperties properties) throws Exception {
        Config config = new Config();
        config.setAccessKeyId(properties.getAccessKeyId());
        config.setAccessKeySecret(properties.getAccessKeySecret());
        config.setEndpoint(properties.getEndpoint());
        return new Client(config);
    }
}
