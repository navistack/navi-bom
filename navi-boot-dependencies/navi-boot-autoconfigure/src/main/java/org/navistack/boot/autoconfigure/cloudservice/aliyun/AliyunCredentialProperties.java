package org.navistack.boot.autoconfigure.cloudservice.aliyun;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

@Data
public class AliyunCredentialProperties implements InitializingBean {
    private String accessKeyId;
    private String accessKeySecret;

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasText(getAccessKeyId(), "accessKeyId must not be empty");
        Assert.hasText(getAccessKeySecret(), "accessKeySecret must not be empty");
    }
}
