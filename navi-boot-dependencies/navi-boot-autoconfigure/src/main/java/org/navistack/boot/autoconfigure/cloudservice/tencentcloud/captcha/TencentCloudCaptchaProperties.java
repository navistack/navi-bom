package org.navistack.boot.autoconfigure.cloudservice.tencentcloud.captcha;

import lombok.Data;
import org.navistack.boot.autoconfigure.cloudservice.tencentcloud.TencentCloudClientProperties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.Assert;

@ConfigurationProperties(prefix = TencentCloudCaptchaProperties.PROPERTY_PREFIX)
@Data
public class TencentCloudCaptchaProperties implements InitializingBean {
    public static final String PROPERTY_PREFIX = "navi.cloud-service.tencent-cloud.captcha";

    private boolean enabled = false;
    private TencentCloudClientProperties client = new TencentCloudClientProperties();
    private Long captchaAppId;
    private String appSecretKey;

    @Override
    public void afterPropertiesSet() throws Exception {
        client.afterPropertiesSet();
        Assert.notNull(captchaAppId, "captchaAppId can not be null");
        Assert.hasText(appSecretKey, "appSecretKey can not be empty");
    }
}
