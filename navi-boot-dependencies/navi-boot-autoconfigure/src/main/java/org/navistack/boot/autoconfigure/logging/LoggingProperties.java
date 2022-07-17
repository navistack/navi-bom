package org.navistack.boot.autoconfigure.logging;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = LoggingProperties.PROPERTIES_PREFIX)
@Data
public class LoggingProperties {
    public static final String PROPERTIES_PREFIX = "navi.logging";

    private boolean enabled = true;
}
