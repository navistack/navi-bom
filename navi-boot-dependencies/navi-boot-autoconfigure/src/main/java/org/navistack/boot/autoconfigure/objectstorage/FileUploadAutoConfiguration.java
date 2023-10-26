package org.navistack.boot.autoconfigure.objectstorage;

import org.navistack.framework.objectstorage.DefaultFileUploadPolicyEnforcer;
import org.navistack.framework.objectstorage.DefaultFileUploadService;
import org.navistack.framework.objectstorage.FileUploadPolicy;
import org.navistack.framework.objectstorage.FileUploadService;
import org.navistack.framework.objectstorage.ObjectStorageService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(FileUploadProperties.class)
@ConditionalOnClass(FileUploadService.class)
public class FileUploadAutoConfiguration {
    @Bean
    public FileUploadPolicy defaultFileUploadPolicy(FileUploadProperties properties) {
        FileUploadPolicy policy = new FileUploadPolicy();
        policy.setContentSizeLimit(properties.getUploadPolicy().getContentSizeLimit());
        policy.setContentTypeLimit(properties.getUploadPolicy().getContentTypeLimit());
        return policy;
    }

    @Bean
    @ConditionalOnBean(ObjectStorageService.class)
    @ConditionalOnMissingBean
    public FileUploadService fileUploadService(ObjectStorageService objectStorageService,
                                               FileUploadPolicy defaultFileUploadPolicy) {
        DefaultFileUploadService service = new DefaultFileUploadService(objectStorageService);
        service.setUploadPolicyEnforcer(new DefaultFileUploadPolicyEnforcer(defaultFileUploadPolicy));
        return service;
    }
}
