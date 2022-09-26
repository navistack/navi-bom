package org.navistack.boot.autoconfigure.cloudservice.aliyun.afs;

import com.aliyuncs.IAcsClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(IAcsClient.class)
@EnableConfigurationProperties(AliyunAfsProperties.class)
public class AliyunAfsConfiguration {
}
