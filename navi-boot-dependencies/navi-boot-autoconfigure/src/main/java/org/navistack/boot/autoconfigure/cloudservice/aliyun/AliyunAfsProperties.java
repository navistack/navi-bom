package org.navistack.boot.autoconfigure.cloudservice.aliyun;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = AliyunAfsProperties.PROPERTY_PREFIX)
@Data
public class AliyunAfsProperties {
    public static final String PROPERTY_PREFIX = "navi.cloud-service.aliyun.afs";

    private String appKey;
    private String scene;
}
