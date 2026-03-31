package org.navistack.boot.objectstorage.autoconfigure;

import io.minio.MinioClient;
import org.navistack.framework.objectstorage.FilesystemObjectStorageService;
import org.navistack.framework.objectstorage.HttpServletRequestPublicRootUriSupplier;
import org.navistack.framework.objectstorage.MinioObjectStorageService;
import org.navistack.framework.objectstorage.ObjectStorageService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@AutoConfiguration
public class ObjectStorageAutoConfiguration {

    @ConditionalOnClass(MinioClient.class)
    @Import(MinioConfiguration.class)
    public static class MinioObjectStorageConfiguration {
        @Bean
        @ConditionalOnBean(MinioClient.class)
        @ConditionalOnMissingBean
        public ObjectStorageService minioObjectStorageService(MinioClient minioClient) {
            return new MinioObjectStorageService(minioClient);
        }
    }

    @ConditionalOnClass(FilesystemObjectStorageService.class)
    @Import(FilesystemConfiguration.class)
    public static class FilesystemObjectStorageConfiguration {
        @Bean
        @ConditionalOnMissingBean
        public ObjectStorageService filesystemObjectStorageService(FilesystemProperties properties) {
            FilesystemObjectStorageService service = new FilesystemObjectStorageService(properties.getDataDir());
            service.setPublicRootUriSupplier(new HttpServletRequestPublicRootUriSupplier());
            return service;
        }
    }
}
