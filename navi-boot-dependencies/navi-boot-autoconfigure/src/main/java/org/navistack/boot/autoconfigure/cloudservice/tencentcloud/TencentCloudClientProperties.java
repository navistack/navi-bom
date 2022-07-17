package org.navistack.boot.autoconfigure.cloudservice.tencentcloud;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.Assert;

@ConfigurationProperties(prefix = TencentCloudClientProperties.PROPERTY_PREFIX)
@Data
public class TencentCloudClientProperties implements InitializingBean {
    public static final String PROPERTY_PREFIX = "navi.cloud-service.tencent-cloud.client";

    private String region = "";
    private Credential credential = new Credential();

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(region, "Region can not be null");
        Assert.hasText(credential.getSecretId(), "Secret ID can not be empty");
        Assert.hasText(credential.getSecretKey(), "Secret Key can not be empty");
    }

    @Data
    public static class Credential {
        private String secretId;
        private String secretKey;
    }
}
