package org.navistack.boot.autoconfigure.cloudservice.aliyun;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.Assert;

import java.util.LinkedList;
import java.util.List;

@ConfigurationProperties(prefix = AliyunClientProperties.PROPERTY_PREFIX)
@Data
public class AliyunClientProperties implements InitializingBean {
    public static final String PROPERTY_PREFIX = "navi.cloud-service.aliyun.client";

    private boolean enabled = false;

    private String region;
    private AliyunCredentialProperties credential = new AliyunCredentialProperties();
    private List<AliyunEndpointProperties> endpoints = new LinkedList<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasText(region, "Region can not be empty");
        credential.afterPropertiesSet();
        if (endpoints != null && !endpoints.isEmpty()) {
            for (AliyunEndpointProperties endpoint : endpoints) {
                endpoint.afterPropertiesSet();
            }
        }
    }
}
