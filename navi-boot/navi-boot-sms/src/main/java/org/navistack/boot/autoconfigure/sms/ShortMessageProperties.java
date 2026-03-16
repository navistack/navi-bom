package org.navistack.boot.autoconfigure.sms;

import lombok.Data;
import org.navistack.framework.sms.SimpleShortMessageTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collection;
import java.util.LinkedList;

@ConfigurationProperties(prefix = ShortMessageProperties.PROPERTIES_PREFIX)
@Data
public class ShortMessageProperties {
    public static final String PROPERTIES_PREFIX = "navi.security.short-message";

    private Collection<SimpleShortMessageTemplate> templates = new LinkedList<>();
}
