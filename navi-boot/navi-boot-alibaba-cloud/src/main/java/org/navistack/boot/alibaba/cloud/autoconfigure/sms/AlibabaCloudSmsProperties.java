package org.navistack.boot.alibaba.cloud.autoconfigure.sms;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.Assert;

@ConfigurationProperties(prefix = AlibabaCloudSmsProperties.PROPERTY_PREFIX)
@Data
public class AlibabaCloudSmsProperties implements InitializingBean {
    public static final String PROPERTY_PREFIX = "navi.cloud-service.alibaba-cloud.sms";

    private boolean enabled = false;
    private String accessKeyId;
    private String accessKeySecret;
    private String endpoint;

    @Override
    public void afterPropertiesSet() {
        Assert.hasText(accessKeyId, "accessKeyId must not be empty");
        Assert.hasText(accessKeySecret, "accessKeySecret must not be empty");
        Assert.hasText(endpoint, "endpoint must not be empty");
    }
}
