package org.navistack.boot.autoconfigure.objectstorage;

import io.minio.MinioClient;
import org.navistack.framework.objectstorage.MinioObjectStorageService;
import org.navistack.framework.objectstorage.ObjectStorageService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@Import(MinioConfiguration.class)
public class ObjectStorageAutoConfiguration {
    @Bean
    @ConditionalOnClass(MinioClient.class)
    @ConditionalOnBean(MinioClient.class)
    @ConditionalOnMissingBean
    public ObjectStorageService MinioObjectStorageService(MinioClient minioClient) {
        return new MinioObjectStorageService(minioClient);
    }
}
