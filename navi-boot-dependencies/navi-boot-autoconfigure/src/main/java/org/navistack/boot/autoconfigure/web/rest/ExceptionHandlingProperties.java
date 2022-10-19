package org.navistack.boot.autoconfigure.web.rest;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = ExceptionHandlingProperties.PROPERTIES_PREFIX)
@Data
public class ExceptionHandlingProperties {
    public static final String PROPERTIES_PREFIX = "navi.web.exception-handling";

    private boolean includeStackTrace = false;
}
