package org.navistack.boot.autoconfigure.cloudservice.aliyun;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.Assert;

import java.util.List;

@ConfigurationProperties(prefix = AliyunClientProperties.PROPERTY_PREFIX)
@Data
public class AliyunClientProperties implements InitializingBean {
    public static final String PROPERTY_PREFIX = "navi.cloud-service.aliyun.client";

    private String region;
    private Credential credential;
    private List<Endpoint> endpoints;

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasText(region, "Region can not be empty");
        Assert.hasText(credential.getAccessKeyId(), "Access Key ID can not be empty");
        Assert.hasText(credential.getAccessKeySecret(), "Access Key Secret can not be empty");

        if (endpoints != null && !endpoints.isEmpty()) {
            for (Endpoint endpoint : endpoints) {
                Assert.hasText(endpoint.getRegionId(), "Region ID of endpoint can not be empty");
                Assert.hasText(endpoint.getProduct(), "Product of endpoint can not be empty");
                Assert.hasText(endpoint.getEndpoint(), "Endpoint of endpoint can not be empty");
            }
        }
    }

    @Data
    public static class Credential {
        private String accessKeyId;
        private String accessKeySecret;
    }

    @Data
    public static class Endpoint {
        private String regionId;
        private String product;
        private String endpoint;
    }
}
