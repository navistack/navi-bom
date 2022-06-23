package org.navistack.boot.autoconfigure.captcha;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = CaptchaProperties.PROPERTY_PREFIX)
public class CaptchaProperties {
    public static final String PROPERTY_PREFIX = "navi.captcha";
    private String service = "SIMPLE_CAPTCHA";
}
