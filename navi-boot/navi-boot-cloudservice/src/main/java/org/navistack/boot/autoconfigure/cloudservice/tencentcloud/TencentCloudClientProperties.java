package org.navistack.boot.autoconfigure.cloudservice.tencentcloud;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

@Data
public class TencentCloudClientProperties implements InitializingBean {
    private String region = "";
    private TencentCloudCredentialProperties credential = new TencentCloudCredentialProperties();
    private TencentCloudClientProfileProperties clientProfile = new TencentCloudClientProfileProperties();

    @Override
    public void afterPropertiesSet() throws Exception {
        credential.afterPropertiesSet();
        Assert.notNull(region, "Region must not be null");
    }

}
