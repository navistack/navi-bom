package org.navistack.boot.autoconfigure.locking;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = LockingProperties.PROPERTIES_PREFIX)
@Data
public class LockingProperties {
    public static final String PROPERTIES_PREFIX = "navi.locking";

    private boolean enabled = true;
}
