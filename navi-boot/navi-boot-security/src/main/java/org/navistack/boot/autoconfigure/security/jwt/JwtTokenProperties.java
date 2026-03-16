package org.navistack.boot.autoconfigure.security.jwt;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.Assert;

@ConfigurationProperties(prefix = JwtTokenProperties.PROPERTIES_PREFIX)
@Data
public class JwtTokenProperties implements InitializingBean {
    public static final String PROPERTIES_PREFIX = "navi.security.jwt";

    private static final int DEFAULT_VALIDITY = 2 * 60 * 60 * 1000;

    /**
     * Validity in milliseconds.
     */
    private int validity = DEFAULT_VALIDITY;

    /**
     * Secret encoded in Base64.
     */
    private String secret;

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.isTrue(validity > 0, "Validity can not be negative or zero");
    }
}
