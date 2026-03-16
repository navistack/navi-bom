package org.navistack.boot.autoconfigure.web.rest;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = RestResultProperties.PROPERTIES_PREFIX)
@Data
public class RestResultProperties {
    public static final String PROPERTIES_PREFIX = "navi.web.rest-result";

    private boolean includeStackTrace = false;
}
