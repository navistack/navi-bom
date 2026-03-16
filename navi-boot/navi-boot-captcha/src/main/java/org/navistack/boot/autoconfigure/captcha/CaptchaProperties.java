package org.navistack.boot.autoconfigure.captcha;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collection;
import java.util.Collections;

@Data
@ConfigurationProperties(prefix = CaptchaProperties.PROPERTIES_PREFIX)
public class CaptchaProperties {
    public static final String PROPERTIES_PREFIX = "navi.captcha";

    private Collection<String> urlPatterns = Collections.emptyList();
}
