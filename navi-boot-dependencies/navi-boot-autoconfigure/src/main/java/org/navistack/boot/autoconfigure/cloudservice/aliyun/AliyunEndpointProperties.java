package org.navistack.boot.autoconfigure.cloudservice.aliyun;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

@Data
public class AliyunEndpointProperties implements InitializingBean {
    private String regionId;
    private String product;
    private String endpoint;

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasText(regionId, "regionId must not be empty");
        Assert.hasText(product, "product must not be empty");
        Assert.hasText(endpoint, "endpoint must not be empty");
    }
}
