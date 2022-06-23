package org.navistack.boot.autoconfigure.captcha.aliyun;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.endpoint.DefaultEndpointResolver;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConditionalOnProperty({AliyunClientProperties.PROPERTY_PREFIX, AliyunAfsProperties.PROPERTY_PREFIX})
@EnableConfigurationProperties({AliyunClientProperties.class, AliyunAfsProperties.class})
@ConditionalOnClass(IAcsClient.class)
public class AliyunAutoConfiguration {
    @Bean
    @ConditionalOnClass(IAcsClient.class)
    @ConditionalOnMissingBean(IAcsClient.class)
    public DefaultAcsClient defaultAcsClient(AliyunClientProperties clientProperties) {
        AliyunClientProperties.Credential credential = clientProperties.getCredential();

        IClientProfile clientProfile = DefaultProfile.getProfile(
                clientProperties.getRegion(),
                credential.getAccessKeyId(),
                credential.getAccessKeySecret()
        );
        DefaultAcsClient client = new DefaultAcsClient(clientProfile);

        List<AliyunClientProperties.Endpoint> endpoints = clientProperties.getEndpoints();
        if (endpoints != null && !endpoints.isEmpty()) {
            DefaultEndpointResolver endpointResolver = new DefaultEndpointResolver(client, clientProfile);
            for (AliyunClientProperties.Endpoint endpoint : endpoints) {
                endpointResolver.putEndpointEntry(
                        endpoint.getRegionId(),
                        endpoint.getProduct(),
                        endpoint.getEndpoint()
                );
            }
            client.setEndpointResolver(endpointResolver);
        }

        return client;
    }
}
