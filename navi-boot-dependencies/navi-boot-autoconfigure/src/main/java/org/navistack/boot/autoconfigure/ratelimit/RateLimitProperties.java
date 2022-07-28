package org.navistack.boot.autoconfigure.ratelimit;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = RateLimitProperties.PROPERTIES_PREFIX)
@Data
public class RateLimitProperties {
    public static final String PROPERTIES_PREFIX = "navi.rate-limit";

    private boolean enabled = true;
}
