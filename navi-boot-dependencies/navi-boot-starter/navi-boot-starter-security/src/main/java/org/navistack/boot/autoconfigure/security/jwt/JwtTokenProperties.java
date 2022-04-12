package org.navistack.boot.autoconfigure.security.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = JwtTokenProperties.PROPERTIES_PREFIX)
@Data
public class JwtTokenProperties {
    public static final String PROPERTIES_PREFIX = "navi.security.jwt";

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
