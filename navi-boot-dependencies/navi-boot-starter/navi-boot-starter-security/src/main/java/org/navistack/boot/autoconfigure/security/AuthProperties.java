package org.navistack.boot.autoconfigure.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = AuthProperties.PROPERTIES_PREFIX)
@Data
public class AuthProperties {
    public static final String PROPERTIES_PREFIX = "navi.auth";

    private Token token = new Token();

    @Data
    public static class Token {
        private final static int DEFAULT_VALIDITY = 2 * 60 * 60 * 1000;

        /**
         * Validity in milliseconds
         */
        private int validity = DEFAULT_VALIDITY;

        /**
         * Secret encoded in Base64
         */
        private String secret;
    }
}
