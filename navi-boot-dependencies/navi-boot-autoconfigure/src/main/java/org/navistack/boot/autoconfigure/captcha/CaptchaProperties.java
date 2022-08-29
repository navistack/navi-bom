package org.navistack.boot.autoconfigure.captcha;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = CaptchaProperties.PROPERTIES_PREFIX)
public class CaptchaProperties {
    public static final String PROPERTIES_PREFIX = "navi.captcha";

    private boolean checkForAnnotation = true;
}
