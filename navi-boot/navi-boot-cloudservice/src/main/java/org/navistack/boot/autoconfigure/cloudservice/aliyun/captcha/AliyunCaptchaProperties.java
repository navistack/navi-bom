package org.navistack.boot.autoconfigure.cloudservice.aliyun.captcha;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.Assert;

@ConfigurationProperties(prefix = AliyunCaptchaProperties.PROPERTY_PREFIX)
@Data
public class AliyunCaptchaProperties implements InitializingBean {
    public static final String PROPERTY_PREFIX = "navi.cloud-service.aliyun.captcha";

    private boolean enabled = false;
    private String accessKeyId;
    private String accessKeySecret;
    private String endpoint;

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasText(accessKeyId, "accessKeyId must not be empty");
        Assert.hasText(accessKeySecret, "accessKeySecret must not be empty");
        Assert.hasText(endpoint, "endpoint must not be empty");
    }
}
