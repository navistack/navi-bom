package org.navistack.boot.autoconfigure.objectstorage;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.Assert;

@ConfigurationProperties(prefix = MinioProperties.PROPERTIES_PREFIX)
@Data
public class MinioProperties implements InitializingBean {
    public static final String PROPERTIES_PREFIX = "navi.object-storage.minio";

    private String endpoint;
    private Credentials credentials = new Credentials();

    @Data
    public static class Credentials {
        private String accessKey;
        private String secretKey;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasText(endpoint, "Endpoint can't not be empty");
        Assert.hasText(credentials.accessKey, "Access key can't not be empty");
        Assert.hasText(credentials.secretKey, "Secret key can't not be empty");
    }
}
