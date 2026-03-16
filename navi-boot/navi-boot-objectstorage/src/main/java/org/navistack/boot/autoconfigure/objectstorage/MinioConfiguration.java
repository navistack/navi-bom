package org.navistack.boot.autoconfigure.objectstorage;

import io.minio.MinioClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnClass(MinioClient.class)
@EnableConfigurationProperties(MinioProperties.class)
@ConditionalOnProperty(MinioProperties.PROPERTIES_PREFIX)
public class MinioConfiguration {
    @Bean
    @ConditionalOnMissingBean(MinioClient.class)
    public MinioClient minioClient(MinioProperties properties) {
        MinioProperties.Credentials credentials = properties.getCredentials();
        return MinioClient.builder()
                .endpoint(properties.getEndpoint())
                .credentials(credentials.getAccessKey(), credentials.getSecretKey())
                .build();
    }
}
