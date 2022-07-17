package org.navistack.boot.autoconfigure.cloudservice.tencentcloud;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.Assert;

@ConfigurationProperties(prefix = TencentCloudCaptchaProperties.PROPERTY_PREFIX)
@Data
public class TencentCloudCaptchaProperties implements InitializingBean {
    public static final String PROPERTY_PREFIX = "navi.cloud-service.tencent-cloud.captcha";

    private Long appId;
    private String appSecret;

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(appId, "App ID can not be null");
        Assert.hasText(appSecret, "App Secret can not be empty");
    }
}
