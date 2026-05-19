package org.navistack.boot.tencent.cloud.autoconfigure;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

@Data
public class TencentCloudCredentialProperties implements InitializingBean {
    private String secretId;
    private String secretKey;

    @Override
    public void afterPropertiesSet() {
        Assert.hasText(secretId, "secretId must not be empty");
        Assert.hasText(secretKey, "secretKey must not be empty");
    }
}
