package org.navistack.boot.autoconfigure.cloudservice.tencentcloud.sms;

import lombok.Data;
import org.navistack.boot.autoconfigure.cloudservice.tencentcloud.TencentCloudClientProperties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.Assert;

@ConfigurationProperties(prefix = TencentCloudSmsProperties.PROPERTY_PREFIX)
@Data
public class TencentCloudSmsProperties implements InitializingBean {
    public static final String PROPERTY_PREFIX = "navi.cloud-service.tencent-cloud.sms";

    private boolean enabled = false;
    private TencentCloudClientProperties client = new TencentCloudClientProperties();
    private String sdkAppId;
    private String appKey;

    @Override
    public void afterPropertiesSet() throws Exception {
        client.afterPropertiesSet();
        Assert.notNull(sdkAppId, "sdkAppId must not be null");
        Assert.hasText(appKey, "appKey must not be empty");
    }
}
